package io_stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Buffer {
    public static void main(String[] args) {
        int bufferedSize = 8*1024;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("files/text.txt"), bufferedSize);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("io_stream/buffered_output.txt"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                bufferedWriter.write(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
