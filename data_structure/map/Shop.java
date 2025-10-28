package data_structure.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class Shop {
    private Map<Integer, Product> products = new LinkedHashMap<>();

    public Shop() {
        products.put(1, new Product(1, "Laptop", 1000, 5, 10));
        products.put(2, new Product(2, "Tablet", 300, 15, 20));
        products.put(3, new Product(3, "Smartphone", 1500, 0, 20));
        products.put(4, new Product(4, "Headphones", 100, 0, 10));
        products.put(5, new Product(5, "Keyboard", 250, 3, 3));
    }

    public void viewProducts() {
        System.out.println("========== Products ==========");
        for (Product p : products.values()) {
            System.out.println(p);
        }
    }

    public Product getProductById(int id) {
        for (Product p : products.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new NonExistProductException("Don't have product with id: " + id);
    }

}