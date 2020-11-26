package island.cards;

import island.enums.TreasureNames;

public class TreasureCard extends TreasureDeckCard{
	private final TreasureNames Tname;

	public TreasureCard(TreasureNames name) {
		this.Tname = name;
		super.name = "Treasure";
	}
	
	public void play() {
		System.out.println("Cannot play this card by itself!\n");
	}
	
	public String getName() {
		return Tname.getString();
	}
}
