package com.thehecklers.fsrjavacoffeeservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private final CoffeeService service;

    public CoffeeController(CoffeeService service) {
        this.service = service;
    }

    @GetMapping
    Flux<Coffee> all() {
        return service.getAllCoffees();
    }

    @GetMapping("/{id}")
    Mono<Coffee> byId(@PathVariable String id) {
        return service.getCoffeeById(id);
    }

    @GetMapping(value = "/{id}/orders", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<CoffeeOrder> orders(@PathVariable String id) {
        return service.getOrdersForCoffeeById(id);
    }
}
