package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    private File dataFile = new File("Log.txt");

    public void logTransaction(String process, BigDecimal transactionAmount, BigDecimal remainingBalance) {
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(dataFile, true))) {
            String formattedLog  = String.format("%s $%.2f $%.2f", process, transactionAmount, remainingBalance);
            formattedLog += (process.equals("GIVE CHANGE:")) ? "\n" : "";
            dataOutput.println(LocalDateTime.now().format(DATE_TIME_FORMAT) + " " + formattedLog);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
