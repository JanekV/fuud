package service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parsers.bitstop.BitStopParser;
import parsers.daily.DailyMain;

import java.io.IOException;

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

    @GetMapping("/daily")
    public FoodData daily() {
        try {
            return new FoodData(DailyMain.getMenuData());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
