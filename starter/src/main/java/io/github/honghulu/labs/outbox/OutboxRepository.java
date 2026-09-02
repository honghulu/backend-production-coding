package io.github.honghulu.labs.outbox;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OutboxRepository extends JpaRepository<OutboxEvent, String> {}
