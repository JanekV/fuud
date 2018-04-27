package parsers.fooditem;

import java.util.List;

public class FoodItem {
    private String name_est;
    private String name_eng;
    private String price;
    private List<String> providers;

    public FoodItem(String name_est, String name_eng, String price, List<String> providers) {
        this.name_est = name_est;
        this.name_eng = name_eng;
        this.price = price;
        this.providers = providers;
    }

    public String getName_est() {
        return name_est;
    }

    public String getName_eng() {
        return name_eng;
    }

    public String getPrice() {
        return price;
    }

    public List<String> getProviders() {
        return providers;
    }
}
