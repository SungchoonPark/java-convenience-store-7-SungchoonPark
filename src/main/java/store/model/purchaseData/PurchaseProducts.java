package store.model.purchaseData;

import java.util.List;

public class PurchaseProducts {
    private final List<GeneralProduct> generalProducts;
    private final List<PromotionNotApplyProduct> promotionNotApplyProducts;
    private final List<UnderQuantityProduct> underQuantityProducts;
    private final List<LowPromotionStockProduct> lowPromotionStockProducts;

    public PurchaseProducts(List<GeneralProduct> generalProducts, List<PromotionNotApplyProduct> promotionNotApplyProducts, List<UnderQuantityProduct> underQuantityProducts, List<LowPromotionStockProduct> lowPromotionStockProducts) {
        this.generalProducts = generalProducts;
        this.promotionNotApplyProducts = promotionNotApplyProducts;
        this.underQuantityProducts = underQuantityProducts;
        this.lowPromotionStockProducts = lowPromotionStockProducts;
    }

    public List<UnderQuantityProduct> getUnderQuantityProducts() {
        return underQuantityProducts;
    }

    public List<LowPromotionStockProduct> getLowPromotionStockProducts() {
        return lowPromotionStockProducts;
    }

//    public void applyMembership() {
//        long totalPrice = 0L;
//        for (GeneralProduct generalProduct : generalProducts) {
//            totalPrice += generalProduct.getPrice();
//        }
//
//        return (int) Math.round(totalPrice * 0.7);
//    }

    public String getTotalProductList() {
        // 산 제품들의 이름과 재고, 가격등을 축적해서 가져와야할 메서드
        // 추후 dto로 변경 가능함.

        return null;
    }

    public String getFreeInfo() {
        // 증정 내역들을 가져올 메서드

        return null;
    }

    public long getTotalPrice() {
        // 총구매액을 구하는 메서드

        return 0L;
    }

    public long getDiscountPrice() {
        // 행사할인을 구하는 메서드

        return 0L;
    }

    public long getMembershipDiscount() {
        // 멤버십할인을 구하는 메서드
        // 이건 generalProducts에서만 구해오면 됨

        return 0L;
    }

    public long getPayPrice() {
        // 내실돈 을 구해오는 메서드

        return 0L;
    }
}
