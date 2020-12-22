package gameLogic;

import java.util.ArrayList;
import java.util.Scanner;
import island.board.Tile;
import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureDeckCard;
import island.cards.WaterRiseCard;
import players.*;
import observers.*;
import utility.Utility;

/**
 * Class for the user Interface for the game manager
 * @author Liam Fitzgerald and Hayley Chan
 *
 */

public class GameView {
	private GameController controller;
	private static GameView gameView = null;

	/**
	 * Creating instance of the game view 
	 * @return GameView instance
	 */
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
		PlayerObserver playerObserver=PlayerObserver.getInstance();
		ArrayList<Player> listSunkenPlayers = playerObserver.getSunkenPlayers();	//Gets list of sunken players from player observer
		PlayerView playerView = PlayerView.getInstanace();

		if(listSunkenPlayers.isEmpty()) return; //No players have been sunk return

		for(Player player: listSunkenPlayers) {		
			if(!controller.canSunkenPlayerMove(player)) {	//If sunken player can't move sunken player ending
				sunkenPlayerEnding(player);
			}
			playerView.doForcedMovement(inputScanner, player); //otherwise player does forced movement
		}
		playerObserver.updateMoved(); //Update the observer that the sunken players have moved
		return;
	} 
	
	/**
	 * Checks if a player is beside a treasure tile or a fool's tile that is about to sink and asks the 
	 * player if they want to save it
	 * @param inputScanner The scanner
	 */
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
	
	/**
	 * The game is lost if player decides to not save the tile that is being sunk or if no player
	 * is beside that tile
	 * @param tile The tile being sunk
	 */
	public void invokeLoseGame(Tile tile) {
		if(controller.loseCondition()) {
			if(tile.getNameString() == "Fool's Landing")
				foolsLost();
			else
				treasureLost();
		}
	}
	
	/**
	 * Prints the player's number of actions remaining
	 * @param player The player
	 */
	public void printPlayersActions(Player player) {
		System.out.println("\nYou have " + controller.getPlayerActions(player) + " actions remaining");
	}
	
	/**
	 * Does the player's turn. Able to choose any option from player view.
	 * @param inputScanner The scanner
	 */
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
				printPlayersActions(player);
				playerView.printOptions();
				playerView.selectOption(inputScanner, player);	//Select one of available actions options
				isTurnOver=controller.isTurnOver(player);	
			} 
			treasureDeckTurn(player, inputScanner);
			floodDeckTurn();
		}

	}

	/**
	 * After player ends their actions. Two cards are drawn from treasure deck.
	 * @param player The player
	 * @param inputScanner The scanner
	 */
	public void treasureDeckTurn(Player player, Scanner inputScanner) {
		System.out.println("\nDrawing 2 cards from treasure deck...");
		Hand playerHand = controller.getHand(player);
		ArrayList<Card> cardsDrawn = controller.treasureDeckTurn(player);
		printCardsDrawn(cardsDrawn, player);
		if(controller.checksHand(playerHand)) {
			tooManyCardsPrompt(player, playerHand, inputScanner);
		}
		ArrayList<TreasureDeckCard> playableCards = playerHand.getPlayableCards();
		if(!playableCards.isEmpty())
			runCardView(inputScanner, player);
	}
	
	/**
	 * Prints the cards drawn from the treasure deck to the user
	 * @param cardsDrawn List of cards drawn from the treasure deck
	 * @param player The player
	 */
	public void printCardsDrawn(ArrayList<Card> cardsDrawn, Player player) {
		for(Card card: cardsDrawn) {
			if(card instanceof WaterRiseCard) {
				int waterLevel = controller.getWaterMeter();
				System.out.println("\nPlay Water Rise card...");
				System.out.println("Water Level increased.\nCurrent water level: " + waterLevel);
				System.out.println("Flood Deck reshuffled.\n");
			}
			else
				System.out.println("Adding " + card + " to " + player + "'s hand...");
		}
	}
	
	/**
	 * Run the card view
	 * @param inputScanner The scanner
	 * @param player The player
	 */
	public void runCardView(Scanner inputScanner, Player player) {
		PlayerView.getInstanace().runCardView(inputScanner, player);
	}
	
	/**
	 * Ask the player to remove card(s) if they have more than 5 cards in their hand
	 * @param player The player
	 * @param playerHand The player's hand
	 * @param inputScanner The scanner
	 */
	public void tooManyCardsPrompt(Player player, Hand playerHand, Scanner inputScanner) {
		ArrayList<Card> cards = playerHand.getCards();
		int numCardsToRemove = cards.size()-5;
		
		System.out.println("\nCannot have more than 5 cards in hand! Remove " + numCardsToRemove + ".");
		while(cards.size()>=6) {
			PlayerView.getInstanace().printHand(player);
			System.out.print("\nEnter index of card you want to remove: ");
			int userInput = Utility.acceptableInput(0, cards.size()-1, inputScanner);
			System.out.println("\nRemoved " + cards.get(userInput));
			controller.removeFromHand(cards.get(userInput), player);
		}
	}
	
	/**
	 * After treasure deck cards are drawn. Flood cards equal to the number of the water meter level are
	 * drawn. Print to user which tiles have been flooded or sunk.
	 */
	public void floodDeckTurn() {
		System.out.println("\nDrawing flood cards...");
		ArrayList<Tile> tilesFlooded = controller.floodDeckTurn();
		for(Tile tile: tilesFlooded) {
			if(tile.isFlooded() && tile.isPresent())
				System.out.println("Flooding " + tile);
			else if(!tile.isPresent())
				System.out.println("Sinking " + tile);
		}
	}
	
	/**
	 * Starts the game.
	 * @param inputScanner The scanner
	 */
	public void doGame(Scanner inputScanner) {
		startGame();
		while(!controller.isGameOver()) { //Will keep looping over tile game is over
			doTurn(inputScanner);
		}
	}

	/**
	 * Prints game over message
	 */
	public void gameOver() {
		System.out.println("\n******************************************");
		System.out.println("**                                      **");
		System.out.println("**               You lose!              **");
		System.out.println("**                                      **");
		System.out.println("******************************************");	}
	
	/**
	 * Prints winning message
	 */
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
	
	/**
	 * Prints a losing message when both treasure tiles have sunk before the treasure has been captured
	 */
	public void treasureLost() {
		ArrayList<Tile> sunkTiles = GameOverObserver.getInstance().getSunkTiles();
		Tile lastTile = sunkTiles.get(sunkTiles.size() - 1);
		
		System.out.println(lastTile + " has sunk before the treasure was captured!");
		endGame();
	}
	
	/**
	 * Ends game when Fool's Landing has sunk
	 */
	public void foolsLost() {
		endGame();
	}
	
	/**
	 * Prints when water meter has reached level 5
	 */
	public void waterLost() {
		System.out.println("Water meter has reached 5!");
		endGame();
	}

	/**
	 * Method to invoke the end of the game.
	 */
	public void endGame() {
		gameOver();
		controller.gameOver();
		System.exit(0);
	}
	
	
}
