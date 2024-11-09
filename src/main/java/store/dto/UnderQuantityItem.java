package store.dto;

import store.model.Product;

public record UnderQuantityItem(
        Product product,
        int purchaseQuantity
) {
    public String getProductName() {
        return product.getProductName();
    }
}
