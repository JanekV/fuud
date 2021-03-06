package service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import parsers.bitstop.BitStopParser;
import parsers.daily.DailyMain;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
public class ResponseController {

    @GetMapping("/bitstop")
    public FoodItemList bitstop() {
        try {
            return new FoodItemList(BitStopParser.getBitStopDataList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/daily")
    public FoodItemList daily() {
        try {
            return new FoodItemList(DailyMain.getMenuData());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/downloadDailyMenus")
    public List<String> downloadDailyMenus() {
        DailyMain.downloadMenus();
        return Collections.singletonList("Daily data updated.");
    }
}
