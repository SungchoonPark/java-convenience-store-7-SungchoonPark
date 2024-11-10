package store.controller;

import camp.nextstep.edu.missionutils.DateTimes;
import store.dto.Stocks;
import store.model.PurchaseItems;
import store.model.TemporaryPurchaseList;
import store.model.purchaseData.LowPromotionStockProduct;
import store.model.purchaseData.PurchaseProducts;
import store.model.purchaseData.UnderQuantityProduct;
import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;

import java.time.LocalDateTime;

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
        PurchaseProducts purchaseProducts = storeService.getPurchaseProducts(purchaseItems);
        readUserChoice(purchaseProducts);

        // 영수증 보여주기
    }

    private void readUserChoice(PurchaseProducts purchaseProducts) {
        for (UnderQuantityProduct underQuantityProduct : purchaseProducts.getUnderQuantityProducts()) {
            if (readUnderQuantityUserChoice(underQuantityProduct).equals("Y")) {
                underQuantityProduct.updatePurchaseQuantity();
            }
        }
        for (LowPromotionStockProduct lowPromotionStockProduct : purchaseProducts.getLowPromotionStockProducts()) {
            if(readLowPromotionStockUserChoice(lowPromotionStockProduct).equals("N")) {
                lowPromotionStockProduct.updateQuantity();
            }
        }
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

    private String readUnderQuantityUserChoice(UnderQuantityProduct underQuantityProduct) {
        while (true) {
            try {
                return inputView.readUnderQuantityUserChoice(underQuantityProduct.getProductName());
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private String readLowPromotionStockUserChoice(LowPromotionStockProduct lowPromotionStockProduct) {
        while (true) {
            try {
                return inputView.readLowPromotionStockUserChoice(lowPromotionStockProduct);
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

}
