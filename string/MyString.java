package string;

public class MyString {

    String name;
    int age;

    void greet() {
        System.out.println("Hello everyone!\n" + "I\'m " + name + ". I\'m " + age + " \"next year\"");
    }

    public MyString(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        String myString1 = "hello world";
        String myString2 = "hello world";

        String objString = new String("hello world");
        String objString1 = new String("hello world");

        System.out.println("String literal: " + (myString1 == myString2));
        System.out.println("new String: " + (objString == objString1));

        MyString myString = new MyString("Nhat Minh", 22);
        myString.greet();
    }
}
