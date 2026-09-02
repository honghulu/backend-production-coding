# Exercise 05 — DB + Kafka Dual Write

A service updates business state in a relational database and must publish an event to Kafka.

## Naive implementation

```python
def update_campaign(request):
    db.update_campaign(request)
    kafka.publish("campaign.updated", request)
    return {"status": "success"}
```

## Problem

Analyze both failure orders:

```text
DB success -> Kafka failure
Kafka success -> DB failure
```

Explain why retry alone does not automatically fix correctness.

## Requirements

Design a solution that guarantees that every committed business update eventually produces the corresponding event without publishing events for rolled-back updates.

## Implement / describe

1. naive dual write and its failure cases
2. transactional outbox schema
3. transaction that writes business data + outbox row
4. outbox publisher worker
5. retry behavior
6. consumer-side idempotency assumptions

Suggested schema:

```sql
outbox_events(
    event_id PRIMARY KEY,
    aggregate_type,
    aggregate_id,
    event_type,
    payload,
    status,
    created_at,
    published_at
)
```

## Failure injection

Reason about crashes:

- before DB transaction
- after business update but before outbox insert
- after outbox insert but before commit
- after commit but before publisher sees row
- after Kafka publish but before outbox row is marked published

## Required questions

- Why can the publisher safely publish the same outbox event more than once?
- What must downstream consumers do?
- What does "exactly once" usually mean at the business-effect level?
- How is this related to idempotency?
