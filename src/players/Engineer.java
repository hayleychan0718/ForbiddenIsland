package players;

import island.board.Board;
import pawns.Pawn;

//ability will be implemented by game logic

public class Engineer extends Player {

	private static Engineer theEngineer;

	private Engineer(String playerName, int playerNumber) {
		super(playerName,playerNumber); //player class constructor
		Board board = Board.getInstance();
		Pawn playerPawn=new Pawn(board.getTile("Bronze Gate")); //Gets the Bronze Gate Tile and sets it as pawn starter tile
		//Hand constructed in super class(player)
	}



	public static Engineer getInstance(String playerName, int playerNumber) {
		if(theEngineer == null) {
			theEngineer = new Engineer(playerName ,playerNumber);
		}
		return theEngineer;
	}

	public static Engineer returnInstance() {
		return theEngineer;
	}

}

