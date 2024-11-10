package store.model.purchaseData;

import store.model.Product;

public class GeneralProduct {
    private final Product product;
    private final long purchaseQuantity;

    public GeneralProduct(Product product, long purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }

    public void updateQuantity() {
        // 구매한 수량만큼 product의 재고에서 깎으면 됨.
        // 최후에 하면 됨.
    }

    public long getPrice() {
        return product.getTotalPrice(purchaseQuantity);
    }



}
