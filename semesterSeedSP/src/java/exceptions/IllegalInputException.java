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
public class IllegalInputException extends Exception {
    public IllegalInputException(String illegalInput) {
        super(illegalInput);
    }
}
