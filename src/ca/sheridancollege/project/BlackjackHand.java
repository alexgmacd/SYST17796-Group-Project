/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author Alex
 * @modifier Eleonora
 */
public class BlackjackHand extends GroupOfCards {

    private int aceCount;

    /**
     * Constructor calls super with size of 0, sets aceCount to 0 and initiates
     * cards ArrayList.
     */           
    public BlackjackHand() {
        super(0);
        aceCount = 0;
        cards = new ArrayList<>();
    }
    
    public int getAceCount(){
        return aceCount;
    }
    
    public void setAceCount(int aceCount){
        this.aceCount = aceCount;
    }

    /**
     * Method to draw a card and add to cards ArrayList, and increase aceCount 
     * by 1 if the added card is of Rank value ACE.
     * 
     * @param card the card to be added to the ArrayList.
     */           
    public void draw(BlackjackCard card) {
        cards.add(card);
        setSize(getSize() + 1);
        if (card.getRank() == Rank.ACE) {
            aceCount++;
        }
    }
    
    /**
     * Method to remove second card from cards ArrayList after play has split.
     */           
    public void removeCardToSplit() {
        if (cards.size() == 2) {
            cards.remove(1);
            super.setSize(1);
        }
    }

    /**
     * Method to clear cards ArrayList and reset counters to 0.
     */       
    public void clearHand() {
        cards.clear();
        aceCount = 0;
        setSize(0);
    }

    /**
     * Method to check for natural in the hand, natural is valid if
     * the hand's size is 2 and value is 21.
     *
     * @return true if natural is valid and false if it is invalid.
     */          
    public boolean checkNaturals() {
        return getSize() == 2 && handValue() == 21;
    }
    
    /**
     * Method to check if cards ArrayList is empty.
     * 
     * @return true if it is empty and false if it is not.
     */       
    public boolean isEmpty() {
        return super.cards.isEmpty();
    }

    /**
     * Method to calculate the value of the hand, grabs Rank value of each card
     * and adds the appropriate value to handValue. For any ACES, they will be
     * treated as adding 1 to the hand value unless the total value is less than
     * 11, if so they will be treated as adding 11.
     * 
     * @return the total hand value.
     */           
    public int handValue() {
        int handValue = 0;
        for (Card card : cards) {
            switch (((BlackjackCard)card).getRank()) {
                case TWO:
                    handValue += 2;
                    break;
                case THREE:
                    handValue += 3;
                    break;
                case FOUR:
                    handValue += 4;
                    break;
                case FIVE:
                    handValue += 5;
                    break;
                case SIX:
                    handValue += 6;
                    break;
                case SEVEN:
                    handValue += 7;
                    break;
                case EIGHT:
                    handValue += 8;
                    break;
                case NINE:
                    handValue += 9;
                    break;
                case TEN:
                    handValue += 10;
                    break;
                case JACK:
                    handValue += 10;
                    break;
                case QUEEN:
                    handValue += 10;
                    break;
                case KING:
                    handValue += 10;
                    break;
            }
        }

        for (int i = 0; i < aceCount; i++) {
            if (handValue < 11) {
                handValue += 11;
            } else {
                handValue += 1;
            }
        }

        return handValue;
    }
    
    /**
     * Getter method to get a specific card of the cards ArrayList.
     * 
     * @param index the index of the cards ArrayList.
     * @return the card at the specified index.
     */           
    public BlackjackCard getCardAtIndex(int index) {
        return (BlackjackCard)cards.get(index);
    }
    
    /**
     * Return's String of the hand.
     * 
     * @return String representation of the hand.
     */          
    public String toString(){
        return "Hand: " + cards; 
    }
}
