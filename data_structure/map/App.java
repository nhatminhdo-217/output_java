package data_structure.map;

import java.util.Scanner;

public class App {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Shop shop = new Shop();
    private Cart cart = new Cart();

    public static void main(String[] args) {
        App app = new App();
        app.menu();
    }

    public void menu() {
        while (true) {
            try {
                System.out.println("========== Shop ==========");
                System.out.println("1. View Products");
                System.out.println("2. Add to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Remove from Cart");
                System.out.println("5. Clear Cart");
                System.out.println("6. Checkout and Exit");
                System.out.print("Choose: ");

                String strChoose = SCANNER.nextLine();
                int choose = Integer.parseInt(strChoose);

                program(choose);

            } catch (NumberFormatException nfe) {
                System.err.println("Wrong input format. Try again!");
            } catch (OutOfQuantityException ooqe) {
                System.err.println("Out of stock. Try later!");
            } catch (NonExistProductException nepe) {
                System.err.println(nepe.getMessage());
            }
        }
    }

    public void program(int choose) {
        switch (choose) {
            case 1:
                shop.viewProducts();
                break;
            case 2:
                shop.viewProducts();
                System.out.print("Enter productID to add: ");
                int id = Integer.parseInt(SCANNER.nextLine());

                Product p = shop.getProductById(id);

                System.out.print("Enter quantity: ");
                int quantity = Integer.parseInt(SCANNER.nextLine());

                if (cart.addToCart(p, quantity)) {
                    System.out.println("Add success");
                }
                break;
            case 3:
                cart.viewCart();
                break;
            case 4:
                cart.viewCart();
                System.out.print("Enter product ID to remove: ");
                int removeId = Integer.parseInt(SCANNER.nextLine());

                Product removeProduct = shop.getProductById(removeId);

                if (cart.removeProduct(removeProduct)) {
                    System.out.println("Remove success");
                }
                break;
            case 5:
                if (cart.clearCart()) {
                    System.out.println("Cart are cleared");
                } else {
                    System.out.println("Cart is empty");
                }
                break;
            case 6:
                cart.viewCart();
                System.out.println("Thanks for shopping");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid option.");
                break;
        }
    }
}
