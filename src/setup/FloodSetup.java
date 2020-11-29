package setup;

import island.board.Board;
import island.board.Tile;
import island.cards.FloodCard;
import island.cards.FloodDeck;


public class FloodSetup {
	/*
	 * Instance variables
	 */
	private FloodDeck floodDeck;
	
	/**
	 * Constructor for FloodSetup
	 */
	public FloodSetup() {
		this.floodDeck = FloodDeck.getInstance();
	}
	
	/**
	 * Flooding at start of game
	 */
	public void startGame() {
		Board board = Board.getInstance();
		for(int i=0;i<6;i++) {
			FloodCard card = floodDeck.getStack().pop();
			Tile tileToFlood = board.getTile(card.getName());
			tileToFlood.setFlood(true);
			floodDeck.getDiscardPile().push(card);
		}
	}
}
