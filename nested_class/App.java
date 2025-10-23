package nested_class;

public class App {
    private int outer = 15;

    static class Nested {
        int inner = 10;

        void sayHello(){
            System.out.println("Hello World");
        }

        void getOuterData() {
            App app = new App();
            System.out.println(app.outer);
        }
    }

    class NonStaticNested{
        int nonStaticData = 1;

        void sayHello() {
            System.out.println("Say hello from non static nested class");
        }
        
        void getOuterData() {
            System.out.println(outer); //This is different from static nested class
        }
    }

    public static void main(String[] args) {
        App.Nested nested = new App.Nested(); //Declare a nested static class

        nested.getOuterData();

        App app = new App();
        App.NonStaticNested nonStaticClass = app.new NonStaticNested();

        nonStaticClass.getOuterData();
        nonStaticClass.sayHello();
    }
}