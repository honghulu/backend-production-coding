package io.github.honghulu.labs.outbox;

import org.springframework.stereotype.Component;

@Component
public class OutboxPublisher {
    private final OutboxRepository outbox;
    public OutboxPublisher(OutboxRepository outbox) { this.outbox = outbox; }

    // TODO LAB 04: claim unpublished events, publish to Kafka, then mark published.
    // Think carefully about crash after Kafka publish but before DB update.
    public void publishBatch() { throw new UnsupportedOperationException("TODO: publish outbox batch"); }
}
