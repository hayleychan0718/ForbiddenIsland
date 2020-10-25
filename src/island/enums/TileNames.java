package island.enums; 

//comment
public enum TileNames {
	Watchtower ("Watch Tower"),
	Observatory ("Observatory"),
	DunesOfDeception ("Dunes of Deception"),
	CliffsOfAgandon ("Cliffs of Agandon"),
	BreakersBridge ("Breakers Bridge"),
	TwilightHollow ("Twilight Hollow"),
	BronzeGate ("Bronze Gate"),
	CopperGate ("Copper Gate"),
	FoolsLanding ("Fool's Landing"),
	MistyMarsh ("Misty Marsh"),
	LostLagoon ("Lost Lagoon"),
	GoldGate ("Gold Gate"),
	CrimsonForest ("Crimson Forest"),
	IronGate ("Iron Gate"),
	SilverGate ("Silver Gate"),
	PhantomRock ("Phantom Rock"),
	TempleOfTheSun ("Temple of The Sun"),
	TempleOfTheMoon ("Temple of The Moon"),
	HowlingGarden ("Howling Garden"), 
	WhisperingGarden ("Whispering Garden"),
	CaveOfShadows ("Cafe of Shadows"),
	CaveOfEmbers ("Cave of Embers"),
	CoralPalace ("Coral Palace"),
	TidalPalace ("Tidal Palace");
	
	private final String name;
	
	private TileNames(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
