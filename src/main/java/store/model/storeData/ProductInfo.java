package store.model.storeData;

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
        return numberFormat.format(price) + "원";
    }

    private String formatQuantity() {
        if (quantity != 0) {
            return quantity + "개";
        }

        return "재고 없음";
    }

    public String getProductName() {
        return name;
    }

    public long getTotalPrice(long purchaseQuantity) {
        return price * purchaseQuantity;
    }
}
