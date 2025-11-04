package com.example;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;

public class App {
    
    public static void main(String[] args) {
        try (CSVReader reader = new CSVReader(new FileReader("library_management_system/src/main/java/data/student.csv"))) {
            List<String[]> rows = reader.readAll();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                String id = row[0];
                String name = row[1];
                String phone = row[2];

                System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
