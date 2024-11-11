package store.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.exception.ExceptionMessage;

class TemporaryPurchaseInfoTest {
    private TemporaryPurchaseInfo temporaryPurchaseInfo;

    @DisplayName("정상적인 구매 형식")
    @Test
    void 정상적인_구매_형식() {
        // given
        String validPurchaseFormat = "[사이다-1]";

        // when
        temporaryPurchaseInfo = new TemporaryPurchaseInfo(validPurchaseFormat);

        // then
        Assertions.assertThat(temporaryPurchaseInfo).isNotNull();
    }

    @DisplayName("비정상적인 구매 형식인 경우 예외발생")
    @ParameterizedTest
    @ValueSource(strings = {"{사이다-1}", "사이다 1개", "[콜라=5]", "{맥주-10}"})
    void 비정상적인_구매_형식의_경우_예외발생(String invalidPurchaseFormat) {
        // given, when, then
        Assertions.assertThatThrownBy(() -> new TemporaryPurchaseInfo(invalidPurchaseFormat))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_PRODUCT_AND_STOCK_INPUT.getMessage());
    }


}