package io.github.honghulu.labs.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) { this.service = service; }

    public record CreateOrderRequest(@NotNull Long customerId, @NotNull @Min(1) Long amountCents) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderEntity create(@Valid @RequestBody CreateOrderRequest request) {
        return service.create(request.customerId(), request.amountCents());
    }
}
