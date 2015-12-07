/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 */

@Entity
public class Flight {
    
    @Id
    private String flightID;
    private String airLine;
    private int numberOfSeats;
    private String date;
    private String totalPrice;
    private int traveltime;
    private String origin;
    private String destination;

    public Flight() {
    }

    public Flight(String flightID, String airLine, int numberOfSeats, String date, String totalPrice, int traveltime, String origin, String destination) {
        this.flightID = flightID;
        this.airLine = airLine;
        this.numberOfSeats = numberOfSeats;
        this.date = date;
        this.totalPrice = totalPrice;
        this.traveltime = traveltime;
        this.origin = origin;
        this.destination = destination;
    }

    
    public Flight(String flightID, int numberOfSeats, String date, String totalPrice, int traveltime, String origin, String destination) {
        this.flightID = flightID;
        this.numberOfSeats = numberOfSeats;
        this.date = date;
        this.totalPrice = totalPrice;
        this.traveltime = traveltime;
        this.origin = origin;
        this.destination = destination;
    }
 
    
    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getAirLine() {
        return airLine;
    }

    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(int traveltime) {
        this.traveltime = traveltime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    
    
    
}
