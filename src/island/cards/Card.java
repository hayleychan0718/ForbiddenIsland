package island.cards;


public abstract class Card {
	protected String name;
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return getName();
	}
	
}
