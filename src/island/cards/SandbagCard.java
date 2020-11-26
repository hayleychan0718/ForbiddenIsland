package island.cards;

import java.util.ArrayList;

import island.board.Board;
import island.board.Tile;

public class SandbagCard extends TreasureDeckCard{

	public SandbagCard() {
		super.name = "Sandbag";
	}
	
	public void play() {
		System.out.println("Play Sandbag Card...\n");
		System.out.println("Tiles you can shore up:");
		Board board = Board.getInstance();
		ArrayList<Tile> listOfFloodedTiles = board.listOfFloodedTiles();
		int index = 0;

		for(int i=0;i<listOfFloodedTiles.size();i++) {
			System.out.println(listOfFloodedTiles.get(i).getNameString() + " [" + index + "]");
			index++;
		}
	}
	
}
