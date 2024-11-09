package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.dto.StockInfo;
import store.dto.Stocks;

public class InputView {

    private static final String STOCK_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n";
    private static final String PURCHASE_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    public String readPurchaseInfo(Stocks stocks) {
        printNowStock(stocks);
        System.out.println(PURCHASE_MESSAGE);
        return Console.readLine();
    }

    private void printNowStock(Stocks stocks) {
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
}
