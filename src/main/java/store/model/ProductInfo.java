package store.model;

public class ProductInfo {
    private final String name;
    private final long price;
    private long quantity;

    public ProductInfo(String name, long price, long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
