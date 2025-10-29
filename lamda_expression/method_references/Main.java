package lamda_expression.method_references;

@FunctionalInterface
interface Flyable {
    void fly();
}

interface Runable {
    void run();

    void runWithSpeed(int speed);
}

//Traditional way to access class or instance method

class Chicken implements Flyable, Runable { 

    @Override
    public void fly() {
        System.out.println("Chicken is flying...");
    }

    @Override
    public void run() {
        System.out.println("Chicken is running...");
    }

    @Override
    public void runWithSpeed(int speed) {
        System.out.println("Chicken is running with speed " + speed);
    }

}

class Airport {
    public static void takeOff() {
        System.out.println("Airplane is flying...");
    }
}

public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        main.tryFlying(new Chicken());
        //main.tryRunning(new Chicken());

        //Lamda
        main.tryFlying(() -> System.out.println("Bird is flying..."));

        //Method reference
        main.tryFlying(Airport::takeOff);
    }

    public void tryFlying(Flyable somethingCanFly) {
        somethingCanFly.fly();
    }

    public void tryRunning(Runable somethingCanRun) {
        somethingCanRun.run();
        somethingCanRun.runWithSpeed(20);
    }
}