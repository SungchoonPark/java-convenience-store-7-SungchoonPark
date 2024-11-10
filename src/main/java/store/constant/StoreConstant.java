package store.constant;

public enum StoreConstant {
    ITEM_LIST("-"),
    WON("원"),
    COUNT("개"),
    OUT_OF_STOCK("재고 없음");

    private final String value;

    StoreConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
