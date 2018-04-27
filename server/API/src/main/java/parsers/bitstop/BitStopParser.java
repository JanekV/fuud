package parsers.bitstop;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import parsers.fooditem.FoodItem;
import parsers.fooditem.FoodItemBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class BitStopParser {
    private static final String BITSTOP_SPREADSHEET_ID = "1EWuafdBZjeBNwSNfluILEpT8JamxiPls9aBthYOY5UY";


    /**
     * Application name.
     */
    private static final String APPLICATION_NAME =
            "Google Sheets API Java BitStopParser";

    /**
     * Directory to store user credentials for this application.
     */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("./"), ".credentials/sheets.googleapis.com-java-quickstart");

    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes required by this quickstart.
     * <p>
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/sheets.googleapis.com-java-quickstart
     */
    private static final List<String> SCOPES =
            Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException connection error
     */
    private static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                BitStopParser.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Sheets API client service.
     *
     * @return an authorized Sheets API client service
     * @throws IOException connection error
     */
    private static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Returns food items form the bitStop Google Sheet in the correct format.
     *
     * @return List of HashMaps
     * @throws IOException Failed to open Google Sheet
     */
    public static List<FoodItem> getBitStopDataList() throws IOException {
        List<List<Object>> values = getSheetValues();

        if (values == null || values.size() == 0) {
            System.out.println("No data found.");
            return null;
        } else {
            return parseValuesList(values);
        }
    }

    private static List<List<Object>> getSheetValues() throws IOException {
        // Build a new authorized API client service.
        Sheets service = getSheetsService();

        // bitStop keeps their data in range "ROHOAD", row 1 containing unimportant header is excluded
        String range = "ROHOAD!A2:C30";
        ValueRange response = service.spreadsheets().values()
                .get(BITSTOP_SPREADSHEET_ID, range)
                .execute();

        return response.getValues();
    }

    /**
     * Return a List of HashMaps that follow the structure:
     * [
     *    provider: "provider name"
     *    food_name_est: "string"
     *    food_name_eng: "string"
     *    price (single size): "string" (later parsed as float)
     *    price (multiple sizes): "/big(string), /small(string)"
     *  ]
     *  [
     *   ...
     *  ]
     *
     * @param values values from bitStop Google Sheet
     * @return list of HashMaps
     */
    private static List<FoodItem> parseValuesList(List<List<Object>> values) {
        List<FoodItem> result = new ArrayList<>();
        List tempBigPortionRow = null;
        boolean isHalfPortion = false;

        for (List row : values) {
            if (row.size() > 0) {
                if (row.get(0).toString().contains("HOMMIK") || row.get(0).toString().contains("SOOJAD")) continue;
                if (!isHalfPortion) {
                    if (row.get(0).toString().contains("/")) {
                        isHalfPortion = true;
                        tempBigPortionRow = row;
                    } else {
                        result.add(getRowAsFoodItem(row));
                    }
                } else {
                    result.add(getRowAsFoodItem(tempBigPortionRow, row.get(1).toString()));
                    isHalfPortion = false;
                }
            }
        }
        return result;
    }


    /**
     * Alternative parser for small/big portion functionality. If a portion is big or small, "/väike" or "/small"
     * is added to the name column in given row. This method gets the small portion price form the method
     * ParseValuesList (see above).
     *
     * @param row row to be converted to hashmap
     * @param smallPortionPrice big portion row
     * @return HashMap
     */
    private static FoodItem getRowAsFoodItem(List row, String smallPortionPrice) {
        return new FoodItemBuilder()
                .name_est(row.get(0).toString().split("/")[0].trim())
                .name_eng(row.get(2).toString().split("/")[0].trim())
                .price(String.format("/large %s, /small %s", row.get(1).toString().substring(1),
                        smallPortionPrice.substring(1)))
                .providers(Collections.singletonList("bitstop"))
                .createFoodItem();
    }

    private static FoodItem getRowAsFoodItem(List row) {
        return new FoodItemBuilder()
                .name_est(row.get(0).toString().trim())
                .name_eng(row.get(2).toString().trim())
                .price(row.get(1).toString().substring(1))
                .providers(Collections.singletonList("bitstop"))
                .createFoodItem();
    }
}