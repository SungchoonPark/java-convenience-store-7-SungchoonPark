package store.model.purchaseData;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.dto.FreeInfo;
import store.dto.PriceInfo;
import store.dto.ProductListData;
import store.model.PurchaseItem;
import store.model.PurchaseItems;
import store.model.storeData.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class PurchaseProductsTest {
    private PurchaseProducts purchaseProducts;

    @BeforeEach
    void init() {
        purchaseProducts = initPurchaseProducts();
    }

    @DisplayName("구매 리스트 가져오기")
    @Test
    void 구매_리스트_가져오기() {
        // given
        List<ProductListData> expectedProductList = List.of(
                new ProductListData("컵라면", 2, "6,000"),
                new ProductListData("사이다", 5, "15,000"),
                new ProductListData("초코바", 3, "9,000"),
                new ProductListData("오렌지주스", 2, "6,000")
        );

        // when
        List<ProductListData> actualProductList = purchaseProducts.getTotalProductList();

        // then
        Assertions.assertThat(actualProductList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedProductList);
    }

    @DisplayName("증정정보 가져오기")
    @Test
    void 증정정보_가져오기() {
        // given
        List<FreeInfo> expectedFreeInfos = List.of(
                new FreeInfo("컵라면", 1),
                new FreeInfo("초코바", 1),
                new FreeInfo("오렌지주스", 0)
        );

        // when
        List<FreeInfo> actualFreeInfos = purchaseProducts.getFreeInfo();

        // then
        Assertions.assertThat(actualFreeInfos)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedFreeInfos);
    }

    @DisplayName("구매정보 가져오기")
    @Test
    void 구매_금액_정보_가져오기() {
        // given
        PriceInfo expectedPriceInfo = new PriceInfo(
                12,
                36000,
                6000,
                4500,
                25500
        );
        // when
        purchaseProducts.applyMembership();
        PriceInfo actualPriceInfo = purchaseProducts.getPriceInfo();

        // then
        Assertions.assertThat(actualPriceInfo.totalQuantity()).isEqualTo(expectedPriceInfo.totalQuantity());
        Assertions.assertThat(actualPriceInfo.totalPrice()).isEqualTo(expectedPriceInfo.totalPrice());
        Assertions.assertThat(actualPriceInfo.promotionPrice()).isEqualTo(expectedPriceInfo.promotionPrice());
        Assertions.assertThat(actualPriceInfo.membershipPrice()).isEqualTo(expectedPriceInfo.membershipPrice());
        Assertions.assertThat(actualPriceInfo.payPrice()).isEqualTo(expectedPriceInfo.payPrice());
    }

    private PurchaseProducts initPurchaseProducts() {
        PurchaseItems purchaseItems = initPurchaseItems();
        return new PurchaseProducts(
                purchaseItems.getGeneralProducts(),
                purchaseItems.getPromotionNotApplyProducts(),
                purchaseItems.getPromotionUnderQuantity(),
                purchaseItems.getLowPromotionStockItems()
        );
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
                                                        2
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