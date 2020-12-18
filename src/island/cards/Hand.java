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
		/*if(hand.size() == 6) {
			System.out.println("Cannot have more than 5 cards in hand! Remove one.");
			printHand();
			System.out.print("\nEnter number of card you want to remove: ");
		    @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);  
		    int chosen = scanner.nextInt();  
		    hand.remove(chosen);
		    printHand();
		}*/
	}
	
	public ArrayList<Card> getCards(){
		return hand;
	}
	
	public void removeCard(TreasureDeckCard card) {
		hand.remove(card);
	}
	
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