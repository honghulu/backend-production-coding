package io.github.honghulu.labs.outbox;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="outbox_events")
public class OutboxEvent {
    @Id private String id;
    @Column(nullable=false) private String aggregateType;
    @Column(nullable=false) private String aggregateId;
    @Column(nullable=false) private String eventType;
    @Lob @Column(nullable=false) private String payload;
    @Column(nullable=false) private Instant createdAt;
    private Instant publishedAt;
    protected OutboxEvent() {}
}
