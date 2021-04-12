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
    
    public BlackjackPlayer(String playerID) {
        super();
        super.setPlayerID(playerID);
        this.splitHand = new BlackjackHand();
        setBet(0);
        setSideBet(0);
        setMoney(500);
    }
    
    public BlackjackHand getSplitHand() {
        return splitHand;
    }
    
    public void setSplitHand(BlackjackHand splitHand) {
        this.splitHand.draw(super.getHand().getCardAtIndex(1));
        super.getHand().removeCardToSplit();
    }
    
    public double getBet() {
        return bet;
    }
    
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
    
    public double getSideBet() {
        return sideBet;
    }
    
    public void setSideBet(double sideBet) {
         if (sideBet > (this.bet * 0.5))
            throw new IllegalArgumentException("The side bet should be up to half the original");
         else if (sideBet < 0)
             throw new IllegalArgumentException("The side bet amount should be positive");
         else
             this.sideBet = sideBet;
    }
    
     public double getMoney() {
        return money;
    }
    
    public void setMoney(double money) {
        if (money < 0)
            throw new IllegalArgumentException("The amount of money cannot be negative");
        else
            this.money = money;
    }
    
    public void win(double amount) {
        this.money += amount;
        this.bet = 0;
        this.sideBet = 0;
        if (this.splitHand.isEmpty())
            super.getHand().clearHand();
        else if (this.splitHand.isEmpty())
            this.splitHand.clearHand();
    }
    
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
    
    // that one case one the given card is face down (like for dealer)
    // if ever did that, cannot do anything anymore until the end of the round
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
    
    public void surrender() {
        this.money += this.bet * 0.5;
        this.bet = 0;
        this.sideBet = 0;
        super.getHand().clearHand();
        this.splitHand.clearHand();
    }
    
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