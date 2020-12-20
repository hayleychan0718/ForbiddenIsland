package gameLogic;

import java.util.ArrayList;
import java.util.Scanner;

import island.board.Board;
import island.board.Tile;
import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureDeck;
import island.cards.TreasureDeckCard;
import players.*;
import observers.*;
import utility.Utility;

public class GameView {

	//private GameView gameView;
	private GameController controller;
	private static GameView gameView = null;

	public static GameView getInstance() {
		if(gameView==null)
			gameView = new GameView();
		return gameView;
	}


	public  void startGame() {
		System.out.println("Welcome to Forbidden Island");
	}

	public void gameOver() {
		System.out.println("Game Over!");
	}

	public void playerTurn(Player player) {
		System.out.println("\nIt is now " + player + " Turn" );
	}

	public void sunkenPlayerEnding(Player player) {
		System.out.println(player + " has been sunk and cannot move \n");
		gameOver();

	}


	public void doTurn(Scanner inputScanner) {
		boolean isTurnOver;
		ArrayList<Player> playerList = controller.getListOfPlayers();
		PlayerView playerView = PlayerView.getInstanace();

		for(Player player:playerList) { //Loops through the players
			isTurnOver=false;
			controller.reStockActions(player);
			sunkenPlayers(inputScanner); //Takes care of sunken Players
			playerTurn(player);
			Utility.sleep(1500);
		
			while (!isTurnOver) { //Does the turn
				playerView.printOptions();
				playerView.selectOption(inputScanner, player);	//Select One of the printed options
				Utility.sleep(1500);
				isTurnOver=controller.isTurnOver(player);	//View uses controller to check if the player still has actions
			} 
			treasureDeckTurn(player, inputScanner);
			floodDeckTurn();
		}

	}

	public void treasureDeckTurn(Player player, Scanner inputScanner) {
		System.out.println("\nDrawing 2 cards from treasure deck...");
		Hand playerHand = controller.getHand(player);
		ArrayList<Card> cardsDrawn = controller.treasureDeckTurn(player);
		for(Card card: cardsDrawn) {
			System.out.println("Adding " + card.getName() + " to " + player.getName() + "'s hand...");
		}
		if(playerHand.getCards().size()>=6) {
			tooManyCardsPrompt(player, playerHand, inputScanner);
		}
		ArrayList<TreasureDeckCard> playableCards = playerHand.getPlayableCards();
		if(!playableCards.isEmpty())
				controller.runCardView(inputScanner, player);
	}
	
	public void tooManyCardsPrompt(Player player, Hand playerHand, Scanner inputScanner) {
		ArrayList<Card> cards = playerHand.getCards();
		int numCardsToRemove = cards.size()-5;
		
		System.out.println("\nCannot have more than 5 cards in hand! Remove " + numCardsToRemove + ".");
		while(cards.size()>=6) {
			PlayerView.getInstanace().printHand(player);
			System.out.print("\nEnter index of card you want to remove: ");
			int userInput = Utility.acceptableInput(0, cards.size()-1, inputScanner);
			System.out.println("\nRemoved " + cards.get(userInput).getName());
			controller.removeFromHand(cards.get(userInput), player);
		}
	}
	
	public void floodDeckTurn() {
		System.out.println("\nDrawing flood cards...");
		ArrayList<Tile> tilesFlooded = controller.floodDeckTurn();
		for(Tile tile: tilesFlooded) {
			if(tile.isFlooded() && tile.isPresent())
				System.out.println("Flooding " + tile.getNameString());
			else if(!tile.isPresent())
				System.out.println("Sinking " + tile.getNameString());
		}
	}
	
	public void sunkenPlayers(Scanner inputScanner) {	//SunkenPlayers
		ArrayList<Player> listSunkenPlayers = PlayerObserver.getInstance().getSunkenPlayers();
		PlayerView playerView = PlayerView.getInstanace();
		PlayerController playerController = PlayerController.getInstance();

		if(listSunkenPlayers.isEmpty()) return; //No players are sunk
		System.out.println("Test");
		System.out.println(listSunkenPlayers);
		for(Player player: listSunkenPlayers) {
			if(playerController.getForcedMovementTiles(player).isEmpty()) { //This is talking to model
				sunkenPlayerEnding(player);
				gameOver();
				controller.gameOver();
			}
			playerView.doForcedMovement(inputScanner, player);
		}
		PlayerObserver.getInstance().updateMoved();
		return;
	} 

	public void doGame(Scanner inputScanner) {
		startGame();
		while(!controller.isGameOver()) { //Will keep looping over tile game is over
			doTurn(inputScanner);
		}
	}

	public void setController(GameController controller) {
		this.controller=controller;
	}

	public void gameWin() {
		System.out.println("Lifting off Fool's Landing...");
		System.out.println("You win!");
		controller.gameOver();
		System.exit(0);
	}
	
	public void treasureLost() {
		ArrayList<Tile> sunkTiles = GameOverObserver.getInstance().getSunkTiles();
		Tile lastTile = sunkTiles.get(sunkTiles.size() - 1);
		
		System.out.println(lastTile.getNameString() + " has sunk before the treasure was captured!");
		gameOver();
		controller.gameOver();
		System.exit(0);
	}
	
	public void foolsLost() {
		gameOver();
		controller.gameOver();
		System.exit(0);
	}
	
	public void waterLost() {
		System.out.println("Water meter has reached 5!");
		gameOver();
		controller.gameOver();
		System.exit(0);
	}

}
