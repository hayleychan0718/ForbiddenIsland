package island.cards;

import java.util.ArrayList;

import gameLogic.WaterMeter;
import island.board.Tile;
import players.Player;
import players.PlayerList;

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

    /**
     * Get list of players to move with the helicopter card
     * @return List of players that can move
     */
	public ArrayList<Player> playersForHelicopter() {
		return PlayerList.getInstance().getPlayersForHelicopter();
	}

	/**
	 * Get the player's playable cards i.e. sandbag and helicopter cards
	 * @param player The player
	 * @return List of cards that player can play
	 */
	public ArrayList<TreasureDeckCard> getPlayableCards(Player player) {
		return player.getHand().getPlayableCards();
	}

	/**
	 * Gets the water meter
	 * @return Instance of water meter
	 */
	public WaterMeter getWaterMeter() {
		return WaterMeter.getinstance();
	}
}
