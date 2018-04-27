package parsers.rahvatoit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MenuParser {
    private final String menuToBeParsed;
    private final String establishment;

    MenuParser(String menuToBeParsed, String establishment) {
        this.menuToBeParsed = menuToBeParsed;
        this.establishment = establishment;
    }

    public List<Map<String, String>> getMenu() {
        List<Map<String, String>> finalMenu = new LinkedList<>();
        List<String> noEmpties = new LinkedList<>();
        Stream.of(menuToBeParsed.split("\\r?\\n"))
                .filter(line -> !line.equals("")
                        && line.endsWith("€"))
                .forEach(noEmpties::add);
        for (String line : noEmpties) {
            Map<String, String> menuItem = new HashMap<>();
            menuItem.put("provider", "rahvatoit_" + establishment);
            menuItem.put("price", line.substring(line.lastIndexOf(" ")));
            menuItem.put("name_est", line.substring(0, line.lastIndexOf("/") - 1).trim());
            menuItem.put("name_eng", line.substring(line.lastIndexOf("/") + 1, line.lastIndexOf(" ")).trim());
            finalMenu.add(menuItem);
        }
        return finalMenu;
    }
}
