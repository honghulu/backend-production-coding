package io.github.honghulu.labs.payment;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private Long customerId;
    @Column(nullable = false) private Long amountCents;
    @Column(nullable = false, length = 3) private String currency;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private PaymentStatus status;
    protected PaymentEntity() {}
    public PaymentEntity(Long customerId, Long amountCents, String currency) { this.customerId=customerId; this.amountCents=amountCents; this.currency=currency; this.status=PaymentStatus.PROCESSING; }
    public Long getId(){return id;} public PaymentStatus getStatus(){return status;}
}
