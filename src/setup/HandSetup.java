package setup;

import java.util.List;

import island.cards.Card;
import island.cards.TreasureDeck;
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
			handSetup = new PlayerSetup();
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
		for(Player player:listOfPlayers) {
			for(int i=0; i<2; i++) {
				Card card = treasureDeck.getStack().pop();
				player.getHand().addCard(card);
			}
		}
	}
	
}
