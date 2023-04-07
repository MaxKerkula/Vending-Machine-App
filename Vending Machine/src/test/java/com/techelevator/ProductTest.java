package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductTest {

    @Test
    public void dispenseTest() {
        //arrange
        Product dispenseTester = new Product("Glug Glug, Yum!", new BigDecimal(3.55), "Drink", "sounds/drink.wav");
        Balance balance = new Balance();
        balance.deposit(new BigDecimal(5));
        //act
        String actual = dispenseTester.dispense(balance);
        String expected = "Drink | $3.55 | $1.45 Remaining\nGlug Glug, Yum!";
        //assert
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(4, dispenseTester.getStock());
    }
    @Test
    public void toStringTest() {
        Product stringTest = new Product("Glug Glug, Yum!", new BigDecimal(3.55), "Drink", "sounds/drink.wav");
        String actual = stringTest.toString();
        String expected = "$3.55 | Drink | Number Remaining: 5";
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void isSoldOutTrueTest() {
        Product soldOut = new Product("Glug Glug, Yum!", new BigDecimal(3.55), "Drink", "sounds/drink.wav");
        Balance balance = new Balance();
        balance.deposit(new BigDecimal(50));
        Assert.assertFalse(soldOut.isSoldOut());
        soldOut.setStock(0);
        Assert.assertTrue(soldOut.isSoldOut());
    }
    @Test
    public void isSoldOutFalseTest() {
        Product soldOut = new Product("Glug Glug, Yum!", new BigDecimal(3.55), "Drink", "sounds/drink.wav");
        Balance balance = new Balance();
        balance.deposit(new BigDecimal(50));
        Assert.assertFalse(soldOut.isSoldOut());
    }
    @Test
    public void updateNameTest() {
        Product actual = new Product("Glug Glug, Yum!", new BigDecimal(3.55), "Drink", "sounds/drink.wav");
        actual.updateName(10);
        String expected = "Drink     ";
        Assert.assertEquals(expected, actual.getName());
    }
    @Test
    public void updateNameAlreadyLongEnoughTest() {
        Product actual = new Product("Glug Glug, Yum!", new BigDecimal(3.55), "LongNamedBev", "sounds/drink.wav");
        actual.updateName(12);
        String expected = "LongNamedBev";
        Assert.assertEquals(expected, actual.getName());
    }
    @Test
    public void getStockAsStringTest() {
        Product product = new Product("Glug Glug, Yum!", new BigDecimal(3), "Coke", "sounds/drink.wav");
        product.setStock(0);
        String expected = "SOLD OUT";
        String actual = product.getStockAsString();
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void getSoundTest() {
        Beverage bev = new Beverage(new BigDecimal(2), "Coke");
        String expected = "Glug Glug, Yum!";
        String actual = bev.getSound();
        Assert.assertEquals(expected, actual);
    }
}
