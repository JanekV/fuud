package service;

import java.util.List;
import java.util.Map;

public class FoodData {

    private List<Map<String, String>> data;

    FoodData(List<Map<String, String>> data) {
        this.data = data;
    }

    public List<Map<String, String>> getData() {
        return data;
    }
}
