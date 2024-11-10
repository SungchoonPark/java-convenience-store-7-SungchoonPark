package store.service;

import store.dto.Stocks;
import store.model.PurchaseItems;
import store.model.TemporaryPurchaseList;
import store.model.Store;
import store.model.purchaseData.*;

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

    public PurchaseProducts getPurchaseProducts(PurchaseItems purchaseItems) {
        List<GeneralProduct> generalProducts = purchaseItems.getGeneralProducts();
        List<PromotionNotApplyProduct> promotionNotApplyProducts = purchaseItems.getPromotionNotApplyProducts();
        List<UnderQuantityProduct> underQuantityProducts = purchaseItems.getPromotionUnderQuantity();
        List<LowPromotionStockProduct> lowPromotionStockProducts = purchaseItems.getLowPromotionStockItems();

        return new PurchaseProducts(
                generalProducts,
                promotionNotApplyProducts,
                underQuantityProducts,
                lowPromotionStockProducts
        );
    }
}
