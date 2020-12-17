package gameLogic;

import java.lang.reflect.Method;
import java.util.ArrayList;

import island.cards.Hand;
import island.cards.TreasureDeckCard;
import players.Player;
import utility.Utility;

public class CardView {
	public CardController controller;
	private static CardView cardView = null;

	public static CardView getInstance() {
		if(cardView == null)
			cardView = new CardView();
		return cardView;
	}

	public CardController getController() {
		return controller;
	}

	public void setController(CardController controller) {
		this.controller = controller;
	}
	
	public void pickCard(Hand playerHand, ArrayList<TreasureDeckCard> playableCards) {
		int picking = 0;
		do{
			System.out.println("Pick one of the cards you wish to play");
			playerHand.printHand();
			int userInput = Utility.acceptableInput(0, playableCards.size());
			
			//playableCards.get(userInput).play();
			controller.playCard(userInput);
			if(playableCards.get(userInput).getName() == "Treasure") {
				System.out.println("Cannot play a treasure card by itself!");
				picking = 1;
			}
			else
				//playerHand.removeCard(playableCards.get(userInput));	
				controller.removeCard(playableCards.get(userInput));
		}while(picking != 0);
	}
}


//if(card isintance of Helicopter) {
//	getlistofplayers
//	Choosingplayers
//	selected tile 
//}
//else {
//	shoreup(tiles):
//}

//Hleiocpter(){
//	select card Method();
//	printoptions(controller.getplayablecard); //Pick card
//	
//	System.out.println(card.intro()); //" YOU played helicoter card you can move to: , You played sandbag card " String intor method for both
//	
//	printoptions(card.options); // Helicopter return all non sunk  tiles , sandbag returns all flodded tiles
//	selectedTile= acceptableinput(options.size);
//	
//	
//	
//}