package island.cards;

import java.util.ArrayList;

import gameLogic.GameView;
import island.board.Board;
import island.board.Tile;
import island.enums.TreasureNames;
import players.Player;
import players.PlayerList;

/**
 * Helicopter Card subclass of TreasureDeckCard
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class HelicopterCard extends TreasureDeckCard{
	/**
	 * Helicopter card constructor
	 */
	public HelicopterCard() {
		super.name = "Helicopter Lift";
	}

	/**
	 * Play a helicopter card. Move chosen player(s) to a chosen tile on the board.
	 * @param chosenTile The tile to move to
	 * @param chosenPlayers The players to move
	 */
	public static void play(Tile chosenTile, ArrayList<Player> chosenPlayers) {
		for (Player player: chosenPlayers) {
			player.movePlayerPawn(chosenTile);
		}
	}

	/**
	 * Checks if you can win the game with the helicopter card
	 * @return True if you can win the game, false otherwise
	 */
	public static boolean canWinHelicopter() {
		ArrayList<Player> playersList = PlayerList.getInstance().getListOfPlayers();
		TreasureNames treasureNames[] = TreasureNames.values();
		for(Player player: playersList) {
			if(player.getPlayerPawnTile().getNameString() != "Fool's Landing") 
				return false;
		}
		for(TreasureNames treasure: treasureNames) {
			if(!treasure.isCaptured())
				return false;
		}
		return true;
	}

	/**
	 * Return a list of options for the helicopter card
	 * @return List of tiles that are present on the board
	 */
	public static ArrayList<Tile> helicopterOptions(){
		return Board.getInstance().listOfPresentTiles();
	}
}
