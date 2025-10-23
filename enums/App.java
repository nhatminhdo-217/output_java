package enums;

public enum App {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private String description;

    private App(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        App myApp = App.LOW;
        System.out.println(myApp.description);
    }
}
