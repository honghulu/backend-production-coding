package io.github.honghulu.labs.payment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {}
