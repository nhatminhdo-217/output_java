package conditional_statement;

public class Loop {
    public static void main(String[] args) {
        
        int a = 0;
        //do while
        do {
            System.out.println(a);
            a++;
            System.out.println(a);
        } while (a < 0); //Still run at least one time before stop because of while condition

        System.out.println("---------------------------------------------------");

        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                continue;
            }
            System.out.println(i);
            if (i == 9) {
                break;
            }
        }
    }
}
