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
	 * Gets the player's tiles that they can move to during an action
	 * @param player The player
	 * @return The list of tiles the player can move to 
	 */
	public ArrayList<Tile> getStandardMoveTiles(Player player){
		return player.getStandardMoveableTiles();
	}

	/**
	 * Gets the tiles that the player can shore up during an action
	 * @param player The player
	 * @return List of tiles the player can shore up
	 */
	public ArrayList<Tile> getShoreableTiles(Player player){
		return player.getShoreableTiles();
	}

	/**
	 * Get the tiles the player can move to if the tile they are on is sunk
	 * @param player The player
	 * @return The list of tiles the player can move
	 */
	public ArrayList<Tile> getForcedMovementTiles(Player player){
		return player.getForcedMoveableTiles();
	}

	/**
	 * Decrease the player's actions by one
	 * @param player The player
	 */
	public void decementPlayerAction(Player player) {
		player.decrementPlayerActions();
	}

	/**
	 * Gets the players you can give a card to 
	 * @param player The player
	 * @return List of players you can give a card to 
	 */
	public ArrayList<Player> getPlayersForCard(Player player){
		return player.getPlayersForCard();
	}

	/**
	 * Checks if the player can capture treasure
	 * @param player The player
	 * @return True if the player can capture the treasure, false otherwise
	 */
	public boolean canCaptureTreasure(Player player) {
		return player.canCaptureTreasure();
	}

	/**
	 * Shore up a tile
	 * @param tile The tile to shore up
	 */
	public void shoreUpTile(Tile tile) {
		tile.setFlood(false);
	}
	
	/**
	 * Get the model 
	 * @return PlayerController's model
	 */
	public ArrayList<Player> getModel(){
		return model;
	}

	//TODO: Two methods do the same thing???
	public ArrayList<Player> getPlayerForCard(Player player){
		return player.getPlayersForCard();
	}

	/**
	 * Move a player's pawn to a different tile
	 * @param player The player
	 * @param tile The tile
	 */
	public void movePlayerPawn(Player player, Tile tile) {
		player.movePlayerPawn(tile);
	}


	/**
	 * Gets the treasure of the tile the player is on
	 * @param player The player
	 * @return TreasureNames of the treasure
	 */
	public TreasureNames getTileTreasure(Player player) {
		return player.getTreasure();
	}

	/**
	 * Gets the player's hand
	 * @param player The player
	 * @return The player's hand
	 */
	public Hand getPlayerHand(Player player) {
		return player.getHand();
	}

	/**
	 * Give card to another player
	 * @param selectedCard The card to give 
	 * @param toRecieve The hand of the player receiving the card
	 * @param player The player giving the card
	 */
	public void giveCard(Card selectedCard, Hand toRecieve, Player player) {
		player.giveCard(selectedCard, toRecieve);
	}

	/**
	 * End the player's turn
	 * @param player The player
	 */
	public void emptyActions(Player player) {
		player.endTurn();
	}
	
	/**
	 * Remove card from a player's hand
	 * @param card The card to be removed
	 * @param player The player
	 */
    public void removeCard(Card card, Player player) {
		player.getHand().removeCard(card);
    }
    
    /**
     * Get a player's cards in their hand
     * @param player The player
     * @return ArrayList of Card objects
     */
    public ArrayList<Card> getPlayerCards(Player player) {
    	return player.getHand().getCards();
    }
    
    /**
     * Gets the string of the board to print
     * @return Board as a string
     */
    public String showBoard() {
    	return Board.getInstance().showBoard();
    }
    
    /**
     * Gets the symbol representing the player
     * @param player The player
     * @return String of the symbol representing the player
     */
    public String getSymbol(Player player) {
    	return player.getSymbol();
    }
    
    /**
     * Gets the tile the player's pawn is on
     * @param player The player
     * @return The tile of the player's pawn
     */
    public Tile getPawnTile(Player player) {
    	return player.getPlayerPawnTile();
    }    
}
