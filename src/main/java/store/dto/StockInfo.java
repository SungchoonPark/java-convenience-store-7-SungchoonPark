package store.dto;

public record StockInfo(
        StockGeneralInfo generalInfo,
        StockPromotionInfo promotionInfo
) {
    public String formatGeneralInfo() {
        return generalInfo.name() + " " +
                generalInfo.price() + " " +
                generalInfo.defaultQuantity();
    }

    public String formatPromotionInfo() {
        return generalInfo.name() + " " +
                generalInfo.price() + " " +
                promotionInfo.promotionQuantity() + " " +
                promotionInfo.promotionName();
    }
}
