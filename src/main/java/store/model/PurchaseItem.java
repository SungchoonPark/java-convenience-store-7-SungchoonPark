package store.model;

import store.model.purchaseData.GeneralProduct;
import store.model.purchaseData.LowPromotionStockProduct;
import store.model.purchaseData.PromotionNotApplyProduct;
import store.model.purchaseData.UnderQuantityProduct;

public class PurchaseItem {
    private final Product product;
    private final long purchaseQuantity;

    public PurchaseItem(Product product, long purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }

    public boolean isUnderQuantity() {
        return product.isUnderQuantity(purchaseQuantity);
    }

    public boolean isGeneralProduct() {
        return product.isGeneralProduct();
    }

    public boolean isPromotionNotApplyProduct() {
        return product.isPromotionNotApplyProduct(purchaseQuantity);
    }

    public boolean isLowPromotionStock() {
        return product.isLowPromotionStock(purchaseQuantity);
    }

    public GeneralProduct generateGeneralProduct() {
        return new GeneralProduct(product, purchaseQuantity);
    }

    public PromotionNotApplyProduct generatePromotionNotApplyProduct() {
        return new PromotionNotApplyProduct(product, purchaseQuantity);
    }

    public UnderQuantityProduct generateUnderQuantityProduct() {
        return new UnderQuantityProduct(product, purchaseQuantity);
    }

    public LowPromotionStockProduct generateLowPromotionStock() {
        return new LowPromotionStockProduct(product, purchaseQuantity, product.getLowPromotionStock(purchaseQuantity));
    }
}