package island.cards;

import java.util.ArrayList;
import java.util.Scanner;

import gameLogic.GameView;
import island.board.Tile;
import players.Player;
import utility.Utility;

/**
 * Card view for playable cards
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class CardView {
	/*
	 * Instance variables
	 */
	private CardController controller;
	private static CardView cardView = null;

	/**
	 * Get instance of the card view
	 * @return The card view instance
	 */
	public static CardView getInstance() {
		if(cardView == null)
			cardView = new CardView();
		return cardView;
	}
	
	/**
	 * Get the card controller
	 * @return The card controller
	 */
	public CardController getController() {
		return controller;
	}

	/**
	 * Sets the controller for the view
	 * @param controller
	 */
	public void setController(CardController controller) {
		this.controller = controller;

	}
	
	/**
	 * Get the player's choice from playable cards
	 * @param inputScanner Scanner 
	 * @param player The player
	 */
	public void runCardView(Scanner inputScanner, Player player) {
		Hand playerHand = controller.getPlayerHand(player);
		ArrayList<TreasureDeckCard> playableCards = controller.getPlayableCards(player); 
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
	
	/**
	 * Prints out the playable card options that the player has
	 * @param player The player
	 * @return True if they have cards they can play, false otherwise
	 */
	private boolean cardOptions(Player player) {
		ArrayList<TreasureDeckCard> playableCards = controller.getPlayableCards(player);
		System.out.println("\nPlayable cards:");
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
	
	/**
	 * Either move pawn(s) to a chosen tile or to win the game
	 * @param inputScanner Scanner
	 * @param playerHand The player's hand 
	 * @param card The card being played i.e. Helicopter card
	 * @param player The player
	 */
	public void doHelicopter(Scanner inputScanner, Hand playerHand, Card card, Player player) {
		ArrayList<Player> playersForHelicopter = controller.playersForHelicopter();
		ArrayList<Tile> listOfTiles = controller.helicopterOptions();

		if(!helicopterPrompt(inputScanner)) {
			int tileIndex = Utility.acceptableInput(0, listOfTiles.size(), inputScanner); 
			Tile chosenTile = listOfTiles.get(tileIndex);
			ArrayList<Player> chosenPlayers = choosingPlayer(inputScanner, chosenTile, playersForHelicopter);
				
			movingMessage(chosenTile, chosenPlayers);
			controller.doHelicopter(chosenTile, chosenPlayers);
			controller.removeCard(card, player);
		}
		else {
			winHelicopter(inputScanner, playerHand, player);
		}
	}
	
	/**
	 * Either win the game or tell the player they can't win yet
	 * @param inputScanner Scanner
	 * @param playerHand The player's hand
	 * @param player The player
	 */
	private void winHelicopter(Scanner inputScanner, Hand playerHand, Player player) {
		if(controller.canWinHelicopter())
			GameView.getInstance().gameWin();
		else{
			System.out.println("Can't lift off yet!\n");
			runCardView(inputScanner, player);
		}
	}
	
	/**
	 * Checks if player wants to move pawns or if player wants to lift off Fool's Landing for the win
	 * @param inputScanner Scanner
	 * @return True if player wants to move, false if player wants to lift off
	 */
	private boolean helicopterPrompt(Scanner inputScanner) { 
		System.out.println("\nPlay Helicopter Lift card...");
		System.out.println("Do you want to\nMove one or more pawns to any other tile? [0] or\nLift your team off Fool's Landing for the win? [1]");
		int helicopterChosen = Utility.acceptableInput(0, 1, inputScanner);

		if(helicopterChosen == 0) {
			System.out.println("\nTiles you can move to:");
			Utility.printOptions(controller.helicopterOptions());
			System.out.println("Which tile do you want to move to? ");
			return false;
		}
		else {
			System.out.println("\nLifting off Fool's Landing...");
			return true;
		}
	}
	
	/**
	 * Choose the players to move to a certain tile
	 * @param inputScanner Scanner
	 * @param chosenTile The tile to move to
	 * @param playersForHelicopter The players that you can move
	 * @return The player(s) chosen to be moved
	 */
	private ArrayList<Player> choosingPlayer(Scanner inputScanner, Tile chosenTile, ArrayList<Player> playersForHelicopter) {	
		ArrayList<Player> chosenPlayers = new ArrayList<Player>();
		boolean repeat = true;
		
		while(repeat==true) {
			System.out.println("Who do you want to move to " + chosenTile + "?");
			Utility.printOptions(playersForHelicopter);
			int playerIndex = Utility.acceptableInput(0, playersForHelicopter.size()-1, inputScanner);
			chosenPlayers.add(playersForHelicopter.get(playerIndex));	
			playersForHelicopter.remove(playerIndex);
			if(!playersForHelicopter.isEmpty()) { 
				System.out.println("Do you want to choose another player?\nYes [0]\nNo [1]");
				int choice = Utility.acceptableInput(0, 1, inputScanner); 
				if(choice == 1) 
			    	repeat = false;     
			}
			else
				repeat = false; 
		}	
		return chosenPlayers;
	}
	
	/**
	 * Prints a message that a player is being moved
	 * @param chosenTile The tile to move to 
	 * @param chosenPlayers The chosen player(s) to be moved
	 */
	private void movingMessage(Tile chosenTile, ArrayList<Player> chosenPlayers) {
		for(Player player: chosenPlayers) {
			System.out.print("Moving " + player + " to " + chosenTile +  "...\n"); 
		}
	}
	
	/**
	 * Shore up a tile with the sandbag card
	 * @param inputScanner Scanner 
	 * @param playerHand The player's hand
	 * @param card The card being played i.e. Sandbag card
	 * @param player The player
	 */
	public void doSandbag(Scanner inputScanner, Hand playerHand, Card card, Player player) {
		ArrayList<Tile> listOfFloodedTiles = controller.sandBagOptions();
		
		if(listOfFloodedTiles.size()==0) {
			System.out.println("Choose another card.");
			runCardView(inputScanner, player);
		}
		else {
			Tile chosenTile = tileToShoreUp(inputScanner, listOfFloodedTiles);
			controller.doSandbag(chosenTile);
			controller.removeCard(card, player);
			System.out.println("Shored up " + chosenTile);
		}
	}
	
	/**
	 * Returns the tile that the player wants to shore up from the sandbag card
	 * @param inputScanner The scanner
	 * @param listOfFloodedTiles List of tiles that are flooded
	 * @return The chosen tile the player wants to shore up
	 */
	private Tile tileToShoreUp(Scanner inputScanner, ArrayList<Tile> listOfFloodedTiles) {
		System.out.println("Play Sandbag Card...\n");
		System.out.println("Tiles you can shore up:");
		Utility.printOptions(listOfFloodedTiles);
		System.out.println("\nChoose the tile: ");
		int tileIndex = Utility.acceptableInput(0, listOfFloodedTiles.size()-1, inputScanner);
		return listOfFloodedTiles.get(tileIndex);
	}
}
