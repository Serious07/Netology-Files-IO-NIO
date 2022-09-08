import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    private static Product[] products = {
            new Product("Молоко", 50),
            new Product("Хлеб", 14),
            new Product("Гречневая крупа", 80),
    };

    private static int[] amountOfPurchasedProducts = new int[products.length];

    public static void main(String[] args) {
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

                amountOfPurchasedProducts[productIndex - 1] += productsAmount;
            }
        }

        showResults();
    }

    public static void init() {
        for (int i = 0; i < amountOfPurchasedProducts.length; i++) {
            amountOfPurchasedProducts[i] = 0;
        }
    }

    public static void showAllProducts() {
        System.out.println("Список возможных товаров для покупки");

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i].toString());
        }
    }

    public static void showResults() {
        System.out.println("Ваша корзина:");

        int sum = 0;

        for (int i = 0; i < amountOfPurchasedProducts.length; i++) {
            if (amountOfPurchasedProducts[i] > 0) {
                System.out.println(products[i].title + " " +
                        amountOfPurchasedProducts[i] + " шт " +
                        products[i].price + " руб/шт " +
                        (amountOfPurchasedProducts[i] * products[i].price) +
                        " руб в сумме");

                sum += amountOfPurchasedProducts[i] * products[i].price;
            }
        }

        System.out.println("Итого " + sum + " руб");
    }
}
