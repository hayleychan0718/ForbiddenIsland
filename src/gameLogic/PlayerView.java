package gameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import island.board.Board;
import island.board.Tile;
import island.cards.Hand;
import island.cards.TreasureDeckCard;
import island.enums.TileNames;
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
			EndTurn();
		case 1:
			doStandardMovement(inputScanner, player);
			break;
		case 2:
			shoreUp(inputScanner, player);
			break;
		case 3:
			giveTreasureCard(player, inputScanner);
			break;
		case 4:
			CaptureTreasure(player);
			break;
		case 5:
			showBoard();
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
	
	public void CaptureTreasure(Player player) {
		
		if(controller.canCaptureTreasure(player)) {
			System.out.println("You have captured the treasure:" + controller.getTreasure(player));
			controller.decementPlayerAction(player);
			return;
		}
		if(controller.getTreasure(player)==null) {
			System.out.println("You are not on a treasure tile\n");
			return;
		}
		System.out.println("You dont have the required card to capture the treasure");
		return;
	}
	
	public Player selectPlayerForTreasureCard(Player player, Scanner inputScanner) {
		ArrayList<Player> playersForTreasureCard =controller.getPlayerForTreasureCard(player);
		
		if (playersForTreasureCard.isEmpty()) {
			System.out.println("There are no players to give treasure cards to");
			return null;
		}
		Utility.printOptions(playersForTreasureCard);
		int userInput = Utility.acceptableInput(0, playersForTreasureCard.size(), inputScanner); //No option to cancel movement
		Player selectedPlayer = playersForTreasureCard.get(userInput);
		return selectedPlayer;
	}
	

	public void giveTreasureCard(Player player , Scanner inputScanner) {
		 ArrayList<TreasureDeckCard> currentPlayersCards = player.getHand().getCards();
		
		Player selectedPlayer = selectPlayerForTreasureCard(player,inputScanner);
		Hand selectedPlayerHand = controller.getPlayerHand(selectedPlayer);

		if(!canDoCardAction(currentPlayersCards)) return;
	
		int userInput = Utility.acceptableInput(0, currentPlayersCards.size(), inputScanner);
		TreasureDeckCard selectedCard = currentPlayersCards.get(userInput);
		controller.giveCard(selectedCard, selectedPlayerHand, player);
		controller.decementPlayerAction(player);
	}
	
	public boolean canDoCardAction(ArrayList<TreasureDeckCard> cardOptions) {

		if(cardOptions.isEmpty()) {
			System.out.println("You have no cards to give");
			return false;
		}
		else {
			System.out.println("You can give the following cards:");
			Utility.printOptions(cardOptions);
			System.out.println("/n enter" + cardOptions.size() + "[Return] to cancel action");
			return true;
		}

	}

	public boolean notifyPlayer(TileNames tile, Player player) {
		System.out.println(tile + " is about to sink! Do you want to save it?");
		System.out.println("[0] No\n[1] Yes");
		int userInput = Utility.acceptableInput(0, 1);
		if(userInput==1) {
			controller.decementPlayerAction(player);
			controller.saveTile(tile);
			return true;
		}
		else
			return false;
	}
	
	public void treasureLost() {
		ArrayList<Tile> sunkTiles = Board.getInstance().listOfSunkTiles();
		Tile lastTile = sunkTiles.get(sunkTiles.size() - 1);
		
		System.out.println(lastTile.getNameString() + " has sunk before the treasure was captured!");
		System.out.println("Game over...");
	}
	
	public void foolsLost() {
		System.out.println("Fool's Landing has sunk!\nGame over...");
	}
	
	public void waterLost() {
		System.out.println("Water meter has reached 5!\nGamer over...");
	}
	
	public static void main(String[] args) {
		Player Liam = new Player("Liam",1); 
		ArrayList<Player> optionList = new ArrayList<Player>();
		optionList.add(Liam);

		PlayerView view = PlayerView.getInstanace();
		Utility.printOptions(optionList);
	}
}