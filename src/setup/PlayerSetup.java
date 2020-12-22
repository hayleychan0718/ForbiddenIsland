package setup;

import java.util.ArrayList;
import java.util.Collections;
import players.*;

/**
 * Singleton Model used for the player set up at the players at he start of the game 
 * @author Liam Fitzgerald and Hayley Chan
 *
 */

public class PlayerSetup {

	private PlayerList playerList;
	private ArrayList<String> roleList;
	private static PlayerSetup playerSetup;

	/**
	 * Gets the instance of the PlayerSetup
	 * @return playerSetup
	 */
	public static PlayerSetup getInstance() {
		if(playerSetup == null)
			playerSetup = new PlayerSetup();
		return playerSetup;
	}
	
	/**
	 * Constructs a list of all the possible roles and constructs the playerList
	 */
	private PlayerSetup() {
		playerList = PlayerList.getInstance();
		roleList = new ArrayList<String>();
		Collections.addAll(roleList, "Diver","Engineer","Explorer","Messenger","Navigator", "Pilot");
	}
	
	/**
	 * Gets the list of available roles
	 * @return rolelist
	 */
	public ArrayList<String> getRoleList(){
		return roleList;
	}
	
	public void removeRole(int zero) {
		roleList.remove(zero);
	}
	
	/**
	 * Shuffles the role list
	 */
	public void shuffleRoles() {
		Collections.shuffle(roleList);
	}
	
	public void addPlayer(Player player) {
		playerList.addPlayer(player);
	}
	
	public ArrayList<Player> getPlayerList(){
		return playerList.getListOfPlayers();
	}
	
	//Gets each of the possible roles
	public Diver getDiverInstance(String playerName, int playerNumber, String symbol) {
		return Diver.getInstance(playerName, playerNumber, symbol);
	}
	public Engineer getEngineerInstance(String playerName, int playerNumber, String symbol) {
		return Engineer.getInstance(playerName, playerNumber, symbol);
	}
	public Explorer getExplorerInstance(String playerName, int playerNumber, String symbol) {
		return Explorer.getInstance(playerName, playerNumber, symbol);
	}
	public Messenger getMessengerInstance(String playerName, int playerNumber, String symbol) {
		return Messenger.getInstance(playerName, playerNumber, symbol);
	}
	public Navigator getNavigatorInstance(String playerName, int playerNumber, String symbol) {
		return Navigator.getInstance(playerName, playerNumber, symbol);
	}
	public Pilot getPilotInstance(String playerName, int playerNumber, String symbol) {
		return Pilot.getInstance(playerName, playerNumber, symbol);
	}
}
 

	


