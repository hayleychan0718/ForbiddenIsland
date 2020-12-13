package setup;

import java.util.ArrayList;
import java.util.Collections;

import players.Player;

public class PlayerSetupController {

	private PlayerSetup model;
	
	private static PlayerSetupController controller = null;
	
	public static PlayerSetupController getInstance(PlayerSetup model, PlayerSetupView view) {
		if(controller ==null)
			controller = new PlayerSetupController(model, view);
		return controller;
	}
	
	private PlayerSetupController(PlayerSetup model, PlayerSetupView view) {
		this.model=model;
		this.view=view;
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
}
