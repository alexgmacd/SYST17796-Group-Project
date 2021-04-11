package ca.sheridancollege.project;

/**
 *
 * @author Eleonora Kukash
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
    
    public double getBet() {
        return bet;
    }
    
    public void setBet(double bet) {
        if (bet < 0)
            throw new IllegalArgumentException("The bet amount should be positive");
        else if (bet > this.money)
             throw new IllegalArgumentException("Not enough money to make this bet");
        else
            this.bet = bet;
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
    
    public void split() throws InvalidSplitException {
        if ((splitHand.getSize() == 0) && (super.getHand().getSize() == 2) && 
                (super.getHand().getCardAtIndex(0).getRank() == 
                super.getHand().getCardAtIndex(1).getRank())) {
            splitHand.draw(super.getHand().getCardAtIndex(1));
            super.getHand().removeCard();
        } else {
            throw new InvalidSplitException(
                    "Split only available with two cards of the same rank.");
        }
    }
    
    // that one case one the given card is face down (like always for dealer)
    // if ever did that, cannot do anything anymore until the end of the round
    public void doubleDown() throws InvalidDoubleDownException {
        if ((super.getHand().handValue() == 9) || 
                (super.getHand().handValue() == 10) ||
                (super.getHand().handValue() == 11)) {
            this.sideBet = bet;
            //draw ONE card after and is done for the rest of the game
        } else {
            throw new InvalidDoubleDownException(
                    "Double down only available with a hand with total value"
                            + " of 9, 10 or 11.");

        }
    }
    
    public void play() {
        
    }
    
    public String toString() {
      String result = "";
      return result;
    }
}
