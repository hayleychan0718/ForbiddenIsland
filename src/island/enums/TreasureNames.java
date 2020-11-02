package island.enums;

public enum TreasureNames {
	TheEarthStone ("The Earth Stone"),
	TheStatueOfTheWind ("The Statue of The Wind"),
	TheCrystalOfFire ("The Crystal of Fire"),
	TheOceansChalice ("The Oceans Chalice");
	
	private final String name;
	
	private TreasureNames(String name) {
		this.name = name;
	}
	
	public String getString() {
		return name;
	}
	
}
