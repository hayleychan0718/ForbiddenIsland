package island.cards;

import island.enums.TreasureNames;

/**
 * TreasureCard class 
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class TreasureCard extends Card{
	/*
	 * Instance variables
	 */
	private final TreasureNames Tname;

	/**
	 * TreasureCard constructor
	 * @param name TreasureNames 
	 */
	public TreasureCard(TreasureNames name) {
		this.Tname = name;
	}
	
	/**
	 * Get the name of the treasure
	 */
	public String getName() {
		return Tname.getString();
	}
	
	/**
	 * String of the treasure card
	 */
	@Override
	public String toString() {
		return this.getName();
	}
}
