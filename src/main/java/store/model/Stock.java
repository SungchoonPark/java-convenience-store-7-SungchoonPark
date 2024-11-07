package store.model;

import java.util.List;

public class Stock {
    private final List<Product> products;

    public Stock(List<Product> products) {
        this.products = products;
    }
}
