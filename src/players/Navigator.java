package players;

import island.board.Board;
import pawns.Pawn;

public class Navigator extends Player {

	private static Navigator theNavigator;

	private Navigator(String playerName, int playerNumber) {
		super(playerName,playerNumber); //player class constructor
		Board board = Board.getInstance();
		Pawn playerPawn=new Pawn(board.getTile("Golden Gate")); //Gets the Bronze Gate Tile and sets it as pawn starter tile

	}

	public static Navigator getInstance(String playerName, int playerNumber) {
		if(theNavigator == null) {
			theNavigator = new Navigator(playerName ,playerNumber);
		}
		return theNavigator;
	}

	public static Navigator returnInstance() {
		return theNavigator;
	}
}
