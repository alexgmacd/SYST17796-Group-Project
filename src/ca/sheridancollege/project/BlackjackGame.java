package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Eleonora
 */
public class BlackjackGame extends Game {
    private BlackjackDealer dealer;
    private Stack<BlackjackCard> shoe;
    
    public BlackjackGame() {
        super("Blackjack");
        dealer = new BlackjackDealer();
        shoe = new Stack<BlackjackCard>();
        ArrayList futureShoe = new ArrayList<BlackjackCard>();
        for (int i = 0; i < 6; i++) {
            BlackjackDeck deck = new BlackjackDeck();
            deck.shuffle();
            futureShoe.addAll(deck.showCards());
        }
        Collections.shuffle(futureShoe);
        Random random = new Random();
        int blankCard = random.nextInt(76 - 60) + 60;
        for (int i = 0; i < blankCard; i++) {
            futureShoe.remove(i);
        }
        shoe.addAll(futureShoe);
    }
    
    public BlackjackDealer getDealer() {
        return dealer;
    }
    
    public Stack<BlackjackCard> getShoe() {
        return shoe;
    }
    
    public boolean checkPlayer(String playerID) {
        boolean checker = true;
        for (int i = 0; i < super.getPlayers().size(); i++) {
            if ((getPlayerAtIndex(i).getPlayerID().equals(playerID)) || 
                    (getPlayerAtIndex(i).getPlayerID().equals("Dealer"))) {
                checker = false;
                break;
            }
        }
        return checker;
    }
    
    public BlackjackPlayer getPlayerAtIndex(int player) {
        return (BlackjackPlayer)super.getPlayers().get(player);
    }
    
    public void addPlayer(BlackjackPlayer player) {
        super.getPlayers().add(player);
    }
    
    public void play() {
    }
    
    public void declareWinner() {
        
    }
}
