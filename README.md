# Backend Production Coding

Hands-on practice for production-style backend engineering: correctness under retries, concurrency, partial failure, and distributed-system constraints.

## Core mental model

For every state-changing API, ask:

1. What is the happy path?
2. What must be validated before any write?
3. What happens under concurrent requests?
4. What happens if the client retries?
5. What happens if the server crashes between any two steps?
6. Which invariant is enforced by application code vs. the database?
7. Where is atomicity actually guaranteed?

## Practice flow

- 10–15 min: model states, invariants, and failure points
- 25–35 min: implement
- 15 min: add tests for retries, races, and partial failure
- 5 min: explain the design aloud as if in an interview

## Modules

- Mutation correctness
- Idempotency and retry semantics
- Optimistic / pessimistic concurrency control
- Transactions
- Async workflows and state machines
- DB + message queue consistency
- Rate limiting, pagination, caching, and webhook delivery

Start with `01-mutation-correctness/01-update-ad.md`.
