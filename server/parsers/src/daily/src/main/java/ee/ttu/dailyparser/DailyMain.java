package ee.ttu.dailyparser;

import java.io.File;
import java.io.IOException;

public class DailyMain {
    public static void main(String[] args) throws IOException {
        final String keemiaLink = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_keemiainstituut.pdf";
        final String itMajaLink = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_it_maja.pdf";
        final String peamajaLink = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_peahoone.pdf";
        final String neljasKorpusLink = "http://www.daily.ee/files/dn_daily_nadalamenuu_ttu_4_korpus.pdf";

        new DailyDownloader(keemiaLink, "keemia").download();
        new DailyDownloader(itMajaLink, "it_maja").download();
        new DailyDownloader(peamajaLink, "peamaja").download();
        new DailyDownloader(neljasKorpusLink, "neljas_korpus").download();

        final String itMajaPdf = "C:/file/it_maja.pdf";
        final String keemiaPdf = "C:/file/keemia.pdf";
        final String peamajaPdf = "C:/file/peamaja.pdf";
        final String neljasKorpusPdf = "C:/file/neljas_korpus.pdf";

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

        System.out.println(itMajaParser.getFinalMenu());
        System.out.println(keemiaParser.getFinalMenu());
        System.out.println(peamajaParser.getFinalMenu());
        System.out.println(neljasKorpusParser.getFinalMenu());
    }
}
