package com.techelevator;

import java.math.BigDecimal;

public class Beverage extends Product {
    public Beverage(BigDecimal price, String name) {
        super("Glug Glug, Yum!", price, name, "sounds/drink.wav");
    }
}



