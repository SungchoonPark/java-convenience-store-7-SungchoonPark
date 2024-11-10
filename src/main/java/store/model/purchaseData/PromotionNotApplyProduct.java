package store.model.purchaseData;

import store.dto.FreeInfo;
import store.dto.ProductListData;
import store.model.Product;

import java.text.NumberFormat;

public class PromotionNotApplyProduct {
    private final Product product;
    private long purchaseQuantity;

    public PromotionNotApplyProduct(Product product, long purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }

    // 2+1이 고대로 적용된걸수도 있고, 아니면 아예 미달일 수 있음
    // 증정이 있거나 없거나 두 경우만 존재할 뿐.
    // 증정이 있을 경우 증정을 더해주고 + 금액(행사할인에 포함될 금액임)까지
    // 증정이 없는 경우엔 그냥 무르면됨

    public void updateQuantity() {
        // 구매한 수량만큼 product의 재고에서 깎으면 됨.
    }

    public boolean isExistFree() {
        // 증정이 존재하는지 물어보는 메서드
        return product.isExistFree(purchaseQuantity);
    }

    public long getFreePrice() {
        // 증정 금액 얼만지 구해오는 메서드
        return product.getTotalPrice(product.getFreeCnt(purchaseQuantity));
    }

    public ProductListData generateProductListData() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String price = numberFormat.format(product.getTotalPrice(purchaseQuantity));
        System.out.println("price = " + price);
        return new ProductListData(product.getProductName(), purchaseQuantity, price);
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
}
