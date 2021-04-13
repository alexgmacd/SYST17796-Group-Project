package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Eleonora
 */
public class BlackjackGame extends Game {
    private BlackjackDealer dealer;
    private Stack<BlackjackCard> shoe;

    /**
     * Constructor initiates dealer and shoe, creates 6 shuffled decks added to
     * an ArrayList, removes a random amount of cards between 60-76 from the
     * ArrayList and then adds the ArrayList to the shoe.
     */
    public BlackjackGame() {
        super("Blackjack");
        dealer = new BlackjackDealer();
        shoe = new Stack<BlackjackCard>();
        this.setShoe();
    }

    /**
     * Getter method to get the Dealer of the game.
     * 
     * @return the Dealer of the game.
     */           
    public BlackjackDealer getDealer() {
        return dealer;
    }

    /**
     * Getter method to get the Shoe of the game.
     * 
     * @return the Shoe of the game.
     */            
    public Stack<BlackjackCard> getShoe() {
        return shoe;
    }
    
    /**
     * Setter method for the Shoe of the game; called when the game is
     * initialized and every other time the bottom of the shoe is reached
     * and it needs to be reshuffled.
     */  
    public void setShoe() {
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
        this.shoe.addAll(futureShoe);
    }
    
    /**
     * Check if the shoe is empty.
     * @return true if the shoe is empty
     */
    public boolean isShoeEmpty() {
        return shoe.empty();
    }

    /**
     * Method checks if the player's chosen ID is valid, a valid ID is one
     * that is not already taken by another player and is not equal to "Dealer".
     * 
     * @param playerID the desired player ID.
     * @return true if ID is valid, false if ID is invalid.
     */       
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

    /**
     * Getter method to get the specific player of the players ArrayList.
     * 
     * @param player the index of the players ArrayList.
     * @return the player at the specified index.
     */            
    public BlackjackPlayer getPlayerAtIndex(int player) {
        return (BlackjackPlayer)super.getPlayers().get(player);
    }

    /**
     * Method to add a player to the players ArrayList.
     * 
     * @param player the player to be added.
     */          
    public void addPlayer(BlackjackPlayer player) {
        super.getPlayers().add(player);
    }
    
    /**
     * Method to remove a player from the players ArrayList when they quit
     * the game.
     * 
     * @param player the player to be removed.
     */          
    public void removePlayer(BlackjackPlayer player) {
        super.getPlayers().remove(player);
    }
    

    /**
     * Method to implement full game functionality.
     */          
    public void play() {
        Scanner input = new Scanner(System.in);
        boolean continueLoop = true;
        
        // find out how many players
        int numberOfPlayers = 0;
        do {
            System.out.print("Enter a number of players from 1 to 6: ");
            numberOfPlayers = input.nextInt();
            if ((numberOfPlayers < 1) || (numberOfPlayers > 6))
            {
               input.nextLine();
               System.out.println("You must enter a valid number of players. Please try again.");
            }
            else
            {
                continueLoop = false;
            }
        } while (continueLoop);
         
        // prompt users to register
        for (int i = 1; i <= numberOfPlayers; i++) {
            continueLoop = true;
            do {
                System.out.printf("Enter the name of Player %d: ", i);
                String playerName = input.next();
                if (this.checkPlayer(playerName)) {
                    this.addPlayer(new BlackjackPlayer(playerName));
                    continueLoop = false;
                }
                else
                    System.out.println("The player's name should be unique and not \"Dealer\". Please try again.");
            } while (continueLoop);
        }
         
        // betting
        for (int i = 0; i < this.getPlayers().size(); i++) {
           continueLoop = true;
           do {
                try {
                    System.out.printf("Player %s, you currently have $%.2f. Enter your"
                        + " bet amount: ", this.getPlayerAtIndex(i).getPlayerID(), this.getPlayerAtIndex(i).getMoney());
                    double betAmount = input.nextDouble();
                    this.getPlayerAtIndex(i).setBet(betAmount);
                    continueLoop = false;
                }
                catch (IllegalArgumentException IllegalArgumentException) {
                System.out.printf("\nException: %s\n", IllegalArgumentException);
                input.nextLine();
                System.out.println("The bet amount entered was invalid. Please try again." );
                }
            } while (continueLoop);
        }
         
        // everyone gets one card
        for (int i = 0; i < this.getPlayers().size(); i++) {
            this.getPlayerAtIndex(i).hit(this.getShoe().pop());
        }
        this.getDealer().hit(this.getShoe().pop());
         
        //everyone gets the second card, MAKE SURE CARD 2 FOR DEALER IS HIDDEN BTW
        for (int i = 0; i < this.getPlayers().size(); i++) {
            this.getPlayerAtIndex(i).hit(this.getShoe().pop());
        }
        this.getDealer().hit(this.getShoe().pop());
        
        System.out.println("Now, everyone got two cards face up, and the dealer "
                + "got two cards, one face up, another one hidden. Here is the "
                + "current state of the game for everyone:");
        System.out.println(this);
        System.out.println("First of all, we are going to check if anyone has naturals.");
        
        // insurance
        if (this.getDealer().getHand().getCardAtIndex(0).getRank() == Rank.ACE) {
            System.out.println("The dealer's first card is an ace, so the players "
                    + "may make an insurance - a side bet up to a half of their "
                    + "original bet that the dealer's hidden card is 10.");
            for (int i = 0; i < this.getPlayers().size(); i++) {
                System.out.printf("Player %s, do you want"
                             + " to make an insurance? Press y if yes: ", 
                             this.getPlayerAtIndex(i).getPlayerID());
                char answer = input.next().charAt(0);
                if (answer == 'y') {
                    continueLoop = true;
                    do {
                        try {
                            System.out.printf("Player %s, your bet is $%.2f. "
                                    + "Enter your insurance bet amount up to a "
                                    + "half of it: ", this.getPlayerAtIndex(i).getPlayerID(),
                                    this.getPlayerAtIndex(i).getBet());
                            double sideBetAmount = input.nextDouble();
                            this.getPlayerAtIndex(i).setSideBet(sideBetAmount);
                            continueLoop = false;
                        }
                        catch (IllegalArgumentException IllegalArgumentException) {
                            System.out.printf("\nException: %s\n", IllegalArgumentException);
                            input.nextLine();
                            System.out.println("The side bet amount entered was invalid. Please try again." );
                        }
                    } while (continueLoop);
                }
            }
            System.out.println("The dealer's hidden second card is revealed now!"); // IMPLEMENT THIS
            System.out.println(this.getDealer());
            if ((this.getDealer().getHand().getCardAtIndex(1).getRank() == Rank.TEN) &&
                   (this.getDealer().getHand().checkNaturals())) {
                for (int i = 0; i < this.getPlayers().size(); i++) {
                    if ((this.getPlayerAtIndex(i).getSideBet() == 0) && 
                            (this.getPlayerAtIndex(i).getHand().checkNaturals())) {
                        System.out.printf("Player %s, both you and the dealer "
                              + "have a natural, but you did not make a side bet. "
                                + "It's a tie, you get back your bet amount!", 
                                this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getBet());                        
                        this.finish(i);
                    }
                    else if ((this.getPlayerAtIndex(i).getSideBet() != 0) && 
                            (this.getPlayerAtIndex(i).getHand().checkNaturals())) {
                        System.out.printf("Player %s, both you and the dealer "
                              + "have a natural, but you also made a side bet."
                                + "It's a tie, you get back your bet amount, "
                                + "but you also won your side bet!", 
                                this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getBet() + 
                                this.getPlayerAtIndex(i).getSideBet());                        
                        this.finish(i);
                    }
                    else if ((this.getPlayerAtIndex(i).getSideBet() != 0) && 
                            (!this.getPlayerAtIndex(i).getHand().checkNaturals())) {
                        System.out.printf("Player %s, the dealer has a natural "
                              + "and you do not, but you also made a side bet. "
                                + "Unfortunately, you lost your bet, but you "
                                + "won your side bet!", this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getSideBet());
                        this.finish(i);
                    }
                    else if ((this.getPlayerAtIndex(i).getSideBet() == 0) && 
                            (!this.getPlayerAtIndex(i).getHand().checkNaturals())) {
                        System.out.printf("Player %s, the dealer has a natural "
                              + "and you do not, and you did not make a side bet either. "
                                + "Unfortunately, you lost!", 
                                this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(0);
                        this.finish(i);
                    }                                                  
                }
            }
        }
        
        // insurance is impossible; checking for naturals
        else if (this.getDealer().getHand().getCardAtIndex(0).getRank() == Rank.TEN) {
            System.out.println("The dealer's hidden second card is revealed now!"); // IMPLEMENT THIS
            System.out.println(this.getDealer());
            if (this.getDealer().getHand().checkNaturals()) {
                for (int i = 0; i < this.getPlayers().size(); i++) {
                    if (this.getPlayerAtIndex(i).getHand().checkNaturals()) {
                        System.out.printf("Player %s, both you and the dealer "
                              + "have a natural. It's a tie, you get back your"
                                + " bet amount!", this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getBet());                        
                        this.finish(i);
                    }
                    else {
                        System.out.printf("Player %s, the dealer has a natural "
                              + "and you do not. Unfortunately, you lost!", 
                                this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(0);   
                        this.finish(i);
                    }
                }
            }
            else {
                for (int i = 0; i < this.getPlayers().size(); i++) {
                    if (this.getPlayerAtIndex(i).getHand().checkNaturals()) {
                        System.out.printf("Player %s, you have a natural, and "
                                + "the dealer does not. Congratulations, you "
                                + "won 1.5 amount of your bet!", 
                                this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getBet() * 2.5);                        
                        this.finish(i);
                    }
                }
            }
        }
        else
            System.out.println("Nobody has naturals. The game continues for "
                    + "all players accordingly.");
        
        this.deleteFinishedPlayers();
        
        // the actual play
        ArrayList<BlackjackPlayer> doubleDowns = new ArrayList();
        do {
            for (int i = 0; i < this.getPlayers().size(); i++) {
            continueLoop = true;
            if (this.shoe.isEmpty())
                this.setShoe();
            else if (doubleDowns.contains(this.getPlayerAtIndex(i)))
                System.out.printf("Player %s, you did double down, so you skip!", 
                        this.getPlayerAtIndex(i).getPlayerID());
            else {
                do {
                    try {
                        if (!this.getPlayerAtIndex(i).busted()) {
                            this.getPlayerAtIndex(i).play();
                            int action = input.nextInt();
                            if (action == 1) {
                                this.getPlayerAtIndex(i).hit(this.getShoe().pop());
                            }                           
                            if (action == 2) {
                                this.getPlayerAtIndex(i).stand();
                            }  
                            if (action == 3)
                            {
                                this.getPlayerAtIndex(i).split();
                                System.out.println(this.getPlayerAtIndex(i));
                            }
                            if (action == 4) {
                                this.getPlayerAtIndex(i).doubleDown();   
                                this.getPlayerAtIndex(i).hit(this.getShoe().pop());
                                doubleDowns.add(this.getPlayerAtIndex(i));
                                System.out.println(this.getPlayerAtIndex(i));
                            }
                            if (action == 5) {
                                this.getPlayerAtIndex(i).surrender(this.getPlayerAtIndex(i).getHand());
                                this.finish(i);
                            }
                        }
                        if (!this.getPlayerAtIndex(i).getSplitHand().isEmpty()) {
                            this.getPlayerAtIndex(i).play();
                            int action = input.nextInt();
                            if (action == 1) {
                                this.getPlayerAtIndex(i).getHand().draw(this.getShoe().pop());
                            }                           
                            if (action == 2) {
                                this.getPlayerAtIndex(i).stand();
                            }  
                            if (action == 3)
                            {
                                this.getPlayerAtIndex(i).split();
                                System.out.println(this.getPlayerAtIndex(i));
                            }
                            if (action == 4) {
                                this.getPlayerAtIndex(i).doubleDown();   
                                this.getPlayerAtIndex(i).hit(this.getShoe().pop());
                                doubleDowns.add(this.getPlayerAtIndex(i));
                                System.out.println(this.getPlayerAtIndex(i));
                            }
                            if (action == 5) {
                                this.getPlayerAtIndex(i).surrender(this.getPlayerAtIndex(i).getSplitHand());
                                this.finish(i);
                            }
                        }
                        continueLoop = false;
                    }
                    catch (InvalidSplitException e) {
                        System.out.println("You cannot split now. Please try something else.\n");
                    }
                    catch (InvalidDoubleDownException e) {
                        System.out.println("Your cannot double down now. Please try something else.\n");
                    }
                } while (continueLoop);
            }    
        }
        this.dealer.play(this.getShoe().peek());
        
        //check who busted
        for (int i = 0; i < this.getPlayers().size(); i++) {
            if ((this.getPlayerAtIndex(i).busted()) && 
                    (this.getPlayerAtIndex(i).getSplitHand().isEmpty())) {
                System.out.printf("Player %s, you busted!\n", 
                                this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(0);                        
                        this.finish(i);
            } 
            else if ((this.getPlayerAtIndex(i).busted()) && 
                    (!this.getPlayerAtIndex(i).getSplitHand().isEmpty())) {
                 System.out.printf("Player %s, you busted, but you still have "
                         + "your split hand!", this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).setBet(getPlayerAtIndex(i).getBet() * 0.5);
            }
        }
        this.deleteFinishedPlayers();
        } while ((!dealer.busted()) && (dealer.getStatus()));
        
        //check if dealer busted
        if (this.dealer.busted()) {
            System.out.println("The dealer busted! All the remaining players "
            + "win their bets");
            for (int i = 0; i < this.getPlayers().size(); i++) {
                this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getBet() * 2);
                this.finish(i);
            }
            this.declareWinner();
            
        // check if dealer is still playing
        if (!this.dealer.getStatus()) {
            System.out.println("The dealer has not busted, but has reached " +
                    "the hand value of 17 or more, which mean they cannot " +
                    "draw more cards. Time to compare values of all the "
                    + "remaining players and the dealer!");
                for (int i = 0; i < this.getPlayers().size(); i++) {
                    if (this.dealer.getHand().handValue() > this.getPlayerAtIndex(i).getHand().handValue()) {
                        if (this.getPlayerAtIndex(i).getSplitHand().isEmpty()) {
                            System.out.printf("Player %s, you total is further from "
                                + "21 than the dealer's."
                                + "Unfortunately, you lost!", 
                                this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(0);
                        this.finish(i);
                        }
                        else {
                            if (this.dealer.getHand().handValue() < this.getPlayerAtIndex(i).getSplitHand().handValue()) {
                                System.out.printf("Player %s, you total is further from "
                                + "21 than the dealer's on one hand, but closer "
                                        + "on the split hand. It's a tie!", 
                                this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getBet());
                        this.finish(i); 
                            }
                        }
                    }
                    else if (this.dealer.getHand().handValue() < this.getPlayerAtIndex(i).getHand().handValue()) {
                        if (this.getPlayerAtIndex(i).getSplitHand().isEmpty()) {
                            System.out.printf("Player %s, you total is closer to "
                                + "21 than the dealer's. You won!", 
                                this.getPlayerAtIndex(i).getPlayerID());
                            this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getBet() * 2);
                            this.finish(i);
                        }
                        else {
                            if (this.dealer.getHand().handValue() < this.getPlayerAtIndex(i).getSplitHand().handValue()) {
                                System.out.printf("Player %s, you total is further from "
                                        + "21 than the dealer's on split hand, but closer "
                                        + "on the main hand. It's a tie!", 
                                this.getPlayerAtIndex(i).getPlayerID());
                                this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getBet());
                                this.finish(i); 
                            }
                        }
                    }
                    else
                       System.out.printf("Player %s, you total the same as "
                                + "the dealer's. It's a tie!", 
                                this.getPlayerAtIndex(i).getPlayerID());
                        this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getBet());
                        this.finish(i); 
                }
                this.declareWinner();
            }
        }
    }
    
    /**
     * Method to finish the game.
     * 
     */
    public void declareWinner() {
        System.out.println("The game is over!");
        System.exit(0); 
    }

    /**
     * Method to communicate player's total money after finishing the game.
     *
     * @param i the index of the player to print money and id.
     */       
    public void finish(int i) {
        System.out.printf("\nPlayer %s, you have finished the game! "
                + "You have $%.2f now. Thanks for playing!\n",
                this.getPlayerAtIndex(i).getPlayerID(), this.getPlayerAtIndex(i).getMoney());
        this.getPlayerAtIndex(i).stopPlaying();
    }
    
    public void deleteFinishedPlayers() {
        Iterator<Player> iterator = super.getPlayers().iterator();
        while(iterator.hasNext()) {
            Player player = iterator.next();
            BlackjackPlayer castedPlayer = (BlackjackPlayer)player;
            if (!castedPlayer.getStatus()) {
                iterator.remove();
            }
        }
    }

    /**
     * Return's status of all players in the game and the dealer's status.
     *
     * @return String representation of each players status and dealer's status.
     */       
    public String toString() {
        StringBuilder s = new StringBuilder();
         for (int i = 0; i < this.getPlayers().size(); i++) {
             s.append("\n");
             s.append(this.getPlayerAtIndex(i).toString());
             s.append("\n");
         }
         s.append("\n");
         s.append(this.dealer.hideCard());
         return s.toString();
    }
}
