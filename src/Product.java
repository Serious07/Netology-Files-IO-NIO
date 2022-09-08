public class Product {
    public String title;
    public int price;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String toString() {
        return title + " " + price + " руб/шт";
    }
}