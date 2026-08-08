# Roku — Senior Software Engineer (Video Platform, Backend), Bengaluru
## Prep plan — 8 to 24 August 2026

**Interviews: Tuesday 25 August and Thursday 27 August. Two rounds, 60 minutes
each, on HackerRank CodePair.**

You have 17 days. This plan is balanced between coding and system design,
because CodePair is a shared *code editor* — that strongly suggests at least one
round is hands-on coding, not pure whiteboard architecture. Adjust once the
recruiter confirms what each round covers (see "If the round mix changes" at the
end).

Companion files:
- `SOLUTIONS-CODING.md` — Java solutions for every reported coding problem
- `SOLUTIONS-DESIGN.md` — worked answers for every reported design question

---

## 1. What the loop looks like

Reported full Roku loop from an India candidate:

1. Screen — one LeetCode medium plus light system design from your past work
2. Second coding round — two easy-to-medium problems
3. India manager — past experience plus a brief system design
4. US engineer — the deep system design round
5. US manager — past experience and general questions

Your next stage is two 60-minute CodePair rounds with UK and US interviewers.
The AI round comes later.

Practical notes from candidate reports:

- They want **runnable, debuggable code**, not clever one-liners
- Some interviewers go silent while you work — narrate anyway
- High bar for precision; hand-waving on internals is penalised
- Resume deep dives are common — anything on your CV is fair game
- Don't disclose current compensation; pivot to the role's band

---

## 2. Question banks

### System design — reported

**Video, streaming and ads (highest probability)**

1. Design a video streaming platform
2. Design an ad delivery system that limits repeated ads to a user (frequency capping)
3. Low-latency video metadata retrieval during peak hours
4. High-throughput logging and alerting from millions of streaming devices
5. Personalized content recommendation engine

**Distributed systems generics**

6. Real-time Top N words service
7. One-time event scheduler for millions of subscription notifications
8. Session management for a gaming app
9. High-traffic backend service — API, caching, replication
10. Data model for streaming; end-to-end data governance pipeline
11. Fallback classics: URL shortener, news feed, distributed cache

### Coding — reported

**Design / data structures:** LRU Cache (most repeated), thread-safe Max Stack,
deep clone a directed graph, first non-repeating character in a stream

**Arrays / strings:** Top K Frequent Elements, Substring with Concatenation of
All Words, peak element via modified binary search, atoi, anagram in constant
space, best time to buy and sell stock

**Graphs / grids:** Max Area of Island

**DP:** Minimum Cost for Tickets, 0/1 knapsack

**Linked lists:** sort a singly linked list, detect a cycle, reverse a sub-segment

### Concurrency and systems — they ask everyone

Race condition debugging and thread-safe fixes; FIFO ring buffer with
`IsFIFOFull()`; threads vs processes and deadlock prevention; semaphore vs mutex
vs spinlock; what happens on heap allocation (user space, kernel space,
syscalls); virtual memory and context switching.

### Behavioral — reported

Hardest bug of your career. Working with ambiguous requirements. What you like
and dislike about your current stack. A technical disagreement and how it
resolved. Cross-platform consistency versus platform-specific optimisation.

---

## 3. The schedule

Weekdays roughly 2 hours, weekends roughly 4. Every session starts with coding,
because that's the perishable skill.

### Phase 1 — Foundations (Sat 8 – Fri 14 Aug)

Goal: coding reflexes back, design framework internalised.

**Sat 8 Aug** *(4h)*
Coding: LRU Cache from scratch, three times, until it's muscle memory. Then the
thread-safe variant.
Design: read section 0 of `SOLUTIONS-DESIGN.md` and internalise the six steps.
Do a timed 45-minute URL shortener purely to drill the structure.

**Sun 9 Aug** *(4h)*
Coding: Top K Frequent Elements (both heap and bucket versions), Max Area of Island.
Design: **Design a video streaming platform.** First attempt, timed, out loud.

**Mon 10 Aug** *(2h)*
Coding: linked list set — sort, detect cycle, reverse a sub-segment.
Theory: caching strategies, invalidation, thundering herd. Write your own
one-page cheat sheet — writing it is the point, not reading mine.

**Tue 11 Aug** *(2h)*
Coding: Minimum Cost for Tickets, 0/1 knapsack.
Design: redo the video streaming platform, fixing what was weak. Second passes
are where the learning actually happens.

**Wed 12 Aug** *(2h)*
Coding: peak element binary search, atoi. Both are about edge cases, not logic —
force yourself to enumerate them before writing.
Theory: sharding, partition keys, hot keys, consistent hashing.

**Thu 13 Aug** *(2h)*
Coding: deep clone a directed graph, first non-repeating character in a stream.
Design: **Design an ad delivery system with frequency capping.** Your best
question. Go deep on the counter design.

**Fri 14 Aug** *(2h)*
Coding: thread-safe Max Stack, FIFO ring buffer.
Theory: replication, consistency, quorums.

### Phase 2 — Depth (Sat 15 – Fri 21 Aug)

Goal: cover the remaining designs, harder coding, start mocks.

**Sat 15 Aug** *(Independence Day, 4h)*
Concurrency block: work through the whole concurrency section of
`SOLUTIONS-CODING.md`. Write the broken counter and all three fixes yourself.
Design: **low-latency metadata retrieval.**

**Sun 16 Aug** *(4h)*
Coding: Substring with Concatenation of All Words — the hard one. Budget an hour.
Design: **logging and alerting from millions of devices.** Closest to your CDN
log pipeline work, so push for real depth here.

**Mon 17 Aug** *(2h)*
Coding: two random mediums, timed, on HackerRank itself so you get used to the
environment.
Design: **real-time Top N service.**

**Tue 18 Aug** *(2h)*
Coding: revisit whichever problem felt worst so far.
Design: **distributed session management.**

**Wed 19 Aug** *(2h)*
Coding: best time to buy and sell stock plus variants, anagram in constant space.
Design: **one-time event scheduler.**

**Thu 20 Aug** *(2h)*
Coding: LRU Cache again, cold, timed. It's the most likely question and it
should take under ten minutes now.
Design: **recommendation engine** — architecture shape only, don't rabbit-hole
into ML. Then 20-minute sketches of distributed cache and news feed.

**Fri 21 Aug** *(2h)*
Behavioral: write out five stories properly, one per reported question. Then a
resume deep dive — for every line on your CV, can you go three questions deep?

### Phase 3 — Polish (Sat 22 – Mon 24 Aug)

**Sat 22 Aug** *(3h)*
Full mock: one coding problem in 45 minutes, then one design in 45 minutes,
back to back, no notes. Dress rehearsal — treat it like the real thing.

**Sun 23 Aug** *(3h)*
Redo the two designs that felt weakest in the mock. Then rehearse your
90-second background intro until it's tight — you'll use it twice this week and
it sets the tone for both rounds.

**Mon 24 Aug** *(1–2h, light)*
Reread your own cheat sheet, not mine. One easy coding problem to stay warm.
Do a **CodePair dry run** — open the tool, check your setup, make sure you can
type Java without IDE autocomplete. Nobody wants to discover a broken microphone
at 7pm on Tuesday. Then stop. Rest matters more than one more problem.

### Interview days

**Tue 25 Aug — Round 1.** Afterwards, immediately write down every question you
were asked, what went well, and what you fumbled. Memory fades fast.

**Wed 26 Aug — Adjust.** This day is a gift. Use round 1 to calibrate: if the
interviewer went hard on concurrency, revise concurrency. If they pushed on
design tradeoffs, drill that. Do not cram broadly — fix the specific gap.

**Thu 27 Aug — Round 2.**

---

## 4. Standing rules

**Structure beats knowledge in design rounds.** Requirements, numbers, API,
high-level, deep dive, failure modes. State the tradeoff for every choice: what
you picked, what you rejected, why.

**Narrate in coding rounds.** Interviewers reportedly go quiet. Silence from
them is not a signal — keep talking through your approach, your edge cases, and
your complexity.

**Hold the confidentiality line.** Design rounds are where it's easiest to slip
into describing your employer's system. Design from first principles. If it
converges on something similar, fine — just don't narrate it as "this is how we
do it." Section 10 of `SOLUTIONS-DESIGN.md` has a clean-room version of ad
insertion for exactly this reason.

---

## 5. If the round mix changes

Once the recruiter confirms what the two rounds cover, rebalance:

**Both rounds coding** — drop the design work in Phase 2 to a single session per
weekend, and spend the reclaimed time on medium-to-hard problems, especially
graphs and DP, plus concurrency. Roku reportedly asks concurrency of everyone.

**One coding, one design** — the plan as written is already correct.

**Both design** — keep coding to the daily 30-minute warm-up only, and add the
remaining generic designs: rate limiter, distributed job scheduler,
notification system.
