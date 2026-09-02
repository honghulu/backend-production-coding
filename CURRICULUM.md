# Backend Engineering Curriculum — 2–3 YOE Target

This repository is not a notes repo and not a LeetCode repo. The goal is to compress the engineering situations a backend SDE repeatedly encounters in production into hands-on labs.

## How each lab should work

Each lab has two layers:

1. **Problem spec (`README.md`)** — requirements, invariants, failure cases, acceptance criteria, and interview follow-ups.
2. **Runnable starter project** — enough framework to remove setup friction, but the important implementation is left as TODOs.

Default stack: **Java 21 + Spring Boot + Maven + MySQL/PostgreSQL + Redis + Kafka + Docker Compose**. Python may be used for data/ML-oriented labs later.

Do not read a solution first. For every lab:

- model the states and invariants;
- identify failure boundaries;
- implement the feature;
- write unit/integration/concurrency tests;
- run it locally;
- explain trade-offs aloud;
- then compare alternative designs.

## Phase 1 — Feature ownership fundamentals

1. Update API correctness — validation, partial update, transaction boundaries
2. Create Order API — controller/service/repository/schema/test structure
3. Pagination & filtering — cursor vs offset, stable ordering, indexes
4. Database migration — backward-compatible schema evolution
5. File upload — presigned URL, upload state, completion callback
6. Webhook receiver — validation, signature, deduplication

## Phase 2 — Mutation correctness

7. Payment idempotency — idempotency key + DB uniqueness
8. Request replay — persist/replay previous response
9. Inventory reservation — atomic decrement and oversell prevention
10. Optimistic locking — version column / compare-and-swap
11. Pessimistic locking — SELECT FOR UPDATE and lock scope
12. Transaction isolation — lost update, dirty/non-repeatable/phantom behavior
13. Distributed lock — ownership, TTL, renewal, fencing discussion

## Phase 3 — Resilience

14. Retry with exponential backoff + jitter
15. Timeout budgets — downstream deadlines and cancellation
16. Circuit breaker — closed/open/half-open state machine
17. Rate limiter — token bucket + Redis distributed version
18. Graceful shutdown — stop traffic, drain work, close resources
19. Bulkhead / bounded concurrency — protect downstream dependencies

## Phase 4 — Async systems

20. Kafka producer/consumer — partitioning, consumer groups, offsets
21. Idempotent consumer — duplicate message handling
22. Retry topic + DLQ — poison message strategy
23. Transactional outbox — DB + Kafka dual-write consistency
24. Background job worker — leasing, retry, visibility timeout
25. Async order state machine — CREATED→PAID→FULFILLING→COMPLETED/FAILED
26. Saga compensation — multi-service workflow failure handling

## Phase 5 — Cache & data access

27. Cache-aside — TTL, invalidation, stale reads
28. Cache stampede — locking/single-flight/probabilistic refresh
29. Hot key mitigation — local cache, sharding, replication
30. SQL indexing lab — EXPLAIN, composite indexes, leftmost prefix
31. Connection pool exhaustion — reproduce, diagnose, fix
32. Read/write split — replica lag and read-after-write consistency

## Phase 6 — Production engineering

33. Structured logging — request IDs and useful context
34. Metrics — RED metrics and business counters
35. Distributed tracing — propagate trace/span context
36. Health/readiness endpoints — dependency-aware readiness
37. Feature flags — rollout, kill switch, configuration safety
38. Canary deployment exercise — staged rollout + rollback criteria
39. Incident debugging — latency spike from symptoms to root cause
40. Capacity lab — QPS, per-pod capacity, headroom, HPA thresholds

## Phase 7 — Service ownership projects

41. Notification Service — API + DB + queue + workers + retries
42. Payment Service — idempotency + state machine + webhook + ledger concepts
43. Media Upload Service — presigned upload + metadata + async processing
44. Conversion Tracking Service — dedupe + attribution + event pipeline
45. Ad Campaign Budget Service — concurrent spend updates + pacing primitives

## Graduation criteria

You should be able to receive a vague requirement, find the relevant boundaries, design the schema/API/state machine, implement it, test failure modes, run it, observe it, and explain how it behaves under retries, concurrency, partial failure, multiple pods, and deployment changes.
