package gameLogic;

import java.util.ArrayList;
import java.util.Scanner;

import players.Player;
import players.PlayerList;

public class GameManager {


	private static GameManager theGameManager;

	private boolean isGameOver;
	private PlayerList playerList;

	
	
	private GameManager() {
		isGameOver=false;
		playerList= PlayerList.getInstance();
	}

	public static GameManager getInstance() {
		if(theGameManager == null) {
			theGameManager = new GameManager();
		}
		return theGameManager;
	}


	public boolean isTurnOver(Player player) {
		int remainingPlayerActions = player.getPlayerActions();

		if(remainingPlayerActions==0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void gameOver() {
		isGameOver=true;
	}
	
	//May remove
	public ArrayList<Player> getListOfPlayers(){
		return playerList.getListOfPlayers();
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}


}
