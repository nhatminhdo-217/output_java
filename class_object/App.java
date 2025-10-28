package class_object;

public class App { //This is class
    int a = 10; // state

    void sayHello() { // behaviour
        System.out.println("Hello from App");
    }

    public App() {}; //This is default constructor
    public App(int a) { //This is also constructor
        this.a = a;
    }

    public static void main(String[] args) {
        App app = new App(); //app is an object (instance) of class App

        System.out.println(app.a); //An object have status of class
        app.sayHello(); //And object have behavior of class
    }
}

class InnerApp { //This is also class

}