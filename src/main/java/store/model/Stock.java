package store.model;

import store.dto.StockInfo;
import store.dto.Stocks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private final List<Product> products;

    public Stock(List<Product> products) {
        this.products = products;
    }

    public Stocks makeStocks(LocalDateTime today) {
        List<StockInfo> stockInfos = new ArrayList<>();
        for (Product product : products) {
            stockInfos.add(product.getProductInfo(today));
        }

        return new Stocks(stockInfos);
    }
}
