package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.ProductListData;
import store.model.storeData.Product;

import java.text.NumberFormat;

public class PromotionNotApplyProduct {
    private final Product product;
    private final long purchaseQuantity;

    public PromotionNotApplyProduct(Product product, long purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }

    public void updateQuantity() {
        product.updateQuantity(purchaseQuantity);
    }

    public boolean isExistFree() {
        return product.isExistFree(purchaseQuantity);
    }

    public long getFreePrice() {
        return product.getTotalPrice(product.getFreeCnt(purchaseQuantity));
    }

    public long getFreeCnt() {
        return product.getFreeCnt(purchaseQuantity);
    }

    public ProductListData generateProductListData() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        return new ProductListData(
                product.getProductName(),
                purchaseQuantity,
                numberFormat.format(product.getTotalPrice(purchaseQuantity))
        );
    }

    public FreeInfo generateFreeInfo() {
        return new FreeInfo(
                product.getProductName(),
                product.getFreeCnt(purchaseQuantity)
        );
    }

    public long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public long getPurchasePrice() {
        return product.getTotalPrice(purchaseQuantity);
    }
}
