/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author Alex
 */
public class InvalidSplitException extends Exception {
   
    /**
     * Create custom exception with message.
     * 
     * @param message the message to be thrown
     */    
    public InvalidSplitException(String message){
        super(message);
    }
    
}
