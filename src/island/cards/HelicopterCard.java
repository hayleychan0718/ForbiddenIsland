package island.cards;

import java.util.ArrayList;

import island.board.Board;
import island.board.Tile;

public class HelicopterCard extends TreasureDeckCard{
	
	public HelicopterCard() {
		super.name = "Helicopter";
	}
	
	public void play() {
		System.out.println("Tiles you can move to:");
		Board board = Board.getInstance();
		ArrayList<Tile> list = board.listOfTiles();
		int index = 0;
		
		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i).getNameString() + "[" + index + "]");
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
