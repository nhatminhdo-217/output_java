package io_stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Stream {
    public static void main(String[] args) {
        try (InputStream inputStream = new FileInputStream("files/text.txt");
             OutputStream outputStream = new FileOutputStream("io_stream/output.txt")) {
            byte[] byteArr = new byte[1024];
            int data;

            while ((data = inputStream.read(byteArr)) != -1) {
                outputStream.write(byteArr, 0, data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot found file");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
