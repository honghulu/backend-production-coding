# Exercise 04 — Direct Object Upload State Machine

Design and implement the backend side of a direct-to-object-storage media upload flow.

## Scenario

The client wants to upload a large media file without proxying bytes through your application server.

Typical flow:

```text
Client
  ↓ request upload session
API server
  ↓ create DB record + presigned URL
Client
  ↓ upload bytes directly
Object storage
  ↓
Client / event system
  ↓ finalize
API server
  ↓ verify object + update DB
```

## Requirements

- The server creates an upload record before the upload starts.
- Retrying session creation must not create duplicate logical uploads.
- The API must distinguish `PENDING`, `UPLOADED`, `PROCESSING`, `READY`, and `FAILED` states.
- A client claiming upload completion is not sufficient proof that the object exists.
- Finalization must be idempotent.
- Processing may be asynchronous.

## Design API contracts

At minimum:

```http
POST /uploads
POST /uploads/{id}/complete
GET  /uploads/{id}
```

Decide what each request and response contains.

## State transition rules

Define legal transitions, for example:

```text
PENDING -> UPLOADED -> PROCESSING -> READY
                         |
                         -> FAILED
```

Reject illegal transitions.

## Failure cases to reason about

- DB record created but client never uploads
- upload succeeds but client crashes before `complete`
- client calls `complete` twice
- object storage event arrives twice
- finalization server crashes after DB update
- processing worker retries
- object exists but size/checksum does not match expected metadata

## Required implementation

Implement an in-memory version first, then describe the production persistence strategy.

## Interview follow-ups

- Who generates a presigned URL?
- Does object storage need to call your server directly?
- When should you use client finalize vs storage event notification?
- How do you reconcile stuck `PENDING` records?
- Where do idempotency and state-machine validation differ?
