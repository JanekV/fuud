package ee.ttu.dailyparser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;

public class DailyMenuSplitter {
    private static final int INCREMENT = 1;
    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;
    private static final String DEFAULT = "Closed!";
    private LinkedList<String> menuToBeSplit;


    /**
     * Class constructor for a Daily Menu Splitter.
     * This class splits the menu by day.
     */
    DailyMenuSplitter(LinkedList<String> dailyMenu) {
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

    private LinkedList<String> splitMenu(LinkedList<String> menuAsLinkedList, Integer from, Integer to) {
        LinkedList<String> menuSection = new LinkedList<>();
        for (int i = from; i < to; i++) {
            menuSection.add(menuAsLinkedList.get(i));
        }
        return menuSection;
    }

    private LinkedList<String> getMonday() {
        return splitMenu(menuToBeSplit, monday + INCREMENT, tuesday);
    }

    private LinkedList<String> getTuesday() {
        return splitMenu(menuToBeSplit, tuesday + INCREMENT, wednesday);
    }

    private LinkedList<String> getWednesday() {
        return splitMenu(menuToBeSplit, wednesday + INCREMENT, thursday);
    }

    private LinkedList<String> getThursday() {
        return splitMenu(menuToBeSplit, thursday + INCREMENT, friday);
    }

    private LinkedList<String> getFriday() {
        return splitMenu(menuToBeSplit, friday + INCREMENT, (menuToBeSplit.size() - INCREMENT));
    }

    LinkedList<String> getByToday() {
        LocalDate date = LocalDate.now();
        DayOfWeek dow = date.getDayOfWeek();
        LinkedList<String> todaysMenu = new LinkedList<>();
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
