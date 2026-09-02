# Roadmap

This repo is designed to build production backend coding instincts, not just algorithmic coding skill.

## Phase 1 — Mutation correctness

1. Update Ad — partial update, validation, optimistic locking, idempotency
2. Create Order — create semantics, unique constraints, duplicate requests
3. Patch User Profile — missing vs null, cross-field validation
4. Cancel Order — legal state transitions and transactional updates

## Phase 2 — Idempotency and retries

1. Payment Idempotency
2. Conversion Postback Deduplication
3. Job Submission with Retry
4. Webhook Consumer Deduplication

## Phase 3 — Concurrency

1. Inventory Decrement
2. Seat Reservation
3. Concurrent Ad Update
4. Account Balance Transfer

## Phase 4 — Async workflows

1. S3 Direct Upload State Machine
2. Video Processing Job
3. Report Generation Workflow

## Phase 5 — DB + Messaging

1. DB Update + Kafka Publish Failure
2. Transactional Outbox
3. Consumer Deduplication

## Phase 6 — API Infrastructure

1. Rate Limiter
2. Cursor Pagination
3. Cache-Aside
4. Reliable Webhook Delivery

## Completion standard for every exercise

You should be able to answer all of these without hand-waving:

- What are the invariants?
- Where is validation performed?
- Where is atomicity guaranteed?
- What happens on client retry?
- What happens on concurrent requests?
- What happens if the server crashes after each side effect?
- Which guarantees come from application code, Redis, or the database?
- What tests prove the implementation is correct?
