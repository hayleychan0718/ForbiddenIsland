package island.cards;

import island.enums.TileNames;

public class FloodCard {
	private final TileNames name;
	
	public FloodCard(TileNames name) {
		this.name= name;
	}
	
	public TileNames getName() {
		return name;
	}
}
