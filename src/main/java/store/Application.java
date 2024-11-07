package store;

import store.controller.StoreController;
import store.loader.DataLoader;
import store.model.Store;
import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // Todo : md 파일로부터 재고 및 프로모션 등록
        Store store = DataLoader.loadData();

        // Todo : 컨트롤러 실행을 통한 애플리케이션 작동
        StoreController storeController = new StoreController(
                new InputView(),
                new OutputView(),
                new StoreService(store)
        );

        storeController.run();
    }
}
