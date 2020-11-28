package gameLogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureDeckCard;
import players.*;
import island.board.*;
import utility.*;
public class PlayerAction {

	private Player player;
	private Board board;
	private Hand hand;
	private int userInput;
	private Tile selectedTile;

	public PlayerAction(Player player) {
		this.player=player;
		this.board= Board.getInstance();
		hand=player.getHand();

	}

	public void doStandardMovement() {	//do we need back capabilities
		LinkedList<Tile> moveableTiles = player.getStandardMoveableTiles(); 	//Gets move able tiles and prints them out

		System.out.println("\nEnter : [" + moveableTiles.size() + "] +  [Return] to Cancel Movement"); //Largest accpetable input is always to cance
		userInput=Utility.acceptableInput(0,moveableTiles.size()); //Ensures an acceptable input has been entered

		if(userInput==moveableTiles.size()) {
			System.out.println("You cancelled");
			return; //cancels action
		} 	

		selectedTile=moveableTiles.get(userInput);
		System.out.println("Your pawn has been moved to " + selectedTile.getNameString());
		player.movePlayerPawn(selectedTile); 
		player.decrementPlayerActions();
	}

	public void doForcedMovement() {
		LinkedList<Tile> moveableTiles = player.getFocredMoveableTile();

		userInput=Utility.acceptableInput(0,moveableTiles.size()-1);	//Ensures valid Input -1 is to remove option to cancel movement as it is forced

		if(userInput==moveableTiles.size()) { 
			return; //Cancels Action
		}
		selectedTile=moveableTiles.get(userInput);
		System.out.println("Your pawn has been moved to " + selectedTile.getNameString());
		player.movePlayerPawn(selectedTile);
	}

	public void shoreUpTiles() {
		LinkedList<Tile> shoreableTiles = player.getShoreableTiles();	//Gets shore able tiles

		System.out.println("/n enter" + shoreableTiles.size() + "[Return] to cancel action");
		userInput=Utility.acceptableInput(0, shoreableTiles.size());


		if(userInput==shoreableTiles.size()) { 
			return; 	//cancels action
		}
		selectedTile=shoreableTiles.get(userInput);
		System.out.println("You have shored up:" + selectedTile);
		selectedTile.setFlood(false); //shores up the tile add shore up method

		if(player instanceof Engineer) {	//Engineer may shore up two tiles //SMELL
			System.out.println("/nYou are an Engineer so you may shore up another tile");
			shoreableTiles = player.getShoreableTiles();	//must update
			userInput=Utility.acceptableInput(0, shoreableTiles.size()-1);	//Cannot cancel since a tile has already been shore up
			selectedTile=shoreableTiles.get(userInput);
			System.out.println("You have shored up:" + selectedTile);
			selectedTile.setFlood(false);
		}
		player.decrementPlayerActions();
	}

	public void giveCard() {
		Player selectedPlayer;
		TreasureDeckCard selectedCard;

		//System.out.println("/n enter" + possiblePlayers.size() + "[Return] to cancel action");
		selectedPlayer=selectPlayer();
		//if(userInput==possiblePlayers.size()) return; 	//cancels action
		selectedCard=selectCard();
		hand.giveCard(selectedCard, selectedPlayer.getHand());	//adds the selected card to the other players hand
		player.decrementPlayerActions();
	}

	public void captureTreasure() {
		if(player.captureTreasure()==true){
			System.out.println("You have successfully captured:"+  player.getPlayerPawnTile().getTreasure().getString());
			player.decrementPlayerActions();
			return;
		}
		System.out.println("You were unable to capture the treasure");
	}

	public Player selectPlayer() {
		LinkedList<Player> possiblePlayers = player.giveTreasureCard();
		Player selectedPlayer;

		userInput=Utility.acceptableInput(0, possiblePlayers.size());
		selectedPlayer = possiblePlayers.get(userInput);

		return selectedPlayer;
	} 

	public TreasureDeckCard selectCard() {
		TreasureDeckCard selectedCard;
		ArrayList<TreasureDeckCard> cardsInHand = hand.getCards();

		System.out.println("You must know select a card from your hand \n");
		hand.printHand();
		userInput=Utility.acceptableInput(0, cardsInHand.size());
		selectedCard=cardsInHand.get(userInput);

		return selectedCard;
	}


	}
}
