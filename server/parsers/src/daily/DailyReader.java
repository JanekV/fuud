package daily;

import java.net.*;
import java.io.*;

public class DailyReader {
    public static void main(String[] args) throws Exception {

        URL oracle = new URL(
                "http://www.daily.ee/ee/lunch-offers/?obj=akadeemia-tee-15-tallinn-(ttue-keemiainstituut)");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}