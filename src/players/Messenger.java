package players;

import java.util.ArrayList;
import java.util.LinkedList;

import island.board.Board;
import pawns.Pawn;

public class Messenger extends Player{

	private static Messenger theMessenger;

	private Messenger(String playerName, int playerNumber) {
		super(playerName,playerNumber); //player class constructor
		playerPawn=new Pawn(board.getTile("Silver Gate")); //Gets the Bronze Gate Tile and sets it as pawn starter tile
		//Hand constructed in super class(player)

	}

	public static Messenger getInstance(String playerName, int playerNumber) {
		if(theMessenger == null) {
			theMessenger = new Messenger(playerName ,playerNumber);
		}
		return theMessenger;
	}

	public static Messenger returnInstance() {
		return theMessenger;
	}
	
	@Override
	public ArrayList<Player> getPlayersForTreasureCard() { //Returns a lsit of the other players
		ArrayList<Player> otherPlayerList =   PlayerList.getInstance().getListOfOtherPlayers(playerNumber);
		//PlayerList.getInstance().printListOfPlayers();
		return otherPlayerList;
	}

}
