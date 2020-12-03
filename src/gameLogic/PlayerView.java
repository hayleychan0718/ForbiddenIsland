package gameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import island.board.Tile;
import players.Engineer;
import players.Player;
import utility.Utility;

//Class to implement the player view
public class PlayerView {

	private PlayerController controller;
	private static PlayerView playerView = null;

	public static PlayerView getInstanace() {
		if(playerView == null)
			playerView = new PlayerView();
		return playerView;
	}


	public PlayerController getController() {
		return controller;
	}

	public void setController(PlayerController controller) {
		this.controller = controller;

	}

	public void shoreUp(Scanner scannerIn, Player player) {
		ArrayList<Tile> shoreableTiles = controller.getShoreableTiles(player);

		if(!canTilesBeShoredUp(shoreableTiles)) return;
		
		int userInput=Utility.acceptableInput(0, shoreableTiles.size());
		if(userInput==shoreableTiles.size()) return;
		Tile SelectedTile=shoreableTiles.get(userInput);
		controller.shoreUpTile(SelectedTile);
		shoreableTiles.remove(userInput);
		
		if(player instanceof Engineer & !shoreableTiles.isEmpty()) {
			System.out.println("/nYou are an Engineer so you may shore up another tile");
			Utility.printOptions(shoreableTiles);
			userInput=Utility.acceptableInput(0, shoreableTiles.size()-1); //No option to cancel what if now empthy ?
			SelectedTile=shoreableTiles.get(userInput);
			controller.shoreUpTile(SelectedTile);
		}
		controller.decementPlayerAction(player);
}
	
	public boolean canTilesBeShoredUp(ArrayList<Tile> shoreableTiles) {
		
		if(shoreableTiles.isEmpty()) {
			System.out.println("There is no Tiles to shore up");
			return false;
		}
		else {
			System.out.println("You can shore up the following Tiles");
			Utility.printOptions(shoreableTiles);
			System.out.println("/n enter" + shoreableTiles.size() + "[Return] to cancel action");
			return true;
		}
		
	}



public static void main(String[] args) {
	Player Liam = new Player("Liam",1); 
	ArrayList<Player> optionList = new ArrayList<Player>();
	optionList.add(Liam);

	PlayerView view = PlayerView.getInstanace();
	Utility.printOptions(optionList);
}
}