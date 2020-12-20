package players;

import java.util.ArrayList;
import java.util.LinkedList;

import island.board.Board;
import pawns.Pawn;

/**
 * Singleton class to implement the Messenger extends player
 * @author Liam Fitzgerald
 *
 */

public class Messenger extends Player{

	private static Messenger theMessenger;

	/**
	 * Constructed using base Player class and Pawn is constructed on "Silver Gate"
	 * @param playerName
	 * @param playerNumber
	 * @param symbol represents player on the board
	 */
	private Messenger(String playerName, int playerNumber, String symbol) {
		super(playerName,playerNumber, symbol); //player class constructor
		playerPawn=new Pawn(board.getTile("Silver Gate")); //Gets the Bronze Gate Tile and sets it as pawn starter tile
		//Hand constructed in super class(player)

	}

	public static Messenger getInstance(String playerName, int playerNumber, String symbol) {
		if(theMessenger == null) {
			theMessenger = new Messenger(playerName ,playerNumber, symbol);
		}
		return theMessenger;
	}

	public static Messenger returnInstance() {
		return theMessenger;
	}
	
	@Override
	/**
	 * Override to allow the messenger to give card to any player
	 */
	public ArrayList<Player> getPlayersForTreasureCard() { 
		ArrayList<Player> otherPlayerList =   PlayerList.getInstance().getListOfOtherPlayers(playerNumber);
		return otherPlayerList;
	}

}
