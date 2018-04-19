package parsers.daily;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DailyParser {
    private List<Map<String, String>> finalMenu;
    private List<String> menuToBeParsed;

    /**
     * Constructor for parsing Daily menu in a linked list form.
     * @param splitMenu incoming menu as a linked list.
     */
    DailyParser(List<String> splitMenu, String facility) {
        this.finalMenu = new LinkedList<>();
        this.menuToBeParsed = splitMenu;
        parseMenu(facility);
    }

    /**
     * Getter method for receiving the menu as a linked list of hashmaps.
     * @return menu as a linked list of hashmaps.
     */
    List<Map<String, String>> getFinalMenu() {
        return finalMenu;
    }

    /**
     * Method that converts incoming linked list menu into a linked list of hashmaps.
     * The first member in the linked list contains the status of the establishment.
     * status - CLOSED: the establishment is closed.
     * status - OPEN: the establishment is open.
     * The following keys are added to the hashmap:
     * provider - value is set as daily.
     * name_est - name of the dish in Estonian.
     * name_eng - name of the dish in English.
     * price - price of the dish.
     */
    private void parseMenu(String facility) {
        // Checks if the facility is open. If false, then the menu is an empty map.
        if (checkIfOpen()) {
            //For loop converts every menu item into a hashmap, inserts them into a linked list.
            for (int i = 0; i < menuToBeParsed.size(); i += 2) {
                Map<String, String> menuItem = new HashMap<>();
                String nameWithPrice = menuToBeParsed.get(i);
                Integer priceBeginIndex = nameWithPrice.length() - 4;
                menuItem.put("provider", "daily_" + facility);
                menuItem.put("name_est", nameWithPrice.substring(0, priceBeginIndex).trim());
                menuItem.put("price", nameWithPrice.substring(priceBeginIndex));
                menuItem.put("name_eng", menuToBeParsed.get(i + 1).trim());
                finalMenu.add(menuItem);
            }
        }
    }

    /**
     * If the menu size is larger than 2, the menu contains more lines than "Suletud".
     * @return true if establishment is open, false if closed.
     */
    private boolean checkIfOpen() {
        return menuToBeParsed.size() > 2;
    }

}
