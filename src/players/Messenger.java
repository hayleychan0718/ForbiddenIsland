package players;

import java.util.LinkedList;

import island.board.Board;
import pawns.Pawn;

public class Messenger extends Player{

	private static Messenger theMessenger;

	private Messenger(String playerName, int playerNumber) {
		super(playerName,playerNumber); //player class constructor
		Board board = Board.getInstance();
		Pawn playerPawn=new Pawn(board.getTile("Silver Gate")); //Gets the Bronze Gate Tile and sets it as pawn starter tile
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
	public LinkedList<Player> giveTreasureCard() { //Returns a lsit of the other players
		LinkedList<Player> playersForTreasureCard = new LinkedList <Player>();
		PlayerList playerList = PlayerList.getInstance();

		//How do I the player of this class
		for (Player playerInList:playerList.getListOfPlayers()) { //Need to create a list of players
			if (playerInList.getPlayerNumber()!=getPlayerNumber()) {	//Returns a list of the other players
				playersForTreasureCard.add(playerInList);
			}
		}
		return playersForTreasureCard;
	}

}
