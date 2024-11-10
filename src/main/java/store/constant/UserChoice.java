package store.constant;

public enum UserChoice {
    YES("Y"),
    NO("N");

    private final String value;

    UserChoice(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
