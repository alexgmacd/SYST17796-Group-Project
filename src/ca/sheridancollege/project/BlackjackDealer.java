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
    private boolean status;

    /**
     * Constructor that calls super and initiates a BlackjackHand.
     */   
    public BlackjackDealer(){
        super("Dealer");
        hand = new BlackjackHand();
        status = true;
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
     * Getter method to get the Dealer's status.
     * 
     * @return true if player is still playing, false if not.
     */ 
    public boolean getStatus() {
        return status;
    }
    
    /**
     * Setter method for status.
     * @param status the status to be set
     * the game.
     */ 
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    /**
     * Method to switch status to false when they quit the game.
     */ 
    public void stopPlaying() {
        this.status = false;
    }

    /**
     * Calls draw method of hand to add to Dealer's Hand.
     * 
     * @param card the BlackjackCard to be added to the Dealer's hand.
     */       
    public void hit(BlackjackCard card){
        hand.draw(card);
    }
    
    /**
     * Method to stand, therefore, not ask for other card.
     */  
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
     * Returns String of Dealer's hand and hand value with the second card hidden.
     * 
     * @return String representation of Dealer's hand.
     */
    public String hideCard() {
        String s = String.format("%s's Hand: [%s, HIDDEN]\n", 
                getPlayerID(), hand.cards.get(0).toString());
        return s;
    }

    /**
     * Returns String of Dealer's hand and hand value.
     * 
     * @return String representation of Dealer's hand.
     */    
    public String toString(){
        String s = String.format("%s's %s\n", 
                getPlayerID(), hand.toString());
        return s;
    }
    
    public void play() {
    }
    
    /**
     * Method for the dealer to play their turn automatically.
     * @param card to be drawn if the dealer is able to hit.
     */ 
    public void play(BlackjackCard card){
        if (this.hand.handValue() <= 16)
            this.hit(card);
        else {
            this.stopPlaying();
            this.stand();
            
        }
    }
}


