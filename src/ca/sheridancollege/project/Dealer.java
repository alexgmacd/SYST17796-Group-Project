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
public class Dealer extends Player {
    
    //Ask in meeting on implementation, private or protected.
    private BlackjackHand hand;
    
    public Dealer(){
        super("Dealer");
        hand = new BlackjackHand();
    }
    
    public void hit(BlackjackCard card){
        hand.draw(card);
    }
    
    public boolean stand(){
        return hand.handValue() >= 17;
    }
    
    public boolean busted(){
        return hand.handValue()>21;
    }
    
    public void play(){
        //Maybe just showing dealer's first card after the drawing in BlackjackGame
        //Showing values of dealers hand, if statement if it's empty, say he draws card, if it has cards displaying them and the value instead?
        //Showing info about what the dealer did at the start i.e; drew 1 card for dealer, drew 2 for each player
        //ask about this in group meeting
    }
    
}


