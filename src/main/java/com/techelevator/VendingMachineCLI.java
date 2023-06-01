package com.techelevator;

import com.techelevator.utility.Console;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

    private static final String CURRENT_BALANCE_FORMATTED = Console.ANSI_GREEN +
                                "~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                                "CURRENT BALANCE: $%s%n" +
                                "~~~~~~~~~~~~~~~~~~~~~~~~~\n" + Console.ANSI_CYAN;
    private static final String CLEAR_SCREEN = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private final Scanner userInput;
    private Balance balance;
    private Log log;

    public VendingMachineCLI() {
        userInput = new Scanner(System.in);
    }

    /**
     * This is the main execution loop for the VendingMachineCLI Orchestrator Class
     */
    public void run() {

        SoundPlayer sound = new SoundPlayer();

        Inventory inventory = new Inventory();
        balance = new Balance();
        log = new Log();

        boolean runMenu = true;
        String[] currentMenu = MAIN_MENU_OPTIONS;

        while (runMenu) {

            displayMenu(currentMenu, balance);
            System.out.print("\nPlease make a selection: ");
            String selection = userInput.nextLine();
            sound.playMenuClick();
            System.out.println();
            try {
                if (selection.equals("4") && currentMenu.equals(MAIN_MENU_OPTIONS)) {
                    SalesReport report = new SalesReport(inventory.getInventory());

                } else {
                    int selectionIndex = Integer.parseInt(selection) - 1;
                    String menuOption = currentMenu[selectionIndex];

                    System.out.println(CLEAR_SCREEN);

                    if (menuOption.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                        //DISPLAY INVENTORY
                        System.out.println(Console.ANSI_GREEN);
                        System.out.println(inventory);
                        System.out.println(Console.ANSI_RESET);
                    } else if (menuOption.equals(MAIN_MENU_OPTION_PURCHASE)) {
                        //GO TO PURCHASE MENU
                        currentMenu = PURCHASE_MENU_OPTIONS; //Switch to Purchase Menu execution loop
                    } else if (menuOption.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                        //FINISH TRANSACTION/GET CHANGE
                        System.out.println(Console.ANSI_GREEN + balance.getChange(log));
                        currentMenu = MAIN_MENU_OPTIONS; //Return to Main Menu execution loop
                    } else if (menuOption.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                        //FEEDING MONEY
                        while (true) {
                            System.out.printf(CURRENT_BALANCE_FORMATTED, balance.getBalance());
                            System.out.println("~~~~~~" + Console.ANSI_RESET + "FEEDING MONEY" + Console.ANSI_CYAN + "~~~~~~\n" +
                                    "Please enter whole dollar amount "+ Console.ANSI_ORANGE +"(ex: 2.00 or 2)\n" + Console.ANSI_CYAN +
                                    "Press '"+ Console.ANSI_ORANGE +"Q" + Console.ANSI_CYAN + "' to return to Purchase Menu. Press Enter to confirm");
                            String fedMoney = userInput.nextLine();
                            sound.playMenuClick();
                            if (fedMoney.equalsIgnoreCase("q")) {
                                System.out.println(CLEAR_SCREEN);
                                break;
                            }
                            feedMoney(fedMoney);
                            sound.playKaching();
                        }
                    } else if (menuOption.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                        //SELECT A PRODUCT
                        System.out.println(Console.ANSI_GREEN + inventory + Console.ANSI_CYAN);
                        System.out.println("~~~"+ Console.ANSI_RESET +"SELECTING PRODUCT"+ Console.ANSI_CYAN +"~~~\n"
                                + Console.ANSI_RESET + "Enter an ID (ex. A4)");
                        System.out.printf(CURRENT_BALANCE_FORMATTED, balance.getBalance());
                        String selectedProduct = userInput.nextLine();
                        sound.playMenuClick();
                        System.out.println(CLEAR_SCREEN);
                        System.out.println(inventory.selectProduct(selectedProduct, balance, log));
                    } else if (menuOption.equals(MAIN_MENU_OPTION_EXIT)) {
                        //EXIT
                        runMenu = false; //Terminate While Loop
                        System.out.println("Good Bye!");
                    }
                }
            } catch (InvalidEntryException e) {
                sound.playBuzz();
                System.out.println(Console.ANSI_RED);
                System.out.println(e.getMessage());
                System.out.println(Console.ANSI_RESET);
            } catch (NumberFormatException e) {
                sound.playBuzz();
                System.out.println(Console.ANSI_RED);
                System.out.println("Invalid Entry! Please use whole integers");
                System.out.println(Console.ANSI_RESET);
            } catch (Exception ex) {
                sound.playBuzz();
                System.out.println(Console.ANSI_RED);
                System.out.println(Console.fillText("-", 24 + selection.length()));
                System.out.printf("'%s' Is Not a Valid Option%n", selection);
                System.out.println(Console.fillText("-", 24 + selection.length()));
                System.out.println(Console.ANSI_RESET);
            }
        }
    }

    private void displayMenu(String[] menu, Balance balance) {

        System.out.println(Console.ANSI_CYAN + "\n********************************" + Console.ANSI_RESET);
        for (int i = 0; i < menu.length; i++) {
            if (!menu[i].startsWith("*")) {
                System.out.printf("%1$s) %2$s\n", i + 1, menu[i]);
            }
        }
        System.out.println(Console.ANSI_CYAN + "********************************");
        if (menu.equals(PURCHASE_MENU_OPTIONS)) {
            System.out.printf(CURRENT_BALANCE_FORMATTED, balance.getBalance());
        }
    }

    /**
     * the public static void main is the core method of the program
     * allowing it to be executable and calls all other methods. In VendingMachineCLI
     * it is used to create an instance of the class so that the public void run()
     * method can be called and CLI instance variables can be used in a natural
     * OOP way.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    public void feedMoney(String input) throws InvalidEntryException, NumberFormatException {
        if (Double.parseDouble(input) % 1 == 0) { //if whole number
            BigDecimal amountToFeed = new BigDecimal(input);
            balance.deposit(amountToFeed);
            //Generate transaction log
            log.logTransaction("FEED MONEY:", amountToFeed, balance.getBalance());
        } else {
            throw new InvalidEntryException("Invalid Amount: Please use whole numbers");
        }
    }
}
