/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import entity.Flight;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import rest.proxyUtil;

/**
 *
 * @author sabre
 */
public class ProxyUtilUnitTest {

    public ProxyUtilUnitTest() {
    }
    
   
   
   
    @BeforeClass
    public static void setUpClass() throws Exception {
        
        

    }

    @Test
    public void testGetProxySimpleFlightJson() {
        
        Flight f1 = new Flight("COL2256", 1, "2016-01-02T10:00:00.000Z", "80.0", 120, "CPH", "CDG");
        Flight f2= new Flight("COL2334", 1, "2016-01-02T16:00:00.000Z", "120.0", 180, "CPH", "BCN");

        Gson gson = new Gson();
        String f1json = gson.toJson(f1);
        String f2json = gson.toJson(f2);

        String expected = "{\"airline\": \"Angular JS Airline\",\"flights\": [" + f1json + "," + f2json + "]}";

        
//        String result = proxyUtil.httpGetFlight();
        System.out.println(expected);

//        assertEquals(result, expected);
    }

    @Test
    public void testGetProxyFlightJsonWithParam() {
        
        Flight f1 = new Flight("COL2256", 2, "2016-01-02T10:00:00.000Z", "160.0", 120, "CPH", "CDG");
        Flight f2= new Flight("COL2334", 2, "2016-01-02T16:00:00.000Z", "240.0", 180, "CPH", "BCN");

        Gson gson = new Gson();
        String f1json = gson.toJson(f1);
        String f2json = gson.toJson(f2);

        String expected = "{\"airline\": \"Angular JS Airline\",\"flights\": [" + f1json + "," + f2json + "]}";
           

//        String result = proxyUtil.httpGetFlight();

//        assertEquals(result, expected);

    }

}

// http://angularairline-plaul.rhcloud.com/api/flightinfo/CPH/2016-01-02T23:00:00.000Z/1
