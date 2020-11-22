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
	
	public void removeCard(TreasureDeckCard card) {
		hand.remove(card);
	}
	
	public void printHand() {
		int i =0;
		System.out.println("Your Hand is as folllows: ");
		for(Card cardInHand:hand) {
			System.out.println(hand.get(cardInHand).getName +  "[" + i + "]");
			i++;
		}
	}
	
	public void giveCard(TreasureDeckCard given, Hand recieved) {
		this.hand.remove(given); //why doesn't remove card work
		recieved.addCard(given);
	}
	
	//Need list of playable cards return the lost
	//Also print them out and show there index like what i have doene for moveable tiles in player class
	
	//discard list of cards
}
