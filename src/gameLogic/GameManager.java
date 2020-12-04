package gameLogic;

import java.util.ArrayList;
import java.util.Scanner;

import players.Player;
import players.PlayerList;

public class GameManager {


	private static GameManager theGameManager;

	private boolean isGameOver;
	private PlayerList listOfPlayers;
	private PlayerController playerController;
	private PlayerView playerView;

	//Singlton class
	private GameManager(PlayerController playerController, PlayerView playerView) {
		isGameOver=false;
		listOfPlayers= PlayerList.getInstance();
		this.playerController=playerController;
		this.playerView=playerView;
	}

	public static GameManager getInstance(PlayerController playerController,PlayerView playerView) {
		if(theGameManager == null) {
			theGameManager = new GameManager(playerController, playerView);
		}
		return theGameManager;
	}

	public void doGame(Scanner inputScanner) {

		boolean didWinGame;
		boolean didLoseGame;
		PlayerTurn currentTurn;

		while(!isGameOver) { //Will keep looping over tile game is over
			doTurn(inputScanner);
		}
	}


	public void doTurn(Scanner inputScanner) {

		boolean isTurnOver=false;
		ArrayList<Player> playerList = playerController.getModel(); //The model is the list of players

		for(Player player:playerList) { //Loops through the players
			sunkenPlayers(inputScanner); //Takes care of sunken Players
			playerView.printStartOfTurn(player);

			while (!isTurnOver) { //Does the turn
				playerView.printOptions();
				playerView.selectOption(inputScanner, player);	//Select One of the printed options
				isTurnOver=isTurnOver(player);	//View uses controller to check if the player still has actions
			} 
		}
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

	public void sunkenPlayers(Scanner inputScanner) {
		ArrayList<Player> listSunkenPlayers = PlayerObserver.getInstance().sunkenPlayers();

		if(listSunkenPlayers.isEmpty()) return; //No players are sunk

		for(Player player: listSunkenPlayers)
			if(player.getFocredMoveableTiles().isEmpty()) {
				
				Gameover();
			}
		playerView.doForcedMovement(inputScanner, player);

		return;
	}



}
