package io.github.honghulu.labs.payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService service;
    public PaymentController(PaymentService service) { this.service = service; }
    public record CreatePaymentRequest(@NotNull Long customerId, @NotNull @Min(1) Long amountCents, @NotBlank @Size(min=3,max=3) String currency) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentEntity create(@RequestHeader("Idempotency-Key") String key, @Valid @RequestBody CreatePaymentRequest request) {
        return service.create(key, request.customerId(), request.amountCents(), request.currency());
    }
}
