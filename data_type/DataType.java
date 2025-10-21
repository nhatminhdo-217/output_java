package data_type;

public class DataType {
    String name = "Minh";
    int billId = 1;
    double totalBills = 105.3;
    int sale = 10;
    char currency = '$';

    double finalPrice() {
        return totalBills - (totalBills/sale);
    }
    public static void main(String[] args) {
        DataType type = new DataType();
        System.out.println("Name: " + type.name);
        System.out.println("Id: " + type.billId);
        System.out.println("Final price: " + type.finalPrice() + type.currency);
    }
}
