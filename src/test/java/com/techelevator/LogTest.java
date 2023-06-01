package com.techelevator;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class LogTest {
    File dataFile;
    DateTimeFormatter DATE_TIME_FORMAT;
    MathContext mathContext = new MathContext(2);//sets limit of decimal place accuracy to designated number

    @Before
    public void setup() {
        dataFile = new File("Log.txt");
        DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    }

    @Test
    public void logTransactionTestForFileSizeIncrease() {
        Log log = new Log();
        long sizeBeforeWriting = dataFile.length();
        log.logTransaction("FEED MONEY", new BigDecimal(5), new BigDecimal(5));
        long sizeAfterWriting = dataFile.length();
        Assert.assertTrue(sizeBeforeWriting < sizeAfterWriting);
    }

    /*@Test
    public void testLogTransaction() throws IOException {
        Log log1 = new Log();
        *//* Arrange *//*
        BigDecimal transactionAmount = new BigDecimal(5.00).round(mathContext);//sets limit of decimal place to designated number
        BigDecimal remainingBalance = new BigDecimal(10.00).round(mathContext);;

        *//* Act *//*
        log1.logTransaction("PROCESS", transactionAmount, remainingBalance);

        *//* Assert *//*
        assertTrue(dataFile.exists());
        List<String> lines = Files.readAllLines(Paths.get(dataFile.getPath()));
        String expected = LocalDateTime.now().format(DATE_TIME_FORMAT) + " PROCESS $5.00 $10.00";
        assertTrue(lines.get(0).contains(expected));
    }

    @Test
    public void testMultipleTransactions() throws IOException {
        Log log2 = new Log();
        //arrange
        BigDecimal transactionAmount1 = new BigDecimal(4.45); //sends extra decimal places
        BigDecimal remainingBalance1 = new BigDecimal("10.55");//"" specifies string comparison
        BigDecimal transactionAmount2 = new BigDecimal("3.25");
        BigDecimal remainingBalance2 = new BigDecimal("7.30");

        //act
        log2.logTransaction("PROCESS 1", transactionAmount1, remainingBalance1);
        log2.logTransaction("PROCESS 2", transactionAmount2, remainingBalance2);

        //assert
        assertTrue(dataFile.exists());
        List<String> lines = Files.readAllLines(Paths.get(dataFile.getPath()));
        String expected1 = LocalDateTime.now().format(DATE_TIME_FORMAT) + " PROCESS 1 $4.45 $10.55";
        String expected2 = LocalDateTime.now().format(DATE_TIME_FORMAT) + " PROCESS 2 $3.25 $7.30";
        String check1 = lines.get(1);
        String check2 = lines.get(2);
        assertEquals(2, lines.size());
        assertTrue(lines.get(1).equals(expected1));
        assertTrue(lines.get(2).equals(expected2));
    }

    @Test
    public void testEndTransactionSession() throws IOException {
        Log log3 = new Log();
        //arrange
        BigDecimal transactionAmount1 = new BigDecimal(5);
        BigDecimal remainingBalance1 = new BigDecimal(3);

        BigDecimal transactionAmount2 = new BigDecimal(1);
        BigDecimal remainingBalance2 = new BigDecimal(2);

        //act
        log3.logTransaction("GIVE CHANGE:", transactionAmount1, remainingBalance1);
        log3.logTransaction("PROCESS", transactionAmount2, remainingBalance2);
        //assert
        assertTrue(dataFile.exists());
        List<String> lines = Files.readAllLines(Paths.get(dataFile.getPath()));
        String expected1 = LocalDateTime.now().format(DATE_TIME_FORMAT) + "GIVE CHANGE: $5.00 $3.00";
        String expected3 = LocalDateTime.now().format(DATE_TIME_FORMAT) + " PROCESS $1.00 $2.00";
        assertEquals(3, lines.size());
        assertTrue(lines.get(1).equals(expected1));
        assertTrue(lines.get(2).isEmpty());
        assertTrue(lines.get(3).equals(expected3));
    }*/
}
