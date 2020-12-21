package gameLogic;

import java.util.ArrayList;
import java.util.Scanner;
import island.board.Tile;
import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureDeckCard;
import players.*;
import observers.*;
import utility.Utility;

/**
 * CLass for the user INterface for the game manager
 * @author Liam Fitzgerald
 *
 */

public class GameView {

	//private GameView gameView;
	private GameController controller;
	private static GameView gameView = null;

	public static GameView getInstance() {
		if(gameView==null)
			gameView = new GameView();
		return gameView;
	}

	/**
	 * Printed statement at start of the game
	 */
	public  void startGame() {
		System.out.println("\n******************************************");
		System.out.println("**                                      **");
		System.out.println("**     Welcome to Forbidden Island!     **");
		System.out.println("**                                      **");
		System.out.println("******************************************");
	}

	/**
	 * Sets the controller for the GameView
	 * @param controller
	 */
	public void setController(GameController controller) {
		this.controller=controller;
	}

	/**
	 * Prints out the current players turn
	 * @param player
	 */
	public void playerTurn(Player player) {
		System.out.println("\nIt is now " + player + " Turn" );
	}

	/**
	 * Method called if game has ended due to sunken player unable to move
	 * @param player
	 */
	public void sunkenPlayerEnding(Player player) {
		System.out.println(player + " has been sunk and cannot move \n");
		gameOver();

	}
	
	/**
	 * Method called when players have been sunk
	 * @param inputScanner
	 */
	public void sunkenPlayers(Scanner inputScanner) {	//SunkenPlayers
		ArrayList<Player> listSunkenPlayers = PlayerObserver.getInstance().getSunkenPlayers();	//Gets list of sunken players from player observer
		PlayerView playerView = PlayerView.getInstanace();

		if(listSunkenPlayers.isEmpty()) return; //No players have been sunk return

		for(Player player: listSunkenPlayers) {		
			if(!controller.canSunkenPlayerMove(player)) {	//If sunken player can't move sunken player ending
				sunkenPlayerEnding(player);
			}
			playerView.doForcedMovement(inputScanner, player); //otherwise player does forced movement
		}
		PlayerObserver.getInstance().updateMoved(); //Update the observer that the sunken players have moved
		return;
	} 
	
	public void treasureOrFoolSunk(Scanner inputScanner) {
		PlayerView playerView = PlayerView.getInstanace();
		ArrayList<Tile> sunkTiles = GameOverObserver.getInstance().getSunkTiles();
		if(sunkTiles.size()>0) {
			Tile tile = sunkTiles.get(sunkTiles.size() - 1);
			if(controller.playerBeside(tile)) {
				Player player = controller.playerToBeNotified();
				if(!playerView.notifyPlayer(inputScanner , tile, player)) {
					invokeLoseGame(tile);
				}
			}
			else 
				invokeLoseGame(tile);
		}
	}
	
	public void invokeLoseGame(Tile tile) {
		if(controller.loseCondition()) {
			if(tile.getNameString() == "Fool's Landing")
				foolsLost();
			else
				treasureLost();
		}
	}
	
	public void doTurn(Scanner inputScanner) {
		boolean isTurnOver;
		ArrayList<Player> playerList = controller.getListOfPlayers();
		PlayerView playerView = PlayerView.getInstanace();

		for(Player player:playerList) { //Loops through the players
			isTurnOver=false;
			controller.reStockActions(player);
			sunkenPlayers(inputScanner); //Checks for sunken players
			treasureOrFoolSunk(inputScanner);
			
			playerTurn(player);
			
			while (!isTurnOver) { 
				playerView.printOptions();
				playerView.selectOption(inputScanner, player);	//Select one of availabe actions options
				isTurnOver=controller.isTurnOver(player);	
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
			runCardView(inputScanner, player);
	}
	
	public void runCardView(Scanner inputScanner, Player player) {
		PlayerView.getInstanace().runCardView(inputScanner, player);
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
	
	public void doGame(Scanner inputScanner) {
		startGame();
		while(!controller.isGameOver()) { //Will keep looping over tile game is over
			doTurn(inputScanner);
		}
	}

	public void gameOver() {
		System.out.println("Game Over!");
	}
	
	public void gameWin() {
		System.out.println("Lifting off Fool's Landing...");
		
		System.out.println("\n******************************************");
		System.out.println("**               You win!               **");
		System.out.println("**           Congratulations!           **");
		System.out.println("**                                      **");
		System.out.println("******************************************");
		controller.gameOver();
		System.exit(0);
	}
	
	public void treasureLost() {
		ArrayList<Tile> sunkTiles = GameOverObserver.getInstance().getSunkTiles();
		Tile lastTile = sunkTiles.get(sunkTiles.size() - 1);
		
		System.out.println(lastTile.getNameString() + " has sunk before the treasure was captured!");
		endGame();
	}
	
	public void foolsLost() {
		endGame();
	}
	
	public void waterLost() {
		System.out.println("Water meter has reached 5!");
		endGame();
	}

	public void endGame() {
		gameOver();
		controller.gameOver();
		System.exit(0);
	}
	
	
}
