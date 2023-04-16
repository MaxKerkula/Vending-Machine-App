package com.techelevator;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal balance;

    public Balance() {
        this.balance = new BigDecimal(0);
    }
    public Balance(int balance) {this.balance = new BigDecimal(balance);}
    //(First.compareTo(Second)
    public boolean hasEnoughMoney(BigDecimal itemPrice) {
        return itemPrice.compareTo(balance) <= 0;
    }
    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal depositAmount) {
        this.balance = this.balance.add(depositAmount);
    }
    public BigDecimal withdraw(BigDecimal withdrawAmount) {
        balance = balance.subtract(withdrawAmount);
        return balance;
    }
    public String getChange(Log log) {
        BigDecimal[] change = new BigDecimal[3];
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");

        change[0] = balance.divideToIntegralValue(quarter).multiply(quarter);
        int numQuarters = balance.divideToIntegralValue(quarter).intValue();
        balance = balance.subtract(change[0]);

        change[1] = balance.divideToIntegralValue(dime).multiply(dime);
        int numDimes = balance.divideToIntegralValue(dime).intValue();
        balance = balance.subtract(change[1]);

        change[2] = balance.divideToIntegralValue(nickel).multiply(nickel);
        int numNickels = balance.divideToIntegralValue(nickel).intValue();
        BigDecimal total = change[0].add(change[1]).add(change[2]);
        log.logTransaction("GIVE CHANGE:", total, balance);

        return String.format("Quarters: %d ($%.2f), Dimes: %d ($%.2f), Nickels: %d ($%.2f), Total: $%.2f",
                numQuarters, change[0], numDimes, change[1], numNickels, change[2], total);
    }
}