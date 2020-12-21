package island.cards;

import java.util.ArrayList;
import java.util.Scanner;

import gameLogic.WaterMeter;
import island.board.Board;
import island.board.Tile;
import players.Player;
import players.PlayerList;
import utility.Utility;

public class CardView {
	private CardController controller;
	private static CardView cardView = null;

	public static CardView getInstance() {
		if(cardView == null)
			cardView = new CardView();
		return cardView;
	}
	
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
	
	public void doHelicopter(Scanner inputScanner, Hand playerHand, Card card, Player player) {
		ArrayList<Player> playersForHelicopter = PlayerList.getInstance().getPlayersForHelicopter();
		ArrayList<Tile> listOfTiles = controller.getListOfTiles();

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
	
	private void winHelicopter(Scanner inputScanner, Hand playerHand, Player player) {
		if(controller.canWinHelicopter())
			controller.winHelicopter();
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
			Utility.printOptions(controller.getListOfTiles());
			System.out.println("Which tile do you want to move to? ");
			return false;
		}
		else {
			System.out.println("\nLifting off Fool's Landing...");
			return true;
		}
	}
	
	// Used in doHelicopter()
	private ArrayList<Player> choosingPlayer(Scanner inputScanner, Tile chosenTile, ArrayList<Player> playersForHelicopter) {	
		ArrayList<Player> chosenPlayers = new ArrayList<Player>();
		boolean repeat = true;
		
		while(repeat==true) {
			System.out.println("Who do you want to move to " + chosenTile + "?");
			Utility.printOptions(playersForHelicopter);
			int playerIndex = Utility.acceptableInput(0, playersForHelicopter.size(), inputScanner);
			chosenPlayers.add(playersForHelicopter.get(playerIndex));	
			playersForHelicopter.remove(playerIndex);
			if(!playersForHelicopter.isEmpty()) { 
				System.out.println("Do you want to choose another player?\nYes [0]\nNo [1]");
				int choice = Utility.acceptableInput(0, 1, inputScanner); 
				if(choice == 0) 
			    	repeat = true; 
			    else 
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
			System.out.print("Moving " + player + " to " + chosenTile +  "...\n"); 
		}
	}
	
	
	public void doSandbag(Scanner inputScanner, Hand playerHand, Card card, Player player) {
		ArrayList<Tile> listOfFloodedTiles = controller.getListOfFloodedTiles();
		
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

			controller.doSandbag(chosenTile);
			controller.removeCard(card, player);
			System.out.println("Shored up " + chosenTile);
	}
	
	public void doWaterRise(Player player) {
		WaterMeter waterMeter = WaterMeter.getinstance();
		System.out.println("\nPlay Water Rise card...");
		controller.doWaterRise();
		System.out.println("Water Level increased.\nCurrent water level: " + waterMeter.getWaterLevel());
		System.out.println("Flood Deck reshuffled.\n");
	}

}
