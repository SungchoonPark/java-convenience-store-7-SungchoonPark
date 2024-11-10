package store.loader;

import store.model.storeData.Promotion;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PromotionLoader {
    private static final String LINE_DELIMITER = ",";
    private static final String DATE_DELIMITER = "-";

    public static Map<String, Promotion> loadPromotions(Path promotionsFilePath) {
        Map<String, Promotion> promotions = new HashMap<>();

        try (BufferedReader reader = FileLoader.loadFile(promotionsFilePath)) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                addPromotion(promotions, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return promotions;
    }

    private static void addPromotion(Map<String, Promotion> promotions, String line) {
        String[] promotionInfos = line.split(LINE_DELIMITER);
        String promotionName = promotionInfos[0];
        long buy = Long.parseLong(promotionInfos[1]);
        int get = Integer.parseInt(promotionInfos[2]);
        LocalDate startDate = parseDate(promotionInfos[3]);
        LocalDate endDate = parseDate(promotionInfos[4]);

        promotions.put(promotionName, new Promotion(promotionName, buy, get, startDate, endDate));
    }

    private static LocalDate parseDate(String date) {
        String[] dateInfos = date.split(DATE_DELIMITER);
        return LocalDate.of(Integer.parseInt(dateInfos[0]), Integer.parseInt(dateInfos[1]), Integer.parseInt(dateInfos[2]));
    }
}