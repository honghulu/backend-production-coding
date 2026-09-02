# Lab — Payment API Idempotency

## Scenario

You own `POST /payments`. Clients retry after timeouts, connection resets, or lost responses. A retry must not create a second logical payment.

```http
POST /payments
Idempotency-Key: pay-req-001
Content-Type: application/json

{
  "customerId": 123,
  "amount": 4999,
  "currency": "USD"
}
```

## Functional requirements

- Create a payment and return its ID/status.
- `Idempotency-Key` is required.
- Same key + same payload returns the original logical result.
- Same key + different payload is rejected.
- Two concurrent requests with the same key create at most one payment.
- Correctness must survive multiple application pods.

## You must design

- API response/status codes
- `payments` schema
- idempotency storage/schema
- uniqueness constraints
- transaction boundary
- request fingerprint policy
- PROCESSING / SUCCEEDED / FAILED semantics if you use states
- retention/cleanup policy

## Failure injection

Reason about and test:

1. same request twice sequentially
2. two threads with the same key concurrently
3. DB commit succeeds but HTTP response is lost
4. server crashes after claiming the key but before payment creation
5. payment is created but idempotency result is not persisted
6. Redis becomes unavailable, if Redis is part of your design
7. retry reaches another pod

## Acceptance criteria

- No duplicate payment for the same logical operation.
- A durable invariant enforces correctness; an in-memory `if` is insufficient.
- Include a concurrency test.
- Include a retry-after-lost-response integration test.
- Be able to point to the exact atomic boundary and explain why it is safe.

## Interview follow-ups

- Why is `SELECT` then `INSERT` unsafe by itself?
- Could the idempotency key be a unique column on `payments` instead of a separate table?
- What does a DB unique constraint solve that Redis `SETNX` alone does not?
- What happens when the first request is still PROCESSING?
- Should failed requests be replayed?
- What if an external payment provider succeeds but your local DB transaction fails?
- How would you clean up hundreds of millions of old keys?

## Stretch

Add a fake external payment provider that sometimes times out *after* successfully charging. Design the service so an ambiguous downstream result does not lead to a double charge.
