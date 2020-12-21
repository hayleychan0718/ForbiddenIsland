package players;
import java.util.ArrayList;

import gameLogic.CardLogic;
import island.board.*;
import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureDeckCard;
import island.enums.TreasureNames;

import island.enums.TileNames;

import players.*;

/**
 * Singleton Class to implement the Player Controller
 * @author Liam FItzgerald
 *
 */
public class PlayerController {

	private static ArrayList<Player> model;

	private static PlayerController controller = null;


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
	private PlayerController() {;
	}

	public ArrayList<Tile> getStandardMoveTiles(Player player){
		return player.getStandardMoveableTiles();
	}

	public ArrayList<Tile> getShoreableTiles(Player player){
		return player.getShoreableTiles();
	}

	public ArrayList<Tile> getForcedMovementTiles(Player player){
		return player.getForcedMoveableTiles();
	}

	public void decementPlayerAction(Player player) {
		player.decrementPlayerActions();
	}

	public ArrayList<Player> getPlayersForCard(Player player){
		return player.getPlayersForCard();
	}

	public boolean canCaptureTreasure(Player player) {
		return player.canCaptureTreasure();
	}

	public void shoreUpTile(Tile tile) {
		tile.setFlood(false);
	}
	
	public ArrayList<Player> getModel(){
		return model;
	}

	public ArrayList<Player> getPlayerForCard(Player player){
		return player.getPlayersForCard();
	}

	public void movePlayerPawn(Player player, Tile tile) {
		player.movePlayerPawn(tile);
	}


	public TreasureNames getTileTreasure(Player player) {
		return player.getTreasure();
	}

	public Hand getPlayerHand(Player player) {
		return player.getHand();
	}

	public void giveCard(Card selectedCard, Hand toRecieve, Player player) {
		player.giveCard(selectedCard, toRecieve);
	}

	public void emptyActions(Player player) {
		player.endTurn();
	}
	
    public void removeCard(Card card, Player player) {
		player.getHand().removeCard(card);
    }
    
    public ArrayList<Card> getPlayerCards(Player player) {
    	return player.getHand().getCards();
    }
    
    public String showBoard() {
    	return Board.getInstance().showBoard();
    }
    
    public String getSymbol(Player player) {
    	return player.getSymbol();
    }
    
    public Tile getPawnTile(Player player) {
    	return player.getPlayerPawnTile();
    }    
}
