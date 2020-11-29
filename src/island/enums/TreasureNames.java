package island.enums;

public enum TreasureNames {
	TheEarthStone ("The Earth Stone", false),
	TheStatueOfTheWind ("The Statue of The Wind", false),
	TheCrystalOfFire ("The Crystal of Fire", false),
	TheOceansChalice ("The Oceans Chalice", false);
	
	private final String name;
	private boolean captured;
	
	/**
	 * Constructor for TreasureNames enum
	 * @param name Name of the treasure
	 * @param captured True if treasure is captured, false otherwise
	 */
	private TreasureNames(String name, boolean captured) {
		this.name = name;
		this.captured = captured;
	}
	
	/**
	 * Get string of the Treasure name
	 * @return String name, name of the treasure
	 */
	public String getString() {
		return name;
	}
	
	/**
	 * Checks if treasure has been captured
	 * @return True if treasure is captured, false otherwise
	 */
	public boolean isCaptured() {
		return this.captured;
	}
	
	/**
	 * Capture the treasure
	 */
	public void captureTreasure() {
		this.captured = true;
	}
}
