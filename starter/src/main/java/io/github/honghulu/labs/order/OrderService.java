package io.github.honghulu.labs.order;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orders;
    public OrderService(OrderRepository orders) { this.orders = orders; }

    public OrderEntity create(long customerId, long amountCents) {
        // TODO LAB 01: define validation and transaction semantics.
        throw new UnsupportedOperationException("TODO: implement create order");
    }
}
