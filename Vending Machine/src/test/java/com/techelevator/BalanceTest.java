package com.techelevator;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;
import org.junit.Before;


public class BalanceTest {
    private Balance balance;

    @Before
    public void setup() {
        balance = new Balance();
    }

    @Test
    public void testHasEnoughMoney_false() {
        BigDecimal itemPrice = new BigDecimal("1.00");
        assertFalse(balance.hasEnoughMoney(itemPrice));
    }

    @Test
    public void testHasEnoughMoney_true() {
        BigDecimal depositAmount = new BigDecimal("1.00");
        balance.deposit(depositAmount);
        BigDecimal itemPrice = new BigDecimal("0.50");
        assertTrue(balance.hasEnoughMoney(itemPrice));
    }

    @Test
    public void testGetBalance() {
        BigDecimal depositAmount = new BigDecimal("1.00");
        balance.deposit(depositAmount);
        assertEquals(new BigDecimal("1.00"), balance.getBalance());
    }

    @Test
    public void testDeposit() {
        BigDecimal depositAmount = new BigDecimal("1.00");
        balance.deposit(depositAmount);
        assertEquals(new BigDecimal("1.00"), balance.getBalance());
    }

    @Test
    public void testWithdraw() {
        BigDecimal depositAmount = new BigDecimal("1.00");
        balance.deposit(depositAmount);
        BigDecimal withdrawAmount = new BigDecimal("0.50");
        balance.withdraw(withdrawAmount);
        assertEquals(new BigDecimal("0.50"), balance.getBalance());
    }

    @Test
    public void testGetChange() {
        BigDecimal depositAmount = new BigDecimal(3);
        balance.deposit(depositAmount);
        balance.withdraw(new BigDecimal(1.55));
        Log log = new Log();
        String expectedChange = "Quarters: 5 ($1.25), Dimes: 1 ($0.10), Nickels: 1 ($0.05), Total: $1.40";
        assertEquals(expectedChange, balance.getChange(log));
    }
}
