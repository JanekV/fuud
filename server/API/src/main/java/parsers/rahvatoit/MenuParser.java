package parsers.rahvatoit;

import parsers.fooditem.FoodItem;
import parsers.fooditem.FoodItemBuilder;

import java.util.*;
import java.util.stream.Stream;

public class MenuParser {
    private final String menuToBeParsed;
    private final String establishment;

    /**
     * Constructor for a MenuParser object.
     * @param menuToBeParsed the menu that needs to be parsed.
     * @param establishment the establishment of the menu.
     */
    MenuParser(String menuToBeParsed, String establishment) {
        this.menuToBeParsed = menuToBeParsed;
        this.establishment = establishment;
    }

    /**
     * Method for converting the string-form menu into a list of FoodItems
     * @return list of FoodItems for the menu.
     */
    public List<FoodItem> getMenu() {
        List<FoodItem> finalMenu = new LinkedList<>();
        List<String> relevantLines = new LinkedList<>();
        //this stream filters out the relevant lines of the menu and adds them to the relevantLines list.
        Stream.of(menuToBeParsed.split("\\r?\\n"))
                .filter(line -> !line.equals("")
                        && (line.endsWith("-")
                        || line.endsWith("0")
                        || line.endsWith("5")
                        || line.endsWith("€")))
                .forEach(relevantLines::add);
        for (String line : relevantLines) {
            //each item in the relevantLines list is converted to a FoodItem and added to the finalMenu list.
            FoodItem menuItem = new FoodItemBuilder()
                    .name_est(line.substring(0, line.lastIndexOf("/") - 1).trim())
                    .name_eng(line.substring(line.lastIndexOf("/") + 1, line.lastIndexOf(" ")).trim())
                    .price(line.substring(line.lastIndexOf(" ")))
                    .providers(Collections.singletonList("rahvatoit_" + establishment))
                    .createFoodItem();
            finalMenu.add(menuItem);
        }
        return finalMenu;
    }
}
