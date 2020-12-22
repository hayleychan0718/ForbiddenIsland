package island.cards;

import java.util.ArrayList;

import island.board.Board;
import island.board.Tile;

/**
 * Sandbag card subclass of TreasureDeckCard
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class SandbagCard extends TreasureDeckCard{
	/**
	 * Sandbag card constructor
	 */
	public SandbagCard() {
		super.name = "Sandbag";
	}
	
	/**
	 * Plays the sandbag card. Shores up a given tile.
	 * @param chosenTile The tile to shore up
	 */
	public static void play(Tile chosenTile) {
		chosenTile.setFlood(false);
	}

	public static ArrayList<Tile> sandBagOptions(){
		return Board.getInstance().listOfFloodedTiles();
	}
}
