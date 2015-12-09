package util;

import exceptions.FlightNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AirLineHelper {

    // this method returns in JSON the airline object 
    public static String httpGetFromJSON(String startUrl, String from, String timeDate, int persons)
            throws FlightNotFoundException {

        String allOutput = "";
        
        try {
           //http://angularairline-plaul.rhcloud.com//api/flightinfo/CPH/2016-01-04T23:00:00.000Z/3
            // /api/flightinfo/CPH/2016-01-04T23:00:00.000Z/3
            String params = "/api/flightinfo/" + from + "/" + timeDate + "/" + persons;
            String urlString = startUrl +  params;
            //System.out.println(urlString);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                
                System.out.println(conn.getResponseCode());
                
                throw new FlightNotFoundException("No flights found");           
            } 
            
            

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                allOutput += output;
                
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return allOutput;
    }

    // this method returns in JSON the airline object 
    public static String httpGetFromToJSON(String startUrl, String from, String to, String timeDate, int persons)
    throws FlightNotFoundException {

        String allOutput = "";
        
        try {
            //http://angularairline-plaul.rhcloud.com/api/flightinfo/CPH/STN/2016-01-04T23:00:00.000Z/2
          
            String params = "/api/flightinfo/" + from + "/" + to + "/" + timeDate + "/" + persons;
            String urlString = startUrl +  params;
            //System.out.println(urlString);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new FlightNotFoundException("No flights found");         
                        
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                allOutput += output;
                
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            //e.printStackTrace();

        } catch (IOException e) {

            //e.printStackTrace();

        }
        return allOutput;
    }
    //http://angularairline-plaul.rhcloud.com/api/flightinfo/CPH/STN/2016-01-04T23:00:00.000Z/2
    public static void main(String[] args) {
        try {
        System.out.println(httpGetFromJSON("http://angularairline-plaul.rhcloud.com", "CPH", "2016-01-04T23:00:00.000Z", 3));
        System.out.println(httpGetFromToJSON("http://angularairline-plaul.rhcloud.com", "CPH", "STN", "2016-01-04T23:00:00.000Z", 2));
//        try {
        System.out.println(httpGetFromJSON("http://timetravel-tocvfan.rhcloud.com/", "CPH", "2016-01-04T23:00:00.000Z", 3));
//        } catch (Exception e) {
//            System.out.println("");
//        }
        } catch (FlightNotFoundException e) {
            System.out.println("flight not found!");
        } 
    }
}
