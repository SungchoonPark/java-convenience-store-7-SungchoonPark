package store.model.purchaseData;

import store.model.Product;

public class LowPromotionStockProduct {
    private final Product product;
    private long purchaseQuantity;
    private long lowQuantity;

    public LowPromotionStockProduct(Product product, long purchaseQuantity, long lowQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
        this.lowQuantity = lowQuantity;
    }

    public String getProductName() {
        return product.getProductName();
    }

    public long getLowQuantity() {
        return lowQuantity;
    }

    public void updateQuantity() {
        this.purchaseQuantity -= lowQuantity;
    }

    public long getTotalPrice() {
        // 프로모션 적용 전 전체 금액을 구하는 메서드

        return 0L;
    }

    public int getFreePrice() {
        // 할인 금액을 구하는 메서드
        // 선수로 프로모션 몇개 적용되는지 구해야됨

        return 0;
    }

    public String getFreeInfo() {
        // 콜라 1 처럼 상품명과 증정개수를 돌려주면 됨
        // 프로모션 몇개 적용되는지 함수 사용하면 될듯
        // 이 함수는 추후 dto로 바뀔수도 있음

        return null;
    }


}
