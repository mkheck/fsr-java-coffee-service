package com.thehecklers.fsrjavacoffeeservice;

import java.time.Instant;

public class CoffeeOrder {
    private final String coffeeId;
    private final Instant whenOrdered;

    public CoffeeOrder(String coffeeId, Instant whenOrdered) {
        this.coffeeId = coffeeId;
        this.whenOrdered = whenOrdered;
    }

    public String getCoffeeId() {
        return coffeeId;
    }

    public Instant getWhenOrdered() {
        return whenOrdered;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
                "coffeeId='" + coffeeId + '\'' +
                ", whenOrdered=" + whenOrdered +
                '}';
    }
}
