CREATE TABLE inventory (
  sku_id BIGINT PRIMARY KEY,
  available BIGINT NOT NULL,
  version BIGINT NOT NULL DEFAULT 0,
  CHECK (available >= 0)
);
INSERT INTO inventory(sku_id, available, version) VALUES (1001, 10, 0);
