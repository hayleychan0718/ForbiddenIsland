package pawns;

import island.board.Tile;

/**
 * Class to implement the functionality for the players pawns
 * @author Liam Fitzgerald and Hayley Chan
 *
 */

public class Pawn {

	private Tile pawnTile;
	
	/**
	 * Creates a pawn on a starting Tile
	 * @param startingTile
	 */
	public Pawn(Tile startingTile) {
		pawnTile=startingTile;
		}
	
	/**
	 * Gets the tile the pawn is on
	 * @return pawnTile
	 */
	public Tile getPawnTile() {
		return pawnTile;
	}
	
	/**
	 * Moves the pawn to a specified tile
	 * @param tile
	 */
	public void movePawn(Tile tile) {
		pawnTile = tile;
	}
	
	/**
	 * Boolean function to check if the Pawn's tile is present
	 * @return
	 */
	public boolean isPawnTilePresent() {  
		return pawnTile.isPresent();
	}

}
