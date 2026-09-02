package io.github.honghulu.labs.order;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private Long customerId;
    @Column(nullable = false) private Long amountCents;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private OrderStatus status;

    protected OrderEntity() {}
    public OrderEntity(Long customerId, Long amountCents) { this.customerId = customerId; this.amountCents = amountCents; this.status = OrderStatus.CREATED; }
    public Long getId() { return id; }
    public Long getCustomerId() { return customerId; }
    public Long getAmountCents() { return amountCents; }
    public OrderStatus getStatus() { return status; }
}
