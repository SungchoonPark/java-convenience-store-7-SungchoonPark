package store.controller;

import camp.nextstep.edu.missionutils.DateTimes;
import store.constant.UserChoice;
import store.dto.FreeInfo;
import store.dto.PriceInfo;
import store.dto.ProductListData;
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
        printTodayStoreStock(now);
        PurchaseProducts purchaseProducts = storeService.getPurchaseProducts(getPurchaseList());
        readUserChoice(purchaseProducts);
        applyMembershipByUserChoice(purchaseProducts);
        printPurchasePayInfo(storeService.getTotalProductList(purchaseProducts), storeService.getFreeInfo(purchaseProducts), storeService.getPriceInfo(purchaseProducts));
        storeService.updateProductQuantity(purchaseProducts);
        rePurchaseByUserChoice();
    }

    private void rePurchaseByUserChoice() {
        if (readRePurchaseUserChoice().equals(UserChoice.YES.getValue())) {
            run();
        }
    }

    private void applyMembershipByUserChoice(PurchaseProducts purchaseProducts) {
        if (readApplyMembershipUserChoice().equals(UserChoice.YES.getValue())) {
            storeService.applyMembership(purchaseProducts);
        }
    }

    private void printTodayStoreStock(LocalDateTime today) {
        Stocks storeStock = storeService.getStoreStock(today);
        outputView.printNowStock(storeStock);
    }

    private String readRePurchaseUserChoice() {
        while (true) {
            try {
                return inputView.printRePurchase();
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private String readApplyMembershipUserChoice() {
        while (true) {
            try {
                return inputView.readMembershipUserChoice();
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private void readUserChoice(PurchaseProducts purchaseProducts) {
        readQuantityItemsUserUserChoice(purchaseProducts);
        readLowPromotionStockProductsUserChoice(purchaseProducts);
    }

    private void readLowPromotionStockProductsUserChoice(PurchaseProducts purchaseProducts) {
        for (LowPromotionStockProduct lowPromotionStockProduct : purchaseProducts.getLowPromotionStockProducts()) {
            if (readLowPromotionStockUserChoice(lowPromotionStockProduct).equals(UserChoice.NO.getValue())) {
                lowPromotionStockProduct.minusNotPurchaseQuantity();
            }
        }
    }

    private void readQuantityItemsUserUserChoice(PurchaseProducts purchaseProducts) {
        for (UnderQuantityProduct underQuantityProduct : purchaseProducts.getUnderQuantityProducts()) {
            if (readUnderQuantityUserChoice(underQuantityProduct).equals(UserChoice.YES.getValue())) {
                underQuantityProduct.updatePurchaseQuantity();
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

    private void printPurchasePayInfo(List<ProductListData> totalProductList, List<FreeInfo> freeInfo, PriceInfo priceInfo) {
        outputView.printPurchasePayInfo(totalProductList, freeInfo, priceInfo);
    }
}
