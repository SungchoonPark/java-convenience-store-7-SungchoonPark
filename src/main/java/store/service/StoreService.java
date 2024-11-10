package store.service;

import store.dto.FreeInfo;
import store.dto.PriceInfo;
import store.dto.ProductListData;
import store.dto.Stocks;
import store.model.PurchaseItems;
import store.model.TemporaryPurchaseList;
import store.model.storeData.Store;
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
        return new PurchaseProducts(
                purchaseItems.getGeneralProducts(),
                purchaseItems.getPromotionNotApplyProducts(),
                purchaseItems.getPromotionUnderQuantity(),
                purchaseItems.getLowPromotionStockItems()
        );
    }

    public void applyMembership(PurchaseProducts purchaseProducts) {
        purchaseProducts.applyMembership();
    }

    public List<ProductListData> getTotalProductList(PurchaseProducts purchaseProducts) {
        return purchaseProducts.getTotalProductList();
    }

    public List<FreeInfo> getFreeInfo(PurchaseProducts purchaseProducts) {
        return purchaseProducts.getFreeInfo();
    }

    public PriceInfo getPriceInfo(PurchaseProducts purchaseProducts) {
        return purchaseProducts.getPriceInfo();
    }

    public void updateProductQuantity(PurchaseProducts purchaseProducts) {
        purchaseProducts.updateQuantity();
    }
}
