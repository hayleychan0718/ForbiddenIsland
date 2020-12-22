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

	/**
	 * Return a list of options for the sandbag card
	 * @return A list of all the flooded tiles on the board
	 */
	public static ArrayList<Tile> sandBagOptions(){
		return Board.getInstance().listOfFloodedTiles();
	}
}
