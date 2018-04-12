package service;

import java.util.List;
import java.util.Map;

public class foodData {

    private List<Map<String, String>> data;

    public foodData(List<Map<String, String>> data) {
        this.data = data;
    }

    public List<Map<String, String>> getData() {
        return data;
    }
}
