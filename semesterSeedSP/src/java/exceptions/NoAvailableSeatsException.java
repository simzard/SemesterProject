/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author simon
 */
public class NoAvailableSeatsException extends Exception {
    
    public NoAvailableSeatsException(String noAvailableSeats) {
        super(noAvailableSeats);
    }
    
}
