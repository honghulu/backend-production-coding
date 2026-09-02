# Exercise 01 — Update Ad

This is a production-style mutation problem focused on atomicity, validation, idempotency, and optimistic concurrency control.

## Requirement

An advertiser edits several fields of one ad in the UI. The frontend bundles the changes into one backend request.

Implement `update_ad(current_ad, update_request)` so that:

- either all requested field changes take effect, or none do
- invalid updates never partially modify the ad
- stale clients cannot overwrite newer changes
- retries using the same idempotency key do not apply the mutation twice

## Input

```python
current_ad = {
    "ad_id": "A123",
    "version": 7,
    "title": "Summer Sale",
    "budget": 1000.0,
    "bid": 2.5,
    "targeting": ["US", "CA"],
    "landing_url": "https://shop.example.com",
}

update_request = {
    "expected_version": 7,
    "idempotency_key": "req-001",
    "fields": {
        "budget": 1500.0,
        "title": "Summer Sale - Round 2",
    },
}
```

A missing field means "leave unchanged".

A field explicitly set to `None` means "clear this field" if clearing is legal for that field.

## Validation rules

At minimum:

- `budget > 0`
- `0.01 <= bid <= 100`
- `targeting` must be a non-empty list
- `title`, if not `None`, must be a non-empty string
- `landing_url`, if not `None`, must start with `http://` or `https://`

Add at least one cross-field validation rule of your own.

## Output

Success:

```python
{"status": "success", "new_version": 8}
```

Validation failure:

```python
{"status": "rejected", "errors": {"budget": "must be > 0"}}
```

Version conflict:

```python
{"status": "conflict", "current_version": 9}
```

Idempotent retry should return the same logical result as the first successful execution.

## Starter

```python
from copy import deepcopy


def update_ad(current_ad, update_request, idempotency_store):
    # TODO 1: idempotency check

    # TODO 2: optimistic version check

    # TODO 3: construct candidate state without mutating current_ad

    # TODO 4: validate the resulting candidate as a whole

    # TODO 5: commit exactly once

    # TODO 6: persist idempotency result

    raise NotImplementedError
```

## Required tests

Write tests for all of these:

1. successful update of two fields
2. one invalid field causes no fields to change
3. stale `expected_version` returns conflict
4. successful update increments version exactly once
5. retry with same idempotency key does not increment version again
6. same idempotency key reused with a different payload is rejected
7. a field omitted from `fields` remains unchanged
8. explicit `None` behaves differently from omission
9. cross-field validation checks the final merged state, not only changed fields
10. original ad is unchanged after any rejected request

## Interview follow-ups

Be ready to explain:

- Why is validating each field immediately before assigning it risky?
- Why should you validate the final candidate state?
- Is `current_ad.clear(); current_ad.update(candidate)` truly atomic?
- How would this change if the ad lived in MySQL/PostgreSQL?
- Show the SQL for optimistic locking using `WHERE version = ?`.
- Where should the idempotency key be stored in production?
- What if the DB update succeeds but saving the idempotency result fails?
- How would you make business data and idempotency state atomic?

## Level 2 — Production version

After finishing the in-memory version, redesign it using tables like:

```sql
ads(
    ad_id PRIMARY KEY,
    version,
    title,
    budget,
    bid,
    targeting,
    landing_url
)

idempotency_requests(
    idempotency_key PRIMARY KEY,
    resource_id,
    request_hash,
    status,
    response,
    created_at
)
```

Your final design should survive:

- two concurrent writers with the same expected version
- client timeout after DB commit
- process crash before response
- duplicate retry on another server
