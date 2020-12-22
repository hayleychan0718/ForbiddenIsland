package setup;

import java.util.ArrayList;
import players.Diver;
import players.Engineer;
import players.Explorer;
import players.Messenger;
import players.Navigator;
import players.Pilot;
import players.Player;

/**
 * Singleton class to implement the controller needed for the player set up
 * @author liam FItzgerald and Hayley Chan
 *
 */

public class PlayerSetupController {

	private PlayerSetup model;

	private static PlayerSetupController controller = null;

	/**
	 * Gets the instance of the PlayerSetupCOntroller
	 * @param model
	 * @return controller
	 */
	public static PlayerSetupController getInstance(PlayerSetup model) {
		if(controller ==null)
			controller = new PlayerSetupController(model);
		return controller;
	}

	/**
	 * COntructs the PlayerSetupController
	 * @param model
	 */
	private PlayerSetupController(PlayerSetup model) {
		this.model=model;
	}

	public ArrayList<String> getRoleList(){
		return	model.getRoleList();
	}

	public void removeRole(int zero) {
		model.removeRole(zero);
	}

	public void shuffleRoles() {
		model.shuffleRoles();
	}

	public void addPlayer(Player player) {
		model.addPlayer(player);
	}

	public ArrayList<Player> getListofPlayers() {
		return model.getPlayerList();
	}

	public Diver getDiverInstance(String playerName, int playerNumber, String symbol) {
		return model.getDiverInstance(playerName, playerNumber, symbol);
	}
	public Engineer getEngineerInstance(String playerName, int playerNumber, String symbol) {
		return model.getEngineerInstance(playerName, playerNumber, symbol);
	}
	public Explorer getExplorerInstance(String playerName, int playerNumber, String symbol) {
		return model.getExplorerInstance(playerName, playerNumber, symbol);
	}
	public Messenger getMessengerInstance(String playerName, int playerNumber, String symbol) {
		return model.getMessengerInstance(playerName, playerNumber, symbol);
	}
	public Navigator getNavigatorInstance(String playerName, int playerNumber, String symbol) {
		return model.getNavigatorInstance(playerName, playerNumber, symbol);
	}
	public Pilot getPilotInstance(String playerName, int playerNumber, String symbol) {
		return model.getPilotInstance(playerName, playerNumber, symbol);
	}

}

