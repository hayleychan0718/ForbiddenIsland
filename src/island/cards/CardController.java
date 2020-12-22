package island.cards;

import java.util.ArrayList;

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
	
    /**
     * Gets instance of the card controller
     * @param model The card model
     * @return
     */
	public static CardController getInstance() {
		if(controller == null)
			controller = new CardController();
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
    	HelicopterCard.play(chosenTile, chosenPlayers);
    }
    

    /**
     * Win the game with the helicopter card
     */
	public void winHelicopter() {
		HelicopterCard.winHelicopter();
	}
    
	/**
	 * Checks if you can win the game with the helicopter card
	 * @return True if you can win the game, false otherwise
	 */
	public boolean canWinHelicopter() {
		return HelicopterCard.canWinHelicopter();
	}
	
	/**
	 * Play the sandbag card
	 * @param chosenTile The tile to shore up
	 */
	public void doSandbag(Tile chosenTile) {
		SandbagCard.play(chosenTile);
	}
	
	/**
	 * Increment water meter level 
	 */
    public void doWaterRise() {
    	WaterRiseCard.play();
    }
    
    /**
     * Get the list of options for the sandbag card
     * @return Sandbag card options i.e. tiles that are flooded
     */
    public ArrayList<Tile> sandBagOptions(){
    	return SandbagCard.sandBagOptions();
    }
    
    /**
     * Get the list of options for the helicopter card
     * @return Helicoptor card options i.e. present tiles on the board
     */
    public ArrayList<Tile> helicopterOptions(){
    	return HelicopterCard.helicopterOptions();
    }
}
