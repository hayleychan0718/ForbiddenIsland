package gameLogic;

import java.util.Scanner;

import island.board.Board;
import players.Player;

public class PlayerTurn {

	private Player player;
	private Scanner inputScanner;
	private Board board;


	public PlayerTurn(Player player, Scanner inputScanner ) {
		this.player=player;
		this.inputScanner=inputScanner;
		this.board= Board.getInstance();
	}

	//Printout the available options for a players turn

	private void printOptions() {
		System.out.println("You have the following availabe options");
		System.out.println("[1] Move Pawn");
		System.out.println("[2] Shore Up Tile");
		System.out.println("[3] Give Treasure Card");
		System.out.println("[4] Capture Treasure");
		System.out.println("[5] Show Board");
		System.out.println("[0] End Turn");
	}

	public void doTurn() {

		boolean isInputValid;
		boolean isTurnOver=false;
		int userInput;

		System.out.println("It is now " + player.getName() + "'s turn."); // Maybe have them press enter to begin

		//Possibly check Ocean tile

		while (!isTurnOver) {

			printOptions();
			isInputValid=false;
			userInput = 0;

			while(!isInputValid) {
				userInput = inputScanner.nextInt();	//What happens if string is entered

				if((userInput >=0) && (userInput <=5)) {
					isInputValid =true;
				}
			}

			switch(userInput) {
			case 0:
				isTurnOver = true;
				break;
			case 1:
				tryMove();
				break; 
			case 2:
				tryShoreUp();
			case 3:
				tryGiveTreasreCard();
				break;
			case 4:
				tryCaptureTreasure();
				break;
			case 5:
				board.printBoard();
				break;
			default:
			}
		} 



	}
	public void tryMove() {
		PlayerAction movement = new PlayerAction(player);
		movement.doStandardMovement(inputScanner);
	}
	
	


}
