package store.model.storeData;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PromotionInfoTest {

    @DisplayName("프로모션 재고 수량 부족 파악")
    @Test
    void 프로모션_재고_수량_부족_파악() {
        // given
        PromotionInfo promotionInfo = new PromotionInfo(
                new Promotion("1+1", 1, 1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
                1
        );
        long expectedLowPromotionStock = 2;

        // when
        boolean isLowPromotionStock = promotionInfo.isLowPromotionStock(2);
        long actualLowPromotionStock = promotionInfo.calculateLowPromotionStock(2);

        // then
        Assertions.assertThat(isLowPromotionStock).isTrue();
        Assertions.assertThat(actualLowPromotionStock).isEqualTo(expectedLowPromotionStock);
    }

    @DisplayName("정가구매를 원하지 않는 경우 해당 재고 삭제")
    @Test
    void 정가구매를_원하지_않는_경우_해당_재고_삭제() {
        // then
        PromotionInfo promotionInfo = new PromotionInfo(
                new Promotion("1+1", 1, 1, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
                1
        );
        long expectedPromotionStock = 0;

        // when
        boolean isLowPromotionStock = promotionInfo.isLowPromotionStock(2);
        promotionInfo.minusPromotionQuantity(2);
        long actualPromotionStock = promotionInfo.getPromotionQuantity();

        // then
        Assertions.assertThat(isLowPromotionStock).isTrue();
        Assertions.assertThat(actualPromotionStock).isEqualTo(expectedPromotionStock);
    }
}