package ee.ttu.dailyparser;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DailyMain {
    private static final String KEEMIA_LINK = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_keemiainstituut.pdf";
    private static final String IT_MAJA_LINK = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_it_maja.pdf";
    private static final String PEAMAJA_LINK = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_peahoone.pdf";
    private static final String NELJAS_KORPUS_LINK = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_4_korpus.pdf";

    private static final String IT_MAJA_PDF = "C:/file/it_maja.pdf";
    private static final String KEEMIA_PDF = "C:/file/keemia.pdf";
    private static final String PEAMAJA_PDF = "C:/file/peamaja.pdf";
    private static final String NELJAS_KORPUS_PDF = "C:/file/neljas_korpus.pdf";

    private static final String IT_MAJA = "it_maja";
    private static final String KEEMIA = "keemia";
    private static final String PEAMAJA = "peamaja";
    private static final String NELJAS_KORPUS = "neljas_korpus";

    public DailyMain() {

    }

    public String downloadMenus() throws IOException {
        if (new DailyDownloader(KEEMIA_LINK, "keemia").download()
                && new DailyDownloader(IT_MAJA_LINK, "it_maja").download()
                && new DailyDownloader(PEAMAJA_LINK, "peamaja").download()
                && new DailyDownloader(NELJAS_KORPUS_LINK, "neljas_korpus").download()) {
            return "Files downloaded!";
        } else {
            return "Error, please investigate";
        }
    }

    public List<Map<String, String>> getMenuData() throws IOException {
        List<Map<String, String>> menuData = new LinkedList<>();

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

        return menuData;
    }
}
