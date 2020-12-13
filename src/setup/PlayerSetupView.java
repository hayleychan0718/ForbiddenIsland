package setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import players.Diver;
import players.Engineer;
import players.Explorer;
import players.Messenger;
import players.Navigator;
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
	
	public int setNumofPlayers() {
		int userInput;
		Integer numOfPlayers = 0;
		System.out.println("Enter the number of players playing, this must be be of the range 2 to 4:");

		userInput=Utility.acceptableInput(2, 4);		
		numOfPlayers=userInput;
	 
		return numOfPlayers;
	}

	public void createPlayer(int playerNumber, Scanner inputScanner) { //Change using strings
		ArrayList<String> listofRoles = controller.getRoleList();
		
		System.out.println("\nYou are Player number:"+ playerNumber + "\n");
		System.out.println("Please Enter Your Name:");
		String playerName = inputScanner.nextLine();

		controller.shuffleRoles();

		switch(listofRoles.get(0)){
		case "Diver":
			Diver diver = Diver.getInstance(playerName, playerNumber);
			controller.addPlayer(diver);
			break;

		case "Engineer":
			Engineer engineer = Engineer.getInstance(playerName, playerNumber);
			controller.addPlayer(engineer);
			break;

		case "Explorer":
			Explorer explorer = Explorer.getInstance(playerName, playerNumber);
			controller.addPlayer(explorer);
			break;

		case "Messenger":
			Messenger messenger = Messenger.getInstance(playerName, playerNumber);
			controller.addPlayer(messenger);
			break;

		case "Navigator":
			Navigator navigator = Navigator.getInstance(playerName, playerNumber);
			controller.addPlayer(navigator);
			break;

		default:
			break;
		}
		System.out.println("Your Role is: " + listofRoles.get(0));
		controller.removeRole(0); //Makes that another player cannot be assigned that role by removing it from the list
	}

	public void createPlayers() {
		int numOfPlayers=0;
		
		numOfPlayers= setNumofPlayers();
		Scanner inputScanner = new Scanner(System.in);
			for(int i = 1; i< numOfPlayers+1; i++) {	//Start from player 1
				createPlayer(i, inputScanner);
				}
		}
	
	public static void main(String[] args) {
		PlayerSetup playerSetup = Play;
		playerSetup.createPlayers();
		PlayerList.getInstance().printListOfPlayers();
		
	}
}

}
