package store.model;

import java.util.List;

public class PurchaseItems {
    private final List<PurchaseItem> purchaseProducts;

    public PurchaseItems(List<PurchaseItem> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }
}
