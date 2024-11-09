package store.service;

import store.dto.Stocks;
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
}
