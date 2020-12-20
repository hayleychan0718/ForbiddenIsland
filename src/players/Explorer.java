package players;

import java.util.ArrayList;
import java.util.LinkedList;

import island.board.Board;
import island.board.Tile;
import pawns.Pawn;

/**
 * Singleton class to implement the Explorer extends Player
 * @author Liam Fitzgerald
 *
 */

public class Explorer extends Player {

	private static Explorer theExplorer;

	/**
	 * Constructed using base Player class and Pawn is constructed on "Copper Gate"
	 * @param playerName
	 * @param playerNumber
	 * @param symbol represents player on the board
	 */
	private Explorer(String playerName, int playerNumber, String symbol) {
		super(playerName,playerNumber, symbol); //player class constructor
		playerPawn=new Pawn(board.getTile("Copper Gate")); //Gets the Bronze Gate Tile and sets it as pawn starter tile

	}

	public static Explorer getInstance(String playerName, int playerNumber, String symbol) {
		if(theExplorer == null) {
			theExplorer = new Explorer(playerName ,playerNumber, symbol);
		}
		return theExplorer;
	}

	public static Explorer returnInstance() {
		return theExplorer;
	}

	@Override
	/**
	 * Override for the Explorer extra ability
	 */
	public ArrayList<Tile> getForcedMoveableTiles(){ 
		Tile pawnTile = getPlayerPawnTile();
		ArrayList<Tile> adjacentDiagonal = pawnTile.getAdjacentDiagonal();

		ArrayList<Tile> moveableTiles =  new ArrayList<Tile>();
		for(Tile tile: adjacentDiagonal) {	
			if(tile.isPresent()==true) {
				moveableTiles.add(tile);
			}

		}
		return moveableTiles;
	}

}
