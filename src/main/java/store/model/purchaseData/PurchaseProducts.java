package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.PriceInfo;
import store.dto.ProductListData;

import java.util.ArrayList;
import java.util.List;

public class PurchaseProducts {
    private final List<GeneralProduct> generalProducts;
    private final List<PromotionNotApplyProduct> promotionNotApplyProducts;
    private final List<UnderQuantityProduct> underQuantityProducts;
    private final List<LowPromotionStockProduct> lowPromotionStockProducts;
    private long memberShipPrice;

    public PurchaseProducts(List<GeneralProduct> generalProducts, List<PromotionNotApplyProduct> promotionNotApplyProducts, List<UnderQuantityProduct> underQuantityProducts, List<LowPromotionStockProduct> lowPromotionStockProducts) {
        this.generalProducts = generalProducts;
        this.promotionNotApplyProducts = promotionNotApplyProducts;
        this.underQuantityProducts = underQuantityProducts;
        this.lowPromotionStockProducts = lowPromotionStockProducts;
        this.memberShipPrice = 0;
    }

    public List<UnderQuantityProduct> getUnderQuantityProducts() {
        return underQuantityProducts;
    }

    public List<LowPromotionStockProduct> getLowPromotionStockProducts() {
        return lowPromotionStockProducts;
    }

    public void applyMembership() {
        long totalPrice = 0L;
        for (GeneralProduct generalProduct : generalProducts) {
            totalPrice += generalProduct.getPrice();
        }

        memberShipPrice =  Math.min((int) Math.round(totalPrice * 0.3), 8000);
    }

    public List<ProductListData> getTotalProductList() {
        List<ProductListData> productListDatas = new ArrayList<>();
        for (GeneralProduct generalProduct : generalProducts) {
            productListDatas.add(generalProduct.generateProductListData());
        }
        for (PromotionNotApplyProduct promotionNotApplyProduct : promotionNotApplyProducts) {
            productListDatas.add(promotionNotApplyProduct.generateProductListData());
        }
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            productListDatas.add(underQuantityProduct.generateProductListData());
        }
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            productListDatas.add(lowPromotionStockProduct.generateProductListData());
        }

        return productListDatas;
    }

    public List<FreeInfo> getFreeInfo() {
        // 증정 내역들을 가져올 메서드
        List<FreeInfo> freeInfos = new ArrayList<>();
        for (PromotionNotApplyProduct promotionNotApplyProduct : promotionNotApplyProducts) {
            if (promotionNotApplyProduct.isExistFree()) {
                freeInfos.add(promotionNotApplyProduct.generateFreeInfo());
            }
        }
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            if (underQuantityProduct.isExistFree()) {
                freeInfos.add(underQuantityProduct.generateFreeInfo());
            }
        }
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            if (lowPromotionStockProduct.isExistFree()) {
                freeInfos.add(lowPromotionStockProduct.generateFreeInfo());
            }
        }

        return freeInfos;
    }

    public PriceInfo getPriceInfo() {
        return new PriceInfo(
                getTotalQuantity(),//  토탈 개수
                getTotalPrice(),// 토탈 금액
                getPromotionPrice(),// 행사 할인
                getMembershipDiscount(),// 멤버십 할인
                getPayPrice()// 내실돈
        );
    }

    public long getTotalQuantity() {
        // 총 구매개수
        long totalQuantity = 0;
        for (GeneralProduct generalProduct : generalProducts) {
            totalQuantity += generalProduct.getPurchaseQuantity();
        }
        for (PromotionNotApplyProduct promotionNotApplyProduct : promotionNotApplyProducts) {
            totalQuantity += promotionNotApplyProduct.getPurchaseQuantity();
        }
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            totalQuantity += underQuantityProduct.getPurchaseQuantity();
        }
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            totalQuantity += lowPromotionStockProduct.getPurchaseQuantity();
        }
        return totalQuantity;
    }

    public long getTotalPrice() {
        // 총 금액
        long totalPrice = 0;
        for (GeneralProduct generalProduct : generalProducts) {
            totalPrice += generalProduct.getPurchasePrice();
        }
        for (PromotionNotApplyProduct promotionNotApplyProduct : promotionNotApplyProducts) {
            totalPrice += promotionNotApplyProduct.getPurchasePrice();
        }
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            totalPrice += underQuantityProduct.getPurchasePrice();
        }
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            totalPrice += lowPromotionStockProduct.getPurchasePrice();
        }

        return totalPrice;
    }

    public long getPromotionPrice() {
        // 행사할인을 구하는 메서드
        long promotionPrice = 0;
        for (PromotionNotApplyProduct promotionNotApplyProduct : promotionNotApplyProducts) {
            if (promotionNotApplyProduct.isExistFree()) {
                promotionPrice += promotionNotApplyProduct.getFreePrice();
            }
        }
        for (UnderQuantityProduct underQuantityProduct : underQuantityProducts) {
            if (underQuantityProduct.isExistFree()) {
                promotionPrice += underQuantityProduct.getFreePrice();
            }
        }
        for (LowPromotionStockProduct lowPromotionStockProduct : lowPromotionStockProducts) {
            if (lowPromotionStockProduct.isExistFree()) {
                promotionPrice += lowPromotionStockProduct.getFreePrice();
            }
        }
        return promotionPrice;
    }

    public long getMembershipDiscount() {
        return memberShipPrice;
    }

    public long getPayPrice() {
        // 내실돈 을 구해오는 메서드
        return getTotalPrice() - getPromotionPrice() - getMembershipDiscount();
    }

}
