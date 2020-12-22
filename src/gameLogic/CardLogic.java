package gameLogic;
import java.util.ArrayList;

import island.board.Board;
import island.board.Tile;
import island.cards.HelicopterCard;
import island.cards.SandbagCard;
import island.cards.WaterRiseCard;
import island.enums.TreasureNames;
import players.Player;
import players.PlayerList;

/**
 * Card model for MVC
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class CardLogic {
	/*
	 * Instance variables
	 */
    private static CardLogic cardLogic = null;

    /**
     * Get instance of the CardLogic class
     * @return cardLogic instance
     */
    public static CardLogic getInstance() {
    	if(cardLogic == null) {
    		cardLogic = new CardLogic();
    	}
    	return cardLogic;
    }	
	
    /**
     * Play the helicopter card to move player(s) to a tile
     * @param chosenTile The tile to move to
     * @param chosenPlayers The chosen player(s)
     */
	public void playHelicopter(Tile chosenTile, ArrayList<Player> chosenPlayers) {
		HelicopterCard.play(chosenTile, chosenPlayers);
	}
	
	/**
	 * Checks if you can win the game with the helicopter card
	 * @return True if you can win the game, false otherwise
	 */
	public boolean canWinHelicopter() {
		ArrayList<Player> playersList = PlayerList.getInstance().getListOfPlayers();
        TreasureNames treasureNames[] = TreasureNames.values();
		int onFoolsLanding = 0, treasuresCaptured = 0;
		for(Player player: playersList) {
			if(player.getPlayerPawnTile().getNameString() == "Fool's Landing") {
				onFoolsLanding++;
			}
		}
		for(TreasureNames treasure: treasureNames) {
			if(treasure.isCaptured())
				treasuresCaptured++;
		}
		if(onFoolsLanding == playersList.size() && treasuresCaptured == TreasureNames.values().length)
			return true;
		else 
			return false;
	}
	
    /**
     * Win the game with the helicopter card
     */
	public void winHelicopter() {
		GameView.getInstance().gameWin();
	}
	
	/**
	 * Play the sandbag card
	 */
	public void playSandbag(Tile chosenTile) {
		SandbagCard.play(chosenTile);
	}
	
	/**
	 * Play the water rise card
	 */
	public void doWaterRise() {
		WaterRiseCard.play();
	}

	/**
	 * Get the list of tiles on the board
	 * @return List of present tiles on the board
	 */
	public ArrayList<Tile> getListOfTiles() {
		return Board.getInstance().listOfTiles();
	}

	/**
	 * Get the list of flooded tiles
	 * @return List of tiles that are flooded
	 */
	public ArrayList<Tile> getListOfFloodedTiles() {
		return Board.getInstance().listOfFloodedTiles();
	}
}