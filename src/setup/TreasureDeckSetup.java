package setup;

import java.util.List;

import island.cards.TreasureDeck;
import island.cards.TreasureDeckCard;
import players.Player;
import players.PlayerList;

public class TreasureDeckSetup {
	/*
	 * Instance variables
	 */
	private TreasureDeck treasureDeck;
	
	/**
	 * Constructor for TreasureDeckSetup
	 */
	public TreasureDeckSetup() {
		this.treasureDeck = TreasureDeck.getInstance();
	}
	
	/**
	 * Give 2 cards to each player at the beginning of the game
	 */
	public void startGame() {
		List<Player> listOfPlayers = PlayerList.getInstance().getListOfPlayers();
		for(int i=0;i<listOfPlayers.size();i++) {
			for(int j=0; j<2; j++) {
				TreasureDeckCard card = treasureDeck.getStack().pop();
				listOfPlayers.get(i).getHand().addCard(card);
			}
		}
	}
	
}
