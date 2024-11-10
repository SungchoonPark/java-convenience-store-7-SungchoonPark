package store.dto;

public record ProductListData(
        String productName,
        long quantity,
        String price
) {
}
