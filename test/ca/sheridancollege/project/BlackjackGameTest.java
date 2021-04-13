/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.Stack;
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
public class BlackjackGameTest {
    
    public BlackjackGameTest() {
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
    


    /**
     * Test of play method, of class BlackjackGame.
     */
    @Ignore
    public void testPlay() {
        System.out.println("play");
        BlackjackGame instance = new BlackjackGame();
        instance.play();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of declareWinner method, of class BlackjackGame.
     */
    @Ignore
    public void testDeclareWinner() {
        System.out.println("declareWinner");
        BlackjackGame instance = new BlackjackGame();
        instance.declareWinner();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
