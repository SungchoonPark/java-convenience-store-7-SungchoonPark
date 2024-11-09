package store.service;

import store.dto.Stocks;
import store.dto.UnderQuantityItem;
import store.model.PurchaseItems;
import store.model.TemporaryPurchaseList;
import store.model.Store;

import java.time.LocalDateTime;
import java.util.List;

public class StoreService {
    private final Store store;

    public StoreService(Store store) {
        this.store = store;
    }

    public Stocks getStoreStock(LocalDateTime today) {
        return store.getTodayStock(today);
    }

    public PurchaseItems generatePurchaseItems(TemporaryPurchaseList temporaryPurchaseList) {
        return store.validateTemporaryPurchaseItems(temporaryPurchaseList);
    }

    public List<UnderQuantityItem> getPromotionUnderQuantity(PurchaseItems purchaseItems) {
        return purchaseItems.getPromotionUnderQuantity();
    }
}
