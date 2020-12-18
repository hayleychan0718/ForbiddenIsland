package island.cards;

import island.enums.TreasureNames;

public class TreasureCard extends Card{
	private final TreasureNames Tname;

	public TreasureCard(TreasureNames name) {
		this.Tname = name;
		//super.name = "Treasure";
	}
	
	public String getName() {
		return Tname.getString();
	}
}
