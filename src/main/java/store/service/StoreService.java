package store.service;

import store.dto.Stocks;
import store.model.PurchaseItems;
import store.model.TemporaryPurchaseList;
import store.model.Store;

import java.time.LocalDateTime;

public class StoreService {
    private final Store store;

    public StoreService(Store store) {
        this.store = store;
    }

    public Stocks getStoreStock(LocalDateTime today) {
        return store.getTodayStock(today);
    }

//    public void purchase(TemporaryPurchaseList temporaryPurchaseList) {
//        store.purchase(temporaryPurchaseList);
//    }

    public PurchaseItems generatePurchaseItems(TemporaryPurchaseList temporaryPurchaseList) {
        return store.validateTemporaryPurchaseItems(temporaryPurchaseList);
    }
}
