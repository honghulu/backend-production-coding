CREATE TABLE payments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  customer_id BIGINT NOT NULL,
  amount_cents BIGINT NOT NULL,
  currency CHAR(3) NOT NULL,
  status VARCHAR(32) NOT NULL,
  created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
);

-- TODO LAB 02: add the schema/invariant needed for durable idempotency.
