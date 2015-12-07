/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Airline;
import entity.Flight;
import interfaces.AirlinesIF;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simon
 */
public class AirlineFetcher implements AirlinesIF {

    @Override
    public List<Airline> getAirlines(String from, String timeDate, int persons)     {
        List<Airline> airlines = new ArrayList();
        Flight f1 = new Flight("COL2256", 1, "2016-01-02T10:00:00.000Z", 80.0f, 120, "CPH", "CDG");
        Flight f2 = new Flight("COL2334", 1, "2016-01-02T16:00:00.000Z", 120.0f, 180, "CPH", "BCN");
        Airline dummyAir = new Airline();
        dummyAir.setAirline("AngularJS Airline");
        dummyAir.addFlight(f1);
        dummyAir.addFlight(f2);
        airlines.add(dummyAir);
        
        Airline dummyAir2 = new Airline();
        dummyAir2.setAirline("Rubens airways");
        dummyAir2.addFlight(f1);
        dummyAir2.addFlight(f2);
        airlines.add(dummyAir2);
        return airlines;
        

    }

    @Override
    public List<Airline> getAirlines(String from, String to, String timeDate, int persons) {
        List<Airline> airlines = new ArrayList();
        Flight f1 = new Flight("COL2256", 1, "2016-01-02T10:00:00.000Z", 80.0f, 120, "CPH", "CDG");
        Flight f2 = new Flight("COL2334", 1, "2016-01-02T16:00:00.000Z", 120.0f, 180, "CPH", "BCN");
        Airline dummyAir = new Airline();
        dummyAir.setAirline("AngularJS Airline");
        dummyAir.addFlight(f1);
        dummyAir.addFlight(f2);
        airlines.add(dummyAir);
        
        Airline dummyAir2 = new Airline();
        dummyAir2.setAirline("Rubens airways");
        dummyAir2.addFlight(f1);
        dummyAir2.addFlight(f2);
        airlines.add(dummyAir2);
        return airlines;
    }
    
}
