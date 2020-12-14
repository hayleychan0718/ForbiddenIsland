package gameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import island.board.Tile;
import island.cards.Hand;
import island.cards.TreasureDeckCard;
import island.enums.TreasureNames;
import players.Engineer;
import players.Player;
import utility.Utility;

//Class to implement the player view
public class PlayerView {

	private PlayerController controller;
	private static PlayerView playerView = null;

	public static PlayerView getInstanace() {
		if(playerView == null)
			playerView = new PlayerView();
		return playerView;
	}

	//No Constructor

	public PlayerController getController() {
		return controller;
	}

	public void setController(PlayerController controller) {
		this.controller = controller;

	}

	public void printOptions() {
		System.out.println("\nYou have the following available options");
		System.out.println("[1] Move Pawn");
		System.out.println("[2] Shore Up Tile");
		System.out.println("[3] Give Treasure Card");
		System.out.println("[4] Capture Treasure");
		System.out.println("[5] Show Board");
		System.out.println("[0] End Turn");
	}

	public void selectOption(Scanner inputScanner, Player player) {

		int userInput = Utility.acceptableInput(0, 6, inputScanner);

		switch(userInput) {
		case 0:
			endTurn(player); //Concurrent modification
			break;
		case 1:
			doStandardMovement(inputScanner, player);
			break;
		case 2:
			shoreUp(inputScanner, player);
			break;
		case 3:
			giveTreasureCard(player, inputScanner);
			break;
		case 4:
			CaptureTreasure(player);
			break;
		case 5:
			showBoard();
			break;
		}
	}

	public void shoreUp(Scanner inputScanner, Player player) {
		ArrayList<Tile> shoreableTiles = controller.getShoreableTiles(player);

		if(!canDoTileAction(shoreableTiles, "shore up")) return;

		int userInput=Utility.acceptableInput(0, shoreableTiles.size(), inputScanner); 
		Tile SelectedTile=shoreableTiles.get(userInput);
		controller.shoreUpTile(SelectedTile);
		shoreableTiles.remove(userInput);

		if(player instanceof Engineer & !shoreableTiles.isEmpty()) {
			System.out.println("/nYou are an Engineer so you may shore up another tile");
			Utility.printOptions(shoreableTiles);
			userInput=Utility.acceptableInput(0, shoreableTiles.size()-1, inputScanner); 
			SelectedTile=shoreableTiles.get(userInput);
			controller.shoreUpTile(SelectedTile);
		}
		controller.decementPlayerAction(player);
	}

	public boolean canDoTileAction(ArrayList<Tile> tileOptions, String typeOfTileAction) {

		if(tileOptions.isEmpty()) {
			System.out.println("There are no Tiles to " + typeOfTileAction);
			return false;
		}
		else {
			System.out.println("You can " +  typeOfTileAction + "the following Tiles");
			Utility.printOptions(tileOptions);
			System.out.println("Enter " + tileOptions.size() + " to cancel action");
			return true;
		}

	}
	
	public void doStandardMovement(Scanner inputScanner, Player player){  
		ArrayList<Tile> moveableTiles = controller.getStandardMoveTiles(player); //Sand Bag will get tiles you can shore up

		if(!canDoTileAction(moveableTiles, "move to ")) return; //Make method similar to mine to check if it is empty, if empty return

		int userInput = Utility.acceptableInput(0, moveableTiles.size(), inputScanner); // Get user input for the options
		if(userInput==moveableTiles.size()) return;  //If they want to cancel Movement

		Tile selectedTile = moveableTiles.get(userInput); //What the user selected, so the tile choose to shore up.
		controller.movePlayerPawn(player, selectedTile);  //Controller then shore up selected tile
		System.out.println("Your pawn has been moved to " + selectedTile); //The tile shored up
		controller.decementPlayerAction(player); //Use controller to discard card.
	}

	public void doForcedMovement(Scanner inputScanner, Player player) {
		ArrayList<Tile> moveableTiles = controller.getForcedMovementTiles(player);
		System.out.println("\n" + player + " must move their tile has been sunk!");

		Utility.printOptions(moveableTiles);
		int userInput = Utility.acceptableInput(0, moveableTiles.size()-1, inputScanner); //No option to cancel movement

		Tile selectedTile = moveableTiles.get(userInput);
		controller.movePlayerPawn(player, selectedTile);
		System.out.println("Your pawn has been moved to " + selectedTile);
	}

	public void CaptureTreasure(Player player) {

		if(controller.canCaptureTreasure(player)) {
			System.out.println("You have captured the treasure:" + controller.getTileTreasure(player));
			controller.decementPlayerAction(player);
			return;
		}
		if(controller.getTileTreasure(player)==null) {
			System.out.println("Your pawn is not on a treasure tile");   //WHat if treasur eis already captured
			return;
		}
		System.out.println("You dont have the required card's to capture the treasure");
		return;
	}

	public Player selectPlayerForTreasureCard(Player player, Scanner inputScanner) {
		ArrayList<Player> playersForTreasureCard =controller.getPlayerForTreasureCard(player);

		if (playersForTreasureCard.isEmpty()) {
			System.out.println("There are no players to give treasure cards to");
			return null;
		}
		Utility.printOptions(playersForTreasureCard);
		int userInput = Utility.acceptableInput(0, playersForTreasureCard.size(), inputScanner); //No option to cancel movement
		Player selectedPlayer = playersForTreasureCard.get(userInput);
		return selectedPlayer;
	}

	//Needs to be altered slightly should check i
	public void giveTreasureCard(Player player , Scanner inputScanner) {
		
		Player selectedPlayer = selectPlayerForTreasureCard(player,inputScanner);
		if(selectedPlayer==null) return;
		
		ArrayList<TreasureDeckCard> currentPlayersCards = player.getHand().getCards();

		
		Hand selectedPlayerHand = controller.getPlayerHand(selectedPlayer);

		if(!canDoCardAction(currentPlayersCards)) return;

		int userInput = Utility.acceptableInput(0, currentPlayersCards.size(), inputScanner);
		TreasureDeckCard selectedCard = currentPlayersCards.get(userInput);
		controller.giveCard(selectedCard, selectedPlayerHand, player);
		controller.decementPlayerAction(player);
	}

	public boolean canDoCardAction(ArrayList<TreasureDeckCard> cardOptions) {

		if(cardOptions.isEmpty()) {
			System.out.println("You have no cards to give");
			return false;
		}
		else {
			System.out.println("You can give the following cards:");
			Utility.printOptions(cardOptions);
			System.out.println("Enter" + cardOptions.size() + " to cancel action");
			return true;
		}

	}
	
	public void showBoard() {
		Tile[][] board = controller.getboard();
		int[][] islandTiles = controller.getIslandTiles();

		for(int[] x: islandTiles) {
			System.out.println(board[x[0]][x[1]].getName());
			System.out.println("Coord: " + Arrays.toString(board[x[0]][x[1]].getLocation()));
			System.out.println("Flooded: " + board[x[0]][x[1]].isFlooded());
			System.out.println("Present: " + board[x[0]][x[1]].isPresent());
			System.out.println();
		}
	}
		
	public void endTurn(Player player) {
		controller.emptyActions(player);
	}

//	public static void main(String[] args) {
//		Player Liam = new Player("Liam",1); 
//		ArrayList<Player> optionList = new ArrayList<Player>();
//		optionList.add(Liam);
//
//		PlayerView view = PlayerView.getInstanace();
//		Utility.printOptions(optionList);
//	}

	
	



		/*for(int i=0; i<6; i++) {
	        	System.out.println();
	        	for(int j=0; j<6; j++) {
	        		if(board[i][j] == null) 
	        			System.out.printf("%25s", "blank");
	        		else 
	        			System.out.printf("%25s", board[i][j].getName().getString());
	        	}
	        } */   	
	}
