package store.model.purchaseData;

import store.model.Product;

public class UnderQuantityProduct {
    private final Product product;
    private long purchaseQuantity;

    public UnderQuantityProduct(Product product, long purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getProductName() {
        return product.getProductName();
    }

    public void updatePurchaseQuantity() {
        purchaseQuantity++;
    }

    public String getTotalPrice() {
        // 할인전 전체 금액 (가격 * 수량)

        return "";
    }

    public String getDiscountPrice() {
        // 할인 금액

        return "";
    }

    public String getDiscountApplyPrice() {
        // 할인 후 금액 (전체 금액 - 할인 금액)

        return "";
    }

    public void getPromotionApplyCount() {
        // 공짜 개수 구하는 메서드 -> 추후 증정으로 보여질 것들임.
    }
}
