package store.model;

import camp.nextstep.edu.missionutils.DateTimes;
import store.dto.StockInfo;
import store.dto.Stocks;
import store.exception.ExceptionMessage;

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

    public PurchaseItem validPurchaseItem(TemporaryPurchaseInfo purchaseItem) {
        Product product = findProductByName(purchaseItem.getProductName());
        product.checkValidPurchaseQuantity(purchaseItem.getQuantity(), DateTimes.now());

        return new PurchaseItem(product, purchaseItem.getQuantity());
    }

    private Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.isSameProductName(productName)) {
                return product;
            }
        }

        throw new IllegalArgumentException(ExceptionMessage.NOT_EXIST_PRODUCT.getMessage());
    }
}
