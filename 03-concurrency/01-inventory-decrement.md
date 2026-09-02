# Exercise 03 — Inventory Decrement

Implement a purchase path for a product with limited inventory.

## State

```text
product_id = P100
stock = 1
```

Two customers may buy concurrently.

## Requirement

Never allow stock to become negative and never sell more units than exist.

## Version A — Naive

Start from this unsafe shape:

```python
stock = repo.get_stock(product_id)
if stock <= 0:
    return sold_out()
repo.set_stock(product_id, stock - 1)
return success()
```

Explain the race condition.

## Implement three solutions

### 1. Atomic conditional update

Target SQL shape:

```sql
UPDATE products
SET stock = stock - 1
WHERE product_id = ?
  AND stock > 0;
```

Use affected-row count to determine success.

### 2. Optimistic locking

Use a version column and conditional update.

### 3. Pessimistic locking

Use a DB transaction with `SELECT ... FOR UPDATE`.

## Compare them

Explain trade-offs for:

- low contention
- flash sale / high contention
- retry cost
- blocking
- deadlocks
- throughput

## Required tests

1. stock 1 + two concurrent buyers => exactly one success
2. stock never negative
3. failed conditional update creates no order
4. transaction rollback restores consistency
5. optimistic conflict can be retried safely

## Interview follow-ups

- Why is an application-level mutex insufficient across multiple servers?
- When is Redis distributed locking unnecessary because the DB can enforce the invariant directly?
- Why is `UPDATE ... WHERE stock > 0` often better than read-then-write?
