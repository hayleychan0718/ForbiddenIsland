package island.cards;

import island.enums.TreasureNames;

public class TreasureCard extends TreasureDeckCard{
	private final TreasureNames name;

	public TreasureCard(TreasureNames name) {
		this.name = name;
	}
	
	public void play() {
		
	}
	
	public String getName() {
		return name.getString();
	}
}
