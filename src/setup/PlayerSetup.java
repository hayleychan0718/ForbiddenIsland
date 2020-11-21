package setup;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import gameLogic.PlayerAction;
import players.*;
import players.PlayerList;

public class PlayerSetup {

	private PlayerList playerList;
	private List<Integer> RandomIntList;

	public PlayerSetup() {
		playerList = PlayerList.getInstance();
		IntStream theIntStream = IntStream.rangeClosed(0, 5);
		List<Integer> RandomIntList =  theIntStream.boxed().collect(Collectors.toList()); //Create a list 0-5 using the intStream used for random player role
	}


	public int setNumofPlayers(Scanner scannerIn) {
		int userInput;
		Integer numOfPlayers = 0;
		System.out.println("Enter the number of players playing, this must be be of the range 2 to 4:");

		userInput=PlayerAction.acceptableInput(2, 4);
		numOfPlayers=userInput;
	
		return numOfPlayers;
	}

	public void createPlayer(int playerNumber, Scanner scannerIn) {

		System.out.println("\n You are Player number:"+ playerNumber + "\n");
		System.out.println("Please Enter Your Name:");
		String playerName = scannerIn.nextLine();

		Collections.shuffle(RandomIntList);	//Shuffle to randomize selection

		switch(RandomIntList.get(0)){
		case 0:
			Diver diver = Diver.getInstance(playerName, playerNumber);
			playerList.addPlayer(diver);
			break;

		case 1:
			Engineer engineer = Engineer.getInstance(playerName, playerNumber);
			playerList.addPlayer(engineer);
			break;

		case 3:
			Explorer explorer = Explorer.getInstance(playerName, playerNumber);
			playerList.addPlayer(explorer);
			break;

		case 4:
			Messenger messenger = Messenger.getInstance(playerName, playerNumber);
			playerList.addPlayer(messenger);
			break;

		case 5:
			Navigator navigator = Navigator.getInstance(playerName, playerNumber);
			playerList.addPlayer(navigator);
			break;

		default:
			break;
		}

		RandomIntList.remove(0); //Makes that another player cannot be assigned that role by removing it from the lsit

	}

	public void createPlayers(Scanner scannerIn) {
		int numOfPlayers=0;
		boolean areAllPlayersSetup = false;

		while(!areAllPlayersSetup) {
				numOfPlayers= setNumofPlayers(scannerIn);
				for(int i = 0; i< numOfPlayers; i++) {
					createPlayer(i, scannerIn);
				}
				areAllPlayersSetup=true;	
		}
	}
}



