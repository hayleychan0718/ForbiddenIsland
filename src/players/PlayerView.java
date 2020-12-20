/**
 * View to implement the user interface for Player class
 * @author Liam Fitzgerald
 */
package players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import gameLogic.WaterMeter;
import island.board.Board;
import island.board.Tile;
import island.cards.*;
import utility.Utility;


public class PlayerView {

	private PlayerController controller;
	private static PlayerView playerView = null;

	
	public static PlayerView getInstanace() {
		if(playerView == null)
			playerView = new PlayerView();
		return playerView;
	}
	
	public PlayerController getController() {
		return controller;
	}

	/**
	 * Sets the controller for the view
	 * @param controller
	 */
	public void setController(PlayerController controller) {
		this.controller = controller;

	}

	/**
	 * Prints available playable options to user
	 */
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

	/**
	 * Enables a player to select a playable option with inputScanner
	 * @param inputScanner
	 * @param player
	 */
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
			runCardView(inputScanner, player);  
			break;
		case 7:
			printHand(player); 
			break;
		}
	}
	
	/**
	 * User interface for a player to do the shore up action
	 * @param inputScanner
	 * @param player
	 */
	public void shoreUp(Scanner inputScanner, Player player) {
		ArrayList<Tile> shoreableTiles = controller.getShoreableTiles(player);

		if(!canDoTileAction(shoreableTiles, "shore up")) return;

		int userInput=Utility.acceptableInput(0, shoreableTiles.size(), inputScanner); 
		
		if(userInput==shoreableTiles.size()) return; //Cancels action
		
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


	/**
	 * Checks whether the user can do a voluntary tile action either shore up or standard movement
	 * @param tileOptions
	 * @param typeOfTileAction Either "shore up" or "move to "
	 * @return
	 */
	public boolean canDoTileAction(ArrayList<Tile> tileOptions, String typeOfTileAction) {

		if(tileOptions.isEmpty()) {
			System.out.println("There are no Tiles to " + typeOfTileAction);
			return false;
		}
		else {
			System.out.println("You can " +  typeOfTileAction + " the following Tiles");
			Utility.printOptions(tileOptions);
			System.out.println("Enter " + tileOptions.size() + " to cancel action");
			return true;
		}

	}
	
	/**
	 * User interface for a player to do the standard movement action
	 * @param inputScanner
	 * @param player
	 */
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

	/**
	 * User Interface for when a player must do forced movement
	 * @param inputScanner
	 * @param player
	 */
	public void doForcedMovement(Scanner inputScanner, Player player) {
		ArrayList<Tile> moveableTiles = controller.getForcedMovementTiles(player);
		System.out.println("\n" + player + " must move their tile has been sunk!");

		Utility.printOptions(moveableTiles);
		int userInput = Utility.acceptableInput(0, moveableTiles.size()-1, inputScanner); //No option to cancel movement

		Tile selectedTile = moveableTiles.get(userInput);
		controller.movePlayerPawn(player, selectedTile);
		System.out.println("Your pawn has been moved to " + selectedTile);
	}

	/**
	 * User interface for when a player try to capture treasure
	 * @param player
	 */
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

	/**
	 * Returns selected player for treasure card for inputed player
	 * @param player
	 * @param inputScanner
	 * @return selected player or null
	 */
	public Player selectPlayerForCard(Player player, Scanner inputScanner) {
		ArrayList<Player> playersForCard =controller.getPlayerForCard(player);

		if (playersForCard.isEmpty()) {
			System.out.println("There are no players to give treasure cards to");
			return null;
		}
		System.out.println("You can give a card to the following players:");
		Utility.printOptions(playersForCard);
		int userInput = Utility.acceptableInput(0, playersForCard.size(), inputScanner); //No option to cancel movement
		
		Player selectedPlayer = playersForCard.get(userInput);
		return selectedPlayer;
	}

	/**
	 * Takes player are input and this player can give a card to another player, give treasure card action
	 * @param player
	 * @param inputScanner
	 */
	public void giveTreasureCard(Player player , Scanner inputScanner) {
		
		Player selectedPlayer = selectPlayerForCard(player,inputScanner);
		if(selectedPlayer==null) return;
		
		ArrayList<Card> currentPlayersCards = controller.getPlayerCards(player);
		System.out.println("Test straight from player" + player.getHand().getCards());
		System.out.println("Test1:" + currentPlayersCards);
		
		Hand selectedPlayerHand = controller.getPlayerHand(selectedPlayer);

		if(!canDoCardAction(currentPlayersCards)) return;

		int userInput = Utility.acceptableInput(0, currentPlayersCards.size(), inputScanner);
		
		if(userInput == currentPlayersCards.size()) return;
		
		Card selectedCard = currentPlayersCards.get(userInput);
		controller.giveCard(selectedCard, selectedPlayerHand, player);
		System.out.println("You have given" + selectedCard + "to" + selectedPlayer);
		controller.decementPlayerAction(player);
	}

	/**
	 * Checks whether a player has a card to give and prints cards
	 * @param cardOptions
	 * @return
	 */
	public boolean canDoCardAction(ArrayList<Card> cardOptions) {

		if(cardOptions.isEmpty()) {
			System.out.println("You have no cards to give");
			return false;
		}
		else {
			System.out.println("You can give the following cards:");
			System.out.println("Enter " + cardOptions.size() + " to cancel action");
			return true;
		}

	}
		
	public void endTurn(Player player) {
		controller.emptyActions(player);
	}

	public boolean notifyPlayer(Scanner inputScanner, Tile tile, Player player) {
		System.out.println(tile + " is about to sink! Does " + player + " want to save it?");
		System.out.println("[0] No\n[1] Yes");
		int userInput = Utility.acceptableInput(0, 1, inputScanner); //0-2 option 
		if(userInput==1) {
			controller.decementPlayerAction(player);
			System.out.println(tile + " is saved!");
			return true;
		}
		else {
			System.out.println(tile + " is sunk!");
			tile.setNotPresent();
			return false;
		}
	}
	
	
	public void runCardView(Scanner inputScanner, Player player) {
		Hand playerHand = controller.getPlayerHand(player);
		ArrayList<TreasureDeckCard> playableCards = playerHand.getPlayableCards();
		if(cardOptions(player)) {
			int userInput = Utility.acceptableInput(0, playableCards.size(), inputScanner); 
			if(userInput==playableCards.size()) return; 
			
			switch(playableCards.get(userInput).getName()) { 
			case "Helicopter Lift":
				doHelicopter(inputScanner, playerHand, playableCards.get(userInput), player); 
				break;
			case "Sandbag":
				doSandbag(inputScanner, playerHand, playableCards.get(userInput), player);
				break;
			}
		}
	}
	
	// Used in runCardView
	private boolean cardOptions(Player player) {
		Hand playerHand = controller.getPlayerHand(player);
		ArrayList<TreasureDeckCard> playableCards = playerHand.getPlayableCards();
		System.out.println("Playable cards:");
		if(!playableCards.isEmpty()) {
			Utility.printOptions(playableCards);
			System.out.println("Enter " + playableCards.size() + " to cancel action");
			return true;
		}
		else{
			System.out.println("No playable cards available...");
			return false;
		}
	}
	
	public void doHelicopter(Scanner inputScanner, Hand playerHand, Card card, Player player) {
		ArrayList<Player> playersForHelicopter = PlayerList.getInstance().getPlayersForHelicopter(); //NO need for this method
		ArrayList<Tile> listOfTiles = Board.getInstance().listOfTiles();

		if(!helicopterPrompt(inputScanner)) {
			int tileIndex = Utility.acceptableInput(0, listOfTiles.size(), inputScanner); //-1 if no 
			Tile chosenTile = listOfTiles.get(tileIndex);
			ArrayList<Player> chosenPlayers = choosingPlayer(inputScanner, chosenTile, playersForHelicopter);
				
			movingMessage(chosenTile, chosenPlayers);
			controller.doHelicopter(chosenTile, chosenPlayers, player);
			controller.removeCard(card, player);
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
			runCardView(inputScanner, player);
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
				int choice = Utility.acceptableInput(0, 1, inputScanner); //Cancel option
				if(choice == 0) 
			    	repeat = true;     // no need for this
			    else if(choice == 1) //CLean this up only need one condition in loop
			    	repeat = false;     
			}
			else
				repeat = false;  //What is this for//
		}	
		return chosenPlayers;
	}
	
	// Used in doHelicopter()
	private void movingMessage(Tile chosenTile, ArrayList<Player> chosenPlayers) {
		for(Player player: chosenPlayers) {
			System.out.print("Moving " + player + " to " + chosenTile +  "...\n"); //no need for this get name string
		}
	}
	
	public void doSandbag(Scanner inputScanner, Hand playerHand, Card card, Player player) {
		ArrayList<Tile> listOfFloodedTiles = Board.getInstance().listOfFloodedTiles(); //Cant do this
		
		if(listOfFloodedTiles.size()==0) {
			System.out.println("Choose another card.");
			runCardView(inputScanner, player);
		}
		else
			System.out.println("Play Sandbag Card...\n");
			System.out.println("Tiles you can shore up:");
			Utility.printOptions(listOfFloodedTiles);
			System.out.println("\nChoose the tile: ");
			int tileIndex = Utility.acceptableInput(0, listOfFloodedTiles.size()-1, inputScanner);
			Tile chosenTile = listOfFloodedTiles.get(tileIndex);

			controller.doSandbag(chosenTile, player);
			controller.removeCard(card, player);
			System.out.println("Shored up " + chosenTile); //No need for get string
	}

	public void doWaterRise(Player player) {
		WaterMeter waterMeter = WaterMeter.getinstance();
		System.out.println("Play Water Rise card...");
		controller.doWaterRise(player);
		System.out.println("Water Level increased.\nCurrent water level: " + waterMeter.getWaterLevel());
		System.out.println("Flood Deck reshuffled.");
	}
	
	public void printHand(Player player) {
		Hand playerHand = controller.getPlayerHand(player);
		ArrayList<Card> hand = playerHand.getCards();
		if(hand.size()==0) {
			System.out.println("Your hand is empty.");
		}
		else {
			System.out.println("\nYour hand: ");
			Utility.printOptions(hand);
		}
	}
	public void showBoard() {
		String board = controller.showBoard();

			System.out.println(board);
			boardExplanation();
		}
	
	public void boardExplanation() {
		System.out.println("The initial of the Tiles are shown (Not including of or the)");
		System.out.println("A * beside the inital means there is a treasure at this tile");
		System.out.println("A ! beside the inital means the Tile is flooded");
		System.out.println("A blank [  ] means the tile is no longer present ");
		System.out.println("Fools landing is in captials [ FL ] ");
		System.out.println("Your choosen symbol will replace the inital of the Tile ");
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
