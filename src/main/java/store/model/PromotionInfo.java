package store.model;

import store.dto.StockPromotionInfo;

import java.time.LocalDateTime;

public class PromotionInfo {
    private final Promotion promotion;
    private long quantity;

    public PromotionInfo(Promotion promotion, long quantity) {
        this.promotion = promotion;
        this.quantity = quantity;
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

    private String formatQuantity() {
        if (quantity != 0) {
            return quantity + "개";
        }

        return "재고 없음";
    }
}
