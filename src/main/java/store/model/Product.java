package store.model;

import store.dto.StockInfo;

import java.time.LocalDateTime;

public class Product {
    private final ProductInfo productInfo;
    private final ProductPromotion promotions;

    public Product(ProductInfo productInfo, ProductPromotion promotions) {
        this.productInfo = productInfo;
        this.promotions = promotions;
    }

    public StockInfo getProductInfo(LocalDateTime today) {
        return new StockInfo(
                productInfo.getStockGeneralInfo(),
                promotions.getTodayPromotionInfo(today)
        );
    }
}
