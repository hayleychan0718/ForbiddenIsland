package players;

import java.util.ArrayList;
import java.util.LinkedList;

import island.board.Board;
import island.board.Tile;
import pawns.Pawn;

public class Pilot extends Player {

	private static Pilot thePilot;

	private Pilot(String playerName, int playerNumber) {
		super(playerName,playerNumber); //player class constructor
		playerPawn=new Pawn(board.getTile("Fool's Landing")); //Gets the Bronze Gate Tile and sets it as pawn starter tile
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
	
	public ArrayList<Tile> getFocredMoveableTile(){ //overridden as explorer cans swim diagonal tiles
		int i=0;
		
		ArrayList<Tile> moveableTiles = board.listOfTiles();
		
		return moveableTiles;
	}

}
