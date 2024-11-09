package store.model;

import store.dto.UnderQuantityItem;

import java.util.ArrayList;
import java.util.List;

public class PurchaseItems {
    private final List<PurchaseItem> purchaseProducts;

    public PurchaseItems(List<PurchaseItem> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }

    public List<UnderQuantityItem> getPromotionUnderQuantity() {
        List<UnderQuantityItem> underQuantityItems = new ArrayList<>();
        for (PurchaseItem purchaseProduct : purchaseProducts) {
            if (purchaseProduct.isUnderQuantity()) {
                underQuantityItems.add(purchaseProduct.generateUnderQuantityItem());
            }
        }

        return underQuantityItems;
    }
}
