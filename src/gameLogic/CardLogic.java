package gameLogic;

import java.util.ArrayList;

import island.cards.Hand;
import island.cards.HelicopterCard;
import island.cards.SandbagCard;
import island.cards.TreasureCard;
import island.cards.TreasureDeckCard;
import island.cards.WaterRiseCard;
import island.enums.TreasureNames;
import players.Player;

public class CardLogic {

	private Player player;
	private Hand playerHand;
	
	public CardLogic(Player player) {
		this.player=player;
		playerHand=player.getHand();
		
	}
	
	public void pickCard() {
	//	int userInput();
		ArrayList<TreasureDeckCard> playableCards = playerHand.getCards();
		int picking = 0;

		do{
			System.out.println("Pick one of the cards you wish to play");
		//	System.out.println("/n enter" + playableCards.size() + "[Return] to cancel action"); // Hayley: Not sure what this is doing
			playerHand.printHand();
			int userInput = PlayerAction.acceptableInput(0, playableCards.size());
			
		//	if(userInput==playableCardsPlayers.size()) return; 	//cancels action // Hayley: not sure what this does
			
			switch(playableCards.get(userInput).getName()) {
			case "Water Rise":
				playableCards.get(userInput).play();
				playerHand.removeCard(playableCards.get(userInput));		
				break;
			case "Helicopter":
				playableCards.get(userInput).play();
				doHelicopter();
				playerHand.removeCard(playableCards.get(userInput));		
				break;
			case "Sandbag":
				playableCards.get(userInput).play();
				doSandbag();
				playerHand.removeCard(playableCards.get(userInput));		
				break;
			case "The Crystal of Fire":
				playableCards.get(userInput).play();
				picking = 1;
				break;
			case "The Oceans Chalice":
				playableCards.get(userInput).play();
				picking = 1;
				break;
			case "The Statue of The Wind":
				playableCards.get(userInput).play();
				picking = 1;
				break;
			case "The Earth Stone":
				playableCards.get(userInput).play();
				picking = 1;
				break;
			}
		}while(picking != 0);
	}
	
	public void doHelicopter() {
		
	}
	
	public void doSandbag() {
		
	}
	
	
	/*
	 * Tests
	 */
	public static void main (String args[]) {
		Player player = new Player("Hayley", 1);
		SandbagCard card = new SandbagCard();
		Hand theHand = player.getHand();
		//theHand.printHand();
		theHand.addCard(new HelicopterCard());
		theHand.addCard(card);
		theHand.addCard(new WaterRiseCard());
		theHand.addCard(new TreasureCard(TreasureNames.TheCrystalOfFire));
		theHand.addCard(new TreasureCard(TreasureNames.TheEarthStone));

		CardLogic cl = new CardLogic(player);
		cl.pickCard();
	}
}
