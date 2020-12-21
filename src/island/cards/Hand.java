package island.cards;

import java.util.ArrayList;

import island.board.Tile;


public class Hand {
	private ArrayList<Card> hand;
	
	public Hand(){
		this.hand = new ArrayList<Card>();
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
	
	/*public void removeCard(int cardIndex) {
		hand.remove(cardIndex);
	}*/
	
	//Give card to another player
	public void giveCard(Card given, Hand Recieved) {
		removeCard(given);
		Recieved.addCard(given);
	}
	
	public ArrayList<TreasureDeckCard> getPlayableCards() {
		ArrayList<TreasureDeckCard> playableCards = new ArrayList<TreasureDeckCard>();
		
		for(Card card: hand) {
			if(!(card instanceof TreasureCard)) {
				playableCards.add((TreasureDeckCard) card);
			}
		}
		return playableCards;
	}
	
	/**
	 * Returns a list of cards from the players hand that match the current treasure tile
	 * @return matchingTreasureCards
	 */
	public ArrayList<Card> matchingTreasureCards(Tile pawnTile) {
		ArrayList<Card> matchingTreasureCards = new ArrayList <Card>();

		for(Card cardInHand:getCards()) {
			if(cardInHand.getName() == pawnTile.getTreasure().getString()) { //may need null check before 
				matchingTreasureCards.add(cardInHand);
			}
		}
		return matchingTreasureCards;
	}
}