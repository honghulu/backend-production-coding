package io.github.honghulu.labs.inventory;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryRepository inventory;
    public InventoryService(InventoryRepository inventory) { this.inventory = inventory; }

    public void reserve(long skuId, long quantity) {
        // TODO LAB 03: make this safe under concurrent reservations.
        // Implement optimistic locking first, then compare with atomic SQL / pessimistic locking.
        throw new UnsupportedOperationException("TODO: reserve inventory");
    }
}
