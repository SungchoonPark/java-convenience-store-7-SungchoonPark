package store.loader;

import store.model.storeData.*;

import java.nio.file.Paths;
import java.util.*;

public class DataLoader {
    private static final String PATH = "src/main/resources/";
    private static final String PRODUCTS_FILE_NAME = "products.md";
    private static final String PROMOTIONS_FILE_NAME = "promotions.md";

    public static Store loadData() {
        Set<String> productNames = new HashSet<>();
        Map<String, Promotion> promotions = loadPromotions();
        Map<String, ProductInfo> productInfoData = new HashMap<>();
        Map<String, ProductInfo> tmpProductInfo = new HashMap<>();
        Map<String, List<PromotionInfo>> productPromotions = new HashMap<>();

        loadProducts(productNames, promotions, productInfoData, tmpProductInfo, productPromotions);

        return createStore(productNames, productInfoData, tmpProductInfo, productPromotions);
    }

    private static Map<String, Promotion> loadPromotions() {
        return PromotionLoader.loadPromotions(Paths.get(PATH, PROMOTIONS_FILE_NAME));
    }

    private static void loadProducts(
            Set<String> productNames,
            Map<String, Promotion> promotions,
            Map<String, ProductInfo> productInfoData,
            Map<String, ProductInfo> tmpProductInfo,
            Map<String, List<PromotionInfo>> productPromotions
    ) {
        ProductLoader.loadProducts(Paths.get(PATH, PRODUCTS_FILE_NAME), productNames, promotions, productInfoData, tmpProductInfo, productPromotions);
    }

    private static Store createStore(
            Set<String> productNames,
            Map<String, ProductInfo> productInfoData,
            Map<String, ProductInfo> tmpProductInfo,
            Map<String, List<PromotionInfo>> productPromotions
    ) {
        List<Product> allStock = createAllStock(productNames, productInfoData, tmpProductInfo, productPromotions);
        return new Store(new Stock(allStock));
    }

    private static List<Product> createAllStock(
            Set<String> productNames,
            Map<String, ProductInfo> productInfoData,
            Map<String, ProductInfo> tmpProductInfo,
            Map<String, List<PromotionInfo>> productPromotions
    ) {
        List<Product> allStock = new ArrayList<>();
        for (String productName : productNames) {
            ProductInfo productInfo = productInfoData.getOrDefault(productName, tmpProductInfo.get(productName));
            ProductPromotion productPromotion = new ProductPromotion(productPromotions.get(productName));
            allStock.add(new Product(productInfo, productPromotion));
        }
        return allStock;
    }
}