package observers;

import java.util.ArrayList;
import players.Player;

/**
 * Singleton Observer class, used to observer the players tiles if sunk
 * @author Liam Fitzgerald and Hayley Chan
 *
 */

public class PlayerObserver {

	private static PlayerObserver playerObserver;
	private ArrayList<Player> sunkenPlayers;


	/**
	 * PlayerObserver Constructor
	 */
	private PlayerObserver() {
		sunkenPlayers = new ArrayList<Player>();
	}

	/**
	 * Gets instance of the PlayerObserver
	 * @return playerObserver
	 */
	public static PlayerObserver getInstance() {
		if(playerObserver==null) {
			playerObserver = new PlayerObserver();
		}
		return playerObserver;
	}

	/**
	 * Updates if a player is sunk , adds them to sunkenPlayers List
	 * @param player
	 */
	public void updateSunk (Player player) {
		sunkenPlayers.add(player);

	}

	/**
	 * Updates for when all of the players have mode , empties the list of sunken players
	 */
	public void updateMoved () {
		sunkenPlayers.removeAll(sunkenPlayers);
	}

	/**
	 * Gets the list of sunken Players
	 * @return list of sunken Players
	 */
	public ArrayList<Player> getSunkenPlayers (){
		return sunkenPlayers;
	}
}
