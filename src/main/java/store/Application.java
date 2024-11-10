package store;

import store.controller.StoreController;
import store.loader.DataLoader;
import store.model.storeData.Store;
import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Store store = DataLoader.loadData();

        StoreController storeController = new StoreController(
                new InputView(),
                new OutputView(),
                new StoreService(store)
        );

        storeController.run();
    }
}
