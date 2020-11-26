package island.cards;

import gameLogic.WaterMeter;

public class WaterRiseCard extends TreasureDeckCard{

	public WaterRiseCard() {
		super.name = "Water Rise";
	}
	
	public void play() {
		System.out.println("Play Water Rise card...");
		WaterMeter waterMeter = WaterMeter.getinstance();
		FloodDeck floodDeck = FloodDeck.getInstance();

		waterMeter.increaseWaterLevel();
		System.out.println("Water Level increased.\nCurrent water level: " + waterMeter.getWaterLevel());
		floodDeck.reshuffle();
		System.out.println("Flood Deck reshuffled.");
	}
	
}
