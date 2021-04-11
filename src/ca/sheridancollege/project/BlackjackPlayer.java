package ca.sheridancollege.project;

/**
 *
 * @author Eleonora
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
    
    public void split() {
        if ((splitHand.getSize() == 0) && (super.getHand().getSize() == 2) && 
                (super.getHand().getCardAtIndex(0).getRank() == 
                super.getHand().getCardAtIndex(0).getRank())) {
            splitHand.draw(super.getHand().getCardAtIndex(1));
            super.getHand().removeCardToSplit();
        }
        // else throw custom exception
    }
    
    // that one case one the given card is face down (like always for dealer)
    // if ever did that, cannot do anything anymore until the end of the round
    public void doubleDown() {
        if ((super.getHand().handValue() == 9) || 
                (super.getHand().handValue() == 10) ||
                (super.getHand().handValue() == 11)) {
            this.sideBet = bet;
            //draw ONE card after and is done for the rest of the game
        }
        //else EX
    }
    
    public void play() {
        
    }
    
    public String toString() {
      return String.format(getPlayerID() + " " + bet + " " + money);
    }
}
