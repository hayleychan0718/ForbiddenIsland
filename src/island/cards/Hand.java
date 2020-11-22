package island.cards;

import java.util.ArrayList;


//Need to be able to discard cards

public class Hand {
	private ArrayList<TreasureDeckCard> hand;
	
	public Hand(){
		this.hand = new ArrayList<TreasureDeckCard>();
	}
	
	// If more than 5 cards: ask player to remove one. Where do we do this?
	public void addCard(TreasureDeckCard card){
		hand.add(card);
	}
	
	public ArrayList<TreasureDeckCard> getCards(){
		return hand;
	}
	
	public void removeCard(TreasureDeckCard card) {
		hand.remove(card);
	}
	
	public void printHand() {
		int index = 1;
		System.out.println("Your hand: ");
		for(int i=0; i<hand.size(); i++) {
			System.out.println(index + ". " + hand.get(i).getName());
			index++;
		}
	}
	
	//Give card to another player
	public void giveCard(TreasureDeckCard given, Hand Recieved) {
		removeCard(given);
		Recieved.addCard(given);
	}
	
}