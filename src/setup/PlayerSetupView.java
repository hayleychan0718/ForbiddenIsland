package setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import players.Diver;
import players.Engineer;
import players.Explorer;
import players.Messenger;
import players.Navigator;
import players.Player;
import players.PlayerList;
import utility.Utility;

public class PlayerSetupView {
	
	private PlayerSetupController controller;
	private static PlayerSetupView playerSetupView = null;

	public static PlayerSetupView getInstance() {
		if(playerSetupView == null)
			playerSetupView = new PlayerSetupView();
		return playerSetupView;
	}
	
	public int setNumofPlayers(Scanner inputScanner) {
		int userInput;
		Integer numOfPlayers = 0;
		System.out.println("Enter the number of players playing, this must be be of the range 2 to 4:");

		userInput=Utility.acceptableInput(2, 4, inputScanner);	
		inputScanner.nextLine();
		numOfPlayers=userInput;
	 
		return numOfPlayers;
	}

	public void createPlayer(int playerNumber, Scanner inputScanner) { //Change using strings
		ArrayList<String> listofRoles = controller.getRoleList();

		System.out.println("You are Player number:"+ playerNumber + "\n");
		System.out.println("Please Enter Your Name:");
		String playerName = inputScanner.nextLine();
		System.out.println("Please Enter the Symbol (For example $, makes it easier to identify) you want your pawn to be represented by on the board ");
		String symbol = inputScanner.nextLine();

		Collections.shuffle(listofRoles);
		
		switch(listofRoles.get(0)){
		case "Diver":
			Diver diver = Diver.getInstance(playerName, playerNumber, symbol);
			controller.addPlayer(diver);
			break;

		case "Engineer":
			Engineer engineer = Engineer.getInstance(playerName, playerNumber, symbol);
			controller.addPlayer(engineer);
			break;

		case "Explorer":
			Explorer explorer = Explorer.getInstance(playerName, playerNumber, symbol);
			controller.addPlayer(explorer);
			break;

		case "Messenger":
			Messenger messenger = Messenger.getInstance(playerName, playerNumber, symbol);
			controller.addPlayer(messenger);
			break;

		case "Navigator":
			Navigator navigator = Navigator.getInstance(playerName, playerNumber, symbol);
			controller.addPlayer(navigator);
			break;

		default:
			break;
		}
		System.out.println("Your Role is: " + listofRoles.get(0) + "\n");
		controller.removeRole(0); //Makes that another player cannot be assigned that role by removing it from the list
	}

	public void createPlayers(Scanner inputScanner) {
		int numOfPlayers=0;
		
		numOfPlayers= setNumofPlayers(inputScanner);
			for(int i = 1; i< numOfPlayers+1; i++) {	//Start from player 1
				createPlayer(i, inputScanner);
				}
			printListofPlayers();
		}
	
	public void setController(PlayerSetupController controller) {
		this.controller=controller;
	}
	
	  public void printListofPlayers() {
		  ArrayList<Player> playerList = controller.getListofPlayers();
	    	for(Player player: playerList) {
	        	System.out.println(player);
	    	}
	    }
	
}


