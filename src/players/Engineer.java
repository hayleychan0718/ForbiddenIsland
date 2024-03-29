package players;

import pawns.Pawn;

/**
 * Singleton class to implement the Engineer extends player, special ability implemented in player view
 * @author Liam Fitzgerald and Hayley Chan
 *
 */

public class Engineer extends Player {

	private static Engineer theEngineer;

	/**
	 * Constructed using base Player class and Pan is constructed on "Bronze Gate"
	 * @param playerName
	 * @param playerNumber
	 * @param symbol represents player on the board
	 */
	private Engineer(String playerName, int playerNumber, String symbol) {
		super(playerName,playerNumber, symbol); 
		playerPawn=new Pawn(board.getTile("Bronze Gate")); 
	}

	/**
	 * Gets the instance of the Engineer Class
	 * @param playerName
	 * @param playerNumber
	 * @param symbol
	 * @return
	 */
	public static Engineer getInstance(String playerName, int playerNumber, String symbol) {
		if(theEngineer == null) {
			theEngineer = new Engineer(playerName ,playerNumber, symbol);
		}
		return theEngineer;
	}

}

