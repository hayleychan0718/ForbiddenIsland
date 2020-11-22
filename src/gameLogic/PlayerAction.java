package gameLogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import island.board.Board;
import island.cards.Hand;
import players.Engineer;
import players.*;
import island.board.*;

public class PlayerAction {

	private Player player;
	private Board board;

	public PlayerAction(Player player) {
		this.player=player;
		this.board= Board.getInstance();

	}

	public void doStandardMovement(Scanner inputScanner) {	//do we need back capabilities
		int userInput; //Initialized as invalid 
		LinkedList<Tile> moveableTiles = player.getStandardMoveableTiles(); 	//Gets move able tiles and prints them out

		System.out.println("/n enter" + moveableTiles.size() + "[Return] to Cancel Movement"); //Will always be one greater than last tile option
		userInput=acceptableInput(0,moveableTiles.size()); //Ensures an acceptable input has been entered

		if(userInput==moveableTiles.size()) return; 	//cancels action

		System.out.println("Your pawn has been moved to " + moveableTiles.get(userInput).getName());
		player.movePlayerPawn(moveableTiles.get(userInput)); 
		player.decrementPlayerActions();
	}

	public void doForcedMovement(Scanner inputScanner) {
		int userInput;
		LinkedList<Tile> moveableTiles = player.getFocredMoveableTile();

		userInput=acceptableInput(0,moveableTiles.size()-1);	//Ensures valid Input -1 is to remove option to cancel movement as it is forced

		if(userInput==moveableTiles.size()) return; 	//cancels action

		System.out.println("Your pawn has been moved to " + moveableTiles.get(userInput).getName());
		player.movePlayerPawn(moveableTiles.get(userInput));
	}

	public void shoreUpTiles(Scanner inputScanner) {
		int userInput;
		LinkedList<Tile> shoreableTiles = player.getShoreableTiles();	//Gets shore able tiles

		System.out.println("/n enter" + shoreableTiles.size() + "[Return] to cancel action");
		userInput=acceptableInput(0, shoreableTiles.size());
		System.out.println("You have shored up:" + shoreableTiles.get(userInput));
		shoreableTiles.get(userInput).shoreUp(); //shores up the tile

		if(userInput==shoreableTiles.size()) return; 	//cancels action

		if(player instanceof Engineer) {	//Engineer may shore up two tiles
			System.out.println("/nYou are an Engineer so you may shore up another tile");
			shoreableTiles = player.getShoreableTiles();	//must update
			userInput=acceptableInput(0, shoreableTiles.size()-1);	//Cannot cancel since a tile has already been shore up
			System.out.println("You have shored up:" + shoreableTiles.get(userInput));
		}
		player.decrementPlayerActions();
	}

	public void giveCard(Scanner inputScanner) {
		int userInput;
		int userSecondInput;
		LinkedList<Player> possiblePlayers = player.giveTreasureCard();
		Hand hand=player.getHand();

		if(possiblePlayers==null) {
			System.out.println("There is no player you can give cards to");
			return;
		}
		System.out.println("/n enter" + possiblePlayers.size() + "[Return] to cancel action");
		userInput=acceptableInput(0, possiblePlayers.size());
		Player selectedPlayer = possiblePlayers.get(userInput);

		if(userInput==possiblePlayers.size()) return; 	//cancels action

		System.out.println("You must know select a card from your hand \n");
		hand.printHand();
		userSecondInput=acceptableInput(0, hand.getCards().size());
		hand.giveCard(hand.get(userSecondInput), selectedPlayer.getHand());	//adds the selected card to the other players hand
		player.decrementPlayerActions();
	}
	
	public void captureTreasure() {
		if(player.captureTreasure()==true){
			System.out.println("You have successfully captured:"+  player.getPlayerPawnTile().getTreasure().getString());
			player.decrementPlayerActions();
			return;
		}
		System.out.println("You were unable to capture the treasure");
	}

	public static int acceptableInput(int min,int max) { //Checks if input is acceptable by using the size of the actions lists //Maybe intrface
		int userInput=-1;
		Scanner inputScanner = new Scanner(System.in);
		boolean acceptableInput = false;

		while(acceptableInput == false) {

			if(inputScanner.hasNextInt() == true) {	//Checks if what is entered is an integer
				userInput = inputScanner.nextInt();

				if((userInput>=min && userInput <=max)) {	//Checks if it is within the acceptable range
					acceptableInput=true;	//exits method
				}
				else {
					System.out.println("Please enter a number of the range" + min+ "-" + max); //Will be invoked if an int is entered but not int the right range
					continue;
				}
			}
			else {
				System.out.println("Please enter a number of the range" + min + "-" + max); //Will be invoked if a character is entered
			}
		}
		return userInput;
	}


}
