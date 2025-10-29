package lamda_expression.method_references;

public class App {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;

        int result = doAction(a, b, MathUtils::sum);
        System.out.println(result);

        MathUtils utils = new MathUtils();

        int nhanResult = doAction(a, b, utils::nhan);
    }

    public static int doAction(int a, int b, ExecutionFunction func) {
        return func.execute(a, b);
    }
    
}

@FunctionalInterface
interface ExecutionFunction {
    public int execute(int a, int b);
}

class MathUtils {
    public static int sum(int a, int b) {
        return a + b;
    }

    public static int minus(int a, int b) {
        return a - b;
    }

    public int nhan(int a, int b) {
        return a * b;
    }
}
