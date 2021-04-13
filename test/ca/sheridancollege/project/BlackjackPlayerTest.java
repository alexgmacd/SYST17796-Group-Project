/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import org.junit.Test;


/**
 *
 * @author Alex
 */
public class BlackjackPlayerTest {
    
    public BlackjackPlayerTest() {
    }
    
    /**
     * Good Test of setBet method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test 
    public void goodTestSetBet() {
        System.out.println("Good setBet test");
        double bet = 150.0;
        BlackjackPlayer player = new BlackjackPlayer("");
        player.setBet(bet);
    }
    
    /**
     * Bad Test of setBet method, of class BlackjackPlayer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void badTestSetBet() {
        System.out.println("Bad setBet test");
        double bet = -50.0;
        BlackjackPlayer player = new BlackjackPlayer("");
        player.setBet(bet);
    }
    
    /**
     * Second Bad Test of setBet method, of class BlackjackPlayer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void secondBadTestSetBet() {
        System.out.println("Second bad setBet test");
        double bet = 501;
        BlackjackPlayer player = new BlackjackPlayer("");
        player.setBet(bet);
    }
    
    /**
     * Good Test of setBet method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test 
    public void boundaryTestSetBet() {
        System.out.println("Boundary setBet test");
        double bet = 500.0;
        BlackjackPlayer player = new BlackjackPlayer("");
        player.setBet(bet);
    }
    

    /**
     * Good Test of setSideBet method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test 
    public void goodTestSetSideBet() {
        System.out.println("Good setSideBet test");
        double bet = 150.0;
        double sideBet = 50.0;
        BlackjackPlayer player = new BlackjackPlayer("");
        player.setBet(bet);
        player.setSideBet(sideBet);
    }
    
    /**
     * Bad Test of setSideBet method, of class BlackjackPlayer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void badTestSetSideBet() {
        System.out.println("Bad setSideBet test");
        double bet = 150.0;
        double sideBet = -50.0;
        BlackjackPlayer player = new BlackjackPlayer("");
        player.setBet(bet);        
        player.setSideBet(sideBet);
    }
    
    /**
     * Second Bad Test of setSideBet method, of class BlackjackPlayer.
     */
    @Test (expected = IllegalArgumentException.class)
    public void secondBadTestSetSideBet() {
        System.out.println("Second Bad setSideBet test");
        double bet = 150.0;
        double sideBet = 100.0;
        BlackjackPlayer player = new BlackjackPlayer("");
        player.setBet(bet);        
        player.setSideBet(sideBet);
    }
    
    /**
     * Boundary Test of setSideBet method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test 
    public void boundaryTestSetSideBet() {
        System.out.println("Boundary setSideBet test");
        double bet = 150.0;
        double sideBet = 75.0;
        BlackjackPlayer player = new BlackjackPlayer("");
        player.setBet(bet);        
        player.setSideBet(sideBet);
    }   


    /**
     * Good Test of setMoney method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test
    public void goodTestSetMoney() {
        System.out.println("Good setMoney test");
        double money = 1000.0;
        BlackjackPlayer instance = new BlackjackPlayer("");
        instance.setMoney(money);
    }
    
    /**
     * Bad Test of setMoney method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test (expected = IllegalArgumentException.class)
    public void badTestSetMoney() {
        System.out.println("Bad setMoney test");
        double money = -50.0;
        BlackjackPlayer instance = new BlackjackPlayer("");
        instance.setMoney(money);
    }
    
    /**
     * Boundary Test of setMoney method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test
    public void boundaryTestSetMoney() {
        System.out.println("Boundary setMoney test");
        double money = 0.0;
        BlackjackPlayer instance = new BlackjackPlayer("");
        instance.setMoney(money);
    }

    
    /**
     * Good Test of split method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test
    public void goodTestSplit() throws Exception {
        System.out.println("Good split test");
        BlackjackPlayer player = new BlackjackPlayer("");
        player.hit(new BlackjackCard(Rank.FIVE));
        player.hit(new BlackjackCard(Rank.FIVE));
        player.split();
    }
    
    /**
     * Bad Test of split method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test (expected = InvalidSplitException.class)
    public void badTestSplit() throws Exception {
        System.out.println("Bad split test");
        BlackjackPlayer player = new BlackjackPlayer("");
        player.hit(new BlackjackCard(Rank.SIX));
        player.hit(new BlackjackCard(Rank.FIVE));
        player.split();
    }
 
    /**
     * Boundary Test of split method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test (expected = InvalidSplitException.class)
    public void boundaryTestSplit() throws Exception {
        System.out.println("Boundary split test");
        BlackjackPlayer player = new BlackjackPlayer("");
        player.hit(new BlackjackCard(Rank.SIX));
        player.hit(new BlackjackCard(Rank.SIX));
        player.hit(new BlackjackCard(Rank.SIX));
        player.split();
    }

    /**
     * Good Test of doubleDown method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test
    public void goodTestDoubleDown() throws Exception {
        System.out.println("Good doubleDown test");
        BlackjackPlayer player = new BlackjackPlayer("");
        player.hit(new BlackjackCard(Rank.FIVE));
        player.hit(new BlackjackCard(Rank.FIVE));
        player.doubleDown();
    }
    
    /**
     * Bad Test of doubleDown method, of class BlackjackPlayer.
     */
    @Test (expected = InvalidDoubleDownException.class)
    public void badTestDoubleDown() throws Exception {
        System.out.println("Bad doubleDown test");
        BlackjackPlayer player = new BlackjackPlayer("");
        player.hit(new BlackjackCard(Rank.TEN));
        player.hit(new BlackjackCard(Rank.FIVE));
        player.doubleDown();
    }
    
    /**
     * Boundary Test of doubleDown method, of class BlackjackPlayer.
     * Expecting no exception thrown.
     */
    @Test 
    public void boundaryTestDoubleDown() throws Exception {
        System.out.println("Boundary doubleDown test");
        BlackjackPlayer player = new BlackjackPlayer("");
        player.hit(new BlackjackCard(Rank.THREE));
        player.hit(new BlackjackCard(Rank.THREE));
        player.hit(new BlackjackCard(Rank.THREE));
        player.hit(new BlackjackCard(Rank.TWO));
        player.doubleDown();
    }
    
}
