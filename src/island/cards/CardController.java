package island.cards;

import java.util.ArrayList;

import gameLogic.CardLogic;
import island.board.Tile;
import players.Player;

/**
 * Card controller MVC
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class CardController {
	/*
	 * Instance variables
	 */
	private static CardController controller = null;
	private CardLogic model;
	
	/**
	 * CardController constructor
	 * @param model The card model
	 */
    private CardController(CardLogic model) {
    	this.model = model;        
    }
	
    /**
     * Gets instance of the card controller
     * @param model The card model
     * @return
     */
	public static CardController getInstance(CardLogic model) {
		if(controller == null)
			controller = new CardController(model);
		return controller;
	}
	
	/**
	 * Gets the hand of the player
	 * @param player The player
	 * @return The player's hand
	 */
	public Hand getPlayerHand(Player player) {
		return player.getHand();
	}

	/**
	 * Remove card from player's hand
	 * @param card The card to be removed
	 * @param player The player
	 */
    public void removeCard(Card card, Player player) {
		player.getHand().removeCard(card);
    }
	
    /**
     * Play the helicopter card
     * @param chosenTile The tile to move to 
     * @param chosenPlayers The player(s) moving to the tile
     */
    public void doHelicopter(Tile chosenTile, ArrayList<Player> chosenPlayers){
    	model.playHelicopter(chosenTile, chosenPlayers);
    }
    
    /**
     * Win the game with the helicopter card
     */
	public void winHelicopter() {
		model.winHelicopter();
	}
    
	/**
	 * Checks if you can win the game with the helicopter card
	 * @return True if you can win the game, false otherwise
	 */
	public boolean canWinHelicopter() {
		return model.canWinHelicopter();
	}
	
	/**
	 * Play the sandbag card
	 * @param chosenTile The tile to shore up
	 */
	public void doSandbag(Tile chosenTile) {
		model.playSandbag(chosenTile);
	}
	
	/**
	 * Increment water meter level 
	 */
    public void doWaterRise() {
    	model.doWaterRise();
    }

    /**
     * Returns the list of tiles on the board
     * @return List of present tiles 
     */
	public ArrayList<Tile> getListOfTiles() {
		return model.getListOfTiles();
	}

	/**
	 * Returns the list of flooded tiles
	 * @return List of tiles that are flooded
	 */
	public ArrayList<Tile> getListOfFloodedTiles() {
		return model.getListOfFloodedTiles();
	}
}
