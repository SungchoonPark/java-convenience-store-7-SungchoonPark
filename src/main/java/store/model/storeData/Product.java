package store.model.storeData;

import camp.nextstep.edu.missionutils.DateTimes;
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

        if (purchaseQuantity < 1) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }

    public boolean isGeneralProduct() {
        return promotions.getTodayPromotionInfo(DateTimes.now()) == null;
    }

    public boolean isPromotionNotApplyProduct(long purchaseQuantity) {
        if (promotions.getTodayPromotionInfo(DateTimes.now()) == null) {
            return false;
        }

        return !isUnderQuantity(purchaseQuantity) && !isLowPromotionStock(purchaseQuantity);
    }

    public boolean isUnderQuantity(long purchaseQuantity) {
        return promotions.isUnderQuantity(purchaseQuantity, DateTimes.now());
    }

    public boolean isLowPromotionStock(long purchaseQuantity) {
        return promotions.isLowPromotionStock(purchaseQuantity, DateTimes.now());
    }

    public long getLowPromotionStock(long purchaseQuantity) {
        return promotions.calculateLowPromotionStock(purchaseQuantity, DateTimes.now());
    }

    public String getProductName() {
        return productInfo.getProductName();
    }

    public long getTotalPrice(long purchaseQuantity) {
        return productInfo.getTotalPrice(purchaseQuantity);
    }

    public long getFreeCnt(long purchaseQuantity) {
        return promotions.getFreeCnt(purchaseQuantity, DateTimes.now());
    }

    private long getTotalQuantity(LocalDateTime now) {
        return productInfo.getQuantity() + promotions.getTodayPromotionQuantity(now);
    }

    public void updateQuantity(long purchaseQuantity) {
        if (promotions.isExistTodayPromotion(DateTimes.now())) {
            long remainingQuantity = promotions.minusPromotionQuantity(purchaseQuantity, DateTimes.now());
            productInfo.updateQuantity(remainingQuantity);
            return;
        }

        productInfo.updateQuantity(purchaseQuantity);
    }
}