package store.constant;

public enum StoreConstant {
    ITEM_LIST("-");

    private final String value;

    StoreConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
