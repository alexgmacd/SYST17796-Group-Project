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
    
    public BlackjackCard(Rank rank){
        this.rank = rank;
    }
    
    public Rank getRank(){
        return rank;
    }

    public String toString(){
        return rank.toString();
    }
}
