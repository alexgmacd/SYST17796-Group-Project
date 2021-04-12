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
public class BlackjackDeck extends GroupOfCards {
    
    /**
     * Constructor creates BlackjackDeck with 52 cards, 13 Rank values times 4.
     */
    public BlackjackDeck(){
        super(0);
        cards = new ArrayList<>();
        for(int i = 0; i<4; i++){
            for(Rank rank:Rank.values()){
                BlackjackCard card = new BlackjackCard(rank);
                cards.add(card);
            }
        }
    }
    
    
}
