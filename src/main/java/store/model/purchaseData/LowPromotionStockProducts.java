package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.ProductListData;

import java.util.ArrayList;
import java.util.List;

public class LowPromotionStockProducts {
    private final List<LowPromotionStockProduct> lowPromotionStockProducts;

    public LowPromotionStockProducts(List<LowPromotionStockProduct> lowPromotionStockProducts) {
        this.lowPromotionStockProducts = lowPromotionStockProducts;
    }

    public List<LowPromotionStockProduct> getLowPromotionStockProducts() {
        return lowPromotionStockProducts;
    }

    public List<ProductListData> generateProductListData() {
        List<ProductListData> productListDatas = new ArrayList<>();
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            productListDatas.add(lowPromotionStockProduct.generateProductListData());
        }
        return productListDatas;
    }

    public List<FreeInfo> generateFreeInfos() {
        List<FreeInfo> freeInfos = new ArrayList<>();
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            freeInfos.add(lowPromotionStockProduct.generateFreeInfo());
        }
        return freeInfos;
    }

    public long getTotalQuantity() {
        long totalQuantity = 0;
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            totalQuantity += lowPromotionStockProduct.getPurchaseQuantity();
        }
        return totalQuantity;
    }

    public long getTotalPrice() {
        long totalQuantity = 0;
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            totalQuantity += lowPromotionStockProduct.getPurchasePrice();
        }
        return totalQuantity;
    }

    public long getPromotionPrice() {
        long promotionPrice = 0;
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            promotionPrice += lowPromotionStockProduct.getFreePrice();
        }
        return promotionPrice;
    }

    public void updateQuantity() {
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            lowPromotionStockProduct.updateQuantity();
        }
    }
}
