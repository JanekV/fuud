package parsers.rahvatoit;

import java.io.IOException;
import java.util.*;
import org.json.JSONException;


public class Rahvatoit {
    private static final String SOC = "soc";
    private static final String RAAMATUKOGU= "raamatukogu";

    public static List<Map<String, String>> parseRahvatoit() throws IOException, JSONException {
        JsonParser jsonParser = new JsonParser("https://graph.facebook.com/v2.12/rahvatoitttu/posts?access_token=EAACEdEose0cBAL3yYr6AjzRU0xFeosH291sxuYPFiTpgYN38ir7c7GUhZA3ALhycK9X3ZCmGujtbJXNwMqb0AmtuloTvowQDdZBs0L1PNkmHpDt1zissV3WpwrSPvgKrQ1G51rB3TXVbs1FcmiZBIUwed0AvvGV9I5ueKlgZC8gmWh8sapfbNcBiWI9umZCe2WjJNZAeA7QswZDZD");

        MenuParser socParser = new MenuParser(jsonParser.getSocMenu(), SOC);
        MenuParser raamatukoguParser = new MenuParser(jsonParser.getRaamatukoguMenu(), RAAMATUKOGU);

        System.out.println(socParser.getMenu());
        System.out.println(raamatukoguParser.getMenu());

        List<Map<String, String>> finalMenu = new LinkedList<>();

        finalMenu.addAll(socParser.getMenu());
        finalMenu.addAll(raamatukoguParser.getMenu());

        return finalMenu;
    }
}
