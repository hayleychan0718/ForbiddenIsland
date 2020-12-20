package players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import gameLogic.CardController;
import gameLogic.CardView;
import gameLogic.WaterMeter;
import island.board.Board;
import island.board.Tile;
import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureCard;
import island.cards.TreasureDeckCard;
import island.enums.TileNames;
import island.enums.TreasureNames;
import observers.GameOverObserver;
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
		System.out.println("[6] Play Cards");
		System.out.println("[7] Show Hand");
		System.out.println("[0] End Turn");
	}

	public void selectOption(Scanner inputScanner, Player player) {

		int userInput = Utility.acceptableInput(0, 7, inputScanner);

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
		case 6:
			runCardView(inputScanner, player.getHand(), player);
			break;
		case 7:
			printHand(player.getHand());
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

	public boolean notifyPlayer(Scanner inputScanner, Tile tile, Player player) {
		System.out.println(tile + " is about to sink! Do you want to save it?");
		System.out.println("[0] No\n[1] Yes");
		int userInput = Utility.acceptableInput(0, 1, inputScanner);
		if(userInput==1) {
			controller.decementPlayerAction(player);
			controller.saveTile(tile);
			System.out.println(tile + " is saved!");
			return true;
		}
		else
			System.out.println(tile + " is sunk!");
			return false;
	}
	
	
	public void runCardView(Scanner inputScanner, Hand playerHand, Player player) {
		ArrayList<TreasureDeckCard> playableCards = playerHand.getPlayableCards();
		if(cardOptions(playerHand)) {
			int userInput = Utility.acceptableInput(0, playableCards.size(), inputScanner);
			if(userInput==playableCards.size()) return;  //If they want to cancel Movement

			switch(playableCards.get(userInput).getName()) {
			case "Helicopter Lift":
				doHelicopter(inputScanner, playerHand, userInput, player);
				break;
			case "Sandbag":
				doSandbag(inputScanner, playerHand, userInput, player);
				break;
			}
		}
	}
	
	// Used in runCardView
	private boolean cardOptions(Hand playerHand) {
		ArrayList<Card> cards = playerHand.getCards();
		System.out.println("Playable cards:\n");
		int i=0, none=0;
		for(Card card: cards) {
			if(card instanceof TreasureDeckCard) {
				System.out.println(card.getName() + " [" + i + "]");
				i++;
			}
			else if(card instanceof TreasureCard) {
				none++;
			}
		}
		if(none == cards.size()) {
			System.out.println("No playable cards available...");
			return false;
		}
		else {
			System.out.println("Enter " + i + " to cancel action");
			return true;
		}
	}
	
	public void doHelicopter(Scanner inputScanner, Hand playerHand, int cardIndex, Player player) {
		ArrayList<Player> playersForHelicopter = PlayerList.getInstance().getPlayersForHelicopter();
		ArrayList<Tile> listOfTiles = Board.getInstance().listOfTiles();

		if(!helicopterPrompt(inputScanner)) {
			int tileIndex = Utility.acceptableInput(0, listOfTiles.size(), inputScanner);
			Tile chosenTile = listOfTiles.get(tileIndex);
			ArrayList<Player> chosenPlayers = choosingPlayer(inputScanner, chosenTile, playersForHelicopter);
				
			movingMessage(chosenTile, chosenPlayers);
			controller.doHelicopter(chosenTile, chosenPlayers, player);
			controller.removeCard(cardIndex);
		}
		else {
			winHelicopter(inputScanner, playerHand, player);
		}
	}
	
	private void winHelicopter(Scanner inputScanner, Hand playerHand, Player player) {
		if(controller.canWinHelicopter(player))
			controller.winHelicopter(player);
		else{
			System.out.println("Can't lift off yet!\n");
			runCardView(inputScanner, playerHand, player);
		}
	}
	
	// Used in doHelicopter()
	private boolean helicopterPrompt(Scanner inputScanner) {
		System.out.println("\nPlay Helicopter Lift card...");
		System.out.println("Do you want to\nMove one or more pawns to any other tile? [0] or\nLift your team off Fool's Landing for the win? [1]");
		int helicopterChosen = Utility.acceptableInput(0, 1, inputScanner);

		if(helicopterChosen == 0) {
			System.out.println("\nTiles you can move to:");
			Utility.printOptions(Board.getInstance().listOfTiles());
			System.out.println("Which tile do you want to move to? ");
			return false;
		}
		else {
			System.out.println("Lifting off Fool's Landing...");
			return true;
		}
	}
	
	// Used in doHelicopter()
	private ArrayList<Player> choosingPlayer(Scanner inputScanner, Tile chosenTile, ArrayList<Player> playersForHelicopter) {	
		ArrayList<Player> chosenPlayers = new ArrayList<Player>();
		boolean repeat = true;
		
		while(repeat==true) {
			System.out.println("Who do you want to move to " + chosenTile.getNameString() + "?");
			Utility.printOptions(playersForHelicopter);
			int playerIndex = Utility.acceptableInput(0, playersForHelicopter.size(), inputScanner);
			chosenPlayers.add(playersForHelicopter.get(playerIndex));	
			playersForHelicopter.remove(playerIndex);
			if(!playersForHelicopter.isEmpty()) {
				System.out.println("Do you want to choose another player?\nYes [0]\nNo [1]");
				int choice = Utility.acceptableInput(0, 1, inputScanner);
				if(choice == 0) 
			    	repeat = true;
			    else if(choice == 1)
			    	repeat = false;
			}
			else
				repeat = false;
		}	
		return chosenPlayers;
	}
	
	// Used in doHelicopter()
	private void movingMessage(Tile chosenTile, ArrayList<Player> chosenPlayers) {
		for(Player player: chosenPlayers) {
			System.out.print("Moving " + player.getName() + " to " + chosenTile.getNameString() +  "...\n");
		}
	}
	
	public void doSandbag(Scanner inputScanner, Hand playerHand, int cardIndex, Player player) {
		ArrayList<Tile> listOfFloodedTiles = Board.getInstance().listOfFloodedTiles();
		
		if(listOfFloodedTiles.size()==0) {
			System.out.println("Choose another card.");
			runCardView(inputScanner, playerHand, player);
		}
		else
			System.out.println("Play Sandbag Card...\n");
			System.out.println("Tiles you can shore up:");
			Utility.printOptions(listOfFloodedTiles);
			System.out.println("\nChoose the tile: ");
			int tileIndex = Utility.acceptableInput(0, listOfFloodedTiles.size(), inputScanner);
			Tile chosenTile = listOfFloodedTiles.get(tileIndex);

			controller.doSandbag(chosenTile, player);
			controller.removeCard(cardIndex);
			System.out.println("Shored up " + chosenTile.getNameString());
	}

	public void doWaterRise(Player player) {
		WaterMeter waterMeter = WaterMeter.getinstance();
		System.out.println("Play Water Rise card...");
		controller.doWaterRise(player);
		System.out.println("Water Level increased.\nCurrent water level: " + waterMeter.getWaterLevel());
		System.out.println("Flood Deck reshuffled.");
	}
	
	public void printHand(Hand playerHand) {
		int index = 0;
		ArrayList<Card> hand = playerHand.getCards();
		if(hand.size()==0) {
			System.out.println("Your hand is empty.");
		}
		else {
			System.out.println("\nYour hand: ");
			for(int i=0; i<hand.size(); i++) {
				System.out.println(index + ". " + hand.get(i).getName());
				index++;
			}
		}
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
