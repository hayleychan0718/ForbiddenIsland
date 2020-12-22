package setup;

import java.util.List;

import island.cards.Card;
import island.cards.TreasureDeck;
import island.cards.WaterRiseCard;
import players.Player;
import players.PlayerList;

/**
 * Setting up each player's hand at the start of the game. Each player has 2 cards from the 
 * treasure deck at start of the game.
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class HandSetup {
	/*
	 * Instance variables
	 */
	private TreasureDeck treasureDeck;
	private List<Player> listOfPlayers;
	private static HandSetup handSetup;
	
	/**
	 * Creaing instance of hand setup
	 * @return
	 */
	public static HandSetup getInstance() {
		if(handSetup == null)
			handSetup = new HandSetup();
		return handSetup;
	}
	
	/**
	 * Hand setup constructor
	 */
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
