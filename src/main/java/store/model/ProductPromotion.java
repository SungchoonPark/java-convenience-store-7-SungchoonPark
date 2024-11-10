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

    public boolean isLowPromotionStock(long purchaseQuantity, LocalDateTime today) {
        for (PromotionInfo promotion : promotions) {
            if(promotion.isOngoingPromotion(today)) {
                return promotion.isLowPromotionStock(purchaseQuantity);
            }
        }

        return false;
    }

    public long calculateLowPromotionStock(long purchaseQuantity, LocalDateTime today) {
        for (PromotionInfo promotion : promotions) {
            if(promotion.isOngoingPromotion(today)) {
                return promotion.calculateLowPromotionStock(purchaseQuantity);
            }
        }

        return 0L;
    }

    public void updatePromotionQuantity(int purchaseQuantity, LocalDateTime today) {
        for (PromotionInfo promotion : promotions) {
            if(promotion.isOngoingPromotion(today)) {
                promotion.minusQuantity(purchaseQuantity);
            }
        }
    }

    public boolean isExistFree(long purchaseQuantity, LocalDateTime today) {
        for (PromotionInfo promotion : promotions) {
            if(promotion.isOngoingPromotion(today)) {
                return promotion.isExistFree(purchaseQuantity);
            }
        }

        return false;
    }

    public long getFreeCnt(long purchaseQuantity, LocalDateTime today) {
        for (PromotionInfo promotion : promotions) {
            if(promotion.isOngoingPromotion(today)) {
                return promotion.getFreeCnt(purchaseQuantity);
            }
        }

        return 0L;
    }
}
