package parsers.daily;

import parsers.duplicatehunter.DuplicateHunter;
import parsers.fooditem.FoodItem;
import parsers.fooditem.FoodItemBuilder;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DailyMain {
    private static final String IT_MAJA_PDF = "./file/it_maja.pdf";
    private static final String KEEMIA_PDF = "./file/keemia.pdf";
    private static final String PEAMAJA_PDF = "./file/peamaja.pdf";
    private static final String NELJAS_KORPUS_PDF = "./file/neljas_korpus.pdf";

    private static final String IT_MAJA = "it_maja";
    private static final String KEEMIA = "keemia";
    private static final String PEAMAJA = "peamaja";
    private static final String NELJAS_KORPUS = "neljas_korpus";

    public DailyMain() {

    }

    public static void downloadMenus() {
        try {
            LinkFetcher fetcher = new LinkFetcher();
            new DailyDownloader(fetcher.getKeemia(), "keemia").download();
            new DailyDownloader(fetcher.getItMaja(), "it_maja").download();
            new DailyDownloader(fetcher.getPeamaja(), "peamaja").download();
            new DailyDownloader(fetcher.getNeljasKorpus(), "neljas_korpus").download();
        } catch (IOException ioe) {
            System.out.println("Download error.");
            ioe.printStackTrace();
        }
    }

    /**
     * Method for getting the FoodItem list for the Daily chain.
     * @return  List of food items belonging to the Daily chain.
     * @throws IOException when the file(s) cannot be found.
     */
    public static List<FoodItem> getMenuData() throws IOException {
        List<FoodItem> menuData = new LinkedList<>();

        DailyReader itMajaReader = new DailyReader(new File(IT_MAJA_PDF));
        DailyReader keemiaReader = new DailyReader(new File(KEEMIA_PDF));
        DailyReader peamajaReader = new DailyReader(new File(PEAMAJA_PDF));
        DailyReader neljasKorpusReader = new DailyReader(new File(NELJAS_KORPUS_PDF));

        DailyMenuSplitter itMajaSplitter = new DailyMenuSplitter(itMajaReader.getMenuAsLinkedList());
        DailyMenuSplitter keemiaSplitter = new DailyMenuSplitter(keemiaReader.getMenuAsLinkedList());
        DailyMenuSplitter peamajaSplitter = new DailyMenuSplitter(peamajaReader.getMenuAsLinkedList());
        DailyMenuSplitter neljasKorpusSplitter = new DailyMenuSplitter(neljasKorpusReader.getMenuAsLinkedList());

        DailyParser itMajaParser = new DailyParser(itMajaSplitter.getByToday(), IT_MAJA);
        DailyParser keemiaParser = new DailyParser(keemiaSplitter.getByToday(), KEEMIA);
        DailyParser peamajaParser = new DailyParser(peamajaSplitter.getByToday(), PEAMAJA);
        DailyParser neljasKorpusParser = new DailyParser(neljasKorpusSplitter.getByToday(), NELJAS_KORPUS);

        menuData.addAll(itMajaParser.getFinalMenu());
        menuData.addAll(keemiaParser.getFinalMenu());
        menuData.addAll(peamajaParser.getFinalMenu());
        menuData.addAll(neljasKorpusParser.getFinalMenu());

        return new DuplicateHunter().noDuplicates(menuData);
    }

    public static void main(String[] args) throws IOException {
        downloadMenus();
        System.out.println(getMenuData());
    }
}
