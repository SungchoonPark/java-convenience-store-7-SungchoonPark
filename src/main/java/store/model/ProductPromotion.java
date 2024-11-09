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
        // Todo : promotions 돌면서 현재 진행중인 promotion 가져오면 됨.
        for (PromotionInfo promotion : promotions) {
            if(promotion.isOngoingPromotion(today)) {
                return promotion.getStockPromotionInfo();
            }
        }

        return null;
    }
}
