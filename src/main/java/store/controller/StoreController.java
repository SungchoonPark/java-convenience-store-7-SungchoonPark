package store.controller;

import camp.nextstep.edu.missionutils.DateTimes;
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
        Stocks storeStock = storeService.getStoreStock(now);
        outputView.printNowStock(storeStock);

        PurchaseItems purchaseItems = getPurchaseList();
        PurchaseProducts purchaseProducts = storeService.getPurchaseProducts(purchaseItems);
        readUserChoice(purchaseProducts);

        if (readApplyMembershipUserChoice().equals("Y")) {
            purchaseProducts.applyMembership();
        }

        // 영수증 출력.
        printPurchasePayInfo(
                purchaseProducts.getTotalProductList(),
                purchaseProducts.getFreeInfo(),
                purchaseProducts.getPriceInfo()
        );

        // 재고 갱신
        purchaseProducts.updateQuantity();

        // 다시할건지 입력받기
        if (readRePurchaseUserChoice().equals("Y")) {
            run();
        }
    }

    private String readRePurchaseUserChoice() {
        while(true) {
            try {
                return inputView.printRePurchase();
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private String readApplyMembershipUserChoice() {
        while(true) {
            try {
                return inputView.printMembership();
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private void readUserChoice(PurchaseProducts purchaseProducts) {
        for (UnderQuantityProduct underQuantityProduct : purchaseProducts.getUnderQuantityProducts()) {
            if (readUnderQuantityUserChoice(underQuantityProduct).equals("Y")) {
                underQuantityProduct.updatePurchaseQuantity();
            }
        }
        for (LowPromotionStockProduct lowPromotionStockProduct : purchaseProducts.getLowPromotionStockProducts()) {
            if(readLowPromotionStockUserChoice(lowPromotionStockProduct).equals("N")) {
                lowPromotionStockProduct.minusNotPurchaseQuantity();
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
