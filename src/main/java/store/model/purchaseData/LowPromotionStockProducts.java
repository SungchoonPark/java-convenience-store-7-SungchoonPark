package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.ProductListData;

import java.util.ArrayList;
import java.util.List;

public class LowPromotionStockProducts {
    private final List<LowPromotionStockProduct> products;

    public LowPromotionStockProducts(List<LowPromotionStockProduct> products) {
        this.products = products;
    }

    public List<LowPromotionStockProduct> getProducts() {
        return products;
    }

    public List<ProductListData> generateProductListData() {
        List<ProductListData> productListDatas = new ArrayList<>();
        for (LowPromotionStockProduct lowPromotionStockProduct : products) {
            productListDatas.add(lowPromotionStockProduct.generateProductListData());
        }
        return productListDatas;
    }

    public List<FreeInfo> generateFreeInfos() {
        List<FreeInfo> freeInfos = new ArrayList<>();
        for (LowPromotionStockProduct lowPromotionStockProduct : products) {
            freeInfos.add(lowPromotionStockProduct.generateFreeInfo());
        }
        return freeInfos;
    }

    public long getTotalQuantity() {
        long totalQuantity = 0;
        for (LowPromotionStockProduct lowPromotionStockProduct : products) {
            totalQuantity += lowPromotionStockProduct.getPurchaseQuantity();
        }
        return totalQuantity;
    }

    public long getTotalPrice() {
        long totalQuantity = 0;
        for (LowPromotionStockProduct lowPromotionStockProduct : products) {
            totalQuantity += lowPromotionStockProduct.getPurchasePrice();
        }
        return totalQuantity;
    }

    public long getPromotionPrice() {
        long promotionPrice = 0;
        for (LowPromotionStockProduct lowPromotionStockProduct : products) {
            promotionPrice += lowPromotionStockProduct.getFreePrice();
        }
        return promotionPrice;
    }

    public void updateQuantity() {
        for (LowPromotionStockProduct lowPromotionStockProduct : products) {
            lowPromotionStockProduct.updateQuantity();
        }
    }
}
