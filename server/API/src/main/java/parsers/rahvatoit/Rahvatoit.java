package parsers.rahvatoit;

import java.io.IOException;
import java.util.*;
import org.json.JSONException;
import parsers.fooditem.FoodItem;


public class Rahvatoit {
    private static final String SOC = "soc";
    private static final String RAAMATUKOGU= "raamatukogu";

    public static List<FoodItem> parseRahvatoit() throws IOException, JSONException {
        JsonParser jsonParser = new JsonParser("https://graph.facebook.com/v2.12/rahvatoitttu/posts?access_token=EAACEdEose0cBAH69PiZAEyq8NwgEayxXK3Gl6oQ23nNgJFlvHgUnZB1FlGkkxQCMy7u0izVLKT6ilVpTDcZALfxXchYkwyWsp1kwuhPSEtqZBfBTOqcsBkOz69ILKynUw1LMKA9INleIZALbvI3ZBNQgNrQPtFfHi3OPSZAOb3rL0352BtZCpwUFPzDqBh60RDoZD");

        MenuParser socParser = new MenuParser(jsonParser.getSocMenu(), SOC);
        MenuParser raamatukoguParser = new MenuParser(jsonParser.getRaamatukoguMenu(), RAAMATUKOGU);

        System.out.println(socParser.getMenu());
        System.out.println(raamatukoguParser.getMenu());

        List<FoodItem> finalMenu = new LinkedList<>();

        finalMenu.addAll(socParser.getMenu());
        finalMenu.addAll(raamatukoguParser.getMenu());

        return finalMenu;
    }
}
