package store.controller;

import camp.nextstep.edu.missionutils.DateTimes;
import store.dto.Stocks;
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
    }
}
