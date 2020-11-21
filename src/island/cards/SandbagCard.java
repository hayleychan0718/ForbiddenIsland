package island.cards;

import java.util.ArrayList;

import island.board.Board;
import island.board.Tile;

public class SandbagCard extends TreasureDeckCard{

	public SandbagCard() {
		super.name = "Sandbag";
	}
	
	public void play() {
		System.out.println("Tiles you can shore up:");
		Board board = Board.getInstance();
		ArrayList<Tile> listOfTiles = board.listOfTiles();
		int index = 0;

		for(int i=0;i<listOfTiles.size();i++) {
			if(listOfTiles.get(i).isFlooded() == true) {
				System.out.println(listOfTiles.get(i).getNameString() + "[" + index + "]");
				index++;
			}
		}
	}
	
}
