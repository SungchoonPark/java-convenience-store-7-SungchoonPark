package store.model.storeData;

import store.constant.StoreConstant;
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
        if (promotionQuantity >= purchaseQuantity + 1) {
            return promotion.isUnderQuantity(purchaseQuantity);
        }

        return false;
    }

    public boolean isLowPromotionStock(long purchaseQuantity) {
        long totalEventValue = promotion.getTotalEventValue();
        long requiredQuantity = (purchaseQuantity / totalEventValue) * totalEventValue;

        return requiredQuantity > promotionQuantity;
    }

    public long calculateLowPromotionStock(long purchaseQuantity) {
        long totalEventValue = promotion.getTotalEventValue();
        long maxPromotionQuantity = (promotionQuantity / totalEventValue) * totalEventValue;

        return purchaseQuantity - maxPromotionQuantity;
    }

    private String formatQuantity() {
        if (promotionQuantity != 0) {
            return promotionQuantity + StoreConstant.COUNT.getValue();
        }

        return StoreConstant.OUT_OF_STOCK.getValue();
    }

    public void minusQuantity(int purchaseQuantity) {
        promotionQuantity -= purchaseQuantity;
    }

    public long getFreeCnt(long purchaseQuantity) {
        return purchaseQuantity / promotion.getTotalEventValue();
    }

    public long minusPromotionQuantity(long purchaseQuantity) {
        if (promotionQuantity < purchaseQuantity) {
            long remainingQuantity = purchaseQuantity - promotionQuantity;
            promotionQuantity = 0;
            return remainingQuantity;
        }

        minusQuantity((int) purchaseQuantity);
        return 0;
    }
}
