package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class SalesReportTest {

    @Test
    public void getLewLineTest() {
        Inventory inv = new Inventory();
        try {
            SalesReport report = new SalesReport(inv.getInventory());
            Product item = new Beverage(new BigDecimal(2.5), "Coke");
            String actual = report.getNewLine(item);
            String expected = "Coke|0";
            Assert.assertEquals(expected, actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
