package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SalesReport {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("_MM-dd-yyyy_hh-mm-ss");
    private File dataFile;
    private String pathName = "SalesReports\\SalesReport.txt";
    private BigDecimal totalSales = new BigDecimal(0);

    public SalesReport(Map<String, Product> inventory) throws IOException {
        //String pathName = String.format("SalesReports %s%t%s", DATE_TIME_FORMAT,"SalesReport.txt";
        dataFile = new File(makeNewPath());
        dataFile.createNewFile();
        try(PrintWriter dataOutput = new PrintWriter(new FileOutputStream(dataFile, true))){
            //for each product in inventory
            for(Map.Entry<String, Product> entry: inventory.entrySet()) {
                dataOutput.println(getNewLine(entry.getValue()));
            }
            dataOutput.println(String.format("\n**TOTAL SALES** $%s", totalSales));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String makeNewPath() {
        pathName = pathName.substring(0, pathName.length() - 4) + LocalDateTime.now().format(DATE_TIME_FORMAT) + ".txt";
        return pathName;
    }
    public String getNewLine(Product item) {
        String formattedString = item.getName();
        int numberSold = 5 - item.getStock();
        if(numberSold > 0) {
            totalSales = totalSales.add(item.getPrice().multiply(new BigDecimal(numberSold)));
        }
        return String.format("%s|%s", item.getName(), numberSold);
    }
}
