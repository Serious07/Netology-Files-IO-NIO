package Loging;

public class ProductPair {
    private int productIndex;
    private int productAmount;

    public ProductPair(int productIndex, int productAmount) {
        this.productIndex = productIndex;
        this.productAmount = productAmount;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    @Override
    public String toString() {
        return productIndex + "," + productAmount;
    }
}
