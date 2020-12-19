package setup;

import java.util.List;

import island.cards.Card;
import island.cards.TreasureDeck;
import island.cards.WaterRiseCard;
import players.Player;
import players.PlayerList;

public class HandSetup {
	/*
	 * Instance variables
	 */
	private TreasureDeck treasureDeck;
	private List<Player> listOfPlayers;
	private static HandSetup handSetup;
	
	public static HandSetup getInstance() {
		if(handSetup == null)
			handSetup = new HandSetup();
		return handSetup;
	}
	
	
	public HandSetup() {
		this.treasureDeck = TreasureDeck.getInstance();
		this.listOfPlayers = PlayerList.getInstance().getListOfPlayers();
	}
	
	/**
	 * Give 2 cards to each player at the beginning of the game
	 */
	public void startGame() {
		for(Player player: listOfPlayers) {
			for(int j=0; j<2; j++) {
				Card card = treasureDeck.getStack().pop();
				while(card instanceof WaterRiseCard) {
					treasureDeck.getDiscardPile().add(card);
					card = treasureDeck.getStack().pop();
				}
				player.getHand().addCard(card);
			}
		}
	}
	
}
