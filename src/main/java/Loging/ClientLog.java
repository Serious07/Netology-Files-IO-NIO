package Loging;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    private static final String firstRow = "productNum,amount";
    private List<ProductPair> loggedData = new ArrayList();

    public void log(int productNum, int amount){
        loggedData.add(new ProductPair(productNum, amount));
    }

    public void exportAsCSV(File txtFile){
        try (CSVWriter writer = new CSVWriter(new FileWriter(txtFile.getName()))) {
            writer.writeNext(firstRow.split(","));

            for(ProductPair productPair : loggedData){
                writer.writeNext(productPair.toString().split(","));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
