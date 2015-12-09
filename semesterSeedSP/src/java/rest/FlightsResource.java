/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Airline;
import entity.Flight;
import entity.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.minidev.json.JSONArray;
import util.AirlineFetcher;

/**
 * REST Web Service
 *
 * @author Ib Routhe
 */
@Path("flightinfo")
public class FlightsResource {

    @Context
    private UriInfo context;

  
    public FlightsResource() {
    }

    /**
     * Returns a JSON array with flight objects
     * Based on FROM - DATE - NUMTICKETS
     */
    @GET
    @Path("{from}/{date}/{numTickets}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFlightInfo(
            @PathParam("from") String from,
            @PathParam("date") String date,
            @PathParam("numTickets") int tickets) {

        AirlineFetcher af = new AirlineFetcher();

        List<Airline> airlines = null;
        try {
            airlines = af.getAirlines(from, date, tickets);
        } catch (InterruptedException ex) {
            Logger.getLogger(FlightsResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(FlightsResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        String returnString = "[";
        Gson gson = new Gson();

       // Loop through ArrayList of Airlines       
        for (Airline al : airlines) {

            // Loop through each Airline, that has a Arraylist of Flights.
            for (int i = 0; i < al.getFlights().size(); i++) {

                Ticket ticket = new Ticket();
                ticket.setAirLine(al.getAirline());
                ticket.setFlightID(al.getFlights().get(i).getFlightID());
                ticket.setNumberOfSeats(al.getFlights().get(i).getNumberOfSeats());
                ticket.setDate(al.getFlights().get(i).getDate());
                ticket.setTotalPrice(al.getFlights().get(i).getTotalPrice());
                ticket.setTraveltime(al.getFlights().get(i).getTraveltime());
                ticket.setOrigin(al.getFlights().get(i).getOrigin());
                ticket.setDestination(al.getFlights().get(i).getDestination());

                String json = gson.toJson(ticket);
                returnString = returnString + json;
            }

        }
        returnString = returnString + "]";
        return returnString;

    }

}

