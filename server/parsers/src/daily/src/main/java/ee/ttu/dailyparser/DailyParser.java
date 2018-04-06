package ee.ttu.dailyparser;

import java.util.HashMap;
import java.util.LinkedList;

public class DailyParser {
    enum status {
        OPEN, CLOSED
    }
    private LinkedList<HashMap<String, String>> finalMenu;
    private LinkedList<String> menuToBeParsed;
    private HashMap<String, String> providerStatus;

    DailyParser(LinkedList<String> splitMenu) {
        this.finalMenu = new LinkedList<>();
        this.menuToBeParsed = splitMenu;
        parseMenu();
    }

    LinkedList<HashMap<String, String>> getFinalMenu() {
        return finalMenu;
    }

    private void parseMenu() {
        if (menuToBeParsed.size() < 2) {
            setStatus(status.CLOSED);
            finalMenu.add(providerStatus);
        } else {
            setStatus(status.OPEN);
            finalMenu.add(providerStatus);
            for (int i = 0; i < menuToBeParsed.size(); i += 2) {
                HashMap<String, String> menuItem = new HashMap<>();
                String nameWithPrice = menuToBeParsed.get(i);
                Integer priceBeginIndex = nameWithPrice.length() - 4;
                menuItem.put("provider", "daily");
                menuItem.put("name", nameWithPrice.substring(0, priceBeginIndex).trim());
                menuItem.put("price", nameWithPrice.substring(priceBeginIndex));
                menuItem.put("name_eng", menuToBeParsed.get(i + 1).trim());
                finalMenu.add(menuItem);
            }
        }
    }

    private void setStatus(Enum status) {
        HashMap<String, String> providerStatus = new HashMap<>();
        providerStatus.put("provider", "daily");
        providerStatus.put("status", status.toString());
        this.providerStatus = providerStatus;
    }

}
