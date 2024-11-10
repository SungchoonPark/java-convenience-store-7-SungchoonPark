package store.model.storeData;

import store.dto.Stocks;
import store.model.PurchaseItem;
import store.model.PurchaseItems;
import store.model.TemporaryPurchaseInfo;
import store.model.TemporaryPurchaseList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final Stock stock;

    public Store(Stock stock) {
        this.stock = stock;
    }

    public Stocks getTodayStock(LocalDateTime today) {
        return stock.makeStocks(today);
    }

    public PurchaseItems validateTemporaryPurchaseItems(TemporaryPurchaseList temporaryPurchaseList) {
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        for (TemporaryPurchaseInfo purchaseItem : temporaryPurchaseList.getPurchaseItems()) {
            purchaseItems.add(stock.validPurchaseItem(purchaseItem));
        }

        return new PurchaseItems(purchaseItems);
    }
}
