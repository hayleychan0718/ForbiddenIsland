package gameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import island.board.Tile;
import island.enums.TreasureNames;
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

	public void printOptions() {
		System.out.println("You have the following available options");
		System.out.println("[1] Move Pawn");
		System.out.println("[2] Shore Up Tile");
		System.out.println("[3] Give Treasure Card");
		System.out.println("[4] Capture Treasure");
		System.out.println("[5] Show Board");
		System.out.println("[0] End Turn");
	}

	public void printStartOfTurn(Player player) {
		System.out.println("It is now" + player + "'s turn \n");
	}

	public void selectOption(Scanner inputScanner, Player player) {

		int userInput = Utility.acceptableInput(0, 6, inputScanner);

		switch(userInput) {
		case 0:
			shoreUp(
);
			break;
		default:
			break;
		}
	}

	
	public void shoreUp(Scanner inputScanner, Player player) {
		ArrayList<Tile> shoreableTiles = controller.getShoreableTiles(player);

		if(!canDoTileAction(shoreableTiles, "shore up")) return;

		int userInput=Utility.acceptableInput(0, shoreableTiles.size(), inputScanner); 
		Tile SelectedTile=shoreableTiles.get(userInput);
		controller.shoreUpTile(SelectedTile);
		shoreableTiles.remove(userInput);

		if(player instanceof Engineer & !shoreableTiles.isEmpty()) {
			System.out.println("/nYou are an Engineer so you may shore up another tile");
			Utility.printOptions(shoreableTiles);
			userInput=Utility.acceptableInput(0, shoreableTiles.size()-1, inputScanner); 
			SelectedTile=shoreableTiles.get(userInput);
			controller.shoreUpTile(SelectedTile);
		}
		controller.decementPlayerAction(player);
	}
	
	public boolean canDoTileAction(ArrayList<Tile> tileOptions, String typeOfTileAction) {

		if(tileOptions.isEmpty()) {
			System.out.println("There is no Tiles to " + typeOfTileAction);
			return false;
		}
		else {
			System.out.println("You can " +  typeOfTileAction + "the following Tiles");
			Utility.printOptions(tileOptions);
			System.out.println("/n enter" + tileOptions.size() + "[Return] to cancel action");
			return true;
		}

	}
	
	public void doStandardMovement(Scanner inputScanner, Player player){ 
		ArrayList<Tile> moveableTiles = controller.getStandardMoveTiles(player);
		
		if(!canDoTileAction(moveableTiles, "move to")) return;
		
		int userInput = Utility.acceptableInput(0, moveableTiles.size(), inputScanner);
		if(userInput==moveableTiles.size()) return;
		
		Tile selectedTile = moveableTiles.get(userInput);
		controller.movePlayerPawn(player, selectedTile);
		System.out.println("Your pawn has been moved to " + selectedTile);
		controller.decementPlayerAction(player);
	}
	
	
	public void doForcedMovement(Scanner inputScanner, Player player) {
		ArrayList<Tile> moveableTiles = controller.getForcedMovementTiles(player);
		
		if(!canDoTileAction(moveableTiles,"move to")) return;
		
		int userInput = Utility.acceptableInput(0, moveableTiles.size()-1, inputScanner); //No option to cancel movement
		
		Tile selectedTile = moveableTiles.get(userInput);
		controller.movePlayerPawn(player, selectedTile);
		System.out.println("Your pawn has been moved to " + selectedTile);
	}
	
	
	public void doCaptureTreasure(Player player) {
		
		if(controller.canCaptureTreasure(player)) {
			System.out.println("You have captured the treasure:" + controller.getTreasure(player));
			return;
		}
		if(controller.getTreasure(player)==null) {
			System.out.println("You are not on a treasure tile\n");
			return;
		}
		System.out.println("You dont have the required card to capture the treasure");
		return;
	}
	
	


	public boolean isTurnOver(Player player) {
		
	}


	public static void main(String[] args) {
		Player Liam = new Player("Liam",1); 
		ArrayList<Player> optionList = new ArrayList<Player>();
		optionList.add(Liam);

		PlayerView view = PlayerView.getInstanace();
		Utility.printOptions(optionList);
	}
}