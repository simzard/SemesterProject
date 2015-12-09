/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ib Routhe
 */

@Entity
public class Ticket implements Serializable{
    
    private String airLine;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String flightID;
    private int numberOfSeats;
    private String date;
    private float totalPrice;
    private int traveltime;
    private String origin;
    private String destination;

    public String getAirLine() {
        return airLine;
    }

    @Override
    public String toString() {
        return "Ticket{" + "airLine=" + airLine + ", flightID=" + flightID + ", numberOfSeats=" + numberOfSeats + ", date=" + date + ", totalPrice=" + totalPrice + ", traveltime=" + traveltime + ", origin=" + origin + ", destination=" + destination + '}';
    }

    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
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

