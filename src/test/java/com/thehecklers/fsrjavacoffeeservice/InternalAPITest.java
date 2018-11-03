package com.thehecklers.fsrjavacoffeeservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebFluxTest(CoffeeService.class)
public class InternalAPITest {
    @Autowired
    private CoffeeService service;

    @MockBean
    private CoffeeRepository repo;

    Coffee coffee1, coffee2;

    @Before
    public void setUp() throws Exception {
        coffee1 = new Coffee("000-TEST-111", "Tester's Choice");
        coffee2 = new Coffee("000-TEST-222", "Failgers");

        Mockito.when(repo.findAll()).thenReturn(Flux.just(coffee1, coffee2));
        Mockito.when(repo.findById(coffee1.getId())).thenReturn(Mono.just(coffee1));
        Mockito.when(repo.findById(coffee2.getId())).thenReturn(Mono.just(coffee2));
    }

    @Test
    public void getAllCoffees() {
        StepVerifier.withVirtualTime(() -> service.getAllCoffees())
                .expectNext(coffee1)
                .expectNext(coffee2)
                .verifyComplete();
    }

    @Test
    public void getCoffeeById() {
        StepVerifier.withVirtualTime(() -> service.getCoffeeById(coffee1.getId()))
                .expectNext(coffee1)
                .verifyComplete();
    }

    @Test
    public void getOrdersForCoffeeById() {
        StepVerifier.withVirtualTime(() -> service.getOrdersForCoffeeById(coffee2.getId()).take(10))
                .thenAwait(Duration.ofHours(10))
                .expectNextCount(10)
                .verifyComplete();
    }
}