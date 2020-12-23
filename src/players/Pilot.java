package players;

import java.util.ArrayList;
import island.board.Tile;
import pawns.Pawn;

/**
 * Singleton class to implement the Pilot extends Player
 * @author Liam Fitzgerald and Hayley Chan
 *
 */

public class Pilot extends Player {

	private static Pilot thePilot;

	
	/**
	 * Constructed using base Player class and Pawn is constructed on "Fool's Landing"
	 * @param playerName
	 * @param playerNumber
	 * @param symbol represents player on the board
	 */
	private Pilot(String playerName, int playerNumber, String symbol) {
		super(playerName,playerNumber, symbol); 
		playerPawn=new Pawn(board.getTile("Fool's Landing")); 

	}

	/**
	 * Gets instance of Pilot class
	 * @param playerName
	 * @param playerNumber
	 * @param symbol
	 * @return thePilot
	 */
	public static Pilot getInstance(String playerName, int playerNumber, String symbol) {
		if(thePilot == null) {
			thePilot = new Pilot(playerName ,playerNumber, symbol);
		}
		return thePilot;
	}

	@Override
	/**
	 * Pilot can move to any present tile when forced to move due to their tile sinking
	 * @return List of tile the player can move to
	 */
	public ArrayList<Tile> getForcedMoveableTiles(){ 
		
		ArrayList<Tile> moveableTiles = board.listOfPresentTiles(); 
		
		return moveableTiles;
	}

}
