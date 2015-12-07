/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Flight;

/**
 *
 * @author sabre
 */
public class proxyUtil {

    // The method must get flightinfo from other airlines.
    public static String httpGetFlight(String from, String timeDate, int persons) {

        Flight f1 = new Flight("COL2256", 1, "2016-01-02T10:00:00.000Z", 80.0f, 120, "CPH", "CDG");

        Flight f2 = new Flight("COL2334", 1, "2016-01-02T16:00:00.000Z", 120.0f, 180, "CPH", "BCN");

        Gson gson = new Gson();
        String f1json = gson.toJson(f1);
        String f2json = gson.toJson(f2);

        String outputJson = "{\"airline\": \"Angular JS Airline\",\"flights\": [" + f1json + "," + f2json + "]}";

        return outputJson;

    }

}
