package com.techelevator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {
    private Inventory inventory;
    private Balance balance;
    private Log log;

    @Before
    public void setup() {
        inventory = new Inventory();
        balance = new Balance();
        log = new Log();
    }

    @Test
    public void testSelectProduct_InvalidId_Failure() {
        balance.deposit(new BigDecimal("5.00"));
        try {
            inventory.selectProduct("Z9", balance, log);
        } catch (InvalidEntryException e) {
            Assert.assertEquals("Invalid ID!", e.getMessage());
        }
    }

    @Test
    public void testSelectProduct_InsufficientFunds_Failure() {
        try {
            inventory.selectProduct("A1", balance, log);
        } catch (InvalidEntryException e) {
            Assert.assertEquals("Insufficient Funds!", e.getMessage());
        }
    }

    @Test
    public void testSelectProduct_SoldOut_Failure() {
        //arrange
        Product product = inventory.getProductAtKey("A1");
        product.setStock(0);
        try {
            inventory.selectProduct("A1", balance, log);
        } catch (InvalidEntryException e) {
            Assert.assertEquals("Sold Out!", e.getMessage());
        }
    }


    @Test
    public void makeNewProductTest() {
        Inventory inv = new Inventory();
        String[] input = {"A1","Drink","3.00","Beverage"};
        Product actual = inv.makeNewProduct(input);
        Beverage expected = new Beverage(new BigDecimal(3), "Drink");
        Assert.assertEquals(expected.toString(), actual.toString());

        String[] inputGum = {"D1","Big Red","2.00","Gum"};
        Product actualGum = inv.makeNewProduct(inputGum);
        Gum expectedGum = new Gum(new BigDecimal(2), "Big Red");
        Assert.assertEquals(expectedGum.toString(), actualGum.toString());
    }
    @Test
    public void getProductAtKeyForValidKey()  {
        Inventory inv = new Inventory();
        Product actual = inv.getProductAtKey("D4");
        Product expected = new Gum(new BigDecimal(.75), "Triplemint");
        Assert.assertEquals(expected.toString(), actual.toString());
    }
    @Test
    public void getProductAtKeyForInvalidKey() {
        Inventory inv = new Inventory();
        Product actual = inv.getProductAtKey("E1");
        Assert.assertEquals(null, actual);
    }
    @Test
    public void updateNameLengthTest() {
        Inventory inv = new Inventory();
        int expectedNameLength = 20;
        inv.updateNameLength(expectedNameLength);
        int actualNameLength = inv.getProductAtKey("A1").getName().length();
        Assert.assertEquals(expectedNameLength, actualNameLength);
    }
    @Test
    public void selectProductValidTest() throws InvalidEntryException {
        Inventory inv = new Inventory();
        Balance bal = new Balance(10);
        String actual = inv.selectProduct("D2", bal, new Log());
        String expected = "Little League Chew | $0.95 | $9.05 Remaining\nChew Chew, Yum!";
        Assert.assertEquals(expected, actual);
    }
    //TODO toString Testing and displayInventory testing
    //toString and displayInventory appear to be the same???
    @Test
    public void toStringTest() {
        Inventory inv = new Inventory();
        inv.getProductAtKey("A1").setStock(3);
        inv.getProductAtKey("A2").setStock(0);
        String expected =
                "A1 | $3.05 | Potato Crisps      | Number Remaining: 3\n" +
                "A2 | $1.45 | Stackers           | Number Remaining: SOLD OUT\n" +
                "A3 | $2.75 | Grain Waves        | Number Remaining: 5\n" +
                "A4 | $3.65 | Cloud Popcorn      | Number Remaining: 5\n" +
                "B1 | $1.80 | Moonpie            | Number Remaining: 5\n" +
                "B2 | $1.50 | Cowtales           | Number Remaining: 5\n" +
                "B3 | $1.50 | Wonka Bar          | Number Remaining: 5\n" +
                "B4 | $1.75 | Crunchie           | Number Remaining: 5\n" +
                "C1 | $1.25 | Cola               | Number Remaining: 5\n" +
                "C2 | $1.50 | Dr. Salt           | Number Remaining: 5\n" +
                "C3 | $1.50 | Mountain Melter    | Number Remaining: 5\n" +
                "C4 | $1.50 | Heavy              | Number Remaining: 5\n" +
                "D1 | $0.85 | U-Chews            | Number Remaining: 5\n" +
                "D2 | $0.95 | Little League Chew | Number Remaining: 5\n" +
                "D3 | $0.75 | Chiclets           | Number Remaining: 5\n" +
                "D4 | $0.75 | Triplemint         | Number Remaining: 5\n";
        String actual = inv.toString();
        Assert.assertEquals(expected, actual);
    }

}