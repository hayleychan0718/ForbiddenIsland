package island.cards;

import java.util.HashSet;
import java.util.Set;

//Need to be able to discard cards

public class Hand {
	private Set<TreasureDeckCard> hand;
	
	public Hand(){
		this.hand = new HashSet<TreasureDeckCard>();
	}
	
	public void addCard(TreasureDeckCard card){
		hand.add(card);
	}
	
	public Set<TreasureDeckCard> getCards(){
		return hand;
	}
}
