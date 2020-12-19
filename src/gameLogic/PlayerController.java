package gameLogic;
import java.util.ArrayList;
import island.board.*;

import island.cards.Hand;
import island.cards.TreasureDeckCard;
import island.enums.TreasureNames;

import island.enums.TileNames;

import players.*;

//Class to implement the player controller
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
		return player.getFocredMoveableTiles();
	}

	public void decementPlayerAction(Player player) {
		player.decrementPlayerActions();
	}

	public ArrayList<Player> getPlayersForTreasureCard(Player player){
		return player.getPlayersForTreasureCard();
	}

	public boolean canCaptureTreasure(Player player) {
		return player.canCaptureTreasure();
	}

	public void shoreUpTile(Tile tile) {
		tile.setFlood(false);
	}

	
	public void saveTile(Tile tile){
		tile.saveTile();
	}
	

	public ArrayList<Player> getModel(){
		return model;
	}

	public ArrayList<Player> getPlayerForTreasureCard(Player player){
		return player.getPlayersForTreasureCard();
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

	public void giveCard(TreasureDeckCard toGive, Hand toRecieve, Player player) {
		player.giveCard(toGive, toRecieve);
	}

	public int[][] getIslandTiles(){
		return Board.getInstance().getIslandTiles();
	}

	public Tile[][] getboard(){
		return Board.getInstance().getBoard();
	}

	public void emptyActions(Player player) {
		player.emptyActions();
	}
}
