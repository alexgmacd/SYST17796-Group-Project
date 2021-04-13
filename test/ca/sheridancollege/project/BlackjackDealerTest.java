/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Alex
 */
public class BlackjackDealerTest {
    
    public BlackjackDealerTest() {
    }


    /**
     * Good Test of busted method, of class BlackjackDealer.
     */
    @Test
    public void goodTestBusted() {
        System.out.println("Good busted test");
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.hit(new BlackjackCard(Rank.TEN));
        dealer.hit(new BlackjackCard(Rank.TEN));
        dealer.hit(new BlackjackCard(Rank.TEN));
  
        boolean expResult = true;
        boolean result = dealer.busted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Bad Test of busted method, of class BlackjackDealer.
     */
    @Test
    public void badTestBusted() {
        System.out.println("Bad busted test");
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.hit(new BlackjackCard(Rank.TEN));
        dealer.hit(new BlackjackCard(Rank.TEN));
  
        boolean expResult = false;
        boolean result = dealer.busted();
        assertEquals(expResult, result);
    }
    
    /**
     * Boundary Test of busted method, of class BlackjackDealer.
     */
    @Test
    public void boundaryTestBusted() {
        System.out.println("Boundary busted test");
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.hit(new BlackjackCard(Rank.TEN));
        dealer.hit(new BlackjackCard(Rank.TEN));
        dealer.hit(new BlackjackCard(Rank.ACE));
  
        boolean expResult = false;
        boolean result = dealer.busted();
        assertEquals(expResult, result);
    }
    
}
