package island.cards;

import java.util.ArrayList;

import gameLogic.GameView;
import island.board.Board;
import island.board.Tile;
import players.Player;

public class HelicopterCard extends TreasureDeckCard{
	
	public HelicopterCard() {
		super.name = "Helicopter Lift";
	}
	
	public static void play(Tile chosenTile, ArrayList<Player> chosenPlayers) {
		for (Player player: chosenPlayers) {
			player.movePlayerPawn(chosenTile);
		}
	}
}
