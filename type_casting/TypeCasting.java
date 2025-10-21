package type_casting;

public class TypeCasting {
    int totalTicket = 1000;

    int soldTicket = 365;

    double percentage = (double) soldTicket/totalTicket * 100.0d; //int -> double

    public static void main(String[] args) {
        TypeCasting typeCasting = new TypeCasting();
        System.out.println(typeCasting.percentage);
    }
}
