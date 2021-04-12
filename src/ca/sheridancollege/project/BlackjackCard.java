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

public class BlackjackCard extends Card {
    
    private Rank rank;
    
     /**
     * Constructor that takes enum of Rank type.
     * 
     * @param rank the Rank of the Card.
     */    
    public BlackjackCard(Rank rank){
        this.rank = rank;
    }
    
     /**
     * Getter method that returns the Rank value of the BlackjackCard.
     * 
     * @return the Card Rank value.
     */
    public Rank getRank(){
        return rank;
    }
    
     /**
     * Returns String of BlackjackCard's Rank value.
     * 
     * @return String representation of the Rank value.
     */
    public String toString(){
        return rank.toString();
    }
}
