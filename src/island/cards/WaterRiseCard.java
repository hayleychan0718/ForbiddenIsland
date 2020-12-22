package island.cards;

import gameLogic.WaterMeter;

/**
 * WaterRiseCard subclass of TreasureDeckCard
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class WaterRiseCard extends TreasureDeckCard{

	/**
	 * WaterRiseCard constructor
	 */
	public WaterRiseCard() {
		super.name = "Water Rise";
	}
	
	/**
	 * Plays the water rise card. Increases the water meter level and reshuffles the discard flood pile
	 * and adds cards back to the flood deck
	 */
	public static void play() {
		WaterMeter waterMeter = WaterMeter.getinstance();
		FloodDeck floodDeck = FloodDeck.getInstance();

		waterMeter.increaseWaterLevel();
		floodDeck.reshuffle(floodDeck.getStack(), floodDeck.getDiscardPile());
	}
	
}
