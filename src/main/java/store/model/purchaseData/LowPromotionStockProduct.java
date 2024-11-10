package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.ProductListData;
import store.model.storeData.Product;

import java.text.NumberFormat;

public class LowPromotionStockProduct {
    private final Product product;
    private long purchaseQuantity;
    private final long lowQuantity;
    private final long promotionApplyQuantity;

    public LowPromotionStockProduct(Product product, long purchaseQuantity, long lowQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
        this.lowQuantity = lowQuantity;
        this.promotionApplyQuantity = purchaseQuantity - lowQuantity;
    }

    public String getProductName() {
        return product.getProductName();
    }

    public long getLowQuantity() {
        return lowQuantity;
    }

    public void minusNotPurchaseQuantity() {
        this.purchaseQuantity -= lowQuantity;
    }

    public long getFreePrice() {
        return product.getTotalPrice(product.getFreeCnt(purchaseQuantity));
    }

    public ProductListData generateProductListData() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String price = numberFormat.format(product.getTotalPrice(purchaseQuantity));
        return new ProductListData(product.getProductName(), purchaseQuantity, price);
    }

    public boolean isExistFree() {
        return product.isExistFree(purchaseQuantity);
    }

    public FreeInfo generateFreeInfo() {
        return new FreeInfo(product.getProductName(), product.getFreeCnt(promotionApplyQuantity));
    }

    public long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public long getPurchasePrice() {
        return product.getTotalPrice(purchaseQuantity);
    }

    public void updateQuantity() {
        product.updateQuantity(purchaseQuantity);
    }

    public long getFreeCnt() {
        return product.getFreeCnt(purchaseQuantity);
    }
}
