package io.github.honghulu.labs.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository payments;
    public PaymentService(PaymentRepository payments) { this.payments = payments; }

    public PaymentEntity create(String idempotencyKey, long customerId, long amountCents, String currency) {
        // TODO LAB 02: design durable idempotency. Do not solve this with an in-memory map.
        throw new UnsupportedOperationException("TODO: implement idempotent payment creation");
    }
}
