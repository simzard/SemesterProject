/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Airline;
import entity.Flight;
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

    /**
     * Creates a new instance of FlightsResource
     */
    public FlightsResource() {
    }

    /**
     * Retrieves representation of an instance of rest.FlightsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{from}/{date}/{numTickets}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFlightInfo(
            @PathParam("from") String from,
            @PathParam("date") String date,
            @PathParam("numTickets") int tickets) {
//returns list with airlines, which all has a list of flights

        AirlineFetcher af = new AirlineFetcher();
        
        
        List<Airline> airlines = null;
        try {
            airlines = af.getAirlines(from,date,tickets);
        } catch (InterruptedException ex) {
            Logger.getLogger(FlightsResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(FlightsResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        String returnString = "";

        for (Airline al : airlines) {

            for (int i = 0; i < al.getFlights().size(); i++) {

                returnString = returnString + al.getFlights().get(i);

            }

        }

        return returnString;

    }

}
