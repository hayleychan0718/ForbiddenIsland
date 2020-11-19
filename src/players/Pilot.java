package players;

import java.util.LinkedList;

import island.board.Board;
import island.board.Tile;
import pawns.Pawn;

public class Pilot extends Player {

	private static Pilot thePilot;

	private Pilot(String playerName, int playerNumber) {
		super(playerName,playerNumber); //player class constructor
		Board board = Board.getInstance();
		Pawn playerPawn=new Pawn(board.getTile("Fool's Landing")); //Gets the Bronze Gate Tile and sets it as pawn starter tile
		//Hand constructed in super class(player)

	}

	public static Pilot getInstance(String playerName, int playerNumber) {
		if(thePilot == null) {
			thePilot = new Pilot(playerName ,playerNumber);
		}
		return thePilot;
	}

	public static Pilot returnInstance() {
		return thePilot;
	}
	
	public LinkedList<Tile> getFocredMoveableTile(){ //overridden as explorer cans swim diagonal tiles
		int i=0;
		LinkedList<Tile> moveableTiles = //How ot get just a list of tiles
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
