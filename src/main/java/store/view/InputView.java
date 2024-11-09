package store.view;

import store.dto.StockInfo;
import store.dto.Stocks;

public class InputView {

    private static final String STOCK_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n";

    public void printNowStock(Stocks stocks) {
        System.out.println(STOCK_MESSAGE);
        System.out.println(formatNowStock(stocks));
    }

    private String formatNowStock(Stocks stocks) {
        StringBuilder nowStock = new StringBuilder();
        for (StockInfo stock : stocks.stocks()) {
            nowStock.append("- ");
            if (stock.promotionInfo() == null) {
                nowStock.append(stock.formatGeneralInfo()).append("\n");
                continue;
            }

            nowStock.append(stock.formatPromotionInfo()).append("\n");
            nowStock.append("- ").append(stock.formatGeneralInfo()).append("\n");
        }

        return nowStock.toString();
    }
}
