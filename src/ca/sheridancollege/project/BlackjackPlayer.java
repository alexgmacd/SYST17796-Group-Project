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
    
    public void split() {
        if ((splitHand.getSize() == 0) && (super.hand.getSize() == 2) && 
                (super.hand.get(0).getType() == super.hand.get(1).getType())) {
            splitHand.add(super.hand.get(1));
            super.hand.remove(1);
        }
        // else throw custom exception
    }
    
    public void doubleDown() {
        
    }
    
    public void insurance(double sideBetAmount) {
        
    }
    
    public void surrender() {
        
    }
    
    public void play() {
        
    }
    
    public String toString() {
      String result = "";
      return result;
    }
}
