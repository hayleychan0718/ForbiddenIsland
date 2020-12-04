package gameLogic;
import java.util.ArrayList;
import island.board.*;
import players.*;

//Class to implement the player controller
public class PlayerController {
	
	private PlayerView view;
	private ArrayList<Player> model;
	
	private static PlayerController controller = null;
	 
	
	public static PlayerController getInstance(ArrayList<Player> model,PlayerView view) {
		if(controller == null)
			model = PlayerList.getInstance().getListOfPlayers();
			controller = new PlayerController(model,view);
		return controller;
	}
	
	private PlayerController(ArrayList<Player> model,PlayerView view) {
		this.model = model;
		this.view = view;
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
	
	public ArrayList<Player> getModel(){
		return model;
	}
	
	public ArrayList<Player> getPlayerForTreasureCard(Player player){
		return player.getPlayersForTreasureCard();
	}
	
	public void movePlayerPawn(Player player, Tile tile) {
		player.movePlayerPawn(tile);
	}
	
	
	
	
	
	
	
	
	
	
	
}
