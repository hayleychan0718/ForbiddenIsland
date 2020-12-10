/**
 * This WaterMeter Class is implements the Water Meter features in the game
 * It is a Singleton Class
 */

package gameLogic;

import observers.GameOverObserver;

public class WaterMeter {
	
	private GameOverObserver gameOverObserver;
	private static WaterMeter theWaterMeter;
	private int waterLevel;
	
	//Water level constructor sets it to the default level
	private WaterMeter() {
		this.waterLevel = 1;
	}
	
	public static WaterMeter getinstance() {
		if(theWaterMeter == null) {
			theWaterMeter = new WaterMeter();
		}
		return theWaterMeter;
	}
	
	//This allows the user to increase the water level at the beginning of the game if they want
	public void setWaterLevel(int waterLevel) {
		this.waterLevel=waterLevel;
		
	}
	
	
	public void increaseWaterLevel() {
		waterLevel++;
		// Notify observer to end game when water level has reached 5
		if(waterLevel==5) {
			gameOverObserver.update();
		}
	}
	
	public int getWaterLevel() {
		return waterLevel;
	}
}
