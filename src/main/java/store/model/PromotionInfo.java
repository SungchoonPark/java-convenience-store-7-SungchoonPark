package store.model;

import store.dto.StockPromotionInfo;

import java.time.LocalDateTime;

public class PromotionInfo {
    private final Promotion promotion;
    private long promotionQuantity;

    public PromotionInfo(Promotion promotion, long promotionQuantity) {
        this.promotion = promotion;
        this.promotionQuantity = promotionQuantity;
    }

    public StockPromotionInfo getStockPromotionInfo() {
        return new StockPromotionInfo(
                formatQuantity(),
                promotion.getPromotionName()
        );
    }

    public boolean isOngoingPromotion(LocalDateTime today) {
        if (promotion.isOngoingPromotion(today)) {
            return true;
        }

        return false;
    }

    public long getPromotionQuantity() {
        return promotionQuantity;
    }

    public boolean isUnderQuantity(long purchaseQuantity) {
        if (promotionQuantity >= purchaseQuantity) {
            return promotion.isUnderQuantity(purchaseQuantity);
        }

        return false;
    }

    private String formatQuantity() {
        if (promotionQuantity != 0) {
            return promotionQuantity + "개";
        }

        return "재고 없음";
    }

    public void minusQuantity(int purchaseQuantity) {
        promotionQuantity -= purchaseQuantity;
    }
}
