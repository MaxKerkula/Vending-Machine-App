# Vending Machine

## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Features](#features)
5. [Usage](#usage)
6. [Troubleshooting](#troubleshooting)

## General Info
This is a Java-based CLI program that simulates a vending machine. It allows users to view available products, feed money into the machine, select a product to purchase, and receive change.

## Technologies
The project is created with:
* Java
* OOP
* File I/O
* JUnit
* IntelliJ

## Installation Steps
To install the Vending-Machine-App, follow these steps:

1. Download and install IntelliJ IDEA if you haven't done so already. You can download it from the official JetBrains website. The free Community version will suffice for running this application.

2. Open IntelliJ IDEA, on the Welcome screen, click on "Get from VCS".

3. In the "URL" field, paste the URL of the GitHub repository and click "Clone".

4. Once the project is cloned and loaded, open the Project window by going to "View" -> "Tool Windows" -> "Project" and navigate to the `VendingMachineCLI.java` file located under `src/com/techelevator`.

5. Right-click on the `VendingMachineCLI.java` file in the Project window and select "Run 'VendingMachineCLI.main()'" from the context menu.

6. The program will now execute in IntelliJ's built-in console.

If the project has any dependencies (like external libraries or modules), they should be managed by IntelliJ automatically. However, if there are any issues with dependencies or project setup, you may need to manually resolve them in IntelliJ's Project Structure window (File -> Project Structure).


## Features
* Product Listing: Users can view available products in the vending machine.
* Money Feeding: Users can feed money into the machine in whole dollar amounts.
* Product Selection: Users can select a product to purchase.
* Transaction Logging: All transactions are logged to prevent theft.

## Usage
To use the Vending Machine program, follow these steps:

  1. Run the program. This will present you with a main menu, where you can choose to display items, purchase items, or exit the program.
  2. To view available items, select the "Display Vending Machine Items" option. This will list all items available for purchase.
  3. To purchase an item, select the "Purchase" option. This will present you with another menu, where you can choose to feed money, select a product, or finish the transaction.
  4. To feed money into the machine, select the "Feed Money" option and follow the prompts.
  5. To select a product, choose the "Select Product" option, and follow the prompts.
  6. Once you have selected your product and paid, you can choose the "Finish Transaction" option to complete your purchase and receive your change.

## Troubleshooting
If you encounter any issues while using the Vending Machine program, try the following troubleshooting tips:

  * Ensure you have the latest version of Java installed.
  * Check for error messages in the console.
  * If all else fails, try restarting the application.
