package store.model;

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
}
