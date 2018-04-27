package parsers.rahvatoit;

import parsers.fooditem.FoodItem;
import parsers.fooditem.FoodItemBuilder;

import java.util.*;
import java.util.stream.Stream;

public class MenuParser {
    private final String menuToBeParsed;
    private final String establishment;

    MenuParser(String menuToBeParsed, String establishment) {
        this.menuToBeParsed = menuToBeParsed;
        this.establishment = establishment;
    }

    public List<FoodItem> getMenu() {
        System.out.println(menuToBeParsed);
        System.out.println(establishment);
        List<FoodItem> finalMenu = new LinkedList<>();
        List<String> noEmpties = new LinkedList<>();
        Stream.of(menuToBeParsed.split("\\r?\\n"))
                .filter(line -> !line.equals("")
                        && (line.endsWith("-")
                        || line.endsWith("0")
                        || line.endsWith("5")))
                .forEach(noEmpties::add);
        for (String line : noEmpties) {
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
