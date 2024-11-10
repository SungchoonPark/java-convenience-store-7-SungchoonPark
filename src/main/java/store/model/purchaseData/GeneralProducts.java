package store.model.purchaseData;

import store.dto.ProductListData;

import java.util.ArrayList;
import java.util.List;

public class GeneralProducts {
    private final List<GeneralProduct> products;
    private long membershipPrice;

    public GeneralProducts(List<GeneralProduct> products) {
        this.products = products;
        this.membershipPrice = 0;
    }

    public List<ProductListData> generateProductListData() {
        List<ProductListData> productListDatas = new ArrayList<>();
        for (GeneralProduct generalProduct : products) {
            productListDatas.add(generalProduct.generateProductListData());
        }
        return productListDatas;
    }

    public void applyMembership() {
        long totalPrice = 0L;
        for (GeneralProduct generalProduct : products) {
            totalPrice += generalProduct.getPrice();
        }

        membershipPrice = Math.min((int) Math.round(totalPrice * 0.3), 8000);
    }

    public long getMembershipPrice() {
        return membershipPrice;
    }

    public long getTotalQuantity() {
        long totalQuantity = 0;
        for (GeneralProduct generalProduct : products) {
            totalQuantity += generalProduct.getPurchaseQuantity();
        }
        return totalQuantity;
    }

    public long getTotalPrice() {
        long totalQuantity = 0;
        for (GeneralProduct generalProduct : products) {
            totalQuantity += generalProduct.getPurchasePrice();
        }
        return totalQuantity;
    }

    public void updateQuantity() {
        for (GeneralProduct generalProduct : products) {
            generalProduct.updateQuantity();
        }
    }
}
