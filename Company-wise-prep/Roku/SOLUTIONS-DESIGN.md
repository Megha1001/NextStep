# Roku prep — System design solutions

Worked answers for every design question reported in Roku interviews over the
last two years. Java appears where concrete code makes the design clearer.

Read section 0 first. Structure is what gets scored; the rest is content.

---

## Contents

0. [The framework and the numbers](#0-the-framework-and-the-numbers)
1. [Design a video streaming platform](#1-design-a-video-streaming-platform)
2. [Design an ad delivery system with frequency capping](#2-design-an-ad-delivery-system-with-frequency-capping)
3. [Low-latency video metadata retrieval](#3-low-latency-video-metadata-retrieval)
4. [Logging and alerting from millions of devices](#4-logging-and-alerting-from-millions-of-devices)
5. [Real-time Top N service](#5-real-time-top-n-service)
6. [One-time event scheduler](#6-one-time-event-scheduler)
7. [Distributed session management](#7-distributed-session-management)
8. [Personalized recommendation engine](#8-personalized-recommendation-engine)
9. [Quick sketches: URL shortener, distributed cache, news feed](#9-quick-sketches)
10. [Bonus: server-guided ad insertion, from first principles](#10-bonus-server-guided-ad-insertion-from-first-principles)

---

## 0. The framework and the numbers

### The six steps

**Requirements (5 min).** Functional: what must it do. Non-functional: scale,
latency target, availability, consistency. Explicitly out of scale: say what you
are *not* building. Ask questions — interviewers withhold detail deliberately.

**Estimates (3 min).** Users, requests per second, storage, read/write ratio.
The numbers are not graded for accuracy; they are the justification for every
later decision. Compute them out loud so you can point back: "this is 50k reads
per second, so a single database will not do."

**API (2 min).** Four or five endpoints. Inputs, outputs, who calls them.

**High-level design (10 min).** Boxes and arrows. Client, edge, services,
storage, async paths. Keep it simple; resist detail here.

**Deep dive (20 min).** Pick one or two components — ideally ones you know — and
go properly deep. Data model, partition key, indexes, concurrency, hot spots.

**Failure and tradeoffs (10 min).** What breaks when each component dies. What
happens under a 10x spike. Where is the single point of failure. What did you
trade away. **Raise this yourself.** It is the strongest senior signal available.

### Numbers worth memorising

| Thing | Rough figure |
| --- | --- |
| L1 cache reference | 1 ns |
| Main memory reference | 100 ns |
| SSD random read | 100 µs |
| Round trip within a datacentre | 500 µs |
| Disk seek | 10 ms |
| Round trip India to US East | 200 ms |
| Reads/sec from one Redis node | ~100k |
| Writes/sec to one Postgres node | ~5k–10k |
| QPS from one well-tuned app server | ~5k–10k |
| One day | ~86,400 s (call it 100k) |
| One month | ~2.5 million seconds |

Handy shortcut: **1 million requests/day ≈ 12 requests/second.** Most estimates
start from there.

### Phrases that score

- "I'll optimise for X and accept worse Y, because the requirement says…"
- "The bottleneck here is going to be…"
- "That's a hot key problem. I'd handle it by…"
- "This is eventually consistent, so the failure mode is…"
- "Let me check that against the numbers I estimated."

---

## 1. Design a video streaming platform

The most likely question in your loop, and it is Roku's actual product.

### Requirements

**Functional.** Creators or partners upload content. Content is transcoded into
multiple renditions. Viewers browse a catalogue and play video on many device
types. Playback adapts to network conditions. Content is protected by DRM.

**Non-functional.** Very high read-to-write ratio (millions watch, few upload).
Startup latency under two seconds. Rebuffering ratio well under one percent.
Global audience. Availability matters far more than strict consistency for the
catalogue.

**Out of scope** unless they steer you there: recommendations, ads, payments,
social features. Say so explicitly — scoping is graded.

### Estimates

100 million daily viewers, two hours each, at an average 5 Mbps. That is
100M × 7200s × 5 Mbit ≈ 3.6 exabits per day of egress. The number itself is less
important than the conclusion you draw from it: **CDN egress dominates
everything, so the entire design is organised around cache hit ratio.** Say that
sentence.

### API

```
POST  /v1/uploads                          -> uploadId, presigned URL
GET   /v1/titles/{titleId}                 -> metadata
GET   /v1/titles/{titleId}/manifest        -> HLS/DASH manifest URL (signed)
POST  /v1/playback/sessions                -> sessionId, DRM licence URL
POST  /v1/playback/heartbeat               -> QoE telemetry
```

### High-level design

Two paths that barely touch each other. Say this early — it is the cleanest way
to organise the answer.

**Ingest (write path, offline, throughput-oriented).** Upload to object storage
via presigned URL. An event lands on a queue. A transcoding fleet picks it up
and produces an ABR ladder — say 180p through 4K, multiple codecs. Output is
segmented (2–6 second chunks) and packaged into HLS and DASH, encrypted for
DRM. Manifests and segments are written to object storage. Metadata is written
to the catalogue database, and only then is the title marked available.

**Playback (read path, online, latency-oriented).** Client fetches metadata from
the catalogue service (cached hard). Client requests a manifest. Client fetches
segments from the CDN. Client fetches a DRM licence from the licence service.
Client posts telemetry asynchronously.

### Deep dive: adaptive bitrate and why segmenting matters

Video is cut into short segments, each available at every quality level. The
manifest lists them. The **player**, not the server, decides which rendition to
fetch next based on measured throughput and buffer level. This is the key
insight: adaptation is client-side, which is why the server can serve identical
cacheable segments to everyone.

Segment length is a real tradeoff. Shorter segments mean faster adaptation and
lower live latency, but more requests, more overhead, and worse compression.
Two to six seconds is the usual compromise; low-latency HLS pushes toward
partial segments of a few hundred milliseconds.

### Deep dive: caching, the thing that actually matters

Layer it and say why each layer exists:

- **Player buffer** — absorbs jitter, tens of seconds
- **CDN edge** — serves the overwhelming majority of segment requests
- **CDN mid-tier / shield** — protects origin from edge misses fanning in
- **Origin** — object storage

Popular content lives entirely at the edge. The long tail misses and pulls
through the shield. Because segments are immutable and content-addressed, you
can cache them effectively forever; manifests need short TTLs because they
change (especially for live).

The number to state: at 95 percent edge hit ratio, origin sees 5 percent of
traffic. Push it to 99 percent and origin load drops fivefold. **That single
ratio is the difference between a viable and an unviable business**, which is
why anything that personalises the segment URL is so expensive.

### Deep dive: live versus on-demand

On-demand is a solved caching problem. Live is not, and it is where Roku
actually lives:

- The manifest changes every few seconds, so it cannot be cached long
- A thundering herd hits the origin at each segment boundary; you mitigate with
  request coalescing at the shield and small but non-zero manifest TTLs
- The encoder is now on the critical path, so it needs redundancy — usually two
  encoders producing bit-identical output so you can fail between them
- Latency budget: encode, package, propagate, buffer. Typical HLS is 20–30
  seconds behind real time; low-latency variants target under five

### Data model

Catalogue metadata is read-heavy, low-volume, and relational-ish — Postgres with
aggressive caching, or DynamoDB keyed by titleId if you want it simpler to scale.
Segments and manifests go to object storage; never in a database. Playback
sessions go to Redis with a TTL. Telemetry goes to a log pipeline (see section 4).

### Failure modes — raise these yourself

- **CDN edge outage in one region.** Multi-CDN with DNS or client-side steering.
  Mention that clients should be able to fail over mid-session.
- **Transcoding backlog.** Ingest is async, so it degrades gracefully — titles
  publish late rather than the site going down. Priority queue for urgent content.
- **Origin overload.** Shield tier plus request coalescing; serve stale on error.
- **DRM licence service down.** Nothing plays. This is a genuine single point of
  failure, so it needs to be highly available and regionally redundant. Naming
  this unprompted is a strong move.
- **Encoder failure on live.** Redundant encoders with identical segment
  boundaries so the packager can switch without a client-visible discontinuity.

### Tradeoffs to state

Longer segments cache better but adapt slower. More ladder rungs mean better
quality matching but higher transcode cost and storage. Multi-CDN improves
resilience but complicates cache warming and reporting.

---

## 2. Design an ad delivery system with frequency capping

Reported almost verbatim: an ad delivery system that limits how often a user
sees the same ad. **This is your strongest question.** Design it from first
principles — do not describe your employer's system.

### Requirements

**Functional.** Given a request with user, device and content context, return
one or more ads. Respect targeting rules. Respect frequency caps ("no more than
3 impressions of campaign X per user per day"). Respect budget pacing. Record
impressions.

**Non-functional.** Hard latency budget — the player is already at the break, so
call it 100 ms at p99 for the whole decision. Very high QPS. Fail open: if
decisioning fails, playback must continue. Counting can be approximate;
overspend is worse than underspend.

### Estimates

50 million daily users, four breaks each, two ads per break: 400 million ad
decisions per day ≈ 5k QPS average, and peaks maybe 5–10x during a live event.
Say the peak number, because live sports spikes are the interesting case and
Roku will care about them.

### API

```
POST /v1/ads/decision
  { userId, deviceId, contentId, breakDurationMs, position, context }
  -> { ads: [ { creativeId, durationMs, trackingUrls } ], decisionId }

POST /v1/ads/impression
  { decisionId, creativeId, userId, eventType, timestamp }
```

### High-level design

```
Player -> Ad Decision Service ----> Targeting / candidate selection
                |                      (campaign index, in-memory)
                +-> Frequency Cap Store (Redis)
                +-> Budget / Pacing Service
                +-> Creative metadata cache
                |
                v
          Impression events -> Kafka -> stream processor
                                          |
                                          +-> update cap counters
                                          +-> update budget spend
                                          +-> analytics warehouse
```

The decision path is synchronous and must be fast. Everything downstream of the
impression is asynchronous. Draw that boundary explicitly.

### Deep dive: the frequency cap counter

This is the heart of the question. Go slowly here.

**Data model.** Key on `(userId, campaignId, timeWindow)`. Value is a counter.
TTL equals the window, so expiry is free — you never run a cleanup job. In Redis:

```
key:   fc:{userId}:{campaignId}:{yyyyMMdd}
value: integer
TTL:   seconds remaining in the window
```

**Read path.** At decision time you need counts for every candidate campaign.
Do not issue one round trip per campaign — pipeline them, or use a single
`MGET` over the candidate keys. One round trip, not fifty. State this; it is the
difference between meeting and blowing the latency budget.

**Write path.** Increment on *impression*, not on decision — an ad returned but
never played must not count against the cap. That distinction matters and few
candidates raise it.

```java
public class FrequencyCapStore {

    private final RedisClient redis;      // your client of choice
    private final int defaultCapPerDay;

    /** Single round trip for all candidate campaigns. */
    public Map<String, Integer> currentCounts(String userId, List<String> campaignIds) {
        List<String> keys = new ArrayList<>(campaignIds.size());
        String day = LocalDate.now(ZoneOffset.UTC).format(DateTimeFormatter.BASIC_ISO_DATE);
        for (String campaignId : campaignIds) {
            keys.add("fc:" + userId + ":" + campaignId + ":" + day);
        }
        List<String> values = redis.mget(keys); // one network round trip
        Map<String, Integer> counts = new HashMap<>();
        for (int i = 0; i < campaignIds.size(); i++) {
            String raw = values.get(i);
            counts.put(campaignIds.get(i), raw == null ? 0 : Integer.parseInt(raw));
        }
        return counts;
    }

    /** Called from the impression consumer, not from the decision path. */
    public void recordImpression(String userId, String campaignId, Duration windowTtl) {
        String day = LocalDate.now(ZoneOffset.UTC).format(DateTimeFormatter.BASIC_ISO_DATE);
        String key = "fc:" + userId + ":" + campaignId + ":" + day;
        redis.incrThenExpireIfNew(key, windowTtl); // INCR + EXPIRE, pipelined
    }
}
```

**The consistency question — expect them to push here.** Strictly enforcing "no
more than 3" across a distributed system requires coordination on every
decision, which you cannot afford inside 100 ms. So you accept approximate
counting: under concurrency a user might occasionally see a fourth impression.
Say this out loud as a deliberate choice, and explain why it is acceptable —
showing one extra ad is a minor business cost, while adding 50 ms of
coordination to every request is a large user-experience cost. Making that trade
explicitly, with a reason, is exactly the senior signal they are looking for.

**Rolling windows.** A calendar-day cap resets abruptly at midnight, letting a
user see 3 ads at 23:59 and 3 more at 00:01. If they raise it: bucket by hour
and sum the last 24 buckets, which is a sliding window at 24x the key count. Or
keep a small sorted set of impression timestamps per user-campaign and trim by
score. Name the cost of each.

**Hot keys.** A single very active user, or a campaign so large every request
touches it. Per-user keys spread naturally by userId. For campaign-level
counters, shard the key into N sub-counters (`budget:{campaignId}:{shard}`) and
sum, or give each decision server a local lease on a slice of the budget.

### Deep dive: budget pacing

A campaign with a daily budget should not spend it all in the first hour. Two
approaches: **even pacing**, where you divide the budget into time slices and
throttle to the slice rate; and **predictive pacing**, where you forecast the
day's traffic and shape spend to match. Even pacing is simpler and usually
enough. Implement as a token bucket per campaign, refilled at the target rate.

Because spend counters are updated asynchronously from impressions, there is a
lag between serving and counting, which means slight overspend is inevitable.
Mitigate with a safety margin — stop at 98 percent of budget — rather than by
trying to make it exact.

### Deep dive: hitting the latency budget

Breakdown of a 100 ms p99: 10 ms network in, 5 ms request parsing and context
lookup, 20 ms candidate selection from an in-memory campaign index, 15 ms
frequency and budget lookups (one pipelined Redis round trip), 20 ms ranking,
10 ms response construction, buffer for the rest.

Techniques to name: keep the campaign index in process memory and refresh it in
the background rather than querying a database per request; do the frequency and
budget lookups concurrently with candidate ranking where possible; set an
aggressive timeout on every dependency; and **fail open** — if the frequency
store times out, serve the ad rather than blocking the break. Under failure you
prefer over-serving to a stalled player.

### Failure modes

Frequency store unavailable: fail open, serve without capping, and log so you
can reconcile. Ad server slow: hard timeout, fall back to a house ad or skip the
break. Kafka backlog: caps and budgets go stale, so overspend grows — alert on
consumer lag specifically. Duplicate impressions from client retries: dedupe on
`decisionId` plus creative, which is why the decision response carries an id.

### Tradeoffs to state

Approximate counting for latency. Async spend tracking for throughput, at the
cost of overspend. In-memory campaign index for speed, at the cost of a
propagation delay when a campaign changes. Fail-open for viewer experience, at
the cost of occasional cap violations.

---

## 3. Low-latency video metadata retrieval

Reported as: a content delivery system ensuring low-latency metadata retrieval
during peak streaming hours.

### Requirements

Read-dominated by orders of magnitude. Sub-50 ms p99. Peak hours are 5–10x the
daily average. Staleness of a few minutes is acceptable for most fields, but not
for availability windows — a title that has been pulled must stop being served
quickly. Raise that distinction yourself; it is the interesting part.

### Design

Tiered caching, and the whole answer is about invalidation.

```
Client -> CDN (short TTL on public metadata)
       -> API gateway
       -> Metadata service
            -> in-process cache (Caffeine, seconds)
            -> Redis (minutes)
            -> Postgres / DynamoDB (source of truth)
```

Each tier absorbs what the one below cannot. The in-process cache is what
actually saves you at peak, because it costs zero network hops.

### Deep dive: invalidation

Three strategies, and you should name the tradeoff for each. **TTL only** is
simple but bounds your staleness by the TTL and causes synchronised expiry
storms. **Write-through** keeps the cache correct but couples the write path to
every cache tier. **Event-driven invalidation**, where writes publish to a topic
and each service invalidates its local cache, is the usual production answer —
at the cost of eventual consistency and a dependency on the event bus.

Use short TTLs as a safety net *underneath* event-driven invalidation, so a
missed event self-heals.

### Deep dive: the thundering herd

When a hot key expires, every concurrent request misses and stampedes the
database. Three fixes worth naming: **request coalescing**, where one caller
fetches and the rest wait on the same future; **early recomputation**, where you
refresh probabilistically before expiry; and **jittered TTLs**, so a million keys
written together do not expire together.

```java
public class CoalescingCache {

    private final ConcurrentHashMap<String, CompletableFuture<Metadata>> inFlight =
            new ConcurrentHashMap<>();
    private final Cache<String, Metadata> local; // Caffeine
    private final MetadataRepository repository;

    public CompletableFuture<Metadata> get(String titleId) {
        Metadata cached = local.getIfPresent(titleId);
        if (cached != null) {
            return CompletableFuture.completedFuture(cached);
        }
        // exactly one loader per key, everyone else joins it
        return inFlight.computeIfAbsent(titleId, id ->
                CompletableFuture
                        .supplyAsync(() -> repository.load(id))
                        .whenComplete((value, error) -> {
                            if (error == null) {
                                local.put(id, value);
                            }
                            inFlight.remove(id);
                        }));
    }
}
```

Walk through why `computeIfAbsent` is the right primitive: it is atomic, so
exactly one thread creates the future and all others receive the same instance.

### Failure modes

Cache tier down: falls through to the database, which must be sized to survive
it or protected by a circuit breaker that serves stale. Stale availability data:
handle availability with a separate short-TTL path or a hard invalidation event,
because serving a pulled title is a legal problem, not a performance one.

---

## 4. Logging and alerting from millions of devices

Reported as a high-throughput logging and alerting system aggregating
performance metrics from millions of streaming devices. **This is closest to
your actual CDN log pipeline work**, so it is a good one to steer toward.

### Requirements

Millions of devices emitting periodic telemetry. Ingest must never lose data,
and must never push back onto devices. Alerting on aggregates within about a
minute. Ad-hoc historical analysis over months. Data volume is enormous, so cost
per stored byte is a first-class constraint.

### Estimates

10 million devices, one payload per minute, 1 KB each: 10 million events per
minute ≈ 170k events/second, ~14 GB/hour, ~350 GB/day raw. Compressed and
columnar, perhaps a fifth of that. Now you can talk about retention tiers with
actual numbers behind you.

### Design

```
Devices -> edge collector (HTTP, batched) -> Kafka
                                              |
              +-------------------------------+------------------+
              |                                                  |
     stream processor (Flink/Spark Streaming)            raw sink -> S3
              |                                                  |
        rolling aggregates -> time-series store            batch ETL (Spark)
              |                                                  |
          alert evaluator -> notifications              columnar tables (Parquet)
                                                                 |
                                                          query engine / BI
```

The shape to articulate: **one ingest path, two consumers.** A fast path for
alerting on aggregates, and a slow path that lands raw data for analysis. This
is the lambda-ish split, and you should name it as a deliberate choice rather
than an accident.

### Deep dive: ingest and backpressure

Devices batch locally and send every N seconds — this collapses request count by
orders of magnitude and lets a device survive a brief network outage without
losing data. The collector does almost nothing: validate, stamp server time,
append to Kafka. Keep it stateless so it scales horizontally behind a load
balancer.

**Never apply backpressure to devices.** You cannot slow down ten million TVs,
and if you reject their writes they will retry and make it worse. Instead, buy
headroom in Kafka (partitions and retention) and let consumers lag. State this
explicitly: the queue is the shock absorber.

Partition Kafka by device id so that a single device's events stay ordered.
Mention the consequence: a small number of very chatty devices creates partition
skew, which you handle by hashing on a composite key if it becomes a problem.

### Deep dive: exactly-once, or the honest version of it

True exactly-once end to end is expensive. The practical answer is **at-least-
once delivery plus idempotent writes**. Give each event a deterministic id
(`deviceId + timestamp + sequence`) and make the sink deduplicate on it. For
aggregates, use idempotent upserts keyed by window and dimension, so replaying a
batch produces the same result.

Also mention late and out-of-order events, since devices have bad clocks and
flaky networks: use event time rather than processing time, with a watermark and
a bounded allowed lateness. Anything later goes to a side output rather than
corrupting a closed window.

### Deep dive: storage tiers

Hot, last 24–48 hours, in a time-series store for alerting and dashboards. Warm,
last 90 days, in columnar files on object storage partitioned by date and a
high-cardinality dimension. Cold, beyond that, in the same format on cheaper
storage or dropped entirely. Explain the partitioning choice — you partition by
what you filter on, and you keep file sizes large enough to avoid the small-file
problem that kills Spark jobs.

### Deep dive: alerting

Evaluate rules against rolling windows in the stream processor, not by querying
the database on a timer. Debounce so that a single blip does not page anyone:
require a condition to hold for N consecutive windows. Deduplicate alerts by
rule and dimension so one bad region does not generate ten thousand pages. And
handle the missing-data case explicitly — no data can mean everything is fine,
or it can mean the pipeline is dead, and those need different responses.

### Failure modes

Consumer lag growing: alert on lag itself as a first-class metric. Schema
change from a device firmware rollout: version the payload and make consumers
tolerant of unknown fields. Poison message: dead letter queue, never block the
partition. Region loss: devices fail over to another collector endpoint via DNS.

---

## 5. Real-time Top N service

Reported as a real-time Top N words service, detailing data structures and
distributed storage.

### Requirements

Very high ingest rate. Query the current top N for a sliding window. N is small
(tens or hundreds), the key space is huge (millions of distinct terms). Approximate
answers are acceptable — establish this early, because it unlocks the whole
solution.

### Single machine first

Say the naive version, then break it: a hash map of counts plus a min-heap of
size N. Insert or update the count; if the item beats the heap minimum, swap it
in. O(log N) per event. Memory is O(distinct keys), which is the thing that
fails at scale.

### Distributed

Partition by key hash. Each shard maintains its own local top N over its own key
space. A merger periodically collects the shard-level top N lists and combines
them.

**Because you partitioned by key, every occurrence of a given term lands on the
same shard, so per-term counts are exact.** The only approximation is that a
term ranked 101st on every shard could theoretically belong in the global top
100. Mitigate by having each shard report top K where K is comfortably larger
than N. Explaining precisely where the approximation lives, and how you bound
it, is the answer they want.

### Bounded memory: Count-Min Sketch

If distinct keys will not fit in memory, use a Count-Min Sketch: a
two-dimensional array of counters with several independent hash functions. To
increment, bump one counter per row. To query, take the minimum across rows.
Collisions only ever inflate a count, never deflate it, so it over-estimates and
never under-estimates — which is exactly the right error direction for heavy
hitters. Pair it with a heap of candidate top items.

Memory becomes fixed and independent of key cardinality; the cost is that rare
items get noisy estimates. That is a clean tradeoff sentence.

### Sliding window

Bucket by time — say one bucket per minute, keeping 60 of them. A query sums the
relevant buckets. Advancing the window drops the oldest bucket, which is O(1).
This is a common follow-up and the bucketing trick is the whole answer.

### Connect it back

Mention that this is the distributed version of the Top K Frequent Elements
coding problem. If both appear in your loop, explicitly linking them reads very
well.

---

## 6. One-time event scheduler

Reported as: notify millions of users when their movie subscription starts.

### Requirements

Schedule an event at an exact future time, once per user. Millions pending.
Delivery within a few seconds of the target time. At-least-once delivery with
idempotent handling. Support cancellation and rescheduling.

### The naive design and why it fails

"Poll the database every second for rows where `fire_at <= now`." At millions of
rows this is a full scan per tick, and multiple workers will double-fire. State
this, then fix it — showing the bad design first and dismantling it is a strong
move.

### Better design

Two tiers, based on how far away the event is.

**Far future (minutes to years).** Persist in a durable store partitioned by
time bucket — for example a table or DynamoDB partition keyed by
`(fire_at_minute, shard)`. A dispatcher polls only the buckets that are about to
come due, so each query touches a bounded slice rather than scanning.

**Near future (next few minutes).** Load due events into an in-memory timer
structure. A hashed timer wheel gives O(1) insert and expiry, which matters at
this volume; a priority queue is O(log n) and fine if the near-term set is
small. Mention both and justify your pick.

On fire, publish to a queue. The actual notification delivery is a separate
consumer, so a slow email or push provider never blocks the scheduler.

### Deep dive: exactly-once-ish delivery

You cannot get true exactly-once across a network. Aim for at-least-once
delivery plus idempotent consumers. Give each scheduled event a stable id and
have the notification service deduplicate on `(eventId, userId)` with a TTL.

To prevent two dispatchers firing the same event, use a conditional update:
claim the row by compare-and-swap from `PENDING` to `CLAIMED` with a lease
expiry. Only the winner dispatches. If a dispatcher dies mid-flight, the lease
expires and another picks it up — which is exactly why the consumer must be
idempotent.

```java
public class EventDispatcher {

    /** Claims a due event; returns false if another dispatcher already has it. */
    public boolean tryClaim(String eventId, String workerId, Instant now) {
        // conditional write: only succeeds if status is PENDING,
        // or if a previous lease has expired
        return repository.compareAndSwap(
                eventId,
                /* expectedStatus */ Status.PENDING,
                /* newStatus     */ Status.CLAIMED,
                /* owner         */ workerId,
                /* leaseUntil    */ now.plusSeconds(30));
    }
}
```

### Deep dive: clock skew and thundering herds

Machine clocks drift, so never make correctness depend on wall time agreeing
across nodes — use a single authoritative time source for ordering, or accept
seconds of imprecision and say so.

A predictable spike (everyone's subscription starting at midnight) creates a
thundering herd. Mitigate by jittering fire times within an acceptable window,
and by rate-limiting the downstream notification fan-out with a token bucket.
Raising the midnight-spike problem unprompted is a nice touch.

### Failure modes

Dispatcher crash: leases expire, another worker takes over. Downstream provider
down: queue absorbs it, with a dead letter queue after N retries. Backlog after
an outage: events fire late, so decide explicitly whether a very late
notification should still be sent or dropped — a product question you should
raise rather than assume.

---

## 7. Distributed session management

Reported verbatim for a Bengaluru SSE candidate, framed as a gaming app. The
substance is distributed state, and it overlaps heavily with playback sessions.

### Requirements

Create a session on login or playback start. Read and update it on every
subsequent request, so reads dominate heavily. Expire on inactivity. Survive
node failure without logging everyone out. Sessions are small (a few KB) but
numerous (tens of millions concurrent).

### Design

Sessions live in a distributed key-value store, not in application memory and
not in a relational database. Redis is the default answer.

**Why not sticky sessions in app memory?** It works until a node dies or you
deploy, and then those users are logged out. It also blocks autoscaling. Say
this — the interviewer is often probing for whether you know why stateless app
servers matter.

**Why not a relational database?** Every request becomes a write. Session traffic
is the highest-QPS, lowest-value data in the system; putting it on your
transactional database is how you take down everything else.

### Data model

```
key:   sess:{sessionId}
value: serialised session (user id, device, permissions, last seen, game state)
TTL:   sliding, refreshed on access
```

`sessionId` should be a cryptographically random opaque token, never a
guessable sequence, and never the user id.

### Deep dive: sharding and rebalancing

Partition by hash of `sessionId`, which distributes evenly by construction —
unlike partitioning by user attribute, which skews. Use consistent hashing so
adding or removing a node only remaps `1/n` of keys instead of reshuffling
everything. Virtual nodes smooth out the distribution.

Be ready for "what happens during a resize?" The honest answer: a slice of
sessions is briefly unavailable or must be re-created, so you either accept
those users re-authenticating, or you run a migration that double-writes during
the transition.

### Deep dive: expiry

Sliding expiry means refreshing the TTL on each access. That turns every read
into a write, which is a real cost at this QPS. The usual optimisation is to
only refresh when more than a fraction of the TTL has elapsed — for a 30 minute
TTL, refresh at most once every few minutes. Mentioning this shows you have
thought about the write amplification rather than just the happy path.

### Deep dive: consistency and durability

Sessions are the classic case where availability beats consistency. If a replica
serves a slightly stale session, the cost is a marginally out-of-date last-seen
timestamp. If the store is unavailable, everyone is logged out. So: asynchronous
replication, multiple replicas, and read from the nearest.

For a gaming app specifically, if the session holds live game state rather than
just identity, the calculus changes — losing it is now a real user-visible
failure, so you would add periodic checkpointing to durable storage and accept
the extra write cost. Raising that distinction is the senior move on this
question.

### Failure modes

Primary node loss: replica promotion, with a small window of lost writes under
async replication — state the window. Full cache loss: everyone re-authenticates,
so make sure login can handle a stampede. Session fixation and hijacking:
rotate the session id on privilege change, bind to device fingerprint, use
secure and http-only cookies.

---

## 8. Personalized recommendation engine

Reported as: the architecture of a personalized content recommendation engine
processing high-throughput user interaction data. **Keep this architectural.**
Do not disappear into model selection unless asked — they are testing pipeline
design, not machine learning depth.

### Requirements

Ingest high-volume interaction events (plays, pauses, completions, searches).
Produce a personalised ranked list per user. Serve it in tens of milliseconds.
Refresh recommendations at a reasonable cadence. Handle new users and new items.

### Design

The standard two-stage architecture, plus an offline and an online half.

**Offline.** Interaction events land in the log pipeline. Batch jobs build user
and item features and write them to a feature store. A training pipeline
produces candidate-generation and ranking models on a schedule.

**Online.** A request arrives. **Candidate generation** narrows millions of items
to a few hundred, cheaply — collaborative filtering neighbours, trending
content, recently watched genres, an approximate nearest neighbour lookup over
embeddings. **Ranking** then scores those few hundred precisely with a heavier
model plus real-time context (time of day, device, current session). Finally,
business rules reorder or filter: availability windows, already-watched,
diversity, editorial pins.

Articulating the two stages and *why* they exist — you cannot afford the heavy
model over millions of items, and you cannot afford the cheap model's precision
in the final list — is the core of a good answer.

### Deep dive: precompute versus compute on request

Precomputing a list per user nightly gives excellent latency but ignores what
the user did five minutes ago. Computing entirely at request time is fresh but
slow and expensive. The usual answer is a hybrid: precompute candidates, rank at
request time with live session context. Say which you would choose and why.

### Deep dive: cold start

New user: fall back to popularity, region and device-type defaults, then adapt
quickly within the first session. New item: content-based features (genre, cast,
metadata similarity) until interaction data accumulates, plus deliberate
exploration traffic so it can earn signal at all.

### Deep dive: evaluation

Offline metrics, then online A/B tests on real engagement. Mention the feedback
loop problem — the model only sees outcomes for items it chose to show, so
without exploration it reinforces its own past decisions. Raising that is a
credibility marker.

### Failure modes

Feature store unavailable: fall back to a cached or popularity-based list, never
an empty carousel. Model regression: shadow deploy and compare before switching
traffic. Stale features after a pipeline failure: monitor feature freshness as
an alert, not just pipeline success.

---

## 9. Quick sketches

Have these ready as warm-ups. Ten minutes each, not thirty.

### URL shortener

Write path: generate a short key, store the mapping, return it. Read path: look
up and 301 or 302 redirect. Key generation via base62 of a distributed counter
(Snowflake-style: timestamp, machine id, sequence) rather than hashing, because
hashing forces you to handle collisions. Read-heavy by roughly 100:1, so cache
aggressively — the mapping is immutable, which makes caching trivial. Partition
by the short key. Mention 301 versus 302: permanent redirects cache in the
browser and destroy your click analytics, so use 302 if you need tracking.

### Distributed cache

Consistent hashing with virtual nodes for placement. LRU eviction per node.
Replication factor of two or three for availability. Client-side routing so the
cache is one hop, not two. Discuss write-through versus write-behind versus
cache-aside, and pick cache-aside as the default because it keeps the cache off
the write critical path. Then hot keys (replicate the hot key to every node) and
the thundering herd (request coalescing, as in section 3).

### News feed

The fanout question. **Fanout on write** pushes each post into every follower's
feed list — fast reads, terrible for users with millions of followers. **Fanout
on read** merges the timelines of everyone you follow at request time —
cheap writes, slow reads. The real answer is hybrid: fanout on write for normal
accounts, fanout on read for celebrities, merged at request time. Knowing that
the hybrid exists, and why, is the entire question.

---

## 10. Bonus: server-guided ad insertion, from first principles

If they ask you to design ad insertion — which is plausible given the team —
build it from scratch in the room. Do not narrate your employer's system.

**The problem.** A content stream has designated ad breaks. Different viewers
should see different ads. The break must start and end cleanly.

**Approach A: stitch server-side.** The server splices ad segments directly into
the manifest, so each viewer receives a personalised manifest. Consequences,
which you derive rather than recite: manifests are per-viewer so CDN caching of
the manifest is lost; ads must be re-encoded to match the content's encoding
ladder or the player will stall at the join; the content timeline gains
discontinuities; and the client cannot render its own ad UI because it does not
know an ad is playing.

**Approach B: signal the break, let the client fetch.** The manifest carries a
marker saying "ad break here, ask this URL for the ad list." The player calls
that URL at break time, receives a list of ads, and plays them on a separate
timeline. Consequences: the content manifest stays near-generic and cacheable;
ads need no re-encoding; the client can render countdowns and ad-progress UI;
DRM stays simple because encrypted content and clear ads never share a timeline.

**What B costs you.** It depends on player support, which is uneven across
devices. It introduces a latency-sensitive network call at the exact moment the
break begins. Impression measurement moves partly client-side, so reporting
fidelity drops. And the ad request is a distinct, identifiable call, which makes
it easier to block.

**The hard parts, in both.** Live breaks whose duration is not known in advance.
Ads that do not fill the break, needing filler. Getting back to content cleanly
at the boundary. Deciding early enough that the decision is ready before the
player asks, without deciding so early that it goes stale. Seek behaviour on
on-demand content. Frequency capping and competitive separation within a pod
(see section 2).

**The deep-dive component to offer:** the service that answers the player's ad
request. Session lookup, ad decisioning call within a hard timeout, response
validation, list construction, and a fallback for every failure. That is a
complete, self-contained design conversation, and it is entirely first
principles.
