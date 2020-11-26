package island.cards;

import java.util.ArrayList;

import island.board.Board;
import island.board.Tile;

public class HelicopterCard extends TreasureDeckCard{
	
	public HelicopterCard() {
		super.name = "Helicopter Lift";
	}
	
	public void play() {
		Board board = Board.getInstance();
		ArrayList<Tile> listOfTiles = board.listOfTiles();

		int index = 0;

		System.out.println("\nPlay Helicopter Lift card...");
		System.out.println("\nTiles you can move to:");
		for(int i=0; i<listOfTiles.size();i++) {
			System.out.println(listOfTiles.get(i).getNameString() + "[" + index + "]");
			index++;
		}
	}
	
	// Observer?
	// IF: all players have all four treasures AND: everyone is on the Fool's Landing
	// then: 

	public void win() {
		
		/*
		 * check if all players have the treasure
		 * gameover() = true
		 */
	}
	

}
