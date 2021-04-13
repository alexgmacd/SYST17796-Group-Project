/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Alex
 */
public class BlackjackHandTest {
    
    public BlackjackHandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Good Test of draw method, of class BlackjackHand.
     */
    @Test
    public void goodTestDraw() {
        System.out.println("Good draw test");
        BlackjackCard card = new BlackjackCard(Rank.ACE);
        BlackjackHand hand = new BlackjackHand();
        hand.draw(card);
        int expResult = 1;    
        assertEquals(expResult,hand.getAceCount());
    }
    
    /**
     * Bad Test of draw method, of class BlackjackHand.
     */
    @Test
    public void badTestDraw() {
        System.out.println("Bad draw test");
        BlackjackCard card = new BlackjackCard(Rank.TWO);
        BlackjackHand hand = new BlackjackHand();
        hand.draw(card);
        int expResult = 0;    
        assertEquals(expResult,hand.getAceCount());
    }
    
    /**
     * Boundary Test of draw method, of class BlackjackHand.
     */
    @Test
    public void boundaryTestDraw() {
        System.out.println("Boundary draw test");
        BlackjackCard card = new BlackjackCard(Rank.ACE);
        BlackjackCard card2 = new BlackjackCard(Rank.ACE);
        BlackjackHand hand = new BlackjackHand();
        hand.draw(card);
        hand.draw(card2);
        int expResult = 2;    
        assertEquals(expResult,hand.getAceCount());
    }

    /**
     * Good Test of checkNaturals method, of class BlackjackHand.
     */
    @Test
    public void goodTestCheckNaturals() {
        System.out.println("Good checkNaturals test");
        BlackjackHand hand = new BlackjackHand();
        hand.draw(new BlackjackCard(Rank.ACE));
        hand.draw(new BlackjackCard(Rank.TEN));
        boolean expResult = true;
        boolean result = hand.checkNaturals();
        assertEquals(expResult, result);
    }

    /**
     * Bad Test of checkNaturals method, of class BlackjackHand.
     */
    @Test
    public void badTestCheckNaturals() {
        System.out.println("Bad checkNaturals test");
        BlackjackHand hand = new BlackjackHand();
        hand.draw(new BlackjackCard(Rank.EIGHT));
        hand.draw(new BlackjackCard(Rank.TEN));
        boolean expResult = false;
        boolean result = hand.checkNaturals();
        assertEquals(expResult, result);
    }

    /**
     * Boundary Test of checkNaturals method, of class BlackjackHand.
     */
    @Test
    public void boundaryTestCheckNaturals() {
        System.out.println("Boundary checkNaturals test");
        BlackjackHand hand = new BlackjackHand();
        hand.draw(new BlackjackCard(Rank.ACE));
        hand.draw(new BlackjackCard(Rank.TEN));
        hand.setSize(3);
        boolean expResult = false;
        boolean result = hand.checkNaturals();
        assertEquals(expResult, result);
    }

    /**
     * Good Test of handValue method, of class BlackjackHand.
     */
    @Test
    public void goodTestHandValue() {
        System.out.println("Good handValue test");
        BlackjackHand hand = new BlackjackHand();
        hand.draw(new BlackjackCard(Rank.TEN));
        hand.draw(new BlackjackCard(Rank.ACE));
        int expResult = 21;
        int result = hand.handValue();
        assertEquals(expResult, result);
    }
    
    /**
     * Bad Test of handValue method, of class BlackjackHand.
     */
    @Test
    public void badTestHandValue() {
        System.out.println("Bad handValue test");
        BlackjackHand hand = new BlackjackHand();
        hand.draw(new BlackjackCard(Rank.TWO));
        hand.draw(new BlackjackCard(Rank.TEN));
        hand.draw(new BlackjackCard(Rank.ACE));
        int expResult = 13;
        int result = hand.handValue();
        assertEquals(expResult, result);
    }
    
    /**
     * Boundary Test of handValue method, of class BlackjackHand.
     */
    @Test
    public void boundaryTestHandValue() {
        System.out.println("Boundary handValue test");
        BlackjackHand hand = new BlackjackHand();
        hand.draw(new BlackjackCard(Rank.ACE));
        hand.draw(new BlackjackCard(Rank.ACE));
        int expResult = 12;
        int result = hand.handValue();
        assertEquals(expResult, result);
    }

}
