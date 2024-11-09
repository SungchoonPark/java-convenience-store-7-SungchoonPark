package store.controller;

import camp.nextstep.edu.missionutils.DateTimes;
import store.dto.Stocks;
import store.dto.UnderQuantityItem;
import store.model.PurchaseItems;
import store.model.TemporaryPurchaseList;
import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;

import java.time.LocalDateTime;
import java.util.List;

public class StoreController {
    private final InputView inputView;
    private final OutputView outputView;
    private final StoreService storeService;

    public StoreController(InputView inputView, OutputView outputView, StoreService storeService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.storeService = storeService;
    }

    public void run() {
        LocalDateTime now = DateTimes.now();
        Stocks storeStock = storeService.getStoreStock(now);

        inputView.printNowStock(storeStock);
        PurchaseItems purchaseItems = getPurchaseList();

        List<UnderQuantityItem> promotionUnderQuantity = storeService.getPromotionUnderQuantity(purchaseItems);
    }

    private PurchaseItems getPurchaseList() {
        while (true) {
            try {
                String userPurchaseInfo = inputView.readPurchaseInfo();
                return validatePurchaseList(new TemporaryPurchaseList(userPurchaseInfo));
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private PurchaseItems validatePurchaseList(TemporaryPurchaseList temporaryPurchaseList) {
        return storeService.generatePurchaseItems(temporaryPurchaseList);
    }

}
