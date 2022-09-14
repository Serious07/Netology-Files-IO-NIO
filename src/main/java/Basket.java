import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Basket implements Serializable {
    public List<Product> products;

    public List<Integer> amountOfPurchasedProducts;

    public Basket(Product[] products){
        this.products = Arrays.asList(products);
        init();
    }

    public Basket(ArrayList<Product> products){
        this.products = new ArrayList<>(products);
        init();
    }

    private void init(){
        amountOfPurchasedProducts = new ArrayList();

        for(int i = 0; i < this.products.size(); i++){
            amountOfPurchasedProducts.add(i, 0);
        }
    }

    public void addToCart(int productNum, int amount){
        amountOfPurchasedProducts.set(productNum, amountOfPurchasedProducts.get(productNum) + amount);
    }

    public void saveJson(File jsonFile) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try(PrintWriter printWriter = new PrintWriter(jsonFile)){
            printWriter.println(gson.toJson(this));
        }
    }

    public static Basket loadFromJsonFile(File textFile) throws IOException{
        Basket result;

        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Integer> amountOfPurchasedProducts = new ArrayList<>();

        String jsonFile = Files.readString(Paths.get(textFile.getPath()), StandardCharsets.UTF_8);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        result = gson.fromJson(jsonFile, Basket.class);

        return result;
    }

    public void saveTxt(File textFile) throws IOException {
        if(textFile.exists()){
            textFile.delete();
        }

        try(PrintWriter printWriter = new PrintWriter(textFile)){
            for(int i = 0; i < products.size(); i++){
                printWriter.println(products.get(i).title + "@" + products.get(i).price + "@" + amountOfPurchasedProducts.get(i));
            }
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException{
        Basket result;
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Integer> amountOfPurchasedProducts = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get(textFile.getName()), StandardCharsets.UTF_8);

        for(String line : lines){
            String[] data = line.split("@");

            if(data.length == 3) {
                String title = data[0];
                int price = Integer.parseInt(data[1]);
                int amount = Integer.parseInt(data[2]);

                products.add(new Product(title, price));
                amountOfPurchasedProducts.add(amount);
            }
        }

        result = new Basket(products);
        result.setAmountOfPurchasedProducts(amountOfPurchasedProducts);

        return result;
    }

    public void setAmountOfPurchasedProducts(List<Integer> amountOfPurchasedProducts) {
        this.amountOfPurchasedProducts = amountOfPurchasedProducts;
    }

    public void printCart(){
        System.out.println("Ваша корзина:");

        int sum = 0;

        for (int i = 0; i < amountOfPurchasedProducts.size(); i++) {
            if (amountOfPurchasedProducts.get(i) > 0) {
                System.out.println(products.get(i).title + " " +
                        amountOfPurchasedProducts.get(i) + " шт " +
                        products.get(i).price + " руб/шт " +
                        (amountOfPurchasedProducts.get(i) * products.get(i).price) +
                        " руб в сумме");

                sum += amountOfPurchasedProducts.get(i) * products.get(i).price;
            }
        }

        System.out.println("Итого " + sum + " руб");
    }
}
