package inheritance;

public class Animal {
    private int leg;
    private boolean isVegetable;
    private String gender;

    public Animal() {};

    public Animal(int leg, boolean isVegetable, String gender) {
        this.leg = leg;
        this.isVegetable = isVegetable;
        this.gender = gender;
    }

    public int getLeg() {
        return leg;
    }
    public void setLeg(int leg) {
        this.leg = leg;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getVegetable(){
        return isVegetable;
    }
    public void setVegetable(boolean isVegetable) {
        this.isVegetable = isVegetable;
    }

    public void makeSound(){
        System.out.println("sound");
    }
}
