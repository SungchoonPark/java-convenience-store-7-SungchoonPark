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
        if (getTotalQuantity(now) < purchaseQuantity) {
            throw new IllegalArgumentException(ExceptionMessage.OVER_STOCK.getMessage());
        }
    }

    public boolean isGeneralProduct() {
        return promotions.getTodayPromotionInfo(LocalDateTime.now()) == null;
    }

    public boolean isPromotionNotApplyProduct(long purchaseQuantity) {
        if (promotions.getTodayPromotionInfo(LocalDateTime.now()) == null) {
            return false;
        }

        if(!isUnderQuantity(purchaseQuantity) && !isLowPromotionStock(purchaseQuantity)) {
            return true;
        }

        return false;
    }

    public boolean isUnderQuantity(long purchaseQuantity) {
        return promotions.isUnderQuantity(purchaseQuantity, LocalDateTime.now());
    }

    public boolean isLowPromotionStock(long purchaseQuantity) {
        return promotions.isLowPromotionStock(purchaseQuantity, LocalDateTime.now());
    }

    public long getLowPromotionStock(long purchaseQuantity) {
        return promotions.calculateLowPromotionStock(purchaseQuantity, LocalDateTime.now());
    }

    public String getProductName() {
        return productInfo.getProductName();
    }

    private long getTotalQuantity(LocalDateTime now) {
        return productInfo.getQuantity() + promotions.getTodayPromotionQuantity(now);
    }


    public long getTotalPrice(long purchaseQuantity) {
        return productInfo.getTotalPrice(purchaseQuantity);
    }
}