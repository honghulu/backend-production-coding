# Runnable Backend Labs Starter

A shared runnable Java/Spring Boot codebase for the first production-engineering labs. This intentionally looks more like an existing company service than a blank coding exercise.

## Stack

- Java 21
- Spring Boot
- Maven
- MySQL + Flyway
- Redis
- Kafka
- Docker Compose
- JUnit / Spring Boot Test / Testcontainers dependencies
- Actuator

## Start dependencies

```bash
docker compose up -d
mvn spring-boot:run
```

Check `GET /actuator/health`.

## First batch

1. **Order feature ownership** — implement `OrderService.create`, validation/transaction behavior, tests.
2. **Payment idempotency** — implement durable same-key semantics and concurrency tests. Spec: `../02-idempotency-retry/01-payment-idempotency.md`.
3. **Inventory concurrency** — prevent overselling; compare optimistic locking, pessimistic locking, and atomic SQL.
4. **Transactional outbox** — reliably move DB events toward Kafka while accepting at-least-once delivery.
5. **Webhook receiver** — signature validation, event dedupe, retry-safe processing.

## Rules

- Do not replace TODOs with an in-memory map just to make tests green.
- DB invariants matter. Identify which invariant is enforced where.
- Add Flyway migrations rather than relying on Hibernate schema generation.
- For every mutation ask: retry? concurrency? crash between steps? multiple pods?
- Write failure tests, not only happy-path tests.

## Suggested workflow

For each lab: read the spec → write/extend tests → implement → deliberately inject failure → explain the design/tradeoffs in that lab's notes.
