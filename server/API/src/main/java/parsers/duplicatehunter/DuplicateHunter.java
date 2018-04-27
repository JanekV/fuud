package parsers.duplicatehunter;

import parsers.fooditem.FoodItem;
import parsers.fooditem.FoodItemBuilder;

import java.util.LinkedList;
import java.util.List;

public class DuplicateHunter {
    public DuplicateHunter() {

    }

    public List<FoodItem> noDuplicates(List<FoodItem> foodItemList) {
        List<FoodItem> noDuplicates = new LinkedList<>();
        List<String> handled = new LinkedList<>();

        for (FoodItem foodItem : foodItemList) {
            if (handled.contains(foodItem.getName_est())) {
                continue;
            }
            List<String> providers = new LinkedList<>();
            for (FoodItem checkAgainst : foodItemList) {
                if (checkAgainst.getName_est().equals(foodItem.getName_est())
                        && !checkAgainst.getProviders().contains(foodItem.getProviders().get(0))
                        && checkAgainst.getPrice().equals(foodItem.getPrice())
                        && checkAgainst.getName_eng().equals(foodItem.getName_eng())) {
                            if (!providers.contains(foodItem.getProviders().get(0))) {
                                providers.add(foodItem.getProviders().get(0));
                            }
                            providers.add(checkAgainst.getProviders().get(0));
                        }

            }
            handled.add(foodItem.getName_est());
            if (providers.size() > 0) {
                noDuplicates.add(new FoodItemBuilder()
                        .name_est(foodItem.getName_est())
                        .name_eng(foodItem.getName_eng())
                        .price(foodItem.getPrice())
                        .providers(providers)
                        .createFoodItem());
            } else {
                noDuplicates.add(foodItem);
            }
        }
        return noDuplicates;
    }
}
