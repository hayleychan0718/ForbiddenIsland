/**
 * This WaterMeter Class is implements the Water Meter features in the game
 */

package gameplay;

public class WaterMeter {
	private int waterLevel;
	
	public WaterMeter() {
		waterLevel = 1;// User input
	}
	
	public WaterMeter(int waterLevel) {
		this.waterLevel = waterLevel;
	}
	
	public void increaseWaterLevel() {
		waterLevel++;
	}
	
	public int getWaterLevel() {
		return waterLevel;
	}
}
