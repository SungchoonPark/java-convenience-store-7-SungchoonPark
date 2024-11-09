package store.model;

import java.util.ArrayList;
import java.util.List;

public class TemporaryPurchaseList {
    private final List<TemporaryPurchaseInfo> purchaseItems;

    public TemporaryPurchaseList(String purchase) {
        purchaseItems = new ArrayList<>();
        String[] parsingItems = purchase.split(",");
        for (String item : parsingItems) {
            this.purchaseItems.add(new TemporaryPurchaseInfo(item));
        }
    }

    public List<TemporaryPurchaseInfo> getPurchaseItems() {
        return purchaseItems;
    }
}
