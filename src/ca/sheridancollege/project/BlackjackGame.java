package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
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
                        this.getPlayerAtIndex(i).win(this.getPlayerAtIndex(i).getBet() * 1.5);                        
                        this.finish(i);
                    }
                }
            }
        }
        else
            System.out.println("Nobody has naturals. The game continues for "
                    + "all players accordingly.");
        
        //each player does something
        ArrayList<BlackjackPlayer> doubleDowns = new ArrayList();
        for (int i = 0; i < this.getPlayers().size(); i++) {
            continueLoop = true;
            if (doubleDowns.contains(this.getPlayerAtIndex(i)))
                System.out.printf("Player %s, you did double down, so you skip!", 
                        this.getPlayerAtIndex(i).getPlayerID());
            else {
                do {
                    try {
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
                            this.getPlayerAtIndex(i).surrender();
                            this.finish(i);
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
        // dealer does their thing
        //check for busted is done, but will be pasted after this
    }
    
    public void declareWinner() {
    }
    
    public void finish(int i) {
        System.out.printf("Player %s, you have finished the game! "
                + "You have $%.2f now. Thanks for playing!\n",
                this.getPlayerAtIndex(i).getPlayerID(), this.getPlayerAtIndex(i).getMoney());
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
         for (int i = 0; i < this.getPlayers().size(); i++) {
             s.append("\n");
             s.append(this.getPlayerAtIndex(i).toString());
             s.append("\n");
         }
         s.append("\n");
         s.append(this.dealer.toString());
         return s.toString();
    }
}
