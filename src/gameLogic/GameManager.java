package gameLogic;

import java.util.ArrayList;
import island.board.*;
import island.cards.*;
import island.enums.TreasureNames;
import observers.GameOverObserver;
import players.*;

/**
 * Singleton Class for the GameManger which runs the games logic
 * @author Liam Fitzgerald and Hayley Chan
 *
 */
public class GameManager {

	private static GameManager theGameManager;

	private boolean isGameOver;
	private PlayerList playerList;
	private Player player;
	
	/**
	 * GameManager constructor
	 */
	private GameManager() {
		isGameOver=false;
		playerList= PlayerList.getInstance();
	}

	/**
	 * Gets the instance of GameManager
	 * @return theGameManager
	 */
	public static GameManager getInstance() {
		if(theGameManager == null) {
			theGameManager = new GameManager();
		}
		return theGameManager;
	}

	/**
	 * Checks whether a players turn is over
	 * @param player
	 * @return true/false
	 */
	public boolean isTurnOver(Player player) {
		int remainingPlayerActions = player.getPlayerActions();

		if(remainingPlayerActions==0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Ends the game
	 */
	public void gameOver() {
		isGameOver=true;
	}
	
	/**
	 * gets the list of players
	 * @return list Of Players
	 */
	public ArrayList<Player> getListOfPlayers(){
		return playerList.getListOfPlayers();
	}
	
	/**
	 * Checks if the game is over
	 * @return
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * Draws 2 cards from the treasure deck 
	 * @param player The player
	 * @return List of cards drawn from the treasure deck
	 */
	public ArrayList<Card> drawTreasureDeck(Player player) {
		return TreasureDeck.getInstance().drawCard(player);
	}

	/**
	 * Draws cards from flood deck equal to the number on the water meter 
	 * @return The list of tiles to flood
	 */
	public ArrayList<Tile> drawFloodDeck() {
		return FloodDeck.getInstance().drawCard();
	}

	/**
	 * Remove a card from a player's hand
	 * @param card The card to be removed
	 * @param player The player
	 */
	public void removeFromHand(Card card, Player player) {
		player.getHand().removeCard(card);
	}

	/**
	 * Get instance of the forbidden island board
	 * @return Instance of the board
	 */
	public Board getBoard() {
		return Board.getInstance();
	}

	/**
	 * Checks if a player is beside a treasure or fool's landing tile that is about to sink
	 * @param tile The tile that is about to sink
	 * @return True if a player is beside a treasure or fool's landing tile, false otherwise
	 */
	public boolean playerBeside(Tile tile) {
		ArrayList<Player> playersList = PlayerList.getInstance().getListOfPlayers();
		for(Player player: playersList) {
			for(Tile t: player.getShoreableTiles()) {
				if(t == tile) {
					this.player = player;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns the player that is beside a treasure tile or a fool's landing tile that is about to sink
	 * @return
	 */
	public Player getPlayerToNotify() {
		return player;
	}

	/**
	 * Returns true if a sunk player can move false otherwise
	 * @return true/false
	 */
	public boolean canSunkenPlayerMove(Player player) {
		if(player.getForcedMoveableTiles().isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks the losing conditions i.e. if both treasure tiles have sunk before the associated treasure
	 * has been captured or when fool's landing is sunk
	 * @return True if losing conditions satisfied, false otherwise
	 */
	public boolean loseCondition() {
		ArrayList<Tile> sunkTiles = GameOverObserver.getInstance().getSunkTiles();
		Board board = Board.getInstance();
		if(sunkTiles.contains(board.getTile("Fool's Landing")) || sunkTiles.contains(board.getTile("Temple of The Sun")) && sunkTiles.contains(board.getTile("Temple of The Moon")) && !TreasureNames.TheEarthStone.isCaptured() ||
				sunkTiles.contains(board.getTile("Cave of Shadows")) && sunkTiles.contains(board.getTile("Cave of Embers")) && !TreasureNames.TheCrystalOfFire.isCaptured() ||
				sunkTiles.contains(board.getTile("Coral Palace")) && sunkTiles.contains(board.getTile("Tidal Palace")) && !TreasureNames.TheOceansChalice.isCaptured() ||
				sunkTiles.contains(board.getTile("Howling Garden")) && sunkTiles.contains(board.getTile("Whispering Garden")) && !TreasureNames.TheStatueOfTheWind.isCaptured()){
			return true;
		}
		else 
			return false;
	}

	/**
	 * Checks if a player has more than 5 cards
	 * @param playerHand The player's hand
	 * @return True if the player has more than 5 cards in their hand, false otherwise
	 */
	public boolean checksHand(Hand playerHand) {
		if(playerHand.getCards().size()>=6) 
			return true;
		else 
			return false;
	}

	/**
	 * Get the water meter level
	 * @return The level of the water meter
	 */
	public int getWaterMeter() {
		return WaterMeter.getinstance().getWaterLevel();
	}
}
