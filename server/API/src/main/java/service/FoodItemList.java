package service;

import parsers.fooditem.FoodItem;

import java.util.List;
import java.util.Map;

public class FoodItemList {

    private List<FoodItem> data;

    FoodItemList(List<FoodItem> data) {
        this.data = data;
    }

    public List<FoodItem> getData() {
        return data;
    }
}
