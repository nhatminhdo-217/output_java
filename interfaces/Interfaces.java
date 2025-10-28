package interfaces;

public interface Interfaces {
    int a = 10; //Allowed but constant
    int b = 20;

    void sayHello(); //scope is auto public
    // protected void sayGoodbye(); //only allow public, static

    default void sayGoodbye() { //Allow default and static concrete method
        System.out.println("Say goodbye from iterface");
    }

    static void printHello() {
        System.out.println("Hello from interface");
    }
}

class Other implements Interfaces{

    @Override
    public void sayHello() {
        System.out.println("Hello from other class");
    }

    @Override
    public void sayGoodbye() {
        System.out.println("Say goodbye from other class");
    }

    //printHello() //access denied

}
