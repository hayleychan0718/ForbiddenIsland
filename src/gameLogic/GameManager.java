package gameLogic;

import java.util.ArrayList;
import island.board.*;
import island.cards.*;
import island.enums.TileNames;
import island.enums.TreasureNames;
import observers.GameOverObserver;
import observers.PlayerObserver;
import players.*;

public class GameManager {


	private static GameManager theGameManager;

	private boolean isGameOver;
	private PlayerList playerList;
	private Player player;
	
	
	private GameManager() {
		isGameOver=false;
		playerList= PlayerList.getInstance();
	}

	public static GameManager getInstance() {
		if(theGameManager == null) {
			theGameManager = new GameManager();
		}
		return theGameManager;
	}

	/**
	 * Checks whether a players turn is over
	 * @param player
	 * @return boolean
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

	public ArrayList<Card> drawTreasureDeck(Player player) {
		return TreasureDeck.getInstance().drawCard(player);
	}

	public ArrayList<Tile> drawFloodDeck() {
		return FloodDeck.getInstance().drawCard();
	}

	public void removeFromHand(Card card, Player player) {
		player.getHand().removeCard(card);
	}

	public Board getBoard() {
		return Board.getInstance();
	}

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
	
	public Player getPlayerToNotify() {
		return player;
	}

	/**
	 * Returns true if a sunk player can move false otherwise
	 * @return boolean
	 */
	public boolean canSunkenPlayerMove(Player player) {
		if(player.getForcedMoveableTiles().isEmpty()) {
			return false;
		}
		return true;
	}
	
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

	public boolean checksHand(Hand playerHand) {
		if(playerHand.getCards().size()>=6) 
			return true;
		else 
			return false;
	}

	public int getWaterMeter() {
		return WaterMeter.getinstance().getWaterLevel();
	}
}
