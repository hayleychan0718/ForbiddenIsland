package setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.tools.DiagnosticCollector;

import gameLogic.PlayerAction;
import island.board.Board;
import players.*;
import utility.Utility;

public class PlayerSetup {

	private PlayerList playerList;
	private ArrayList<String> roleList;
	private static PlayerSetup playerSetup;

	
	public static PlayerSetup getInstance() {
		if(playerSetup == null)
			playerSetup = new PlayerSetup();
		return playerSetup;
	}
	
	private PlayerSetup() {
		playerList = PlayerList.getInstance();
		roleList = new ArrayList<String>();
		Collections.addAll(roleList, "Diver","Engineer","Explorer","Messenger","Navigator");
	}
	
	public ArrayList<String> getRoleList(){
		return roleList;
	}
	
	public void removeRole(int zero) {
		roleList.remove(zero);
	}
	
	public void shuffleRoles() {
		Collections.shuffle(roleList);
	}
	
	public void addPlayer(Player player) {
		playerList.addPlayer(player);
	}
}
 

	


