package store.model;

public class PromotionInfo {
    private final Promotion promotion;
    private long quantity;

    public PromotionInfo(Promotion promotion, long quantity) {
        this.promotion = promotion;
        this.quantity = quantity;
    }
}
