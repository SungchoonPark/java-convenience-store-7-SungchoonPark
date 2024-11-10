package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.exception.ExceptionMessage;
import store.model.purchaseData.LowPromotionStockProduct;

public class InputView {

    private static final String PURCHASE_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String UNDER_QUANTITY_MESSAGE = "현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String LOW_PROMOTION_STOCK_MESSAGE = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private static final String MEMBERSHIP_MESSAGE = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String RE_PURCHASE_MESSAGE = "\n감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";

    public String readPurchaseInfo() {
        System.out.println(PURCHASE_MESSAGE);
        String purchaseInfo = Console.readLine();
        checkUserInputIsNull(purchaseInfo);
        return purchaseInfo;
    }

    public String readUnderQuantityUserChoice(String productName) {
        return readUserChoice(UNDER_QUANTITY_MESSAGE.formatted(productName));
    }

    public String readLowPromotionStockUserChoice(LowPromotionStockProduct lowPromotionStockProduct) {
        return readUserChoice(LOW_PROMOTION_STOCK_MESSAGE.formatted(lowPromotionStockProduct.getProductName(), lowPromotionStockProduct.getLowQuantity()));
    }

    public String readMembershipUserChoice() {
        return readUserChoice(MEMBERSHIP_MESSAGE);
    }

    public String printRePurchase() {
        return readUserChoice(RE_PURCHASE_MESSAGE);
    }

    private String readUserChoice(String message) {
        System.out.println(message);
        String userChoice = Console.readLine();
        checkUserInputIsNull(userChoice);
        checkValidateUserChoice(userChoice);
        return userChoice;
    }

    private void checkUserInputIsNull(String userInput) {
        if (userInput.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }

    private void checkValidateUserChoice(String userChoice) {
        if (!userChoice.contains("Y") && !userChoice.contains("N")) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }
}
