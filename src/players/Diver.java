package players;

import island.board.Board;
import pawns.Pawn;
//Singlton class
public class Diver extends Player {

	private static Diver theDiver;

	private Diver(String playerName, int playerNumber) {
		super(playerName,playerNumber); //player class constructor
		playerPawn=new Pawn(board.getTile("Iron Gate")); //Gets the Bronze Gate Tile and sets it as pawn starter tile
		//Hand constructed in super class(player)

	}



	public static Diver getInstance(String playerName, int playerNumber) {
		if(theDiver == null) {
			theDiver = new Diver(playerName ,playerNumber);
		}
		return theDiver;
	}

	public static Diver returnInstance() {
		return theDiver;
	}

}
