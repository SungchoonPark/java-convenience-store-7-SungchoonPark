package store.model.purchaseData;

import store.dto.ProductListData;
import store.model.storeData.Product;

import java.text.NumberFormat;

public class GeneralProduct {
    private final Product product;
    private final long purchaseQuantity;

    public GeneralProduct(Product product, long purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }

    public void updateQuantity() {
        product.updateQuantity(purchaseQuantity);
    }

    public long getPrice() {
        return product.getTotalPrice(purchaseQuantity);
    }

    public ProductListData generateProductListData() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String price = numberFormat.format(product.getTotalPrice(purchaseQuantity));
        return new ProductListData(product.getProductName(), purchaseQuantity, price);
    }

    public long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public long getPurchasePrice() {
        return product.getTotalPrice(purchaseQuantity);
    }
}
