package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Product{

    public Chip(BigDecimal price, String name) {
        super("Crunch Crunch, Yum!", price, name,"sounds/chip.wav");
    }
}
