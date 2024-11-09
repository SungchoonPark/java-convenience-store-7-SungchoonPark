package store.model;

import store.dto.StockPromotionInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductPromotion {
    private final List<PromotionInfo> promotions;

    public ProductPromotion(List<PromotionInfo> promotions) {
        if (promotions == null) {
            promotions = new ArrayList<>();
        }
        this.promotions = new ArrayList<>(promotions);
    }

    public StockPromotionInfo getTodayPromotionInfo(LocalDateTime today) {
        for (PromotionInfo promotion : promotions) {
            if(promotion.isOngoingPromotion(today)) {
                return promotion.getStockPromotionInfo();
            }
        }

        return null;
    }

    public long getTodayPromotionQuantity(LocalDateTime today) {
        for (PromotionInfo promotion : promotions) {
            if(promotion.isOngoingPromotion(today)) {
                return promotion.getPromotionQuantity();
            }
        }

        return 0L;
    }

    public boolean isUnderQuantity(long purchaseQuantity, LocalDateTime today) {
        for (PromotionInfo promotion : promotions) {
            if(promotion.isOngoingPromotion(today)) {
                return promotion.isUnderQuantity(purchaseQuantity);
            }
        }

        return false;
    }

    public void updatePromotionQuantity(int purchaseQuantity, LocalDateTime today) {
        for (PromotionInfo promotion : promotions) {
            if(promotion.isOngoingPromotion(today)) {
                promotion.minusQuantity(purchaseQuantity);
            }
        }
    }
}
