package parsers.duplicatehunter;

import parsers.fooditem.FoodItem;
import parsers.fooditem.FoodItemBuilder;

import java.util.LinkedList;
import java.util.List;

public class DuplicateHunter {

    /**
     * Class constructor for a DuplicateHunter object.
     */
    public DuplicateHunter() {

    }

    /**
     * Method for scanning through a list of FoodItems.
     * If FoodItems have the same names and prices but a different provider (this might happen within the same
     * food chain), creates a new FoodItem object with the same names and price, but with a provider list containing all
     * the providers that offer the dish.
     *
     * If the FoodItem has no such duplicates it is simply added to the list.
     *
     * Returned list has no duplicates.
     *
     * @param foodItemList - List of FoodItems that needs to be scanned.
     * @return List of FoodItems containing no duplicates.
     */
    public List<FoodItem> noDuplicates(List<FoodItem> foodItemList) {
        List<FoodItem> noDuplicates = new LinkedList<>();
        List<String> handled = new LinkedList<>(); //FoodItem names that have been processed are put here.

        for (FoodItem foodItem : foodItemList) {
            if (handled.contains(foodItem.getName_est())) {
                continue;
            }
            List<String> providers = new LinkedList<>();
            for (FoodItem checkAgainst : foodItemList) {
                //Checks if the FoodItems are equivalent and have a different provider.
                if (checkAgainst.getName_est().equals(foodItem.getName_est())
                        && !checkAgainst.getProviders().contains(foodItem.getProviders().get(0))
                        && checkAgainst.getPrice().equals(foodItem.getPrice())
                        && checkAgainst.getName_eng().equals(foodItem.getName_eng())) {
                            //This clause checks if the provider is already in the providers list.
                            if (!providers.contains(foodItem.getProviders().get(0))) {
                                providers.add(foodItem.getProviders().get(0));
                            }
                            providers.add(checkAgainst.getProviders().get(0));
                        }

            }
            handled.add(foodItem.getName_est());
            //if there are duplicates, the providers list will not be empty.
            if (providers.size() > 0) {
                //a new FoodItem object is created, sharing the same names and price and updated providers list.
                noDuplicates.add(new FoodItemBuilder()
                        .name_est(foodItem.getName_est())
                        .name_eng(foodItem.getName_eng())
                        .price(foodItem.getPrice())
                        .providers(providers)
                        .createFoodItem());
            } else {
                //providers list is empty, no need to create a new object.
                noDuplicates.add(foodItem);
            }
        }
        return noDuplicates;
    }
}
