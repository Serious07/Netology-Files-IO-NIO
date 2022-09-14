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

    public static final String defaultXmlFilePath = "shop.xml";

    private static Options options;

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

                // Logging
                if(options.isLogEnabled()) {
                    clientLog.log(productIndex, productsAmount);
                }

                // Save data
                if(options.isSaveEnabled()) {
                    switch (options.getSaveFormat()) {
                        case "text":
                            basket.saveTxt(new File(options.getSaveFileName()));
                            break;
                        case "json":
                            basket.saveJson(new File(options.getSaveFileName()));
                            break;
                    }
                }
            }
        }

        if(options.isLogEnabled()) {
            clientLog.exportAsCSV(new File(options.getLogFileName()));
        }

        basket.printCart();
    }

    public static void init() throws IOException {
        options = new Options(new File(defaultXmlFilePath));

        File f = new File(options.getLoadFileName());

        if(options.isLogEnabled()) {
            clientLog = new ClientLog();
        }

        if(f.exists()){
            if(options.isLoadEnabled()) {
                switch (options.getLoadFormat()) {
                    case "text":
                        basket = Basket.loadFromTxtFile(f);
                        break;
                    case "json":
                        basket = Basket.loadFromJsonFile(f);
                        break;
                }
            } else{
                basket = new Basket(products);
                initSave(f);
            }
        } else {
            basket = new Basket(products);
            initSave(f);
        }
    }

    private static void initSave(File f) throws IOException {
        if(options.isSaveEnabled()){
            switch (options.getSaveFormat()){
                case "text":
                    basket.saveTxt(f);
                    break;
                case "json":
                    basket.saveJson(f);
                    break;
            }
        }
    }

    public static void showAllProducts() {
        System.out.println("Список возможных товаров для покупки");

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i].toString());
        }
    }
}
