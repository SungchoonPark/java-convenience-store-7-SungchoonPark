package store.model.purchaseData;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.storeData.*;

import java.time.LocalDate;
import java.util.List;


class UnderQuantityProductTest {

    @DisplayName("무료로 받을 수 있는 수량이 있는 경우 구매 수량 증가")
    @Test
    void 무료로_받을수_있는_수량이_있는_경우_구매수량_증가() {
        // given
        Product purchaseProduct = new Product(
                new ProductInfo("사이다", 3000, 10),
                new ProductPromotion(List.of(
                        new PromotionInfo(
                                new Promotion("1+1", 1, 1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
                                10
                        )
                )
                )
        );
        UnderQuantityProduct underQuantityProduct = new UnderQuantityProduct(purchaseProduct, 1);
        long expectedPurchaseQuantity = 2;

        // when
        underQuantityProduct.updatePurchaseQuantity();
        long actualPurchaseQuantity = underQuantityProduct.getPurchaseQuantity();

        // then
        Assertions.assertThat(actualPurchaseQuantity).isEqualTo(expectedPurchaseQuantity);
    }
}