package store.loader;

import store.model.storeData.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class ProductLoader {
    private static final String LINE_DELIMITER = ",";

    public static void loadProducts(
            Path productsFilePath,
            Set<String> productNames,
            Map<String, Promotion> promotions,
            Map<String, ProductInfo> productInfoData,
            Map<String, ProductInfo> tmpProductInfo,
            Map<String, List<PromotionInfo>> productPromotions
    ) {
        try (BufferedReader reader = FileLoader.loadFile(productsFilePath)) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                processProductLine(line, productNames, promotions, productInfoData, tmpProductInfo, productPromotions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processProductLine(
            String line,
            Set<String> productNames,
            Map<String, Promotion> promotions,
            Map<String, ProductInfo> productInfoData,
            Map<String, ProductInfo> tmpProductInfo,
            Map<String, List<PromotionInfo>> productPromotions
    ) {
        String[] productInfos = line.split(LINE_DELIMITER);
        String productName = productInfos[0];
        long price = Long.parseLong(productInfos[1]);
        long quantity = Long.parseLong(productInfos[2]);
        String promotionName = productInfos[3];

        addProduct(productName, price, quantity, promotionName, productNames, promotions, productInfoData, tmpProductInfo, productPromotions);
    }

    private static void addProduct(
            String productName,
            long price,
            long quantity,
            String promotionName,
            Set<String> productNames,
            Map<String, Promotion> promotions,
            Map<String, ProductInfo> productInfoData,
            Map<String, ProductInfo> tmpProductInfo,
            Map<String, List<PromotionInfo>> productPromotions
    ) {
        Promotion promotion = promotions.get(promotionName);
        productNames.add(productName);
        ProductInfo productInfo = new ProductInfo(productName, price, quantity);

        if (promotion == null) {
            productInfoData.put(productName, productInfo);
        } else {
            handlePromotionProduct(productName, price, quantity, promotion, tmpProductInfo, productPromotions);
        }
    }

    private static void handlePromotionProduct(
            String productName,
            long price,
            long quantity,
            Promotion promotion,
            Map<String, ProductInfo> tmpProductInfo,
            Map<String, List<PromotionInfo>> productPromotions
    ) {
        tmpProductInfo.put(productName, new ProductInfo(productName, price, 0));
        List<PromotionInfo> productPromotion = productPromotions
                .computeIfAbsent(productName, k -> new ArrayList<>());
        productPromotion.add(new PromotionInfo(promotion, quantity));
    }
}