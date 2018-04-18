package service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parsers.bitstop.BitStopParser;
import parsers.daily.DailyReturnAllMenus;

import java.io.IOException;
import java.util.Arrays;

@CrossOrigin
@RestController
public class ResponseController {

    @GetMapping("/bitstop")
    public FoodData bitstop() {
        try {
            return new FoodData(BitStopParser.getBitStopDataList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/daily_itmaja")
    public FoodData dailyItMaja() {
        try {
            return new FoodData(DailyReturnAllMenus.getDailyMenus());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
