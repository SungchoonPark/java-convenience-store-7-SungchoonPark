package store.model;

public class PurchaseItem {
    private final Product product;
    private final long quantity;

    public PurchaseItem(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
