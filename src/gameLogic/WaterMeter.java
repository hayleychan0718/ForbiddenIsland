package gameLogic;

import observers.GameOverObserver;
/**
 * SIngleton WaterMeter Class implements the Water Meter features in the game, 
 *
 * @author Liam Fitzgerald and Hayley Chan
 */

public class WaterMeter {
	
	private GameOverObserver gameOverObserver = GameOverObserver.getInstance();
	private static WaterMeter theWaterMeter;
	private int waterLevel;
	
	/**
	 * WaterMeter constructor sets the water level to default of 1
	 */
	private WaterMeter() {
		this.waterLevel = 1;
	}
	
	/**
	 * Gets instance of WaterMeter
	 * @return the Water Meter
	 */
	public static WaterMeter getinstance() {
		if(theWaterMeter == null) {
			theWaterMeter = new WaterMeter();
		}
		return theWaterMeter;
	}
	
	/**
	 * Allows the user to specify the water level at the beginning of the game
	 * @param waterLevel
	 */
	public void setWaterLevel(int waterLevel) {
		this.waterLevel=waterLevel;
		
	}
	
	/**
	 * When called increases the water level by one, if water level is equal to 5 game over
	 *  observer is updated
	 */
	public void increaseWaterLevel() {
		waterLevel++;
		// Notify observer to end game when water level has reached 5
		if(waterLevel==5) {
			gameOverObserver.update();
		}
	}
	
	/**
	 * Gets the waterLevel
	 * @return water level
	 */
	public int getWaterLevel() {
		return waterLevel;
	}
}
