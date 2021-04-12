/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author Alex
 * @modifier Eleonora
 */
public class BlackjackDealer extends Player {
    
    //Ask in meeting on implementation, private or protected.
    private BlackjackHand hand;
    
    public BlackjackDealer(){
        super("Dealer");
        hand = new BlackjackHand();
    }
    
    public BlackjackHand getHand() {
        return this.hand;
    }
    
    public void setHand(BlackjackHand hand) {
        this.hand = hand;
    }
    
    public void hit(BlackjackCard card){
        hand.draw(card);
    }
    
    public void stand() {
        
    }
    
    public boolean busted(){
        return hand.handValue() > 21;
    }
    
    public String toString(){
        String s = String.format("%s's %s\nHand Value: %d\n", 
                getPlayerID(), hand.toString(), hand.handValue());
        return s;
    }
    
    // might have something to do with displaying the hidden card? cannot really put game logic here b/c drawn card should come strictly for the shoe
    // while we have no acccess to the shoe from here and never will)))
    // can put system.out.print statements here actually
    // same issue with declarewinner and play in game
    public void play(){
        //Maybe just showing dealer's first card after the drawing in BlackjackGame
        //Showing values of dealers hand, if statement if it's empty, say he draws card, if it has cards displaying them and the value instead?
        //Showing info about what the dealer did at the start i.e; drew 1 card for dealer, drew 2 for each player
        //ask about this in group meeting
    }
    
}


