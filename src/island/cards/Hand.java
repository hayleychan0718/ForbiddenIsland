package island.cards;

import java.util.ArrayList;
import java.util.Scanner;


public class Hand {
	private ArrayList<TreasureDeckCard> hand;
	
	public Hand(){
		this.hand = new ArrayList<TreasureDeckCard>();
	}
	
	public void addCard(TreasureDeckCard card){
		hand.add(card);
		if(hand.size() == 6) {
			System.out.println("Cannot have more than 5 cards in hand! Remove one.");
			printHand();
			System.out.print("\nEnter number of card you want to remove: ");
		    @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);  
		    int chosen = scanner.nextInt();  
		    hand.remove(chosen);
		    printHand();
		}
	}
	
	public ArrayList<TreasureDeckCard> getCards(){
		return hand;
	}
	
	public void removeCard(TreasureDeckCard card) {
		hand.remove(card);
	}
	
	public void printHand() {
		int index = 0;
		if(hand.size()==0) {
			System.out.println("Your hand is empty.");
		}
		else {
			System.out.println("\nYour hand: ");
			for(int i=0; i<hand.size(); i++) {
				System.out.println(index + ". " + hand.get(i).getName());
				index++;
			}
		}
	}
	
	//Give card to another player
	public void giveCard(TreasureDeckCard given, Hand Recieved) {
		removeCard(given);
		Recieved.addCard(given);
	}
	
}