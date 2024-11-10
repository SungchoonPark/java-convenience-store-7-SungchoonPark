package store.model;

import store.exception.ExceptionMessage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TemporaryPurchaseList {
    private static final String PURCHASE_DELIMITER = ",";

    private final List<TemporaryPurchaseInfo> purchaseItems;

    public TemporaryPurchaseList(String purchase) {
        purchaseItems = new ArrayList<>();
        Set<String> uniqueProductNames = new HashSet<>();
        String[] parsingItems = purchase.split(PURCHASE_DELIMITER);
        for (String item : parsingItems) {
            TemporaryPurchaseInfo temporaryPurchaseInfo = new TemporaryPurchaseInfo(item);
            checkProductNameIsDuple(uniqueProductNames, temporaryPurchaseInfo.getProductName());
            this.purchaseItems.add(temporaryPurchaseInfo);
        }
    }

    private void checkProductNameIsDuple(Set<String> uniqueNames, String productName) {
        if (uniqueNames.contains(productName)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
        uniqueNames.add(productName);
    }

    public List<TemporaryPurchaseInfo> getPurchaseItems() {
        return purchaseItems;
    }
}
