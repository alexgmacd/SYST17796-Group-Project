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
public class BlackjackGameTest {
    
    public BlackjackGameTest() {
    }
    
    /**
     * Good Test of checkPlayer method, of class BlackjackGame.
     */
    @Test
    public void goodTestCheckPlayer() {
        System.out.println("Good checkPlayer test.");
        String playerID = "A";
        BlackjackGame game = new BlackjackGame();
        game.addPlayer(new BlackjackPlayer("B"));
        boolean expResult = true;
        boolean result = game.checkPlayer(playerID);
        assertEquals(expResult, result);
    }
    
    /**
     * Bad Test of checkPlayer method, of class BlackjackGame.
     */
    @Test
    public void badTestCheckPlayer() {
        System.out.println("Bad checkPlayer test.");
        String playerID = "A";
        BlackjackGame game = new BlackjackGame();
        game.addPlayer(new BlackjackPlayer("A"));
        boolean expResult = false;
        boolean result = game.checkPlayer(playerID);
        assertEquals(expResult, result);
    }

    /**
     * Second Bad Test of checkPlayer method, of class BlackjackGame.
     */
    @Test
    public void secondBadTestCheckPlayer() {
        System.out.println("Second Bad checkPlayer test.");
        String playerID = "Dealer";
        BlackjackGame game = new BlackjackGame();
        game.addPlayer(new BlackjackPlayer("Dealer"));
        boolean expResult = false;
        boolean result = game.checkPlayer(playerID);
        assertEquals(expResult, result);
    }
    
    /**
     * Boundary Test of checkPlayer method, of class BlackjackGame.
     */
    @Test
    public void boundaryTestCheckPlayer() {
        System.out.println("Boundary checkPlayer test.");
        String playerID = "";
        BlackjackGame game = new BlackjackGame();
        boolean expResult = true;
        boolean result = game.checkPlayer(playerID);
        assertEquals(expResult, result);
    }
    
    
}
