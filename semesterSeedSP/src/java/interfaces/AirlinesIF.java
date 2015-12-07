/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Airline;
import java.util.List;

/**
 *
 * @author simon
 */
public interface AirlinesIF {

    List<Airline> getAirlines(String from, String timeDate, int persons);
    List<Airline> getAirlines(String from, String to, String timeDate, int persons);
}
