package gameLogic;

import java.util.ArrayList;
import java.util.Scanner;

import island.board.Board;
import island.board.Tile;
import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureCard;
import island.cards.TreasureDeckCard;
import players.Player;
import players.PlayerList;
import utility.Utility;

public class CardView {
	public CardController controller;
	private static CardView cardView = null;

	public static CardView getInstance() {
		if(cardView == null)
			cardView = new CardView();
		return cardView;
	}

	public CardController getController() {
		return controller;
	}

	public void setController(CardController controller) {
		this.controller = controller;
	}
	
	public void runCardView(Scanner inputScanner, Hand playerHand) {
		ArrayList<TreasureDeckCard> playableCards = playerHand.getPlayableCards();
		if(cardOptions(playerHand)) {
			int userInput = Utility.acceptableInput(0, playableCards.size(), inputScanner);
			
			switch(playableCards.get(userInput).getName()) {
			case "Helicopter Lift":
				doHelicopter(inputScanner, playerHand, userInput);
				break;
			case "Sandbag":
				doSandbag(inputScanner, playerHand, userInput);
				break;
			}
		}
		else
			System.out.println("Skip.");
	}
	
	// Used in runCardView
	private boolean cardOptions(Hand playerHand) {
		ArrayList<Card> cards = playerHand.getCards();
		System.out.println("Pick one of the cards you wish to play");
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
		else
			return true;
	}
	
	public void doHelicopter(Scanner inputScanner, Hand playerHand, int cardIndex) {
		ArrayList<Player> playersForHelicopter = PlayerList.getInstance().getPlayersForHelicopter();
		ArrayList<Tile> listOfTiles = Board.getInstance().listOfTiles();

		if(!helicopterPrompt(inputScanner)) {
			int tileIndex = Utility.acceptableInput(0, listOfTiles.size(), inputScanner);
			Tile chosenTile = listOfTiles.get(tileIndex);
			ArrayList<Player> chosenPlayers = choosingPlayer(inputScanner, chosenTile, playersForHelicopter);
				
			movingMessage(chosenTile, chosenPlayers);
			controller.doHelicopter(chosenTile, chosenPlayers);
			controller.removeCard(cardIndex);
		}
		else {
			winHelicopter(inputScanner, playerHand);
		}
	}
	
	private void winHelicopter(Scanner inputScanner, Hand playerHand) {
		if(controller.canWinHelicopter())
			controller.winHelicopter();
		else{
			System.out.println("Can't lift off yet!");
			runCardView(inputScanner, playerHand);
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
	
	public void doSandbag(Scanner inputScanner, Hand playerHand, int cardIndex) {
		ArrayList<Tile> listOfFloodedTiles = Board.getInstance().listOfFloodedTiles();
		
		if(listOfFloodedTiles.size()==0) {
			System.out.println("Choose another card.");
			runCardView(inputScanner, playerHand);
		}
		else
			System.out.println("Play Sandbag Card...\n");
			System.out.println("Tiles you can shore up:");
			Utility.printOptions(listOfFloodedTiles);
			System.out.println("\nChoose the tile: ");
			int tileIndex = Utility.acceptableInput(0, listOfFloodedTiles.size(), inputScanner);
			Tile chosenTile = listOfFloodedTiles.get(tileIndex);

			controller.doSandbag(chosenTile);
			controller.removeCard(cardIndex);
			System.out.println("Shored up " + chosenTile.getNameString());
	}

	public void doWaterRise() {
		WaterMeter waterMeter = WaterMeter.getinstance();
		System.out.println("Play Water Rise card...");
		controller.doWaterRise();
		System.out.println("Water Level increased.\nCurrent water level: " + waterMeter.getWaterLevel());
		System.out.println("Flood Deck reshuffled.");
	}
}