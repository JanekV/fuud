package parsers.rahvatoit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonParser {
    private String socMenu;
    private String raamatukoguMenu;

    JsonParser(String url) throws IOException, JSONException {
        JSONArray feedArray = (JSONArray) readJsonFromUrl(url).get("data");
        JSONObject socData = (JSONObject) feedArray.get(0);
        JSONObject raamatukoguData = (JSONObject) feedArray.get(1);
        socMenu = socData.get("message").toString();
        raamatukoguMenu = raamatukoguData.get("message").toString();
    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    public String getSocMenu() {
        return socMenu;
    }

    public String getRaamatukoguMenu() {
        return raamatukoguMenu;
    }
}
