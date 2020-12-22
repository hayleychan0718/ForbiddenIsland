package setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import players.*;
import utility.Utility;

/**
 * User interface view for setting up the players at the begining of the game
 * @author Liam Fitzgerald and Hayley Chand
 *
 */
public class PlayerSetupView {
	
	private PlayerSetupController controller;
	private static PlayerSetupView playerSetupView = null;

	/**
	 * Gets the instance of the PlayerSetupView
	 * @return
	 */
	public static PlayerSetupView getInstance() {
		if(playerSetupView == null)
			playerSetupView = new PlayerSetupView();
		return playerSetupView;
	}
	
	/**
	 * Gets the user to set the number of players between 2--4
	 * @param inputScanner
	 * @return numOfPlayers
	 */
	public int setNumofPlayers(Scanner inputScanner) {
		int userInput;
		Integer numOfPlayers = 0;
		System.out.println("Enter the number of players playing, this must be be of the range 2 to 4:");

		userInput=Utility.acceptableInput(2, 4, inputScanner);	
		inputScanner.nextLine();
		numOfPlayers=userInput;
	 
		return numOfPlayers;
	}

	/**
	 * Used to create a player and assign a role at random, ensuring no two players have the same role
	 * @param playerNumber
	 * @param inputScanner
	 */
	public void createPlayer(int playerNumber, Scanner inputScanner) { 
		ArrayList<String> listofRoles = controller.getRoleList();

		System.out.println("You are Player number:"+ playerNumber + "\n");
		System.out.println("Please Enter Your Name:");
		String playerName = inputScanner.nextLine();
		System.out.println("Please Enter the Symbol ( Use { $ , % , & , @} makes it easier to identify) you want your pawn to be represented by on the board ");
		String symbol = inputScanner.nextLine();

		Collections.shuffle(listofRoles);	//shuffle used to ensure random role selected
		
		switch(listofRoles.get(0)){		//Selects the first random role and assigns the player that role
		case "Diver":
			Diver diver = controller.getDiverInstance(playerName, playerNumber, symbol);
			controller.addPlayer(diver);
			break;

		case "Engineer":
			Engineer engineer = controller.getEngineerInstance(playerName, playerNumber, symbol);
			controller.addPlayer(engineer);
			break;

		case "Explorer":
			Explorer explorer = controller.getExplorerInstance(playerName, playerNumber, symbol);
			controller.addPlayer(explorer);
			break;

		case "Messenger":
			Messenger messenger = controller.getMessengerInstance(playerName, playerNumber, symbol);
			controller.addPlayer(messenger);
			break;

		case "Navigator":
			Navigator navigator = controller.getNavigatorInstance(playerName, playerNumber, symbol);
			controller.addPlayer(navigator);
			break;

		case "Pilot":
			Pilot pilot = controller.getPilotInstance(playerName, playerNumber, symbol);
			controller.addPlayer(pilot);
			break;
			
		default:
			break;
		}
		System.out.println("Your Role is: " + listofRoles.get(0) + "\n");
		controller.removeRole(0); //Makes sure that another player cannot be assigned that role by removing it from the available role list
	}

	/**
	 * Creates the decided number of Players
	 * @param inputScanner
	 */
	public void createPlayers(Scanner inputScanner) {
		int numOfPlayers=0;
		
		numOfPlayers= setNumofPlayers(inputScanner);
			for(int i = 1; i< numOfPlayers+1; i++) {	//Start from player 1
				createPlayer(i, inputScanner);
				}
			printListofPlayers();
		}
	
	/**
	 * Set up the controller for the view
	 * @param controller
	 */
	public void setController(PlayerSetupController controller) {
		this.controller=controller;
	}
	
	/**
	 * Prints out the list of players
	 */
	  public void printListofPlayers() {
		  ArrayList<Player> playerList = controller.getListofPlayers();
	    	for(Player player: playerList) {
	        	System.out.println(player);
	    	}
	    }
	
}


