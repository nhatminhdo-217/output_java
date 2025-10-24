package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Files {
    public static void main(String[] args) {

        //Create file
        File myFile = new File("/home/nhatminh/workspace/java_project/output_java/files/text.txt");
        try {
            boolean success = myFile.createNewFile();
            System.out.println(success);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        //Write into file
        try(FileWriter writer = new FileWriter("/home/nhatminh/workspace/java_project/output_java/files/text.txt")) {
            writer.write("This is my text");
            System.out.println("Write into text successful");
        } catch (IOException e) {
            System.out.println("File Writer: " + e.getMessage());
        }

        //Read file
        try (Scanner sc = new Scanner(myFile)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            System.out.println("Done!!!");
        } catch (FileNotFoundException e) {
            System.out.println("Read file: " + e.getMessage());
        }
    }
}