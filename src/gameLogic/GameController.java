package gameLogic;
import java.util.ArrayList;
import island.board.Board;
import island.board.Tile;
import island.cards.Card;
import island.cards.Hand;
import players.*;

/**
 * Singleton class to implement the GameCOntroller
 * @author Liam Fitzgerald and Hayley Chan
 *
 */

public class GameController {

	private GameManager model;
	private static GameController controller = null;

	/**
	 * Get instance of the GameController
	 * @param model
	 * @return controller
	 */
	public static GameController getInstance(GameManager model) {
		if(controller == null)
			controller = new GameController(model);
		return controller;
	}

	/**
	 * Constructor for game controller
	 * @param model The GameManager model
	 */
	private GameController(GameManager model) {
		this.model=model;
	}

	/**
	 * Gets list of players in the game
	 * @return ArrayList<Player> of all the players in the game 
	 */
	public ArrayList<Player> getListOfPlayers() {
		return model.getListOfPlayers();
	}

	/**
	 * Checks if the player's turn is over
	 * @param player The player
	 * @return True if the player's turn is over, false otherwise
	 */
	public boolean isTurnOver(Player player) {
		return model.isTurnOver(player);
	}

	/**
	 * Makes the game end
	 */
	public void gameOver() {
		model.gameOver();
	}
	
	/**
	 * Reset the player's actions to 3
	 * @param player The player
	 */
	public void reStockActions(Player player) {
		player.ReStockActions();
	}
	
	/**
	 * Gets the number of actions a player has
	 * @param player The player
	 * @return The number of actions remaining
	 */
	public int getPlayerActions(Player player) {
		return player.getPlayerActions();
	}
	
	/**
	 * Checks if game is over
	 * @return True if game is over, false otherwise
	 */
	public boolean isGameOver() {
		return model.isGameOver();
	}

	/**
	 * Gets the model to draw treasure deck cards
	 * @param player The player
	 * @return The list of cards drawn from treasure deck
	 */
	public ArrayList<Card> treasureDeckTurn(Player player) {
		return model.drawTreasureDeck(player);
	}
	
	/**
	 * Draws cards(s) from flood deck
	 * @return The tiles that are flooded from flood card
	 */
	public ArrayList<Tile> floodDeckTurn() {
		return model.drawFloodDeck();
	}

	/**
	 * Removed a card from the hand of a player
	 * @param card The card to be removed
	 * @param player The player's hand
	 */
	public void removeFromHand(Card card, Player player) {
		model.removeFromHand(card, player);
	}
	
	/**
	 * Get a player's hand of cards
	 * @param player The player
	 * @return The player's hand
	 */
	public Hand getHand(Player player) {
		return player.getHand();
	}

	/**
	 * Gets instance of the board
	 * @return Instance of the board
	 */
	public Board getBoard() {
		return model.getBoard();
	}

	/**
	 * Checks if player is beside a tile
	 * @param tile The tile
	 * @return True if player is beside the tile, false otherwise
	 */
	public boolean playerBeside(Tile tile) {
		return model.playerBeside(tile);
	}

	/**
	 * Gets the player to be notified
	 * @return The player 
	 */
	public Player playerToBeNotified() {
		return model.getPlayerToNotify();
	}

	/**
	 * Checks if losing conditions are met
	 * @return True if losing conditions are satisfied, false otherwise
	 */
	public boolean loseCondition() {
		return model.loseCondition();
	}
	
	/**
	 * Checks if the player that is on a sunken tile can move to another tile
	 * @param player The player
	 * @return True is the player can move to a different tile, false otherwise
	 */
	public boolean canSunkenPlayerMove(Player player) {
		return model.canSunkenPlayerMove(player);
	}

	/**
	 * Checks the player's hand doesn't have more than 5 cards
	 * @param playerHand The player's hand
	 * @return True if the player has more than 5 cards, false otherwise
	 */
	public boolean checksHand(Hand playerHand) {
		return model.checksHand(playerHand);
	}

	/**
	 * Gets the water meter level
	 * @return The level of the water meter
	 */
	public int getWaterMeter() {
		return model.getWaterMeter();
	}
}

