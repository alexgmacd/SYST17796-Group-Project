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
    
    private BlackjackHand hand;

    /**
     * Constructor that calls super and initiates a BlackjackHand.
     */   
    public BlackjackDealer(){
        super("Dealer");
        hand = new BlackjackHand();
    }

    /**
     * Getter method for the Dealer's hand.
     * 
     * @return the Dealer's hand.
     */        
    public BlackjackHand getHand() {
        return this.hand;
    }
    
    /**
     * Setter method for the Dealer's hand.
     * 
     * @param hand the BlackjackHand to be set.
     */        
    public void setHand(BlackjackHand hand) {
        this.hand = hand;
    }

    /**
     * Calls draw method of hand to add to Dealer's Hand.
     * 
     * @param card the BlackjackCard to be added to the Dealer's hand.
     */       
    public void hit(BlackjackCard card){
        hand.draw(card);
    }
    
    public void stand() {
        
    }

    /**
     * Checking if hand has busted.
     * 
     * @return true if hand value is greater than 21, false if it is less.
     */   
    public boolean busted(){
        return hand.handValue() > 21;
    }

    /**
     * Returns String of Dealer's hand and hand value.
     * 
     * @return String representation of Dealer's hand.
     */    
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


