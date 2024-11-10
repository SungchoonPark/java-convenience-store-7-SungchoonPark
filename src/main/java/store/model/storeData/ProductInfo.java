package store.model.storeData;

import store.constant.StoreConstant;
import store.dto.StockGeneralInfo;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductInfo {
    private final String name;
    private final long price;
    private long quantity;

    public ProductInfo(String name, long price, long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void updateQuantity(long purchaseQuantity) {
        quantity -= purchaseQuantity;
    }

    public StockGeneralInfo getStockGeneralInfo() {
        return new StockGeneralInfo(
                name,
                formatPrice(),
                formatQuantity()
        );
    }

    public boolean isSameProductName(String productName) {
        return this.name.equals(productName);
    }

    public long getQuantity() {
        return quantity;
    }

    private String formatPrice() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(price) + StoreConstant.WON.getValue();
    }

    private String formatQuantity() {
        if (quantity != 0) {
            return quantity + StoreConstant.COUNT.getValue();
        }

        return StoreConstant.OUT_OF_STOCK.getValue();
    }

    public String getProductName() {
        return name;
    }

    public long getTotalPrice(long purchaseQuantity) {
        return price * purchaseQuantity;
    }
}
