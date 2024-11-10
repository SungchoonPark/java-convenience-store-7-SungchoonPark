package store.view;

import store.dto.*;

import java.util.List;

public class OutputView {
    private static final String STOCK_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n";

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
        System.out.println("\n===========W 편의점=============");
        System.out.printf("%-10s %5s %10s%n", "상품명", "수량", "금액");

        // 상품 리스트 출력
        for (ProductListData product : totalProductList) {
            System.out.printf("%-10s %5d %10s%n", product.productName(), product.quantity(), product.price());
        }

        System.out.println("===========증\t정=============");

        // 증정 품목 출력
        for (FreeInfo free : freeInfo) {
            System.out.printf("%-10s %5d%n", free.productName(), free.freeCnt());
        }

        System.out.println("==============================");
        System.out.printf("%-10s %5d %10s%n", "총구매액", priceInfo.totalQuantity(), priceInfo.totalPrice());
        System.out.printf("%-10s %18s%n", "행사할인", priceInfo.promotionPrice());
        System.out.printf("%-10s %18s%n", "멤버십할인", priceInfo.membershipPrice());
        System.out.printf("%-10s %18s%n", "내실돈", priceInfo.payPrice());
    }
}
