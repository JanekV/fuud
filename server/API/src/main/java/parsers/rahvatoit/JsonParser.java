package parsers.rahvatoit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Stream;

public class JsonParser {
    private String firstMenu;
    private String secondMenu;
    private String socMenu;
    private String raamatukoguMenu;

    /**
     * Class object for parsing the JSON from input Rahva Toit data feed URL.
     */
    JsonParser(String url) throws IOException, JSONException {
        JSONArray feedArray = (JSONArray) readJsonFromUrl(url).get("data");
        JSONObject firstData = (JSONObject) feedArray.get(0);
        JSONObject secondData = (JSONObject) feedArray.get(1);
        firstMenu = firstData.get("message").toString();
        secondMenu = secondData.get("message").toString();
        checkMenu(firstMenu);
        checkMenu(secondMenu);
    }

    /**
     * Assisting method for readJsonFromUrl.
     *
     * @param rd BufferedReader containing the JSON.
     * @return JSON text.
     * @throws IOException when something goes wrong.
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Method for parsing the JSON.
     * @param url URL containing the JSON.
     * @return JSON text as JSONObject.
     * @throws IOException when something goes very wrong.
     * @throws JSONException when something's wrong with the JSON.
     */
    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    /**
     * Determines if the menu is for SOC or Raamatukogu.
     * @param menu the menu to be parsed.
     */
    private void checkMenu(String menu) {
        if (menu.toLowerCase().contains("raamatukogu")) {
            raamatukoguMenu = menu;
        } else {
            socMenu = menu;
        }
    }

    public String getSocMenu() {
        return socMenu;
    }

    public String getRaamatukoguMenu() {
        return raamatukoguMenu;
    }
}
