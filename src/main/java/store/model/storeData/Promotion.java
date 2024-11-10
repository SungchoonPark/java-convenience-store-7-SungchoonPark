package store.model.storeData;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Promotion {
    private final String name;
    private final long buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, long buy, int get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPromotionName() {
        return name;
    }

    public boolean isOngoingPromotion(LocalDateTime today) {
        LocalDate todayDate = today.toLocalDate();

        return (todayDate.isEqual(startDate) || todayDate.isAfter(startDate))
                && (todayDate.isEqual(endDate) || todayDate.isBefore(endDate));
    }

    public boolean isUnderQuantity(long purchaseQuantity) {
        return purchaseQuantity % (buy + get) == buy;
    }

    public long getTotalEventValue (){
        return buy + get;
    }
}
