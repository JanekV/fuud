package parsers.fooditem;

import java.util.List;

public class FoodItemBuilder {
    private String name_est;
    private String name_eng;
    private String price;
    private List<String> providers;

    public FoodItemBuilder name_est(String name_est) {
        this.name_est = name_est;
        return this;
    }

    public FoodItemBuilder name_eng(String name_eng) {
        this.name_eng = name_eng;
        return this;
    }

    public FoodItemBuilder price(String price) {
        this.price = price;
        return this;
    }

    public FoodItemBuilder providers(List<String> providers) {
        this.providers = providers;
        return this;
    }

    public FoodItem createFoodItem() {
        return new FoodItem(name_est, name_eng, price, providers);
    }
}