package ee.ttu.dailyparser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class DailyMenuSplitter {
    private static final int INCREMENT = 1;
    private int monday; //Index of the line that contains "Esmaspäev".
    private int tuesday; //Index of the line that contains "Teisipäev".
    private int wednesday; //Index of the line that contains "Kolmapäev".
    private int thursday; //Index of the line that contains "Neljapäev".
    private int friday; //Index of the line that contains "Reede".
    private static final String DEFAULT = "Closed!"; //Used when weekday is Saturday or Sunday.
    private List<String> menuToBeSplit; //Incoming menu that needs to be split.


    /**
     * Class constructor for a Daily Menu Splitter.
     * This class splits the menu by day.
     */
    DailyMenuSplitter(List<String> dailyMenu) {
        this.menuToBeSplit = dailyMenu;
        for (int i = 0; i < menuToBeSplit.size(); i++) {
            if (menuToBeSplit.get(i).toLowerCase().contains("esmaspäev")) {
                this.monday = i;
            }
            if (menuToBeSplit.get(i).toLowerCase().contains("teisipäev")) {
                this.tuesday = i;
            }
            if (menuToBeSplit.get(i).toLowerCase().contains("kolmapäev")) {
                this.wednesday = i;
            }
            if (menuToBeSplit.get(i).toLowerCase().contains("neljapäev")) {
                this.thursday = i;
            }
            if (menuToBeSplit.get(i).toLowerCase().contains("reede")) {
                this.friday = i;
            }
        }
    }

    /**
     * Method for retreiving a section of the menu based on indexes.
     * @param menuAsList menu to be split.
     * @param from starting index.
     * @param to end index.
     * @return section of the menu based on from and to indexes.
     */
    private List<String> splitMenu(List<String> menuAsList, Integer from, Integer to) {
        LinkedList<String> menuSection = new LinkedList<>();
        for (int i = from; i < to; i++) {
            menuSection.add(menuAsList.get(i));
        }
        return menuSection;
    }

    /**
     * Method that uses the splitMenu funtion to get Monday's menu.
     * @return Monday's menu
     */
    private List<String> getMonday() {
        return splitMenu(menuToBeSplit, monday + INCREMENT, tuesday);
    }

    /**
     * Method that uses the splitMenu funtion to get Tuesday's menu.
     * @return Tuesday's menu
     */
    private List<String> getTuesday() {
        return splitMenu(menuToBeSplit, tuesday + INCREMENT, wednesday);
    }

    /**
     * Method that uses the splitMenu funtion to get Wednesday's menu.
     * @return Wednesday's menu
     */
    private List<String> getWednesday() {
        return splitMenu(menuToBeSplit, wednesday + INCREMENT, thursday);
    }

    /**
     * Method that uses the splitMenu funtion to get Thursday's menu.
     * @return Thursday's menu
     */
    private List<String> getThursday() {
        return splitMenu(menuToBeSplit, thursday + INCREMENT, friday);
    }

    /**
     * Method that uses the splitMenu funtion to get Friday's menu.
     * @return Friday's menu
     */
    private List<String> getFriday() {
        return splitMenu(menuToBeSplit, friday + INCREMENT, (menuToBeSplit.size() - INCREMENT));
    }

    /**
     * Method that determines current weekday and returns the menu based on it.
     * @return menu of the current weekday as a linked list.
     */
    List<String> getByToday() {
        LocalDate date = LocalDate.now();
        DayOfWeek dow = date.getDayOfWeek();
        List<String> todaysMenu = new LinkedList<>();
        switch (dow) {
            case MONDAY:
                todaysMenu = getMonday();
                break;
            case TUESDAY:
                todaysMenu = getTuesday();
                break;
            case WEDNESDAY:
                todaysMenu = getWednesday();
                break;
            case THURSDAY:
                todaysMenu = getThursday();
                break;
            case FRIDAY:
                todaysMenu = getFriday();
                break;
            default:
                todaysMenu.add(DEFAULT);
                break;
        }
        return todaysMenu;
    }
}
