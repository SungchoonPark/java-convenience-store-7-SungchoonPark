package store.model;

import store.exception.ExceptionMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemporaryPurchaseInfo {
    private static final Pattern VALID_PURCHASE_FORMAT = Pattern.compile("^\\[([a-zA-Z가-힣0-9]+)-(\\d+)\\]$");

    private final String productName;
    private final long quantity;

    public TemporaryPurchaseInfo(String purchaseInfo) {
        String[] infos = parsingPurchaseInfo(purchaseInfo);
        this.productName = infos[0];
        this.quantity = Long.parseLong(infos[1]);
    }

    public String getProductName() {
        return productName;
    }

    public long getQuantity() {
        return quantity;
    }

    private String[] parsingPurchaseInfo(String purchaseInfo) {
        Matcher matcher = VALID_PURCHASE_FORMAT.matcher(purchaseInfo);
        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2)};
        } else {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }

}
