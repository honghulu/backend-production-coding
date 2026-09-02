CREATE TABLE orders (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  customer_id BIGINT NOT NULL,
  amount_cents BIGINT NOT NULL,
  status VARCHAR(32) NOT NULL,
  created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  INDEX idx_orders_customer_id_id (customer_id, id)
);
