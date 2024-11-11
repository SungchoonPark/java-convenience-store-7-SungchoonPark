package store.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.exception.ExceptionMessage;

class TemporaryPurchaseListTest {
    private TemporaryPurchaseList temporaryPurchaseList;

    @DisplayName("정상적인 상품구분자를 사용")
    @Test
    void 정상적인_상품구분자를_사용() {
        // given
        String validDelimiter = "[사이다-1],[콜라-5]";

        // when
        temporaryPurchaseList = new TemporaryPurchaseList(validDelimiter);

        // then
        Assertions.assertThat(temporaryPurchaseList).isNotNull();
    }

    @DisplayName("상품 구분자가 ',' 가 아닌 경우 예외발생")
    @ParameterizedTest
    @ValueSource(strings = {"[사이다-2].[컵라면-1]", "[사이다-2] [컵라면-1]"})
    void 상품구분자가_쉼표가_아닌_경우_예외발생(String invalidDelimiterInput) {
        // given, when, then
        Assertions.assertThatThrownBy(() -> new TemporaryPurchaseInfo(invalidDelimiterInput))
                .hasMessage(ExceptionMessage.INVALID_INPUT.getMessage());
    }

    @DisplayName("중복된 상품명 입력한 경우 예외발생")
    @Test
    void 중복된_상품명_입력한_경우_예외발생() {
        // given
        String dupleProductName = "[사이다-3],[사이다-1]";

        // when, then
        Assertions.assertThatThrownBy(() -> new TemporaryPurchaseList(dupleProductName))
                .hasMessage(ExceptionMessage.INVALID_INPUT.getMessage());
    }

}