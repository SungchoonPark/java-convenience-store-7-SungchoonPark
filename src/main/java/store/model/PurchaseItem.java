package store.model;

import store.dto.UnderQuantityItem;

public class PurchaseItem {
    private final Product product;
    private final long purchaseQuantity;

    public PurchaseItem(Product product, long purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }

    public boolean isUnderQuantity() {
        return product.isUnderQuantity(purchaseQuantity);
    }

    public UnderQuantityItem generateUnderQuantityItem() {
        return new UnderQuantityItem(product, (int) purchaseQuantity);
    }
}
