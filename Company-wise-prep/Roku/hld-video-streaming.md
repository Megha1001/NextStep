# Design a Video Streaming Platform
### Roku interview — poora answer, aasan bhasha mein

---

# PEHLE — 10 shabd jo baar-baar aayenge

Inhe pehle samajh lo, phir poora design aasan lagega.

**Bitrate** — video ek second mein kitna data khaati hai. 1080p ≈ 6 Mbps.
Zyada bitrate = better quality = zyada internet.

**Transcoding** — ek video se kai qualities banana (240p, 480p, 720p, 1080p, 4K).
Jaise ek photo ko small, medium, large mein save karna.

**Segment** — video ko 4-4 second ke chhote tukdon mein kaat dena. Poori file
ek saath nahi bhejte.

**Manifest** — ek chhoti text file jo batati hai: "is video ki 6 qualities hain,
har quality ke tukde in URLs pe milenge." Player pehle yahi padhta hai.

**CDN** — duniya bhar mein phaile servers jo aapki video ki copy rakhte hain,
taaki user ko apne sheher se mile, na ki dusre desh se.

**Origin** — aapka asli storage, jahan original files padi hain. CDN yahin se
copy leta hai.

**DRM** — video ko lock kar dena. Chaabi sirf paying user ko milti hai.

**Keyframe** — ek poora complete video frame. Baaki frames sirf "pichhle se kya
badla" store karte hain.

**Concurrent viewers** — kisi ek pal pe kitne log ek saath dekh rahe hain.

**Cache hit ratio** — kitni requests CDN se hi mil gayi, origin tak jaana hi
nahi pada. Yeh jitna zyada, utna sasta system.

---

# ROUND SHURU

**Interviewer:** "Design a video streaming platform."

**Time budget (60 min):**
```
0-5    Sawaal poocho
5-8    Requirements
8-13   Numbers nikalo
13-16  API
16-30  Design ka khaka
30-48  Deep dive
48-55  Kya-kya toot sakta hai
55-58  Tradeoffs
```

---

# STEP 1 — Pehle sawaal poocho (0-5 min)

**Sabse badi galti:** turant boxes banane lag jaana. Pehle sawaal.

> "Before I start, let me scope this.
> One — on-demand only, or live as well?
> Two — what scale? How many daily users?
> Three — user-generated content, or licensed premium content?
> Four — should I include recommendations and payments, or focus on upload
> and playback?"

Maan lo jawab mila: **on-demand, 100M daily users, premium content, sirf core
path.**

Ab summarise karo:

> "So the core problem is: take a video in, process it, and deliver it to
> 100 million users a day across many device types."

**Kyun important:** interviewer dekh raha hai ki aap sun rahi ho ya ratta maar
kar bol rahi ho.

---

# STEP 2 — Requirements (5-8 min)

## Kya-kya kaam karna hai

```
1. Video upload ho
2. Uski kai qualities ban jaayein
3. Log catalogue mein dhoondh sakein
4. TV, mobile, web — sab pe chale
5. Slow internet pe quality apne aap kam ho jaaye
6. Jahan chhoda tha wahin se shuru ho
7. Content chori na ho sake (DRM)
```

## Kaisa hona chahiye — numbers ke saath

Sirf "fast" ya "scalable" mat bolna. Number do:

```
Availability     : 99.99% (playback ke liye)
Startup latency  : 2 second se kam, play dabane se pehla frame tak
Rebuffering      : 1% se kam (video atakna)
Reads vs Writes  : reads bahut, bahut zyada
```

**Ek line jo zaroor bolni hai:**

> "I'm choosing availability over consistency. If a new title takes 30 seconds
> to appear in the catalogue, that's fine. If playback breaks for a minute,
> that's a major incident."

**Kyun:** aapne CAP theorem ratta nahi maara — **is system ke liye** decision
liya aur wajah batayi.

## Kya nahi banayenge

> "I'll leave out recommendations, payments and social features."

---

# STEP 3 — Numbers (8-13 min)

## Do cheezein pehle

**Bits aur bytes alag hain:**
```
8 bits = 1 byte
Bitrate hamesha BITS mein   (5 Mbps)
Storage hamesha BYTES mein  (2 GB)
Bitrate se storage nikalni ho → 8 se DIVIDE karo
```

**Din mein kitne second:**
```
1 din = 86,400 seconds → interview mein 100,000 maan lo
"per day" se "per second" = 5 zero hatao
```

Bol dena: *"I'll round 86,400 to 100,000 to keep the arithmetic simple."*

## Assumptions likho

```
Daily users      = 100M
Har user dekhta  = 2 ghante roz
Average bitrate  = 5 Mbps
Segment          = 4 second ka
Uploads          = 500k video roz
```

## Ab hisaab

### Total kitna dekha gaya

```
100M users  x  2 ghante  =  200M viewer-hours roz
```

### Ek saath kitne log dekh rahe hain

```
200M viewer-hours  ÷  24 ghante  =  8.3M log ek saath
```

**Yeh samajhna zaroori hai.** Poore din mein 200M ghante ka dekhna hua. Din 24
ghante ka hai. Toh har ghante average 8.3M ghante dekha ja raha hai — matlab
8.3M log ek saath baithe hain.

*Library wali misaal:* poore din 240 person-hours padha gaya, library 24 ghante
khuli thi → average 10 log ek saath baithe the. Wahi formula.

**Peak:** raat 8-11 baje sab dekhte hain, subah 4 baje koi nahi. Peak = 3 guna.

```
Peak = 8.3M x 3 = 25M log ek saath
```

### Kitni requests per second

Har viewer har 4 second mein ek tukda maangta hai.

```
8.3M ÷ 4  =  ~2M requests per second
Peak      =  ~6M requests per second
```

### Kitna internet chahiye (bandwidth)

```
8.3M log  x  5 Mbps  =  41,500,000 Mbps

Units theek karo (har baar 1000 se divide):
41,500,000 Mbps → 41,500 Gbps → 41.5 Tbps

Peak = 25M x 5 Mbps = 125 Tbps
```

### Kitni jagah chahiye (storage)

```
Ek video ke saare qualities milakar   = ~30 Mbps

10 minute ki video:
600 second x 30 Mbps = 18,000 megabits
18,000 ÷ 8           = 2,250 MB = ~2.25 GB

Roz  = 500,000 videos x 2.25 GB = ~1 PB roz
Saal = ~400 PB
```

## Ab yeh line bolo — poore interview ki sabse badi line

> "125 terabits per second at peak. No origin can serve that. So the entire
> design is organised around CDN cache hit ratio. Everything else follows."

**Kyun:** zyaadatar log numbers nikaal kar bhool jaate hain. Aapne number se ek
**faisla** nikala. Ab aage jo bhi bologe, uske peeche wajah khadi hai.

## Formula table — yaad rakhna

| Kya nikalna hai | Kaise |
| --- | --- |
| Ek saath kitne log | total watch-hours ÷ 24 |
| Peak | average × 3 |
| Per second | per day ÷ 100,000 |
| Bitrate → storage | ÷ 8 |
| Requests/sec | concurrent ÷ segment length |
| Bandwidth | concurrent × bitrate |

---

# STEP 4 — API (13-16 min)

```
── UPLOAD ────────────────────────────────────────────
POST /v1/videos                    -> { videoId, uploadUrl }
POST /v1/videos/{videoId}/complete -> { status: PROCESSING }

── BROWSE ────────────────────────────────────────────
GET  /v1/videos/{videoId}          -> title, duration, etc.

── PLAYBACK ──────────────────────────────────────────
POST /v1/playback/sessions
     bhejo: { videoId, deviceId }
     milega: { sessionId, manifestUrl, licenseUrl }

GET  /manifest/{...}.m3u8          (CDN dega, hum nahi)
GET  /segment/{...}.mp4            (CDN dega, hum nahi)
POST /v1/drm/license               -> chaabi
POST /v1/playback/heartbeat
     bhejo: { sessionId, position, quality, rebufferCount }
```

## Har cheez ka matlab

**`videoId`** — is video ka unique naam, jaise `vid_8Kd2mP9x`. Upload abhi shuru
hui hai, file aayi nahi. Par client ko kuch chahiye jisse baad mein kahe "mera
upload ho gaya" ya "iska status kya hai".

**`uploadUrl` (presigned URL)** — ek temporary special URL jo **seedha storage
pe** point karta hai, aur permission usme andar hi likhi hai:

```
https://storage.example.com/raw/vid_8Kd2mP9x?signature=abc123&expires=...
```

Matlab: *"is file ke liye, agle 15 minute tak, upload karne ki ijazat hai."*

*Godown wali misaal:* aapko godown mein saamaan rakhna hai. Office aapko **gate
pass** deta hai — "locker 47, agle 15 minute". Aap seedha godown chale jaate ho.
Saamaan office se hokar nahi jaata, office ne sirf parchi di.

**Kyun zaroori:** video files 2-50 GB ki hoti hain. Agar file aapke API server
se hokar jaaye toh server bandwidth mein doob jaayega.

**`deviceId`** — kaunsa device hai. TV pe 4K de sakte hain, purane phone pe nahi.
Aur DRM bhi device ke hisaab se alag hota hai.

**`manifestUrl`** — **signed** aur time-limited. Taaki koi URL copy karke
WhatsApp pe share na kar de. 5 minute baad woh URL mar jaata hai.

**`position`** — abhi kitne second pe hai. Resume ke liye.

## Do baatein bolni hain

> "Uploads go directly to object storage via a presigned URL — the video never
> passes through my API servers. And manifests and segments are served by the
> CDN, not my API. My API only hands out a signed URL."

---

# STEP 5 — Design ka khaka (16-30 min)

**Sabse pehle yeh bolo:** "There are two completely separate paths here."

## Path 1 — Upload (aaram se, koi jaldi nahi)

```
  ┌──────────┐   1. upload karna hai   ┌───────────────┐
  │  Studio  │ ──────────────────────► │  Upload Svc   │
  │  Client  │ ◄────────────────────── │               │
  └────┬─────┘   2. gate pass (URL)    └───────────────┘
       │
       │ 3. file seedha storage mein (server ko chhui bhi nahi)
       ▼
  ┌──────────────┐   4. "nayi file aayi"  ┌───────────┐
  │   Storage    │ ─────────────────────► │   Queue   │
  │   (raw)      │                        │  (Kafka)  │
  └──────────────┘                        └─────┬─────┘
                                                ▼
                            ┌───────────────────────────┐
                            │  Transcode Orchestrator   │
                            │  (kaam baant-ne wala)     │
                            └─────────────┬─────────────┘
                                          │ tukdon mein baanto
                        ┌─────────────────┼─────────────────┐
                        ▼                 ▼                 ▼
                  ┌──────────┐      ┌──────────┐      ┌──────────┐
                  │ Worker 1 │      │ Worker 2 │ ···  │ Worker N │
                  └─────┬────┘      └─────┬────┘      └─────┬────┘
                        └─────────────────┼─────────────────┘
                                          ▼
                            ┌────────────────────────┐
                            │  Packager + DRM        │
                            │  (kaato + lock karo)   │
                            └───────────┬────────────┘
                                        ▼
                            ┌────────────────────────┐
                            │  Storage               │
                            │  (segments + manifest) │
                            └───────────┬────────────┘
                                        ▼
                            ┌────────────────────────┐
                            │  Database              │
                            │  status = AVAILABLE    │
                            └────────────────────────┘
```

> "Everything here is asynchronous. Nobody is waiting. If transcoding backs up,
> titles publish late but the site stays up."

## Path 2 — Playback (yahan har millisecond matter karta hai)

```
   ┌──────────┐
   │  Player  │
   └────┬─────┘
        │ 1. video ki detail do
        ▼
   ┌─────────────┐    ┌──────────────┐   ┌─────────┐   ┌──────────┐
   │ API Gateway │───►│ Metadata Svc │──►│  Redis  │──►│ Database │
   └──────┬──────┘    │ (local cache)│   │ (cache) │   └──────────┘
          │           └──────────────┘   └─────────┘
          │ 2. playback shuru karo
          ▼
   ┌────────────────┐
   │  Playback Svc  │──► signed manifest URL + licence URL deta hai
   └────────────────┘
          │ 3. manifest, phir segments
          ▼
   ┌────────────┐ nahi mila ┌─────────────┐ nahi mila ┌──────────────┐
   │  CDN EDGE  │──────────►│ CDN SHIELD  │──────────►│   Origin     │
   │ (95%+ yahi │           │ (beech ka   │           │  (storage)   │
   │  se milta) │           │  layer)     │           └──────────────┘
   └────────────┘           └─────────────┘
          ▲
          │ 4. chaabi maango
   ┌──────────────────┐
   │ DRM Licence Svc  │
   └──────────────────┘
          │ 5. "main 45 min pe hoon" (har 10 sec)
          ▼
   ┌──────────┐     ┌─────────────┐
   │  Kafka   │────►│  Analytics  │
   └──────────┘     └─────────────┘
```

## CDN Edge aur Shield — yeh kya hain

**Edge** = sabse nazdeek server, user ke sheher mein. Inki sankhya hazaaron
hoti hai. Request pehle yahin jaati hai.

**Shield** = edge aur origin ke beech ka layer. Inki sankhya kam, 10-20.

**Shield kyun chahiye:**

```
   BINA SHIELD:
   1000 Edge servers ke paas nayi movie nahi hai
        │ │ │ │ │ │ │ │ │ │   (1000 requests EK SAATH)
        ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼
      ┌──────────────────┐
      │      ORIGIN      │   ← mar gaya
      └──────────────────┘

   SHIELD ke saath:
   1000 Edge servers
        │ │ │ │ │ │ │ │ │ │
        ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼
      ┌──────────────────┐
      │   10 SHIELDS     │   ← 1000 requests yahan ruk gayi
      └────────┬─────────┘
               ▼   (sirf 10, ya coalescing se sirf 1)
      ┌──────────────────┐
      │      ORIGIN      │   ← aaram se
      └──────────────────┘
```

*Office wali misaal:* 1000 employees ko pen chahiye. Sab warehouse jaayein toh
bheed. Har floor pe chhota store bana do jo warehouse se **ek baar bulk mein**
le aata hai. Warehouse pe 1000 ki jagah 10 requests.

---

# STEP 6 — Deep dive (30-48 min)

## 6a. Transcoding — tukdon mein, saath-saath

**Interviewer:** "How would you transcode a 2-hour movie?"

Seedha-sadha tareeka: ek worker poori movie convert kare. 6 qualities ke liye
ghante lag jaayenge. **Nahi chalega.**

Sahi tareeka: **video ko tukdon mein baanto, sab tukde ek saath convert karo.**

```
   Poori 2-ghante ki movie
   ══════════════════════════════════════════════

   120 tukde, har ek 1 minute ka
   ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
   │ 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │...│120│
   └─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴───┴─┬─┘
     ▼   ▼   ▼   ▼   ▼   ▼   ▼   ▼   ▼       ▼
    [W] [W] [W] [W] [W] [W] [W] [W] [W] ... [W]
     └───┴───┴───┴─┬─┴───┴───┴───┴───────────┘
                   ▼
              wapas jodo → 6 qualities taiyaar
```

> "A 2-hour film becomes 120 one-minute jobs running at the same time, so it
> finishes in minutes instead of hours."

**Teen aur baatein bolna:**
- **Priority queue** — nayi release pehle, purani movie baad mein
- **Har tukda idempotent** — worker mar gaya toh bas woh tukda dobara chala do
- **Spot instances** — CPU ka kaam hai aur beech mein ruk sakta hai, toh sasta

## 6b. Kahin bhi nahi kaat sakte — GOP boundaries

**Interviewer:** "Can you split the video anywhere?"

**Nahi.** Aur yeh answer aapko alag khada kar dega.

Video ke andar frames aise store hote hain:

```
   [I][P][P][P][B][P][P]  [I][P][P][B][P][P][P]  [I][P][P]...
    └────── GOP 1 ─────┘   └────── GOP 2 ─────┘
    ▲                      ▲
    keyframe               keyframe

   I-frame = poori photo, apne aap mein complete
   P-frame = sirf "pichhle frame se kya badla"
   B-frame = aage aur peeche dono se difference
```

90% frames sirf "difference" store karte hain — isiliye video files chhoti hoti
hain. Par iska matlab: **ek P-frame akela bekaar hai.** Usme likha hai "haath 2
pixel upar" — par kis photo mein? Bina I-frame ke kuch decode nahi hoga.

**GOP** = ek I-frame + uske baad ke saare P/B frames, agla I-frame aane tak.

**Toh har cut keyframe pe hona chahiye.** Teen jagah:
1. Parallel transcoding ke tukde
2. Playback ke 4-second segments
3. Ad break ki jagah

> "Chunks have to be split at GOP boundaries — at keyframes — because frames in
> between are stored as differences and can't be decoded independently."

**Yeh bolte hi interviewer samajh jaayega ki aapne video pe kaam kiya hai.**

## 6c. Quality apne aap kaise badalti hai

```
   Manifest kehti hai:
   240p  -> seg_001, seg_002, seg_003 ...
   480p  -> seg_001, seg_002, seg_003 ...
   720p  -> seg_001, seg_002, seg_003 ...
   1080p -> seg_001, seg_002, seg_003 ...

   Player har 4 second mein sochta hai:
   internet tez + buffer bhara  ──► agla tukda 1080p se
   internet slow + buffer khali ──► agla tukda 480p se

   [1080p][1080p][720p][480p][480p][720p][1080p]
                    ▲                  ▲
              net slow hua        net theek hua
```

**Sabse important baat:**

> "The client decides, not the server. That's why every viewer gets
> byte-identical segments, so the CDN can cache them. If the server personalised
> the stream, cache hit ratio would collapse and we'd be back to serving
> 125 Tbps from origin."

**4 second hi kyun?**
- Chhote tukde (1 sec) — quality jaldi badalti hai, par bahut zyada requests
  aur compression kharab
- Bade tukde (10 sec) — kam requests, achhi compression, par player slow
  react karta hai
- **4-6 second beech ka raasta hai**

## 6d. Caching — yahan paisa bachta hai

```
   ┌────────────────────────────────────────────────────┐
   │ Player buffer (~30 sec)     network ke jhatke jhele│
   ├────────────────────────────────────────────────────┤
   │ CDN Edge      95-99% hit    yahi sab kuch deta hai │
   ├────────────────────────────────────────────────────┤
   │ CDN Shield    misses roke   origin ko bachaye      │
   ├────────────────────────────────────────────────────┤
   │ Origin        aakhri sahara                        │
   └────────────────────────────────────────────────────┘
```

**Segments kabhi badalte nahi** — segment 47 hamesha wahi rahega. Isliye jitna
chaho cache karo. Manifest badalti hai (live mein khaas kar), toh uski TTL
chhoti.

> "At 95% edge hit ratio, origin sees 5% of traffic. Push it to 99% and origin
> load drops fivefold. That ratio is the difference between a viable and an
> unviable business."

### Do problems aur unke solutions

**Problem 1 — sab ek saath expire ho gaye (cache stampede)**

```
Time 0:00  ── 10 lakh entries cache mein, sabki TTL 60 minute
Time 1:00  ── SAB EK SAATH expire
              ↓ 10 lakh requests ek saath database pe
              ↓ DATABASE DOWN
```

**Solution — jitter.** TTL fix mat rakho, thoda random milao:

```java
int ttl = 3600 + random.nextInt(600);  // 60 se 70 min ke beech
```

*School wali misaal:* poori school ko ek ghanti pe canteen bhejo → bheed. Har
class ko 5-5 minute alag bhejo → smooth.

**Problem 2 — ek hi cheez ke liye 10,000 requests (thundering herd)**

**Solution — request coalescing.** Sirf pehli request aage jaane do, baaki 9,999
ko usi ka jawab do.

```
   BINA:  10,000 requests ─────────► ORIGIN (10,000 baar)

   SAATH: Request 1     ─────────►  ORIGIN (sirf 1 baar)
          Request 2-10000 ──┐          │
                    wait    │          │
                            ▲──────────┘
                        sabko wahi jawab
```

*Class wali misaal:* 50 bachhon ko ek hi doubt hai. Ek monitor jaake pooche aur
sabko bata de. Teacher pe load 50 se 1.

**Farak:** jitter **rokta** hai ki sab ek saath expire hon. Coalescing
**sambhalta** hai jab phir bhi bheed aa jaaye. Dono chahiye.

## 6e. DRM

```
   Packaging ke waqt:  segments ko lock (encrypt) kar do
                       locked file CDN pe padi rahe — bina chaabi bekaar

   Playback ke waqt:   Player ──► Licence Server
                                 "yeh user subscribed hai? sahi region?"
                                 ──► chaabi mili
                       Player khol kar chala deta hai
```

**Teen DRM systems**, kyunki har company ka apna hai:
- **Widevine** — Google (Android, Chrome)
- **FairPlay** — Apple (iPhone, Apple TV)
- **PlayReady** — Microsoft (Xbox, kai Smart TVs)

Isliye ek hi content ko teeno ke liye encrypt karna padta hai.

**Yeh khud se bolna:**

> "The DRM licence service is the biggest single point of failure in the system.
> If it goes down, nothing plays anywhere — even if CDN, storage and database
> are all healthy. It needs the tightest SLO and full regional redundancy."

## 6f. Resume — "jahan chhoda tha wahin se"

**Kaam kya hai:** user ne 45 minute pe band ki. Kal 45 minute se shuru ho.

**Kaise:** player har 10 second backend ko batata hai "main 2712 second pe hoon".

**Ab problem dekho:**

```
   Ek saath dekhne wale     = 8,300,000
   Har 10 second mein update

   8,300,000 ÷ 10  =  830,000 writes per second
```

```
   Ek Postgres node jhel sakta hai   ~5,000 - 10,000 writes/sec
   Humein chahiye                    830,000 writes/sec
                                     → 100 guna zyada
```

Agar yeh main database pe daal diya toh **poora system girega** — sirf resume
nahi, catalogue bhi, sab kuch. Kyunki wahi database sab serve kar raha hai.

**Aur socho yeh data hai kya:** "user X, video Y, 2712 second." Bas. Yeh **sabse
kam kaam ka, sabse zyada volume wala** data hai. Ise sabse mehnge database pe
rakhna sabse badi galti hogi.

**Solution — Redis:**

```
   Player heartbeat
        ▼
   ┌──────────────────────────────────┐
   │  REDIS  (memory mein, bahut tez) │
   │  key:   progress:{user}:{video}  │
   │  value: 2712                     │
   └──────────────┬───────────────────┘
                  │ har 1-2 min, ya video band hone pe
                  ▼
   ┌──────────────────────────────────┐
   │  Database (pakka record)         │
   └──────────────────────────────────┘
```

**Ab tradeoff — yahi asli answer hai:**

> "I accept losing up to 30 seconds of watch position if a Redis node dies. The
> user resumes half a minute earlier than expected. In exchange, I keep 830,000
> writes per second off my transactional database. That's a trade I'd make
> every time."

**Kyun yeh strong hai:** aapne sirf solution nahi bataya. Aapne bataya **kya kho
rahe ho**, **kya paa rahe ho**, aur **kyun sauda faydemand hai**.

**Ek aur baat:** wahi heartbeat do jagah jaata hai —
```
   Heartbeat
      ├──► Redis  (position — resume ke liye, sirf latest chahiye)
      └──► Kafka  (quality, rebuffers — analytics, poora history chahiye)
```

---

# STEP 7 — Kya-kya toot sakta hai (48-55 min)

**Yeh khud se uthana hai. Poochne ka intezaar mat karna.**

```
KYA TOOTA                 KYA HOGA              KYA KARENGE
─────────────────────────────────────────────────────────────────────
CDN ka ek region down     Us region mein        2-3 CDN rakho,
                          video nahi chalegi    player khud switch kare

DRM licence down          KUCH BHI nahi         Har region mein copy,
                          chalega, kahin bhi    sabse strict SLO

Origin pe zyada load      Sab kuch girega       Shield + coalescing
                                                + purana data hi de do

Transcoding pichhad gayi  Videos late           Koi baat nahi, async hai
                          publish hongi         + priority queue

Database down             Browse nahi hoga,     Cache se reads chalti
                          par chalti video      rahengi
                          chalti rahegi

Badi release aayi         Sab ek saath miss     Pehle se CDN warm
                                                kar do
```

> "The one I'd invest most in is the DRM licence service, because it's the only
> component whose failure takes down playback everywhere with no fallback."

## CDN switch kaise — client-side steering

Do tareeke:

**DNS se** — DNS batata hai kaunsa CDN use karo. CDN kharab? DNS badal do.
*Problem:* DNS har jagah cache hota hai, 5-10 minute lag jaate hain. Aur faisla
sabke liye ek hi hota hai.

**Client-side steering** — player ko shuru mein hi 2-3 CDN ke URLs de do. Player
khud apni speed naapta hai. CDN A slow hua? Player khud, **beech video mein**,
CDN B pe chala jaata hai. Seconds mein.

*Misaal:* DNS = police ne board laga diya "road band" — sahi hai par dikhne mein
time lagta hai, aur sabke liye ek hi. Client-side = aapke phone mein Maps hai,
aap khud jam dekh kar turant raasta badal lete ho.

---

# STEP 8 — Tradeoffs (55-58 min)

> "Let me summarise the tradeoffs I made.
>
> Longer segments cache better but adapt slower — I chose 4 seconds.
> More quality levels give better matching but cost more to transcode.
> Async ingest means resilient uploads but delayed availability.
> Redis for watch progress means losing 30 seconds on failure, but keeps 830k
> writes per second off the database.
> Multi-CDN improves resilience but complicates cache warming.
>
> Overall I optimised everything for cache hit ratio, because at 125 Tbps that's
> the only thing that makes this possible at all."

---

# AGAR TIME BACHE — Live streaming

**Interviewer:** "What changes for live?"

> "Live is much harder, mostly in caching.
>
> The manifest changes every few seconds, so it can't be cached for long. Every
> viewer asks for the new manifest at almost the same moment — a thundering herd
> at each segment boundary. I'd handle that with request coalescing at the
> shield.
>
> The encoder is now on the critical path, so I'd run two encoders producing
> identical segment boundaries and fail between them without the player
> noticing.
>
> And latency becomes real: encode, package, propagate, buffer. Standard HLS
> runs 20-30 seconds behind real time; low-latency variants target under 5."

---

# YAAD RAKHNE WALI 5 LINES

1. **"125 Tbps at peak — so the entire design is organised around CDN cache hit ratio."**
2. **"The client adapts, not the server — that's why segments stay cacheable."**
3. **"Chunks must split at GOP boundaries, because P-frames can't decode independently."**
4. **"The DRM licence service is my biggest single point of failure."**
5. **"Heartbeats are 830k writes per second — that goes to Redis, and I accept losing 30 seconds."**

---

# SABSE ZAROORI AADAT

Har faisle ke saath **kyun** bolna:

> **"I chose X, I rejected Y, because Z."**

Yahi ek cheez senior aur mid-level candidate ka farak hai. Jo log sirf boxes
bana kar naam gina dete hain, woh mid-level lagte hain. Jo har choice ki wajah
aur uski keemat bata dete hain, woh senior lagte hain.
