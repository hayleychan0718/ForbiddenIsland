package players;
import java.util.ArrayList;

import island.board.*;
import island.cards.Card;
import island.cards.Hand;
import island.enums.TreasureNames;

/**
 * Singleton Class to implement the Player Controller
 * @author Liam Fitzgerald and Hayley Chan
 *
 */
public class PlayerController {

	private static ArrayList<Player> model;

	private static PlayerController controller = null;


	/**
	 * Gets instance of the PlayerController
	 * @return controller
	 */
	public static PlayerController getInstance() {
		if(controller == null) {
			model = PlayerList.getInstance().getListOfPlayers();
			controller = new PlayerController();
			return controller;
		}
		else {
			return controller;
		}
	}

	/**
	 * Gets the list of players standard move able tiles
	 * @param player
	 * @return list of move able tiles
	 */
	public ArrayList<Tile> getStandardMoveTiles(Player player){
		return player.getStandardMoveableTiles();
	}

	/**
	 * Gets the list of players shore able tiles
	 * @param player
	 * @return list of shore able tiles
	 */
	public ArrayList<Tile> getShoreableTiles(Player player){
		return player.getShoreableTiles();
	}

	/**
	 * Gets the list of players forced movement tiles when there tile is sunk
	 * @param player
	 * @return list of forced movement tiles
	 */
	public ArrayList<Tile> getForcedMovementTiles(Player player){
		return player.getForcedMoveableTiles();
	}

	/**
	 * Decrements the players actions
	 * @param player
	 */
	public void decementPlayerAction(Player player) {
		player.decrementPlayerActions();
	}

	/**
	 * Gets the list of players available for card
	 * @param player
	 * @return list of players available for card
	 */
	public ArrayList<Player> getPlayersForCard(Player player){
		return player.getPlayersForCard();
	}

	/**
	 * Boolean for whether a player can capture a treasure , if true treasure is captured
	 * @param player
	 * @return true/false
	 */
	public boolean canCaptureTreasure(Player player) {
		return player.canCaptureTreasure();
	}

	/**
	 * Shores up selected tile
	 * @param tile
	 */
	public void shoreUpTile(Tile tile) {
		tile.setFlood(false);
	}
	
	/**
	 * gets the model
	 * @return model
	 */
	public ArrayList<Player> getModel(){
		return model;
	}

	/**
	 * Moves pawn to Tile
	 * @param player
	 * @param tile
	 */
	public void movePlayerPawn(Player player, Tile tile) {
		player.movePlayerPawn(tile);
	}


	/**
	 * Gets the treasure the player is currently in
	 * @param player
	 * @return the treasure
	 */
	public TreasureNames getTileTreasure(Player player) {
		return player.getTreasure();
	}

	/**
	 * Gets the players hand
	 * @param player
	 * @return player hand
	 */
	public Hand getPlayerHand(Player player) {
		return player.getHand();
	}

	/**
	 * Gives a card from input player to another players hand
	 * @param selectedCard
	 * @param toRecieve
	 * @param player
	 */
	public void giveCard(Card selectedCard, Hand toRecieve, Player player) {
		player.giveCard(selectedCard, toRecieve);
	}

	/**
	 * Empties the players action, therefore ending their turn
	 * @param player
	 */
	public void emptyActions(Player player) {
		player.endTurn();
	}
	
	/**
	 * Removes a card from the input player's hand
	 * @param card
	 * @param player
	 */
    public void removeCard(Card card, Player player) {
		player.getHand().removeCard(card);
    }
    
    /**
     * Gets the input players hand
     * @param player
     * @return list of cards in players hand
     */
    public ArrayList<Card> getPlayerCards(Player player) {
    	return player.getHand().getCards();
    }
    
    /**
     * Gets string representation of the board
     * @return String Board
     */
    public String showBoard() {
    	return Board.getInstance().showBoard();
    }
    
    /**
     * Gets the symbol the players is represented by on the board
     * @param player
     * @return String symbol
     */
    public String getSymbol(Player player) {
    	return player.getSymbol();
    }
    
    /**
     * Gets the pawns tile of the input player
     * @param player
     * @return tile of the player's pawn
     */
    public Tile getPawnTile(Player player) {
    	return player.getPlayerPawnTile();
    }    
}
