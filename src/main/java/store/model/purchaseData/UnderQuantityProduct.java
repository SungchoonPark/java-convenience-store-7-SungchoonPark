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
        String price = numberFormat.format(product.getTotalPrice(purchaseQuantity));
        System.out.println("price = " + price);
        return new ProductListData(product.getProductName(), purchaseQuantity, price);
    }

    public boolean isExistFree() {
        return product.isExistFree(purchaseQuantity);
    }

    public FreeInfo generateFreeInfo() {
        return new FreeInfo(product.getProductName(), product.getFreeCnt(purchaseQuantity));
    }

    public long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public long getPurchasePrice() {
        return product.getTotalPrice(purchaseQuantity);
    }

    public long getFreePrice() {
        return product.getTotalPrice(product.getFreeCnt(purchaseQuantity));
    }
}
