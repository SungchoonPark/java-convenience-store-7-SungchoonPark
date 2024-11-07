package store.model;

public class Product {
    private final ProductInfo productInfo;
    private final ProductPromotion promotions;

    public Product(ProductInfo productInfo, ProductPromotion promotions) {
        this.productInfo = productInfo;
        this.promotions = promotions;
    }
}
