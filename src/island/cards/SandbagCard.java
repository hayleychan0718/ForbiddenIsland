package island.cards;

import island.board.Tile;

public class SandbagCard extends TreasureDeckCard{

	public SandbagCard() {
		super.name = "Sandbag";
	}
	
	public static void play(Tile chosenTile) {
		chosenTile.setFlood(false);
	}
}
