package island.cards;

import java.util.ArrayList;


public class Hand {
	private ArrayList<Card> hand;
	private ArrayList<TreasureDeckCard> playableCards;
	
	public Hand(){
		this.hand = new ArrayList<Card>();
		this.playableCards = new ArrayList<TreasureDeckCard>();
	}
	
	public void addCard(Card card){
		hand.add(card);
	}
	
	public ArrayList<Card> getCards(){
		return hand;
	}
	
	public void removeCard(Card card) {
		hand.remove(card);
		TreasureDeck.getInstance().getDiscardPile().add(card);
	}
	
/*	public void removeCard(int cardIndex) {
		hand.remove(cardIndex);
	}*/
	
	//Give card to another player
	public void giveCard(TreasureDeckCard given, Hand Recieved) {
		removeCard(given);
		Recieved.addCard(given);
	}
	
	public ArrayList<TreasureDeckCard> getPlayableCards() {
		for(Card card: hand) {
			if(!(card instanceof TreasureCard)) {
				this.playableCards.add((TreasureDeckCard) card);
			}
		}
		return playableCards;
	}
}