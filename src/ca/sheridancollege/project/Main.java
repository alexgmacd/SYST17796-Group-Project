/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eleonora Kukash
 */
public class Main {
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        Scanner input = new Scanner(System.in);
        boolean continueLoop = true;
        boolean samePlayer = true;
        
        // find out how many players
        ArrayList <BlackjackPlayer> players = new ArrayList();
        int numberOfPlayers = 0;
         do {
             System.out.print("Enter a number of players from 1 to 6: ");
             numberOfPlayers = input.nextInt();
             if ((numberOfPlayers < 1) || (numberOfPlayers > 6))
             {
                input.nextLine();
                System.out.println("You must enter a valid number of players. Please try again.");
             }
             else
             {
                 continueLoop = false;
             }
         } while (continueLoop);
         
         // prompt users to register
         for (int i = 1; i <= numberOfPlayers; i++) {
             continueLoop = true;
             do {
                 System.out.printf("Enter the name of Player %d: ", i);
                 String playerName = input.next();
                 if (game.checkPlayer(playerName)) {
                     game.addPlayer(new BlackjackPlayer(playerName));
                     continueLoop = false;
                 }
                 else
                     System.out.println("The player's name should be unique and not \"Dealer\". Please try again.");
            } while (continueLoop);
         }
         
         // EVERYTHING BELOW WILL BE ADDED INSIDE DO-WHILE (TO REPEAT ROUND); NOT ADDED YET SOLELY FOR NOT CONFUSING MYSELF UNTIL DONE CODING ONE WHOLE ROUND
         
         // betting
         for (int i = 0; i < game.getPlayers().size(); i++) {
            continueLoop = true;
            do {
                try {
                    System.out.printf("Player %s, you currently have $%.2f. Enter your"
                        + " bet amount: ", game.getPlayerAtIndex(i).getPlayerID(), game.getPlayerAtIndex(i).getMoney());
                    double betAmount = input.nextDouble();
                    game.getPlayerAtIndex(i).setBet(betAmount);
                    continueLoop = false;
                }
                catch (IllegalArgumentException IllegalArgumentException) {
                System.out.printf("\nException: %s\n", IllegalArgumentException);
                input.nextLine();
                System.out.println("The bet amount entered was invalid. Please try again." );
                }
            } while (continueLoop);
         }
         
         // everyone gets one card, ADD 2 LINES FOR DISPLAYING
         for (int i = 0; i < game.getPlayers().size(); i++) {
             game.getPlayerAtIndex(i).hit(game.getShoe().pop());
         }
         game.getDealer().hit(game.getShoe().pop());
         
         //everyone gets the second card, ADD 2 LINES FOR DISPLAYING (last card hidden for dealer only)
         for (int i = 0; i < game.getPlayers().size(); i++) {
             game.getPlayerAtIndex(i).hit(game.getShoe().pop());
         }
         game.getDealer().hit(game.getShoe().pop());
         
         // check naturals
         for (int i = 0; i < game.getPlayers().size(); i++) {
             if (game.getPlayerAtIndex(i).getHand().checkNaturals()) {
                 if (game.getDealer().getHand().checkNaturals()) {
                      game.getPlayerAtIndex(i).win(game.getPlayerAtIndex(i).getBet());
                      // ROUND END
                 }
                 else {
                     game.getPlayerAtIndex(i).win(game.getPlayerAtIndex(i).getBet() * 1.5);
                     // ROUND END
                 }
                 
            }
        }
    }
}
