package island.cards;

import java.util.ArrayList;

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
	
	public static boolean canWinHelicopter() {
		ArrayList<Player> playersList = PlayerList.getInstance().getListOfPlayers();
		TreasureNames treasureNames[] = TreasureNames.values();
		Boolean winCondition = true;

		while(winCondition ==true) {
			for(Player player: playersList) {
				if(player.getPlayerPawnTile().getNameString() != "Fool's Landing") {
					winCondition = false;
				}
			}
			for(TreasureNames treasure: treasureNames) {
				if(!treasure.isCaptured())
					winCondition = false;
			}
		}
		return winCondition;
	}
	
	public static ArrayList<Tile> helicopterOptions(){
		return Board.getInstance().listOfPresentTiles();
	}
}
