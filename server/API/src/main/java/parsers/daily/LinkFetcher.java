package parsers.daily;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class LinkFetcher {
    private static final String LINK = "http://www.daily.ee/ee/lunch-offers";
    private static final String KEEMIA = "keemiainstituut";
    private static final String ITMAJA = "it_maja";
    private static final String PEAHOONE = "ttu_peahoone";
    private static final String NELJAS = "4_korpus";

    private String keemia;
    private String itMaja;
    private String peamaja;
    private String neljasKorpus;

    /**
     * LinkFetcher class object, designed to fetch download links for the menus of the Daily facilities
     * contained in Tallinn University of Technology.
     *
     * The links are saved into their respective class variables and can be fetched via getter methods.
     * @throws IOException when something bad happens.
     */
    LinkFetcher() throws IOException {
        Document doc = Jsoup.connect(LINK).get();
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            if (link.attr("href").contains("download")) {
                if (link.attr("href").contains(ITMAJA)) {
                    this.itMaja = link.attr("href");
                }
                if (link.attr("href").contains(KEEMIA)) {
                    this.keemia = link.attr("href");
                }
                if (link.attr("href").contains(PEAHOONE)) {
                    this.peamaja = link.attr("href");
                }
                if (link.attr("href").contains(NELJAS)) {
                    this.neljasKorpus = link.attr("href");
                }
            }
        }
    }

    public String getKeemia() {
        return keemia;
    }

    public String getItMaja() {
        return itMaja;
    }

    public String getPeamaja() {
        return peamaja;
    }

    public String getNeljasKorpus() {
        return neljasKorpus;
    }
}
