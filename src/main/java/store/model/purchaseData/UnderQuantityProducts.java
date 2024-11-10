package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.ProductListData;

import java.util.ArrayList;
import java.util.List;

public class UnderQuantityProducts {
    private final List<UnderQuantityProduct> products;

    public UnderQuantityProducts(List<UnderQuantityProduct> products) {
        this.products = products;
    }

    public List<UnderQuantityProduct> getProducts() {
        return products;
    }

    public List<ProductListData> generateProductListData() {
        List<ProductListData> productListDatas = new ArrayList<>();
        for (UnderQuantityProduct underQuantityProduct : products) {
            productListDatas.add(underQuantityProduct.generateProductListData());
        }
        return productListDatas;
    }

    public List<FreeInfo> generateFreeInfos() {
        List<FreeInfo> freeInfos = new ArrayList<>();
        for (UnderQuantityProduct underQuantityProduct : products) {
            freeInfos.add(underQuantityProduct.generateFreeInfo());
        }
        return freeInfos;
    }

    public long getTotalQuantity() {
        long totalQuantity = 0;
        for (UnderQuantityProduct underQuantityProduct : products) {
            totalQuantity += underQuantityProduct.getPurchaseQuantity();
        }
        return totalQuantity;
    }

    public long getTotalPrice() {
        long totalQuantity = 0;
        for (UnderQuantityProduct underQuantityProduct : products) {
            totalQuantity += underQuantityProduct.getPurchasePrice();
        }
        return totalQuantity;
    }

    public long getPromotionPrice() {
        long promotionPrice = 0;
        for (UnderQuantityProduct underQuantityProduct : products) {
            promotionPrice += underQuantityProduct.getFreePrice();
        }
        return promotionPrice;
    }

    public void updateQuantity() {
        for (UnderQuantityProduct underQuantityProduct : products) {
            underQuantityProduct.updateQuantity();
        }
    }
}
