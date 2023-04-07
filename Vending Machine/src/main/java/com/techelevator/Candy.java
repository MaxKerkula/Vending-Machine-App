package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Product {

    public Candy(BigDecimal price, String name) {
        super("Munch Munch, Yum!", price, name, "sounds/candy.wav");
    }
}
