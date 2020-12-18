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

	
	/**
	 * Constructor for HandSetup
	 */
	public HandSetup() {
		this.treasureDeck = TreasureDeck.getInstance();
		this.listOfPlayers = PlayerList.getInstance().getListOfPlayers();
	}
	
	/**
	 * Give 2 cards to each player at the beginning of the game
	 */
	public void startGame() {
		for(int i=0;i<listOfPlayers.size();i++) {
			for(int j=0; j<2; j++) {
				Card card = treasureDeck.getStack().pop();
				listOfPlayers.get(i).getHand().addCard(card);
			}
		}
	}
	
}
