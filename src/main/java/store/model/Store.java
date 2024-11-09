package store.model;

import store.dto.Stocks;

import java.time.LocalDateTime;

public class Store {
    private final Stock stock;

    public Store(Stock stock) {
        this.stock = stock;
    }

    public Stocks getTodayStock(LocalDateTime today) {
        return stock.makeStocks(today);
    }
}
