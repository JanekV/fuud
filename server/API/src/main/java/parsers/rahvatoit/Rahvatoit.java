package parsers.rahvatoit;

import java.io.IOException;
import java.util.*;
import org.json.JSONException;
import parsers.duplicatehunter.DuplicateHunter;
import parsers.fooditem.FoodItem;

/**
 * Method for parsing the Rahva Toit Facebook feed JSON and converting into a list of FoodItems for the API.
 */
public class Rahvatoit {
    private static final String SOC = "soc";
    private static final String RAAMATUKOGU= "raamatukogu";
    private static final String URL_BASE = "https://graph.facebook.com/v2.12/rahvatoitttu/posts?access_token=";
    private static final String ACCESS_TOKEN = "EAACEdEose0cBAH69PiZAEyq8NwgEayxXK3Gl6oQ23nNgJFlvHgUnZB1FlGkkxQCMy7u0izVLKT6ilVpTDcZALfxXchYkwyWsp1kwuhPSEtqZBfBTOqcsBkOz69ILKynUw1LMKA9INleIZALbvI3ZBNQgNrQPtFfHi3OPSZAOb3rL0352BtZCpwUFPzDqBh60RDoZD";

    public static List<FoodItem> parseRahvatoit() throws IOException, JSONException {

        // NB! THE ACCESS TOKEN IS CURRENTLY NOT WORKING! PARSING IS IMPOSSIBLE AS LONG AS THE ACCESS TOKEN IS NOT WORKING!
        JsonParser jsonParser = new JsonParser(URL_BASE + ACCESS_TOKEN);

        MenuParser socParser = new MenuParser(jsonParser.getSocMenu(), SOC);
        MenuParser raamatukoguParser = new MenuParser(jsonParser.getRaamatukoguMenu(), RAAMATUKOGU);

        List<FoodItem> finalMenu = new LinkedList<>();

        finalMenu.addAll(socParser.getMenu());
        finalMenu.addAll(raamatukoguParser.getMenu());

        return new DuplicateHunter().noDuplicates(finalMenu);
    }
}
