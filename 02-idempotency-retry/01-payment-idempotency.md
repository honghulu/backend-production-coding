# Exercise 02 — Payment Idempotency

Implement a payment creation API that is safe under client retries.

## Scenario

A client calls:

```http
POST /payments
Idempotency-Key: pay-req-001
```

Body:

```json
{
  "account_id": "U100",
  "amount": 100.00,
  "currency": "USD"
}
```

The client may retry because of timeout, connection reset, or a lost response.

## Requirements

- The same logical request must create at most one payment effect.
- Retrying the same idempotency key with the same payload returns the original result.
- Reusing the same key with a different payload must be rejected.
- A process crash after DB commit but before response must not cause a second charge on retry.
- Multiple application servers may receive retries.

## Starter

```python
def create_payment(request, idempotency_key, payment_repo, idempotency_repo):
    raise NotImplementedError
```

## Required design questions

- What exactly is stored under the idempotency key?
- Why is `key -> boolean seen` often insufficient?
- Should failed validation requests be cached?
- Should transient server errors be cached?
- What TTL, if any, should idempotency records have?
- Can you put the idempotency key directly on the `payments` table?
- When is a separate idempotency table preferable?

## Required failure analysis

Walk through the state after a crash at each point:

```text
check idempotency
↓
insert payment
↓
record idempotency result
↓
commit
↓
return response
```

Then redesign the ordering/transaction so retry is safe.

## Required tests

1. first request succeeds
2. same key + same payload returns same payment
3. same key + different payload is rejected
4. two concurrent same-key requests create one payment
5. retry after simulated crash-after-commit creates no duplicate
6. retry may land on another server and still deduplicates
