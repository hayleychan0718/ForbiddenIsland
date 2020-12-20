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
	private static FloodSetup floodSetup;

	/**
	 * Constructor for FloodSetup
	 */
	public FloodSetup() {
		this.floodDeck = FloodDeck.getInstance();
	}
	
    public static FloodSetup getInstance(){
        if(floodSetup == null){
        	floodSetup = new FloodSetup();
        }
        return floodSetup;
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
