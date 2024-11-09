package store.dto;

import store.model.Product;

public record UnderQuantityItem(
        Product product,
        int purchaseQuantity
) {
    public String getProductName() {
        return product.getProductName();
    }

    public void updatePromotionQuantity(String userChoice) {
        if (userChoice.equals("Y")) {
            product.updatePromotionQuantity(purchaseQuantity + 1);
            return;
        }
        product.updatePromotionQuantity(purchaseQuantity);
    }
}
