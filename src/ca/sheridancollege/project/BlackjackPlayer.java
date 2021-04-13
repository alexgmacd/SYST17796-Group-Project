package ca.sheridancollege.project;

/**
 *
 * @author Eleonora
 * @modifier Alex
 */
public class BlackjackPlayer extends BlackjackDealer {
    private BlackjackHand splitHand;
    private double bet;
    private double sideBet;
    private double money;

    /**
     * Constructor that calls super, sets the Player's ID, initiates splitHand 
     * and sets values of bet, sideBet and money.
     * 
     * @param playerID the ID of the player.
     */             
    public BlackjackPlayer(String playerID) {
        super();
        super.setPlayerID(playerID);
        this.splitHand = new BlackjackHand();
        setBet(0);
        setSideBet(0);
        setMoney(500);
        super.setStatus(true);
        
    }

    /**
     * Getter method to get the split hand.
     * 
     * @return the split hand.
     */               
    public BlackjackHand getSplitHand() {
        return splitHand;
    }

    /**
     * Setter method to set the split hand.
     * 
     * @param splitHand the BlackjackHand to set.
     */           
    public void setSplitHand(BlackjackHand splitHand) {
        this.splitHand.draw(super.getHand().getCardAtIndex(1));
        super.getHand().removeCardToSplit();
    }

    /**
     * Getter method to get the player's bet value.
     * 
     * @return the bet value.
     */   
    public double getBet() {
        return bet;
    }

    /**
     * Setter method to set the bet amount, if the value is negative or greater
     * than the current money value then an exception will be thrown.
     * 
     * @param bet the bet value to be set.
     * @throws IllegalArgumentException if bet is negative or greater than money
     * value.
     */               
    public void setBet(double bet) {
        if (bet < 0)
            throw new IllegalArgumentException("The bet amount should be positive");
        else if (bet > this.money)
             throw new IllegalArgumentException("Not enough money to make this bet");
        else {
             this.bet = bet;
             this.money -= bet;
        }
    }

    /**
     * Getter method to get the player's side bet value.
     * 
     * @return the side bet value.
     */               
    public double getSideBet() {
        return sideBet;
    }

    /**
     * Setter method to set the side bet amount, if the value is negative or 
     * greater than half the current bet value an exception will be thrown.
     * 
     * @param sideBet the side bet value to be set.
     * @throws IllegalArgumentException if side bet is greater than half of bet
     * value or is a negative value.
     */ 
    public void setSideBet(double sideBet) {
         if (sideBet > (this.bet * 0.5))
            throw new IllegalArgumentException("The side bet should be up to half the original");
         else if (sideBet < 0)
             throw new IllegalArgumentException("The side bet amount should be positive");
         else
             this.sideBet = sideBet;
    }

    /**
     * Getter method to get the player's money value.
     * 
     * @return the money value.
     */           
     public double getMoney() {
        return money;
    }

    /**
     * Setter method to set the money amount, if the value is negative then an 
     * exception will be thrown.
     * 
     * @param money the money value to be set.
     */                 
    public void setMoney(double money) {
        if (money < 0)
            throw new IllegalArgumentException("The amount of money cannot be negative");
        else
            this.money = money;
    }

    /**
     * Method to call when player win's, adds amount to their money, resets
     * their bets and clear's player's hands if they contain cards.
     * 
     * @param amount the amount won by the player.
     */               
    public void win(double amount) {
        this.money += amount;
        this.bet = 0;
        this.sideBet = 0;
        if (this.splitHand.isEmpty())
            super.getHand().clearHand();
        else if (this.splitHand.isEmpty())
            this.splitHand.clearHand();
    }

    /**
     * Method to split the player's hand if their two card's Rank values match.
     * 
     * @throws InvalidSplitException if the two cards are not the same rank.
     */  
    public void split() throws InvalidSplitException {
        if ((splitHand.getSize() == 0) && (super.getHand().getSize() == 2) && 
                (super.getHand().getCardAtIndex(0).getRank() == 
                super.getHand().getCardAtIndex(1).getRank())) {
            splitHand.draw(super.getHand().getCardAtIndex(1));
            super.getHand().removeCardToSplit();
        } 
        else
            throw new InvalidSplitException(
                    "Split only available with two cards of the same rank.");
    }
    
    /**
     * Method to double down player's hand if their hand value is 9, 10 or 11.
     * 
     * @throws InvalidDoubleDownException if hand value is not 9, 10 or 11.
     */            
    public void doubleDown() throws InvalidDoubleDownException {
        if ((super.getHand().handValue() == 9) || 
                (super.getHand().handValue() == 10) ||
                (super.getHand().handValue() == 11))
            this.bet *= 2;
        else
            throw new InvalidDoubleDownException(
                    "Double down only available with a hand with total value"
                            + " of 9, 10 or 11.");
    }

    /**
     * Method to allow player to surrender their hand.
     * @param hand hand to surrender
     */  
    public void surrender(BlackjackHand hand) {
        this.money += this.bet * 0.5;
        this.bet = 0;
        this.sideBet = 0;
        if (hand == this.getHand())
            super.getHand().clearHand();
        else
            this.splitHand.clearHand();
    }

    /**
     * Method that shows player options they have to play the game.
     */    
    public void play() {
        System.out.println(this);
        System.out.printf("Player %s, choose what you want to do:\n" +
                "Press 1 to hit - take another card in attempt to get closer "
                + "to 21 or hit 21 exactly.\n" +
                "Press 2 to stand - not ask for another card.\n" +
                "Press 3 to split - if you see that you have 2 cards of the " +
                "same denomination, you can treat them as 2 separate hands; " +
                "the original bet amount will go on one card, and equal " + 
                "amount will be placed as a bet on the other.\n"
                + "Press 4 to double down - if you see that your cards are " +
                "total of 9, 10, or 11, you can double your bet in exchange " +
                "of taking just one more card, placed face down until the " +
                "end of the game.\n" +
                "Press 5 to surrender - get a half of your bet amount back " +
                "and quit the game\n", this.getPlayerID());
    }

    /**
     * Return's String of player's hand, money and appends their bet values if 
     * they are greater than 0.
     *
     * @return String representation of the player's current status.
     */       
    public String toString() {
      StringBuilder s = new StringBuilder();
      s.append(String.format("%s's %s Hand Value: %d", 
            getPlayerID(), getHand().toString(), getHand().handValue()));
      s.append("\nCurrent money: ").append(money);
      if(bet > 0){
          s.append("\nCurrent bet: ").append(bet);
      }
      if(sideBet > 0 ){
          s.append("\nCurrent side bet: ").append(sideBet);
      }
      return s.toString();
    }      
}