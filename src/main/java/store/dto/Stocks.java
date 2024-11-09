package store.dto;

import java.util.List;

public record Stocks(
        List<StockInfo> stocks
) {
}
