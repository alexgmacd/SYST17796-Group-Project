package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Eleonora Kukash
 */
public class BlackjackGame extends Game {
    private BlackjackDealer dealer;
    private Stack<BlackjackCard> shoe;
    
    public BlackjackGame() {
        super("Blackjack");
        dealer = new BlackjackDealer();
        shoe = new Stack<BlackjackCard>();
        ArrayList futureShoe = new ArrayList<BlackjackCard>();
        for (int i = 0; i < 6, i++) {
            BlackjackDeck deck =- new Deck();
            deck.shuffle();
            futureShoe.addAll(deck);
        }
        Collections.shuffle(futureShoe);
        Random random = new Random();
        int blankCard = random.nextInt(76 - 60) + 60;
        for (int i = 0; i < blankCard; i++) {
            futureShoe.remove(i);
        }
        shoe.addAll(futureShoe);
    }
    
    public void play() {
        
    }
    
    public void declareWinner() {
        
    }
}
