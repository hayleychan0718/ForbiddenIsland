package island.cards;

/**
 * Abstract card class
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public abstract class Card {
	/*
	 * Instance variables
	 */
	protected String name;
	
	/**
	 * Gets the name of the card
	 * @return Name of the card
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns name of card as a string
	 */
	@Override
	public String toString() {
		return getName();
	}
	
}
