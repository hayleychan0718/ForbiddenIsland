package players;

import java.util.ArrayList;
import java.util.Scanner;
import island.board.Tile;
import island.cards.*;
import utility.Utility;

/**
 * View to implement the user interface for Player class
 * @author Liam Fitzgerald and Hayley Chan
 */

public class PlayerView {

	private PlayerController controller;
	private static PlayerView playerView = null;

	/**
	 * gets instance of the PlayerView
	 * @return playerview
	 */
	public static PlayerView getInstanace() {
		if(playerView == null)
			playerView = new PlayerView();
		return playerView;
	}
	
	/**
	 * Gets the PLayerController
	 * @return controller
	 */
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
		System.out.println("[8] Quit Game");
		System.out.println("[0] End Turn");
	}

	/**
	 * Enables a player to select a playable option with inputScanner
	 * @param inputScanner
	 * @param player
	 */
	public void selectOption(Scanner inputScanner, Player player) {

		int userInput = Utility.acceptableInput(0, 8, inputScanner);

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
		case 8:
			System.exit(0);
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

		if(player instanceof Engineer && !shoreableTiles.isEmpty()) {	//Engineer is allowed shore up another tile and must check there is another tile to shore up
			System.out.println("You are an Engineer so you may shore up another tile");
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
	 * @return true/false
	 */
	public boolean canDoTileAction(ArrayList<Tile> tileOptions, String typeOfTileAction) {

		if(tileOptions.isEmpty()) {
			System.out.println("There are no Tiles to " + typeOfTileAction); //Either "shore up" or "move to"
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
		ArrayList<Tile> moveableTiles = controller.getStandardMoveTiles(player); 

		if(!canDoTileAction(moveableTiles, "move to ")) return; 

		int userInput = Utility.acceptableInput(0, moveableTiles.size(), inputScanner); 
		if(userInput==moveableTiles.size()) return;  //If they want to cancel Movement

		Tile selectedTile = moveableTiles.get(userInput); 
		controller.movePlayerPawn(player, selectedTile);  
		System.out.println("Your pawn has been moved to " + selectedTile);
		controller.decementPlayerAction(player); 
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
		int userInput = Utility.acceptableInput(0, moveableTiles.size()-1, inputScanner); //No option to cancel movement as it is forced

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
			System.out.println("Your pawn is not on a treasure tile"); 
			return;
		}
		System.out.println("You dont have the required card's to capture the treasure"); //only other possibility is the players does not have the required cards
		return;
	}

	/**
	 * Returns selected player for treasure card for inputed player
	 * @param player
	 * @param inputScanner
	 * @return selected player or null
	 */
	public Player selectPlayerForCard(Player player, Scanner inputScanner) {
		ArrayList<Player> playersForCard =controller.getPlayersForCard(player);

		if (playersForCard.isEmpty()) {
			System.out.println("\nThere are no players to give treasure cards to");
			return null;
		}
		System.out.println("\nYou can give a card to the following players:");
		Utility.printOptions(playersForCard);
		int userInput = Utility.acceptableInput(0, playersForCard.size(), inputScanner); 
		
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
		
		Hand selectedPlayerHand = controller.getPlayerHand(selectedPlayer);

		if(!canDoCardAction(currentPlayersCards)) return;
		
		int userInput = Utility.acceptableInput(0, currentPlayersCards.size(), inputScanner);
		
		if(userInput == currentPlayersCards.size()) return; //cancels action
		
		Card selectedCard = currentPlayersCards.get(userInput);
		controller.giveCard(selectedCard, selectedPlayerHand, player); //Gives the card to the selected player
		System.out.println("You have given " + selectedCard + " to " + selectedPlayer);
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
			System.out.println("You can give the following cards:\n");
			Utility.printOptions(cardOptions);
			System.out.println("\nEnter " + cardOptions.size() + " to cancel action");
			return true;
		}

	}
	
	/**
	 * Ends current players turn
	 * @param player
	 */
	public void endTurn(Player player) {
		controller.emptyActions(player);
	}

	/**
	 * When a player is beside a treasure tile or a fool's landing tile that is about to sink, they are
	 * given an option to save the tile i.e. to keep it flooded, or don't save the tile i.e. sink the tile.
	 * 
	 * @param inputScanner The scanner
	 * @param tile The tile that is about to sink
	 * @param player The player
	 * @return True if the player chooses to save the tile, false otherwise
	 */
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
	
	/**
	 * Runs the card view to show the playable card options i.e. sandbag and/or helicopter lift cards
	 * @param inputScanner The scanner
	 * @param player The player
	 */
	public void runCardView(Scanner inputScanner, Player player) {
		CardView.getInstance().runCardView(inputScanner, player);
	}
	
	/**
	 * Prints the cards in the player's hand 
	 * @param player The player
	 */
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
	
	/**
	 * Prints out the board when called
	 */
	public void showBoard() {
		String board = controller.showBoard();

			System.out.println(board);
			boardExplanation();
		}
	
	/**
	 * Prints out an Explanation the board to the user
	 */
	public void boardExplanation() {
		ArrayList<Player> playerList = controller.getModel(); //The model is the list of players

		System.out.println("The Players are located at the following:\n");
		for(Player player: playerList ) {
			System.out.println(player+ ",  Symbol: " + controller.getSymbol(player) + ", Tile: " + controller.getPawnTile(player));
		}
		
		System.out.println("\nThe initial of the Tiles are shown (Not including of or the)");
		System.out.println("A * beside the inital means there is a current a treasure at this tile, when captured it will no longer show");
		System.out.println("A ! beside the inital means the Tile is flooded");
		System.out.println("A blank [  ] means the tile is no longer present ");
		System.out.println("Fools landing is in captials [ FL ] ");
		System.out.println("Your choosen symbol will replace the inital of the Tile ");
	}
}
