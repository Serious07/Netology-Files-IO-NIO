import Loging.ClientLog;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    private static Basket basket;
    private static ClientLog clientLog;

    private static Product[] products = {
            new Product("Молоко", 50),
            new Product("Хлеб", 14),
            new Product("Гречневая крупа", 80),
    };

    public static final String SAVE_FILE_NAME = "basket.txt";
    public static final String JSON_SAVE_FILE_NAME = "basket.json";
    private static final String SAVE_LOGS_NAME = "log.csv";

    public static void main(String[] args) throws IOException {
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
                clientLog.log(productIndex, productsAmount);

                //basket.saveTxt(new File(SAVE_FILE_NAME));
                basket.saveJson(new File(JSON_SAVE_FILE_NAME));
            }
        }

        clientLog.exportAsCSV(new File(SAVE_LOGS_NAME));
        basket.printCart();
    }

    public static void init() throws IOException {
        //File f = new File(SAVE_FILE_NAME);
        File f = new File(JSON_SAVE_FILE_NAME);

        clientLog = new ClientLog();

        if(f.exists()){
            //basket = Basket.loadFromTxtFile(f);
            basket = Basket.loadFromJsonFile(f);
        } else {
            basket = new Basket(products);
            //basket.saveTxt(f);
            basket.saveJson(f);
        }
    }

    public static void showAllProducts() {
        System.out.println("Список возможных товаров для покупки");

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i].toString());
        }
    }
}
