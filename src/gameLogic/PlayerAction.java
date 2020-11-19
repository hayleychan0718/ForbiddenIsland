package gameLogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import island.board.Board;
import players.Player;
import island.board.*;

public class PlayerAction {

	private Player player;
	private Board board;

	public PlayerAction(Player player) {
		this.player=player;
		this.board= Board.getInstance();

	}

	public void doStandardMovement(Scanner inputScanner) {	//do we need back capabilities
		Integer userInput =-1; //Initialized as invalid 

		LinkedList<Tile> moveableTiles = player.getStandardMoveableTiles(); 	//Gets move able tiles and prints them out
		System.out.println("/n enter" + moveableTiles.size() + "[Return] to Cancel Movement"); //Will always be one greater than last tile option

		acceptableInput(moveableTiles.size(), userInput); //Ensures an acceptable input has been entered

		if(userInput==moveableTiles.size()) {	//Users has selected to cancel movement
			System.out.println("You have slected to cancel movement");
			return;
		}
		else {
			System.out.println("Your pawn has been moved to " + moveableTiles.get(userInput).getName());
			player.movePlayerPawn(moveableTiles.get(userInput)); 
			player.decrementPlayerActions();
		}
	}


	public void doForcedMovement(Scanner inputScanner) {
		Integer userInput = -1;

		System.out.println("You must move your pawn as the tile it is on is now an ocean tile.");
		LinkedList<Tile> moveableTiles = player.getFocredMoveableTile();

		userInput = inputScanner.nextInt();
		acceptableInput(moveableTiles.size()-1, userInput);	//Ensures valid Input -1 is to remove option to cancel movement as it is forced

		System.out.println("Your pawn has been moved to " + moveableTiles.get(userInput).getName());
		player.movePlayerPawn(moveableTiles.get(userInput));

	}
	
	
	
	








}

public int acceptableInput(int sizeOfList, int userInput) { //Checks if input is acceptable by using the size of the actions lists //Maybe intrface
	Scanner inputScanner = new Scanner(System.in);
	boolean acceptableInput = false;

	while(acceptableInput == false) {

		if(inputScanner.hasNextInt() == true) {
			userInput = inputScanner.nextInt();
			if((userInput>=0 && userInput <=sizeOfList)) {
				acceptableInput=true;	//exits loop as an acceptable Integer has been entered
			}
			else {
				System.out.println("Please enter a number of the range" + "0-" + sizeOfList); //Will be invoked if an int is entered but not int the right range
				continue;
			}
		}
		else {
			System.out.println("Please enter a number of the range" + "0-" + sizeOfList); //Will be invoked if a character is entered
		}
	}
	return userInput;
}
}

