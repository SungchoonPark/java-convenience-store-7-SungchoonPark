package store.dto;

import java.text.NumberFormat;

public record PriceInfo(
        long totalQuantity,
        String totalPrice,
        String promotionPrice,
        String membershipPrice,
        String payPrice
) {
    public PriceInfo(long totalQuantity, long totalPrice, long promotionPrice, long membershipPrice, long payPrice) {
        this(
                totalQuantity,
                NumberFormat.getNumberInstance().format(totalPrice),
                "-" + NumberFormat.getNumberInstance().format(promotionPrice),
                "-" + NumberFormat.getNumberInstance().format(membershipPrice),
                NumberFormat.getNumberInstance().format(payPrice)
        );
    }
}