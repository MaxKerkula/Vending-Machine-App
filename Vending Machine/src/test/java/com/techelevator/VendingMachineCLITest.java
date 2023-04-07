package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class VendingMachineCLITest {

    @Test
    public void testFeedMoneyDecimalNumber() {
        VendingMachineCLI cli = new VendingMachineCLI();
        String amountToFeed = "2.5";
        String expected = "Invalid Amount: Please use whole numbers";
        try {
            cli.feedMoney(amountToFeed);
        } catch (InvalidEntryException e) {
            Assert.assertEquals(expected, e.getMessage());
        }
    }
    @Test
    public void testFeedMoneyNotANumber() {
        VendingMachineCLI cli = new VendingMachineCLI();
        String amountToFeed = "gh";
        Assert.assertThrows(NumberFormatException.class, () -> {
            cli.feedMoney(amountToFeed);
        });
    }
}
