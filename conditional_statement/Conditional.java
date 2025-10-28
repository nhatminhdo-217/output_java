package conditional_statement;

import java.util.Scanner;

public class Conditional {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean userStatus = true;

        //if else
        System.out.print("User input (1-3): ");
        int input = sc.nextInt();

        //switch case
        switch (input) {
            case 1:
                System.out.println("User name: Do Nhat Minh");
                break;
            case 2:
                userStatus = false;
                System.out.println("Change user status to false");
                break;
            case 3:
                String message = userStatus ? "Please stay" : "Goodbye";
                System.out.println(message);
                System.exit(0);
                break;
        
            default:
                break;
        }
        //tenary
    }
}
