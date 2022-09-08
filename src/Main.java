import java.io.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    private static Basket basket;

    private static Product[] products = {
            new Product("Молоко", 50),
            new Product("Хлеб", 14),
            new Product("Гречневая крупа", 80),
    };

    public static final String SAVE_FILE_NAME = "basket.bin";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        init();

        scanner = new Scanner(System.in);

        showAllProducts();

        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");

            String input = scanner.nextLine();

            if (input.equals("end")) {
                break;
            }

            String[] strNumbers = input.split(" ");

            if (strNumbers.length >= 2) {
                int productIndex = Integer.parseInt(strNumbers[0]);
                int productsAmount = Integer.parseInt(strNumbers[1]);

                basket.addToCart(productIndex - 1, productsAmount);
                basket.saveBin(new File(SAVE_FILE_NAME));
            }
        }

        basket.printCart();
    }

    public static void init() throws IOException, ClassNotFoundException {
        File f = new File(SAVE_FILE_NAME);

        if(f.exists()){
            basket = Basket.loadFromBinFile(f);
        } else {
            basket = new Basket(products);
            basket.saveBin(f);
        }
    }

    public static void showAllProducts() {
        System.out.println("Список возможных товаров для покупки");

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i].toString());
        }
    }
}
