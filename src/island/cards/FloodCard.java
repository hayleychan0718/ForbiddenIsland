package island.cards;

import island.enums.TileNames;

public class FloodCard extends Card{
	private final TileNames name;
	
	public FloodCard(TileNames name) {
		this.name= name;
	}
	
	public String getName() {
		return name.getString();
	}
}
