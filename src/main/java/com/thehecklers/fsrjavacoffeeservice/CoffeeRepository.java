package com.thehecklers.fsrjavacoffeeservice;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CoffeeRepository extends ReactiveCrudRepository<Coffee, String> {
}
