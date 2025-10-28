package data_structure.map;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public boolean addToCart(Product product, int quantity) {
        if (product != null) {
            if (items.containsKey(product)) {

                items.put(product, getProductQuantity(product, quantity, true));
            } else {

                items.put(product, getProductQuantity(product, quantity, false));

            }

            return true;
        }

        throw new OutOfQuantityException("Out of stock. Try later!");
    }

    public boolean removeProduct(Product product) {
        if (product != null) {
            items.remove(product);
            return true;
        }

        throw new NonExistProductException("Product do not exist");
    }

    public void viewCart(){
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Your cart:");
        int totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            System.out.println("- " + product.getName() + " x " + quantity + " = " + getFinalPrice(product.getPrice(), quantity, product.getDiscount()));
            totalPrice += getFinalPrice(product.getPrice(), quantity, product.getDiscount());
        }

        System.out.println("Total: " + totalPrice);
    }

    public boolean clearCart() {
        if (items != null) {
            items.clear();
            return true;
        }
        return false;
    }

    private Integer getProductQuantity(Product product, int quantity, boolean update) {

        int cartQuantity = update ? (items.get(product) + quantity) : quantity;

        if (cartQuantity > product.getQuantity()) {
            throw new OutOfQuantityException("Out of product. Please try again!");
        }

        return cartQuantity;
    }

    private Integer getFinalPrice(double price, int quantity, int discount) {
        Integer afterDiscount = (int) (price - ((price*discount)/100));

        return afterDiscount*quantity;
    }
}
