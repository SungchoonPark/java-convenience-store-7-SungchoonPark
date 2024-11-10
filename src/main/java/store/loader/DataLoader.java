package store.loader;

import store.model.storeData.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class DataLoader {
    private static final String PATH = "src/main/resources/";
    private static final String PRODUCTS_FILE_NAME = "products.md";
    private static final String PROMOTIONS_FILE_NAME = "promotions.md";

    private static final Set<String> productNames = new HashSet<>();
    private static final Map<String, Promotion> promotions = new HashMap<>();
    private static final Map<String, List<PromotionInfo>> productPromotions = new HashMap<>();
    private static final Map<String, ProductInfo> productInfoData = new HashMap<>();
    private static final Map<String, ProductInfo> tmpProductInfo = new HashMap<>();

    private static void loadPromotions() {
        Path promotionsFilePath = Paths.get(PATH, PROMOTIONS_FILE_NAME);

        try (BufferedReader reader = Files.newBufferedReader(promotionsFilePath)) {
            String line;
            reader.readLine();

            while((line = reader.readLine()) != null) {
                String[] promotionInfos = line.split(",");

                String promotionName = promotionInfos[0];
                long buy = Long.parseLong(promotionInfos[1]);
                int get = Integer.parseInt(promotionInfos[2]);
                LocalDate startDate = parsingDate(promotionInfos[3]);
                LocalDate endDate = parsingDate(promotionInfos[4]);
                Promotion promotion = new Promotion(promotionName, buy, get, startDate, endDate);
                promotions.put(promotionName, promotion);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadProducts() {
        Path promotionsFilePath = Paths.get(PATH, PRODUCTS_FILE_NAME);

        try (BufferedReader reader = Files.newBufferedReader(promotionsFilePath)) {
            String line;
            reader.readLine();

            while((line = reader.readLine()) != null) {
                String[] productInfos = line.split(",");

                String productName = productInfos[0];
                long price = Long.parseLong(productInfos[1]);
                long quantity = Long.parseLong(productInfos[2]);
                String promotionName = productInfos[3];

                Promotion promotion = promotions.get(promotionName);

                productNames.add(productName);
                PromotionInfo promotionInfo = new PromotionInfo(promotion, quantity);
                ProductInfo productInfo = new ProductInfo(productName, price, quantity);

                if (promotion == null) {
                    productInfoData.put(productName, productInfo);
                    continue;
                }

                ProductInfo tmpPrInfo = new ProductInfo(productName, price, 0);
                tmpProductInfo.put(productName, tmpPrInfo);

                List<PromotionInfo> productPromotion = productPromotions.getOrDefault(productName, new ArrayList<>());
                productPromotion.add(promotionInfo);
                productPromotions.put(productName, productPromotion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Store setAllStockToStore() {
        List<Product> allStock = new ArrayList<>();
        for (String productName : productNames) {
            ProductInfo productInfo = productInfoData.get(productName);
            ProductPromotion productPromotion = new ProductPromotion(productPromotions.get(productName));

            if (productInfo == null) {
                productInfo = tmpProductInfo.get(productName);
            }

            allStock.add(new Product(productInfo, productPromotion));
        }

        return new Store(new Stock(allStock));
    }

    private static LocalDate parsingDate(String date) {
        String[] dateInfos = date.split("-");
        return LocalDate.of(Integer.parseInt(dateInfos[0]), Integer.parseInt(dateInfos[1]), Integer.parseInt(dateInfos[2]));
    }

    public static Store loadData() {
        loadPromotions();
        loadProducts();

        return setAllStockToStore();
    }
}
