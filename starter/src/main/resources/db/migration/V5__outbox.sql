CREATE TABLE outbox_events (
  id VARCHAR(36) PRIMARY KEY,
  aggregate_type VARCHAR(64) NOT NULL,
  aggregate_id VARCHAR(128) NOT NULL,
  event_type VARCHAR(128) NOT NULL,
  payload TEXT NOT NULL,
  created_at TIMESTAMP(6) NOT NULL,
  published_at TIMESTAMP(6) NULL,
  INDEX idx_outbox_unpublished (published_at, created_at)
);
