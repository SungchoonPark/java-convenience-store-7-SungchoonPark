package store.dto;

import store.model.Product;

public record UnderQuantityItem(
        Product product,
        int purchaseQuantity
) {
}
