package gameLogic;
import java.util.ArrayList;

import island.board.*;
import players.*;
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
	
	
	
	public ArrayList<Tile> getShoreableTiles(Player player){
		return player.getShoreableTiles();
	}
	
	
}
