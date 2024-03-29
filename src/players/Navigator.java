package players;

import pawns.Pawn;

/**
 * Singleton class to implement the Navigator extends Player
 * @author Liam Fitzgerald
 *
 */

public class Navigator extends Player {

	private static Navigator theNavigator;

	/**
	 * Constructed using base Player class and Pawn is constructed on "Golden Gate"
	 * @param playerName
	 * @param playerNumber
	 * @param symbol represents player on the board
	 */
	private Navigator(String playerName, int playerNumber, String symbol) {
		super(playerName,playerNumber, symbol); 
		playerPawn=new Pawn(board.getTile("Gold Gate")); 

	}

	/**
	 * Gets the instance of the Navigator
	 * @param playerName
	 * @param playerNumber
	 * @param symbol
	 * @return theNavigator
	 */
	public static Navigator getInstance(String playerName, int playerNumber, String symbol) {
		if(theNavigator == null) {
			theNavigator = new Navigator(playerName ,playerNumber, symbol);
		}
		return theNavigator;
	}

}
