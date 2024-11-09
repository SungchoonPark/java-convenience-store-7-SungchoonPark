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
                return promotion.getQuantity();
            }
        }

        return 0L;
    }
}
