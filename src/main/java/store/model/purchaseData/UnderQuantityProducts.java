package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.ProductListData;

import java.util.ArrayList;
import java.util.List;

public class UnderQuantityProducts {
    private final List<UnderQuantityProduct> underQuantityProducts;

    public UnderQuantityProducts(List<UnderQuantityProduct> underQuantityProducts) {
        this.underQuantityProducts = underQuantityProducts;
    }

    public List<UnderQuantityProduct> getUnderQuantityProducts() {
        return underQuantityProducts;
    }

    public List<ProductListData> generateProductListData() {
        List<ProductListData> productListDatas = new ArrayList<>();
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            productListDatas.add(underQuantityProduct.generateProductListData());
        }
        return productListDatas;
    }

    public List<FreeInfo> generateFreeInfos() {
        List<FreeInfo> freeInfos = new ArrayList<>();
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            freeInfos.add(underQuantityProduct.generateFreeInfo());
        }
        return freeInfos;
    }

    public long getTotalQuantity() {
        long totalQuantity = 0;
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            totalQuantity += underQuantityProduct.getPurchaseQuantity();
        }
        return totalQuantity;
    }

    public long getTotalPrice() {
        long totalQuantity = 0;
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            totalQuantity += underQuantityProduct.getPurchasePrice();
        }
        return totalQuantity;
    }

    public long getPromotionPrice() {
        long promotionPrice = 0;
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            promotionPrice += underQuantityProduct.getFreePrice();
        }
        return promotionPrice;
    }

    public void updateQuantity() {
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            underQuantityProduct.updateQuantity();
        }
    }
}
