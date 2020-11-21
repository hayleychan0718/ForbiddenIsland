package island.cards;

import gameLogic.WaterMeter;

public class WaterRiseCard extends TreasureDeckCard{

	public WaterRiseCard() {
		super.name = "Water Rise";
	}
	
	public void play() {
		WaterMeter waterMeter = WaterMeter.getinstance();
		FloodDeck floodDeck = FloodDeck.getInstance();

		waterMeter.increaseWaterLevel();
		floodDeck.reshuffle();
	}
	
}
