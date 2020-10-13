package island.enums;

public enum TreasureTileNames {
	TempleOfTheSun ("Temple of The Sun"),
	TempleOfTheMoon ("Temple of The Moon"),
	HowlingGarden ("Howling Garden"), 
	WhisperingGarden ("Whispering Garden"),
	CaveOfShadows ("Cafe of Shadows"),
	CaveOfEmbers ("Cave of Embers"),
	CoralPalace ("Coral Palace"),
	TidalPalace ("Tidal Palace");
	
	private final String name;
	
	private TreasureTileNames(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
