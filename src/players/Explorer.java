package players;

import java.util.LinkedList;

import island.board.Board;
import island.board.Tile;
import pawns.Pawn;

public class Explorer extends Player {

	private static Explorer theExplorer;

	private Explorer(String playerName, int playerNumber) {
		super(playerName,playerNumber); //player class constructor
		Board board = Board.getInstance();
		Pawn playerPawn=new Pawn(board.getTile("Copper Gate")); //Gets the Bronze Gate Tile and sets it as pawn starter tile

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
	public LinkedList<Tile> getFocredMoveableTile(){ //overridden as explorer cans swim diagonal tiles
		int i=0;
		Tile pawnTile = getPlayerPawnTile();
		LinkedList<Tile> moveableTiles = pawnTile.getAdjacentDiagonal();
		for(Tile tile: moveableTiles) {	//Checks if the tiles are present if not removes them
			if(tile.isPresent()==false || tile==null ) {
				moveableTiles.remove(tile);
			}
			else {
				System.out.println("Tiles you can move to:" + tile.getName() + i +"("+i+")");	//prints the tiles you can move to, Is this bad practice
				i++;
			}
		}
		return moveableTiles;
	}

}
