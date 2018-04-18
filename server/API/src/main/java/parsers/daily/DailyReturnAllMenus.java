package parsers.daily;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DailyReturnAllMenus {
    private static final String keemiaLink = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_keemiainstituut.pdf";
    private static final String itMajaLink = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_it_maja.pdf";
    private static final String peamajaLink = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_peahoone.pdf";
    private static final String neljasKorpusLink = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_4_korpus.pdf";

    private final static String itMajaPdf = "C:/file/it_maja.pdf";
    private final static String keemiaPdf = "C:/file/keemia.pdf";
    private final static String peamajaPdf = "C:/file/peamaja.pdf";
    private final static String neljasKorpusPdf = "C:/file/neljas_korpus.pdf";

    public static List<Map<String, String>> getDailyMenus() throws IOException {
        List<LinkedList<HashMap<String, String>>> result = new ArrayList<>();

        new DailyDownloader(keemiaLink, "keemia").download();
        new DailyDownloader(itMajaLink, "it_maja").download();
        new DailyDownloader(peamajaLink, "peamaja").download();
        new DailyDownloader(neljasKorpusLink, "neljas_korpus").download();

        DailyReader itMajaReader = new DailyReader(new File(itMajaPdf));
        DailyReader keemiaReader = new DailyReader(new File(keemiaPdf));
        DailyReader peamajaReader = new DailyReader(new File(peamajaPdf));
        DailyReader neljasKorpusReader = new DailyReader(new File(neljasKorpusPdf));

        DailyMenuSplitter itMajaSplitter = new DailyMenuSplitter(itMajaReader.getMenuAsLinkedList());
        DailyMenuSplitter keemiaSplitter = new DailyMenuSplitter(keemiaReader.getMenuAsLinkedList());
        DailyMenuSplitter peamajaSplitter = new DailyMenuSplitter(peamajaReader.getMenuAsLinkedList());
        DailyMenuSplitter neljasKorpusSplitter = new DailyMenuSplitter(neljasKorpusReader.getMenuAsLinkedList());

        DailyParser itMajaParser = new DailyParser(itMajaSplitter.getByToday());
        DailyParser keemiaParser = new DailyParser(keemiaSplitter.getByToday());
        DailyParser peamajaParser = new DailyParser(peamajaSplitter.getByToday());
        DailyParser neljasKorpusParser = new DailyParser(neljasKorpusSplitter.getByToday());

        /*result.add(itMajaParser.getFinalMenu());
        result.add(keemiaParser.getFinalMenu());
        result.add(peamajaParser.getFinalMenu());
        result.add(peamajaParser.getFinalMenu());
*/
        return itMajaParser.getFinalMenu();
    }
}
