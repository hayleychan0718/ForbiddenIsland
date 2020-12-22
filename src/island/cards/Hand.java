package island.cards;

import java.util.ArrayList;

import island.board.Tile;

/**
 * Hand class representing a player's hand of cards
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class Hand {
	/*
	 * Instance variable
	 */
	private ArrayList<Card> hand;
	
	/**
	 * Hand constructor
	 */
	public Hand(){
		this.hand = new ArrayList<Card>();
	}
	
	/**
	 * Add a card to the hand
	 * @param card The card to be added to the hand
	 */
	public void addCard(Card card){
		hand.add(card);
	}
	
	/**
	 * Get the list of cards in the player's hand
	 * @return List of cards in hand
	 */
	public ArrayList<Card> getCards(){
		return hand;
	}
	
	/**
	 * Remove a card from the player's hand
	 * @param card The card to be removed
	 */
	public void removeCard(Card card) {
		hand.remove(card);
		TreasureDeck.getInstance().getDiscardPile().add(card);
	}
	
	/**
	 * Give card to another player
	 * @param given Card to give
	 * @param Recieved The other player's hand
	 */
	public void giveCard(Card given, Hand Recieved) {
		removeCard(given);
		Recieved.addCard(given);
	}
	
	/**
	 * Get the playable cards in player's hand i.e. Sandbag and Helicopter cards
	 * @return List of playable cards
	 */
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