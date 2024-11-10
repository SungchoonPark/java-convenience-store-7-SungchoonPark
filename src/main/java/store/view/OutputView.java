package store.view;

import store.dto.*;

import java.util.List;

public class OutputView {
    private static final String STOCK_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n";

    private static final String HEADER = "\n===========W 편의점=============";
    private static final String PRODUCT_FORMAT = "%-10s %5s %10s%n";
    private static final String PRODUCT_HEADER = "상품명";
    private static final String QUANTITY_HEADER = "수량";
    private static final String PRICE_HEADER = "금액";
    private static final String GIFT_HEADER = "===========증\t정=============";
    private static final String FOOTER = "==============================";
    private static final String TOTAL_AMOUNT_LABEL = "총구매액";
    private static final String PROMOTION_DISCOUNT_LABEL = "행사할인";
    private static final String MEMBERSHIP_DISCOUNT_LABEL = "멤버십할인";
    private static final String PAY_AMOUNT_LABEL = "내실돈";

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public void printNowStock(Stocks stocks) {
        System.out.println(STOCK_MESSAGE);
        System.out.println(formatNowStock(stocks));
    }

    private String formatNowStock(Stocks stocks) {
        StringBuilder nowStock = new StringBuilder();
        for (StockInfo stock : stocks.stocks()) {
            if (stock.promotionInfo() == null) {
                nowStock.append("- ").append(stock.formatGeneralInfo()).append("\n");
                continue;
            }

            nowStock.append("- ").append(stock.formatPromotionInfo()).append("\n");
            nowStock.append("- ").append(stock.formatGeneralInfo()).append("\n");
        }

        return nowStock.toString();
    }

    public void printPurchasePayInfo(List<ProductListData> totalProductList, List<FreeInfo> freeInfo, PriceInfo priceInfo) {
        System.out.println(HEADER);
        System.out.printf(PRODUCT_FORMAT, PRODUCT_HEADER, QUANTITY_HEADER, PRICE_HEADER);

        for (ProductListData product : totalProductList) {
            System.out.printf(PRODUCT_FORMAT, product.productName(), product.quantity(), product.price());
        }

        System.out.println(GIFT_HEADER);

        for (FreeInfo free : freeInfo) {
            if (free.freeCnt() == 0) continue;
            System.out.printf("%-10s %5d%n", free.productName(), free.freeCnt());
        }

        System.out.println(FOOTER);
        System.out.printf(PRODUCT_FORMAT, TOTAL_AMOUNT_LABEL, priceInfo.totalQuantity(), priceInfo.totalPrice());
        System.out.printf("%-10s %18s%n", PROMOTION_DISCOUNT_LABEL, priceInfo.promotionPrice());
        System.out.printf("%-10s %18s%n", MEMBERSHIP_DISCOUNT_LABEL, priceInfo.membershipPrice());
        System.out.printf("%-10s %18s%n", PAY_AMOUNT_LABEL, priceInfo.payPrice());
    }
}
