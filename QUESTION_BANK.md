# Production Backend Coding — Comprehensive Question Bank

This is the master list for the repo. The goal is to build implementation skill, not just conceptual familiarity.

Each exercise should eventually have some combination of:
- requirement
- starter code
- failing tests
- hidden edge cases
- concurrency/failure cases
- production extension
- interview follow-up questions

## Tier 1 — Core mutation correctness

1. Update Ad with partial fields, validation, version conflict, idempotency
2. Update User Profile with PATCH semantics (`missing` vs `null`)
3. Update Campaign Budget with optimistic locking
4. Create Order with unique idempotency key
5. Cancel Order with legal state transitions
6. Update Subscription Plan atomically
7. Change Account Email with uniqueness validation
8. Bulk Update Ads: all-or-nothing transaction
9. Multi-field Configuration Update with cross-field validation
10. Conditional Resource Update using `expected_version`

Core concepts: validate-before-commit, candidate state, transaction boundary, rollback, optimistic locking, CAS, unique constraints, lost update.

## Tier 2 — Idempotency, retries, and duplicate execution

11. Payment API with idempotency key
12. Refund API safe under client retry
13. Conversion Postback deduplication
14. Job Submission API safe under timeout + retry
15. Webhook Receiver with duplicate event delivery
16. Email Notification request that must not double-send
17. Create Invoice with request fingerprint validation
18. Retry-safe Order Placement after response loss
19. Idempotency record lifecycle: PROCESSING / SUCCEEDED / FAILED
20. Same idempotency key reused with different payload

Core concepts: timeout ambiguity, at-least-once, at-most-once, exactly-once effect, request fingerprint, dedup, retryable vs non-retryable failure.

## Tier 3 — Database transactions and isolation

21. Bank Transfer between two accounts
22. Inventory decrement without going below zero
23. Seat reservation under contention
24. Coupon redemption with a max-use constraint
25. Wallet debit with insufficient-funds protection
26. Apply Loyalty Points with concurrent requests
27. Read-modify-write lost update bug
28. `SELECT ... FOR UPDATE` reservation flow
29. Serializable transaction exercise with write skew
30. Upsert with a unique business key
31. Pagination: offset vs cursor implementation
32. Batch write with rollback on one invalid row

Core concepts: transactions, row locks, MVCC, isolation levels, unique index, check constraints, atomic SQL update, upsert, write skew.

## Tier 4 — In-process concurrency

33. Thread-safe counter
34. Thread-safe LRU cache
35. Bounded worker queue
36. Producer-consumer implementation
37. Read-write lock use case
38. Thread pool with graceful shutdown
39. Concurrent task deduplication / single-flight
40. Deadlock reproduction and fix via lock ordering
41. Semaphore-limited external API calls
42. Future/Promise aggregation with timeout

Core concepts: mutex, semaphore, condition variable, atomic variable, critical section, deadlock, starvation, thread pool, backpressure.

## Tier 5 — Distributed coordination

43. Distributed lock with Redis lease
44. Scheduled job that runs once across many servers
45. Leader worker with heartbeat + lease expiry
46. Distributed rate limiter
47. Partition ownership assignment
48. Distributed counter correctness trade-offs
49. Lock ownership token / safe unlock
50. Fencing token exercise
51. Lease renewal and failure handling
52. Hot-key mitigation design + implementation sketch

Core concepts: local vs distributed lock, lease, TTL, ownership, fencing, leader election, clock assumptions, partition ownership.

## Tier 6 — Async workflows and state machines

53. S3 Presigned Upload state machine
54. Video Processing Job lifecycle
55. Report Generation async job
56. Payment state machine
57. Refund state machine
58. Order fulfillment workflow
59. ML training job state transitions
60. User account deletion workflow
61. Expiring reservation state machine
62. Retryable vs terminal failure transitions

Core concepts: INIT/PENDING/PROCESSING/SUCCESS/FAILED/CANCELLED/EXPIRED, legal transitions, terminal states, compensation, timeout recovery.

## Tier 7 — Messaging and event-driven correctness

63. Kafka consumer with duplicate delivery
64. Consumer idempotency using an inbox table
65. Poison message + DLQ
66. Out-of-order event handling
67. Partition key choice for per-user ordering
68. Offset commit timing exercise
69. DB update + Kafka publish dual-write bug
70. Transactional Outbox implementation
71. CDC-based event publication
72. Consumer replay after deployment
73. Event versioning and backward compatibility
74. Saga-style multi-service workflow

Core concepts: ack/offset, at-least-once delivery, dedup, ordering, outbox, inbox, CDC, saga, replay.

## Tier 8 — API engineering

75. RESTful CRUD API with proper status codes
76. PUT vs PATCH implementation
77. Conditional request with ETag / If-Match
78. Cursor pagination API
79. Filter + sort API with stable pagination
80. API version migration while keeping backward compatibility
81. Validation error model
82. Authentication vs authorization failure handling
83. Rate-limited API with 429 + Retry-After
84. Request timeout propagation
85. Large request/body limit handling
86. Bulk API partial-success vs atomic semantics

Core concepts: API contract, error modeling, 400/401/403/404/409/412/429/5xx, compatibility, pagination.

## Tier 9 — Caching

87. Cache-aside user profile
88. Write-through cache
89. Cache invalidation after DB update
90. Prevent cache stampede
91. Negative caching for missing keys
92. Hot-key mitigation
93. Stale-while-revalidate
94. Single-flight cache fill
95. TTL selection exercise
96. Redis-backed distributed cache consistency

Core concepts: cache-aside, read-through, write-through, TTL, invalidation, stampede, penetration, stale reads.

## Tier 10 — Resource management and overload

97. DB connection pool exhaustion
98. Bounded thread pool under burst traffic
99. Queue backpressure
100. Circuit breaker
101. Bulkhead isolation
102. Load shedding
103. Timeout budget across service calls
104. Exponential backoff + jitter
105. Retry storm prevention
106. Graceful degradation under dependency outage

Core concepts: pools, queue size, backpressure, timeout, retry budget, circuit breaker, overload protection.

## Tier 11 — Observability and operability

107. Structured logging for a write API
108. Request ID / trace ID propagation
109. Metrics for payment processing
110. p50/p95/p99 latency instrumentation
111. Retry-rate and duplicate-rate metrics
112. Queue depth / worker lag alerting
113. Distributed tracing across 3 services
114. SLI/SLO exercise for an API
115. Audit log for sensitive mutations
116. Debugging duplicate side effects from logs

Core concepts: logs, metrics, traces, correlation IDs, histograms, alerts, SLI/SLO, auditability.

## Tier 12 — Testing production behavior

117. Unit tests for validation and state mutation
118. Integration test with a real database
119. Idempotency retry test
120. Two-writer optimistic locking test
121. Pessimistic lock contention test
122. Failure injection before/after DB commit
123. Crash-after-commit retry test
124. Kafka duplicate/replay test
125. Timeout/retry integration test
126. Property-based invariant test
127. Load test with bounded resources
128. Chaos-style dependency failure test

Core concepts: unit, integration, concurrency, failure injection, invariant testing, load testing.

## Tier 13 — Code structure and service architecture

129. Refactor fat controller into Controller / Service / Repository
130. DTO vs domain model separation
131. Centralized exception mapping
132. Validation layer design
133. Repository abstraction around persistence
134. Dependency injection exercise
135. Configuration management
136. Interface-driven external dependency
137. Transaction boundary placement across layers
138. Refactor synchronous side effects into async event flow

Core concepts: separation of concerns, module boundaries, dependency injection, testability, layering.

## Tier 14 — Integrated production scenarios

139. Production-grade Ad Update Service
140. Payment + Ledger + Idempotency Service
141. Flash-sale Inventory Service
142. Seat Booking Service
143. File Upload + Processing Service
144. Webhook Delivery Platform
145. Async Report Generation Platform
146. Notification Service with dedup and retry
147. Order Service with Outbox + Kafka
148. Distributed Job Scheduler
149. API Gateway Rate Limiter
150. Mini Ads Campaign Management Service

These should combine multiple concepts and be treated like mini projects rather than one-function exercises.

## Required interview questions for every state-changing exercise

1. Where is atomicity guaranteed?
2. Where is idempotency guaranteed?
3. What happens if the request is retried?
4. What happens if the server crashes before the write?
5. What happens if it crashes after commit but before response?
6. What happens with two concurrent requests?
7. Which invariants are protected by application code?
8. Which invariants are protected by DB constraints/transactions?
9. Which failures are retryable?
10. What metrics/logs would prove this works in production?
