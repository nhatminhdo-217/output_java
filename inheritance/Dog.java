package inheritance;

public class Dog extends Animal{
    private String name;
    private int age;
    private String breed;

    public Dog(){};

    public Dog(String gender, String name, int age, String breed) {
        super(4, false, gender); //call constructor of superclass
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Woof Woof");
    }

    @Override
    public String toString() {
        return "This is " + name + " - " + breed + (getGender().equalsIgnoreCase("Male") ? ". He's " : ". She's ") + age + " years old.";
    }

    public static void main(String[] args) {
        Dog dog = new Dog("female", "Nixon", 5, "Pitbull");
        System.out.println(dog);
        dog.makeSound();
    }
}