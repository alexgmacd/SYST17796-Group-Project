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
 */
public class BlackjackHand extends GroupOfCards {

    private int aceCount;

    public BlackjackHand() {
        super(0);
        aceCount = 0;
        cards = new ArrayList<>();
    }

    public void draw(BlackjackCard card) {
        cards.add(card);
        setSize(getSize() + 1);
        if (card.getRank() == Rank.ACE) {
            aceCount++;
        }
    }
    
    public void removeCard() {
        cards.remove(cards.size() - 1);
        super.setSize(super.getSize() - 1);
    }
    
    public void clearHand() {
        cards.clear();
        aceCount = 0;
        setSize(0);
    }

    public boolean checkNaturals() {
        return getSize() == 2 && handValue() == 21;
    }

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
    
    public BlackjackCard getCardAtIndex(int index) {
        return (BlackjackCard)cards.get(index);
    }
    
    public String toString(){
        return "Hand: " + cards; 
    }
}
