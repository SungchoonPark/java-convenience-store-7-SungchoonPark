package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.ProductListData;
import store.model.Product;

import java.text.NumberFormat;

public class UnderQuantityProduct {
    private final Product product;
    private long purchaseQuantity;

    public UnderQuantityProduct(Product product, long purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getProductName() {
        return product.getProductName();
    }

    public void updatePurchaseQuantity() {
        purchaseQuantity++;
    }

    public ProductListData generateProductListData() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        return new ProductListData(
                product.getProductName(),
                purchaseQuantity,
                numberFormat.format(product.getTotalPrice(purchaseQuantity))
        );
    }

    public boolean isExistFree() {
        return product.isExistFree(purchaseQuantity);
    }

    public FreeInfo generateFreeInfo() {
        return new FreeInfo(
                product.getProductName(),
                getFreeCnt());
    }

    public long getFreePrice() {
        return product.getTotalPrice(product.getFreeCnt(purchaseQuantity));
    }

    public long getFreeCnt() {
        return product.getFreeCnt(purchaseQuantity);
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
}
