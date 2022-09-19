import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class BasketTest {
    private static Product[] products;
    private static Basket basket;

    private static final String saveFileJsonName = "testBasketJson.json";
    private static final String saveFileText = "testBasketText.txt";

    @BeforeAll
    public static void initAll(){
        products = new Product[] {
                new Product("Хлеб", 10),
                new Product("Соль", 30),
                new Product("Сметана,", 40)
        };

        basket = new Basket(products);
    }

    @Test
    @DisplayName("Добавление товаров в корзину")
    public void addToCart(){
        basket.addToCart(0, 10);
        basket.addToCart(1, 20);
        basket.addToCart(2, 30);
        basket.addToCart(0, 20);
        Assertions.assertTrue(basket.amountOfPurchasedProducts.get(0) == 30);
    }

    @Test
    @DisplayName("Сохранение корзины в json файл")
    public void saveJson() throws IOException {
        File jsonFile = new File(saveFileJsonName);

        if(jsonFile.exists()){
            jsonFile.delete();
        }

        basket.saveJson(jsonFile);

        Assertions.assertTrue(jsonFile.exists());
    }

    @Test
    @DisplayName("Сохранение корзины в текстовый файл")
    public void saveTxt() throws IOException {
        File textFile = new File(saveFileText);

        if(textFile.exists()){
            textFile.delete();
        }

        basket.saveTxt(textFile);

        Assertions.assertTrue(textFile.exists());
    }
}
