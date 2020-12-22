package island.cards;

import island.enums.TileNames;

/**
 * FloodCard class subclass of Card
 * @author Hayley Chan
 *
 */
public class FloodCard extends Card{
	/*
	 * Instance variable
	 */
	private final TileNames name;
	
	/**
	 * Constructor for flood card
	 * @param name Name of the tile as TileNames
	 */
	public FloodCard(TileNames name) {
		this.name= name;
	}
	
	/**
	 * Get name of the card as string
	 */
	public String getName() {
		return name.getString();
	}
}
