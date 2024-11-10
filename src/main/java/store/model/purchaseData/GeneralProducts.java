package store.model.purchaseData;

import store.dto.ProductListData;

import java.util.ArrayList;
import java.util.List;

public class GeneralProducts {
    private final List<GeneralProduct> generalProducts;
    private long membershipPrice;

    public GeneralProducts(List<GeneralProduct> generalProducts) {
        this.generalProducts = generalProducts;
        this.membershipPrice = 0;
    }

    public List<ProductListData> generateProductListData() {
        List<ProductListData> productListDatas = new ArrayList<>();
        for (GeneralProduct generalProduct : generalProducts) {
            productListDatas.add(generalProduct.generateProductListData());
        }
        return productListDatas;
    }

    public void applyMembership() {
        long totalPrice = 0L;
        for (GeneralProduct generalProduct : generalProducts) {
            totalPrice += generalProduct.getPrice();
        }

        membershipPrice =  Math.min((int) Math.round(totalPrice * 0.3), 8000);
    }

    public long getMembershipPrice() {
        return membershipPrice;
    }

    public long getTotalQuantity() {
        long totalQuantity = 0;
        for (GeneralProduct generalProduct : generalProducts) {
            totalQuantity += generalProduct.getPurchaseQuantity();
        }
        return totalQuantity;
    }

    public long getTotalPrice() {
        long totalQuantity = 0;
        for (GeneralProduct generalProduct : generalProducts) {
            totalQuantity += generalProduct.getPurchasePrice();
        }
        return totalQuantity;
    }

    public void updateQuantity() {
        for (GeneralProduct generalProduct : generalProducts) {
            generalProduct.updateQuantity();
        }
    }
}
