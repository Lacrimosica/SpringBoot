package com.lacrimosica.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class OrderController {

    private final OrderRepository orderRepository;

    OrderController(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    ResponseEntity<List<Order>> all() {
        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.findAll());
    }

    @GetMapping("/orders/{id}")
    ResponseEntity<Order> one(@PathVariable Long id) {

        Order order = orderRepository.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping("/orders")
    ResponseEntity<Order> newOrder(@RequestBody Order order) {

        order.setStatus(OrderStatus.IN_PROGRESS);
        Order newOrder = orderRepository.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @DeleteMapping("/orders/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));


        if (order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.CANCELLED);
            return ResponseEntity.status(HttpStatus.OK).body(orderRepository.save(order));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("Order cannot be cancelled if it is in " + order.getStatus() + "status");
    }

    @PutMapping("/orders/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        if (order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.COMPLETED);
            return ResponseEntity.status(HttpStatus.OK).body(orderRepository.save(order));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("Order cannot be completed if it is in " + order.getStatus() + " status");
    }
}