package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Inventory {
    private final Map<String, Product> inventory;

    SoundPlayer sound = new SoundPlayer();

    public Inventory() {
        inventory = new TreeMap<>();
        int maxLength = 0;
        //Initialize inventory from vendingmachine.csv
        File inventoryFile = new File("vendingmachine.csv");
        try (Scanner scanner = new Scanner(inventoryFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //[0] = SlotID
                //[1] = name
                //[2] = price
                //[3] = itemType
                String[] product = line.split("\\|");
                inventory.put(product[0], makeNewProduct(product));
//              maxLength = (product[1].length() > maxLength) ? product[1].length() : maxLength; shortened to
                maxLength = Math.max(product[1].length(), maxLength);
            }
            updateNameLength(maxLength);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
    public void updateNameLength(int maxLength) {
        for (Map.Entry<String, Product> entry : inventory.entrySet()) {
            entry.getValue().updateName(maxLength);
        }
    }

    public Map<String, Product> getInventory() {
        return inventory;
    }

    public Product makeNewProduct(String[] productArray) {
        String itemType = productArray[3];
        BigDecimal price = new BigDecimal(productArray[2]);
        Product newProduct;

        switch (itemType) {
            case "Drink":
                newProduct = new Beverage(price, productArray[1]);
                break;
            case "Chip":
                newProduct = new Chip(price, productArray[1]);
                break;
            case "Candy":
                newProduct = new Candy(price, productArray[1]);
                break;
            default:  //GUM
                newProduct = new Gum(price, productArray[1]);
                break;
        }
        return newProduct;
    }

    @Override
    public String toString() {
        String inventoryDisplay = "";
        for (Map.Entry<String, Product> entry : inventory.entrySet()) {
            inventoryDisplay += entry.getKey() + " | " + entry.getValue().toString() + "\n";
        }
        return inventoryDisplay;
    }

    public Product getProductAtKey(String selectedKey) {
        Product selectedProduct = null;
        for (Map.Entry<String, Product> entry : inventory.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(selectedKey)) {
                selectedProduct = entry.getValue();
            }
        }
        return selectedProduct;
    }

    public String selectProduct(String selectedKey, Balance balance, Log log) throws InvalidEntryException{
        String result = "";
        Product selectedProduct = getProductAtKey(selectedKey);
        if(selectedProduct == null) {
            //result = "Invalid Entry!";
            throw new InvalidEntryException("Invalid ID!");
        } else if (!selectedProduct.isSoldOut()) {
            if (balance.hasEnoughMoney(selectedProduct.getPrice())) {
                result = selectedProduct.dispense(balance);
                sound.playVendingMachine();
                sound.playProductSound(selectedProduct.getSoundFilePath());
                log.logTransaction(selectedProduct.getName().trim() + " " + selectedKey, selectedProduct.getPrice(), balance.getBalance());
            } else {
                //result = "Insufficient Funds!";
                throw new InvalidEntryException("Insufficient Funds!");
            }
        } else {
            //result = "Sold Out!";
                sound.playWompWomp();

            throw new InvalidEntryException("Sold Out!");
        }
        return result;
    }
}