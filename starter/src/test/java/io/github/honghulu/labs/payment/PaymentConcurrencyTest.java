package io.github.honghulu.labs.payment;

import org.junit.jupiter.api.Test;

class PaymentConcurrencyTest {
    @Test void concurrentSameKeyCreatesOneLogicalPayment() throws Exception {
        // TODO LAB 02: use an ExecutorService + barrier/latch to fire concurrent requests.
        // Assert one durable business effect, not merely equal HTTP responses.
    }
}
