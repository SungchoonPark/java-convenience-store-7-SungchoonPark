package store.model;

import store.model.purchaseData.GeneralProduct;
import store.model.purchaseData.LowPromotionStockProduct;
import store.model.purchaseData.PromotionNotApplyProduct;
import store.model.purchaseData.UnderQuantityProduct;

import java.util.ArrayList;
import java.util.List;

public class PurchaseItems {
    private final List<PurchaseItem> purchaseProducts;

    public PurchaseItems(List<PurchaseItem> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }

    public List<GeneralProduct> getGeneralProducts() {
        List<GeneralProduct> generalProducts = new ArrayList<>();
        for (PurchaseItem purchaseProduct : purchaseProducts) {
            if (purchaseProduct.isGeneralProduct()) {
                generalProducts.add(purchaseProduct.generateGeneralProduct());
            }
        }

        return generalProducts;
    }

    public List<PromotionNotApplyProduct> getPromotionNotApplyProducts() {
        List<PromotionNotApplyProduct> promotionNotApplyProducts = new ArrayList<>();
        for (PurchaseItem purchaseProduct : purchaseProducts) {
            if (purchaseProduct.isPromotionNotApplyProduct()) {
                promotionNotApplyProducts.add(purchaseProduct.generatePromotionNotApplyProduct());
            }
        }

        return promotionNotApplyProducts;
    }

    public List<UnderQuantityProduct> getPromotionUnderQuantity() {
        List<UnderQuantityProduct> underQuantityItems = new ArrayList<>();
        for (PurchaseItem purchaseProduct : purchaseProducts) {
            if (purchaseProduct.isUnderQuantity()) {
                underQuantityItems.add(purchaseProduct.generateUnderQuantityProduct());
            }
        }

        return underQuantityItems;
    }

    public List<LowPromotionStockProduct> getLowPromotionStockItems() {
        List<LowPromotionStockProduct> lowPromotionStockItems = new ArrayList<>();
        for (PurchaseItem purchaseProduct : purchaseProducts) {
            if (purchaseProduct.isLowPromotionStock()) {
                lowPromotionStockItems.add(purchaseProduct.generateLowPromotionStock());
            }
        }

        return lowPromotionStockItems;
    }
}
