package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.PriceInfo;
import store.dto.ProductListData;

import java.util.ArrayList;
import java.util.List;

public class PurchaseProducts {
    private final GeneralProducts generalProducts;
    private final PromotionNotApplyProducts promotionNotApplyProducts;
    private final UnderQuantityProducts underQuantityProducts;
    private final LowPromotionStockProducts lowPromotionStockProducts;

    public PurchaseProducts(List<GeneralProduct> generalProducts, List<PromotionNotApplyProduct> promotionNotApplyProducts, List<UnderQuantityProduct> underQuantityProducts, List<LowPromotionStockProduct> lowPromotionStockProducts) {
        this.generalProducts = new GeneralProducts(generalProducts);
        this.promotionNotApplyProducts = new PromotionNotApplyProducts(promotionNotApplyProducts);
        this.underQuantityProducts = new UnderQuantityProducts(underQuantityProducts);
        this.lowPromotionStockProducts = new LowPromotionStockProducts(lowPromotionStockProducts);
    }

    public List<UnderQuantityProduct> getUnderQuantityProducts() {
        return underQuantityProducts.getProducts();
    }

    public List<LowPromotionStockProduct> getLowPromotionStockProducts() {
        return lowPromotionStockProducts.getProducts();
    }

    public void applyMembership() {
        generalProducts.applyMembership();
    }

    public List<ProductListData> getTotalProductList() {
        List<ProductListData> productListDatas = new ArrayList<>();

        productListDatas.addAll(generalProducts.generateProductListData());
        productListDatas.addAll(promotionNotApplyProducts.generateProductListData());
        productListDatas.addAll(underQuantityProducts.generateProductListData());
        productListDatas.addAll(lowPromotionStockProducts.generateProductListData());

        return productListDatas;
    }

    public List<FreeInfo> getFreeInfo() {
        List<FreeInfo> freeInfos = new ArrayList<>();

        freeInfos.addAll(promotionNotApplyProducts.generateFreeInfos());
        freeInfos.addAll(underQuantityProducts.generateFreeInfos());
        freeInfos.addAll(lowPromotionStockProducts.generateFreeInfos());

        return freeInfos;
    }

    public PriceInfo getPriceInfo() {
        return new PriceInfo(
                getTotalQuantity(),
                getTotalPrice(),
                getPromotionPrice(),
                getMembershipDiscount(),
                getPayPrice()
        );
    }

    public long getTotalQuantity() {
        long totalQuantity = 0;
        totalQuantity += generalProducts.getTotalQuantity();
        totalQuantity += promotionNotApplyProducts.getTotalQuantity();
        totalQuantity += underQuantityProducts.getTotalQuantity();
        totalQuantity += lowPromotionStockProducts.getTotalQuantity();

        return totalQuantity;
    }

    public long getTotalPrice() {
        long totalPrice = 0;

        totalPrice += generalProducts.getTotalPrice();
        totalPrice += promotionNotApplyProducts.getTotalPrice();
        totalPrice += underQuantityProducts.getTotalPrice();
        totalPrice += lowPromotionStockProducts.getTotalPrice();

        return totalPrice;
    }

    public long getPromotionPrice() {
        long promotionPrice = 0;
        promotionPrice += promotionNotApplyProducts.getPromotionPrice();
        promotionPrice += underQuantityProducts.getPromotionPrice();
        promotionPrice += lowPromotionStockProducts.getPromotionPrice();

        return promotionPrice;
    }

    public long getMembershipDiscount() {
        return generalProducts.getMembershipPrice();
    }

    public long getPayPrice() {
        return getTotalPrice() - getPromotionPrice() - getMembershipDiscount();
    }

    public void updateQuantity() {
        generalProducts.updateQuantity();
        promotionNotApplyProducts.updateQuantity();
        underQuantityProducts.updateQuantity();
        lowPromotionStockProducts.updateQuantity();
    }
}
