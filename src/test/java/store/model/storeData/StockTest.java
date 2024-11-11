package store.model.storeData;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.exception.ExceptionMessage;
import store.model.PurchaseItem;
import store.model.TemporaryPurchaseInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class StockTest {

    private Stock stock;

    @BeforeEach
    void init() {
        stock = initStock();
    }

    @DisplayName("존재하는 상품과 수량을 입력한 경우")
    @Test
    void 존재하는_상품과_수량을_입력한_경우_정상() {
        // given
        TemporaryPurchaseInfo validTemporaryPurchaseInfo = new TemporaryPurchaseInfo("[사이다-3]");

        // when
        PurchaseItem purchaseItem = stock.validPurchaseItem(validTemporaryPurchaseInfo);

        // then
        Assertions.assertThat(purchaseItem).isNotNull();
    }

    @DisplayName("존재하지 않는 상품을 입력한 경우 예외발생")
    @Test
    void 존재하지_않는_상품을_입력한_경우_예외발생() {
        // given
        TemporaryPurchaseInfo notExistTemporaryPurchaseInfo = new TemporaryPurchaseInfo("[바카스-3]");

        // when, then
        Assertions.assertThatThrownBy(() -> stock.validPurchaseItem(notExistTemporaryPurchaseInfo))
                .hasMessage(ExceptionMessage.NOT_EXIST_PRODUCT.getMessage());
    }

    @DisplayName("수량을 초과한 경우 예외발생")
    @Test
    void 수량을_초과한_경우_예외발생() {
        // given
        TemporaryPurchaseInfo overStockTemporaryPurchaseInfo = new TemporaryPurchaseInfo("[사이다-21]");

        // when, then
        Assertions.assertThatThrownBy(() -> stock.validPurchaseItem(overStockTemporaryPurchaseInfo))
                .hasMessage(ExceptionMessage.OVER_STOCK.getMessage());
    }

    @DisplayName("수량이 0인 경우 예외발생")
    @Test
    void 수량이_0인_경우_예외발생() {
        // given
        TemporaryPurchaseInfo overStockTemporaryPurchaseInfo = new TemporaryPurchaseInfo("[사이다-0]");

        // when, then
        Assertions.assertThatThrownBy(() -> stock.validPurchaseItem(overStockTemporaryPurchaseInfo))
                .hasMessage(ExceptionMessage.INVALID_INPUT.getMessage());
    }

    private Stock initStock() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(
                        new ProductInfo("사이다", 3000, 10),
                        new ProductPromotion(List.of(
                                new PromotionInfo(
                                        new Promotion("1+1", 1, 1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
                                        10
                                )
                        )
                        )
                )
        );
        products.add(new Product(
                        new ProductInfo("콜라", 2000, 5),
                        new ProductPromotion(List.of(
                                new PromotionInfo(
                                        new Promotion("1+1", 1, 1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
                                        10
                                )
                        )
                        )
                )
        );
        products.add(new Product(
                        new ProductInfo("컵라면", 1000, 1),
                        new ProductPromotion(List.of(
                                new PromotionInfo(
                                        new Promotion("1+1", 1, 1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
                                        10
                                )
                        )
                        )
                )
        );
        return new Stock(products);
    }
}