package players;

import java.util.ArrayList;
import java.util.LinkedList;

import island.board.Board;
import island.board.Tile;
import pawns.Pawn;

public class Explorer extends Player {

	private static Explorer theExplorer;

	private Explorer(String playerName, int playerNumber) {
		super(playerName,playerNumber); //player class constructor
		playerPawn=new Pawn(board.getTile("Copper Gate")); //Gets the Bronze Gate Tile and sets it as pawn starter tile

	}

	public static Explorer getInstance(String playerName, int playerNumber) {
		if(theExplorer == null) {
			theExplorer = new Explorer(playerName ,playerNumber);
		}
		return theExplorer;
	}

	public static Explorer returnInstance() {
		return theExplorer;
	}
	
	@Override
	public ArrayList<Tile> getFocredMoveableTiles(){ //overridden as explorer cans swim diagonal tiles
		Tile pawnTile = getPlayerPawnTile();
		ArrayList<Tile> moveableTiles = pawnTile.getAdjacentDiagonal();
		for(Tile tile: moveableTiles) {	//Checks if the tiles are present if not removes them
			if(tile.isPresent()==false) {
				moveableTiles.remove(tile);
			}
			
		}
		return moveableTiles;
	}

}
