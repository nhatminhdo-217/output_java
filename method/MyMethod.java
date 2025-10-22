package method;

public class MyMethod {
    int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args) {
        MyMethod myMethod = new MyMethod();

        int n = 10;

        for (int i = 0; i < n; i++) {
            System.out.print(myMethod.fibonacci(i) + " ");
        }
    }
}
