package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.ProductListData;

import java.util.ArrayList;
import java.util.List;

public class PromotionNotApplyProducts {
    private final List<PromotionNotApplyProduct> products;

    public PromotionNotApplyProducts(List<PromotionNotApplyProduct> products) {
        this.products = products;
    }

    public List<ProductListData> generateProductListData() {
        List<ProductListData> productListDatas = new ArrayList<>();
        for (PromotionNotApplyProduct promotionNotApplyProduct : products) {
            productListDatas.add(promotionNotApplyProduct.generateProductListData());
        }
        return productListDatas;
    }

    public List<FreeInfo> generateFreeInfos() {
        List<FreeInfo> freeInfos = new ArrayList<>();
        for (PromotionNotApplyProduct promotionNotApplyProduct : products) {
            freeInfos.add(promotionNotApplyProduct.generateFreeInfo());
        }
        return freeInfos;
    }

    public long getTotalQuantity() {
        long totalQuantity = 0;
        for (PromotionNotApplyProduct promotionNotApplyProduct : products) {
            totalQuantity += promotionNotApplyProduct.getPurchaseQuantity();
        }
        return totalQuantity;
    }

    public long getTotalPrice() {
        long totalQuantity = 0;
        for (PromotionNotApplyProduct promotionNotApplyProduct : products) {
            totalQuantity += promotionNotApplyProduct.getPurchasePrice();
        }
        return totalQuantity;
    }

    public long getPromotionPrice() {
        long promotionPrice = 0;
        for (PromotionNotApplyProduct promotionNotApplyProduct : products) {
            promotionPrice += promotionNotApplyProduct.getFreePrice();
        }
        return promotionPrice;
    }

    public void updateQuantity() {
        for (PromotionNotApplyProduct promotionNotApplyProduct : products) {
            promotionNotApplyProduct.updateQuantity();
        }
    }
}
