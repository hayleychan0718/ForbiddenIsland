package gameLogic;

import island.cards.Hand;
import players.Player;

public class CardLogic {

	private Player player;
	private Hand playerHand;
	
	public CardLogic(Player player) {
		this.player=player;
		playerHand=player.getHand();
		
	}
	
	public void pickCard() {
		int userInput();
		playeableCards = playerHand.getPlayablecards();
		
		System.out.println("Pick one of the cards you wish to play");
		System.out.println("/n enter" + playableCards.size() + "[Return] to cancel action");
		PlayerAction.acceptableInput(0, playableCards.size());
		
		if(userInput==playableCardsPlayers.size()) return; 	//cancels action
		
		switch//switch depending on card
		
		
		
		
	

		
		
	}
	
	public void helicopterList() {
		
	}
	
	public void sandbag() {
		
	}
	
	
	
}
