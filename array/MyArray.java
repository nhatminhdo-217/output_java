package array;

import java.util.Arrays;
import java.util.Scanner;

public class MyArray {

    Scanner sc = new Scanner(System.in);

    void welcome() {
        System.out.println("========== Array Program ==========");
    }

    int arrLength() {
        while (true) {
            try {
                System.out.print("Enter the array length: ");
                return sc.nextInt();
            } catch (Exception e) {
                System.err.println("Wrong format. Enter again");
            }
        }
    }

    void getRandElement(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.round(Math.random() * 10);
        }
        System.out.println("Array has been created");
    }


    int program() {
        while (true) {
            try {
                System.out.println("1. Print");
                System.out.println("2. Update at Index");
                System.out.println("3. Delete at Index");
                System.out.println("4. Exit");
                System.out.println("Choose: ");

                return sc.nextInt();
            } catch (Exception e) {
                System.err.println("Wrong format. Enter again");
            }
        }
    }

    void performChoose(int choose, int[] arr) {
        switch (choose) {
            case 1:
                printArr(arr);
                break;
            case 2:
                updateAtIndex(arr);
                break;
            case 3:
                deleteAtIndex(arr);
                break;
            case 4:
                System.out.println("Goodbye");
                System.exit(0);
                break;
        
            default:
                break;
        }
    }

    private void deleteAtIndex(int[] arr) {
        System.out.print("Enter index: ");
        int i = sc.nextInt();

        arr[i] = 0;
        System.out.println("The array has been updated");
    }

    private void updateAtIndex(int[] arr) {
        System.out.print("Enter index: ");
        int i = sc.nextInt();
        System.out.print("Enter new value: ");
        int newValue = sc.nextInt();

        arr[i] = newValue;
        System.out.println("The array has been updated");
    }

    private void printArr(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        MyArray myArr = new MyArray();
        myArr.run();
    }

    private void run() {
        welcome();
        int[] arr = new int[arrLength()];
        getRandElement(arr);
        while (true) {
            int program = program();
            performChoose(program, arr);
        }
    }
}