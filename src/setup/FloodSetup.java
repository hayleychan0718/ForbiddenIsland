package setup;

import island.board.Board;
import island.board.Tile;
import island.cards.FloodCard;
import island.cards.FloodDeck;

/**
 * Setting up the game. Flooding 6 tiles at the start of game.
 * @author Hayley Chan and Liam Fitzgerald
 * 
 */
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
	
	/**
	 * Creating and instance of the flood setup
	 * @return
	 */
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
