package com.techelevator;

import java.math.BigDecimal;

public class Product implements Eatable {
    private String sound;
    private BigDecimal price;
    private String name;
    private int stock;
    private String soundFilePath;

    public void setStock(int stock) {this.stock = stock;}

    public Product(){}

    public Product(String sound, BigDecimal price, String name, String soundFilePath) {
        this.sound = sound;
        this.price = price;
        this.name = name;
        this.stock = 5;
        this.soundFilePath = soundFilePath;
    }

    public String getSoundFilePath() {
        return soundFilePath;
    }

    public String dispense(Balance balance) {
        this.stock--;

        return String.format("%s | $%.2f | $%.2f Remaining\n%s", name.trim(), price, balance.withdraw(price), sound);
    }

    @Override
    public String toString() {
        return String.format("$%.2f | %s | Number Remaining: %s", price, name, getStockAsString());
    }

    public String getSound() {
        return sound;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public String getStockAsString() {
        String remainingStock = stock + "";
        if(stock == 0) {
            remainingStock = "SOLD OUT";
        }
        return remainingStock;
    }

    public boolean isSoldOut(){
        return stock == 0;
    }

    public void updateName(int maxLength) {
        while (name.length() < maxLength){
            name += " ";
        }
    }
}
