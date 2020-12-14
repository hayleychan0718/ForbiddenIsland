package setup;

import java.util.ArrayList;
import java.util.Collections;

import players.Player;

public class PlayerSetupController {

	private PlayerSetup model;
	
	private static PlayerSetupController controller = null;
	
	public static PlayerSetupController getInstance(PlayerSetup model) {
		if(controller ==null)
			controller = new PlayerSetupController(model);
		return controller;
	}
	
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
}
