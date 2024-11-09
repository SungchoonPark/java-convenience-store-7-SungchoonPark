package store.model;

import store.dto.StockInfo;
import store.exception.ExceptionMessage;

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

    public boolean isSameProductName(String productName) {
        return productInfo.isSameProductName(productName);
    }

    public void checkValidPurchaseQuantity(long purchaseQuantity, LocalDateTime now) {
        long totalQuantity = productInfo.getQuantity() + promotions.getTodayPromotionQuantity(now);

        if (totalQuantity < purchaseQuantity) {
            throw new IllegalArgumentException(ExceptionMessage.OVER_STOCK.getMessage());
        }
    }
}
