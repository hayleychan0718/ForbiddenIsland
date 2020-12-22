package island.cards;

import java.util.ArrayList;

import island.board.Tile;
import players.Player;

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
}
