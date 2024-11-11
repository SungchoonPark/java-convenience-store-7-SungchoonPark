package store.model;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.purchaseData.GeneralProduct;
import store.model.purchaseData.LowPromotionStockProduct;
import store.model.purchaseData.PromotionNotApplyProduct;
import store.model.purchaseData.UnderQuantityProduct;
import store.model.storeData.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class PurchaseItemsTest {
    private PurchaseItems purchaseItems;

    @BeforeEach
    void init() {
        purchaseItems = initPurchaseItems();
    }

    @DisplayName("구매상품중 종류에따라 적절하게 객체생성")
    @Test
    void 구매상품중_종류에따라_적절하게_객체생성() {
        // then
        int expectedGeneralProductSize= 1;
        int expectedPromotionNotApplyProductSize= 1;
        int expectedUnderQuantityProductSize= 1;
        int expectedLowPromotionStockProductSize= 1;

        // when
        List<GeneralProduct> generalProducts = purchaseItems.getGeneralProducts();
        List<PromotionNotApplyProduct> promotionNotApplyProducts = purchaseItems.getPromotionNotApplyProducts();
        List<UnderQuantityProduct> promotionUnderQuantity = purchaseItems.getPromotionUnderQuantity();
        List<LowPromotionStockProduct> lowPromotionStockItems = purchaseItems.getLowPromotionStockItems();

        // then
        Assertions.assertThat(generalProducts).hasSize(expectedGeneralProductSize);
        Assertions.assertThat(promotionNotApplyProducts).hasSize(expectedPromotionNotApplyProductSize);
        Assertions.assertThat(promotionUnderQuantity).hasSize(expectedUnderQuantityProductSize);
        Assertions.assertThat(lowPromotionStockItems).hasSize(expectedLowPromotionStockProductSize);
    }

    private PurchaseItems initPurchaseItems() {
        return new PurchaseItems(
                List.of(
                        new PurchaseItem(
                                new Product(
                                        new ProductInfo("사이다", 3000, 10),
                                        new ProductPromotion(new ArrayList<>()
                                        )
                                ),
                                5
                        ),
                        new PurchaseItem(
                                new Product(
                                        new ProductInfo("컵라면", 3000, 10),
                                        new ProductPromotion(List.of(
                                                new PromotionInfo(
                                                        new Promotion("1+1", 1, 1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
                                                        1
                                                )
                                        )
                                        )
                                ),
                                2
                        ),
                        new PurchaseItem(
                                new Product(
                                        new ProductInfo("초코바", 3000, 10),
                                        new ProductPromotion(List.of(
                                                new PromotionInfo(
                                                        new Promotion("2+1", 2, 1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
                                                        5
                                                )
                                        )
                                        )
                                ),
                                3
                        ),
                        new PurchaseItem(
                                new Product(
                                        new ProductInfo("오렌지주스", 3000, 15),
                                        new ProductPromotion(List.of(
                                                new PromotionInfo(
                                                        new Promotion("2+1", 2, 1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
                                                        5
                                                )
                                        )
                                        )
                                ),
                                2
                        )
                )
        );
    }

}