package gameLogic;

import java.util.Scanner;

import players.Player;
import players.PlayerList;

public class GameManager {


	private static GameManager theGameManager;

	private boolean isGameOver;
	private PlayerList listOfPlayers;

	//Singlton class
	private GameManager() {
		isGameOver=false;
		listOfPlayers= PlayerList.getInstance();
	}

	public static GameManager getInstance() {
		if(theGameManager == null) {
			theGameManager = new GameManager();
		}
		return theGameManager;
	}

	public void doGame(Scanner inputScanner) {

		boolean didWinGame;
		boolean didLoseGame;
		PlayerTurn currentTurn;

		while(!isGameOver) { //Will keep looping over tile game is over
			for (Player currentPlayer: listOfPlayers.getListOfPlayers()) {

				currentTurn = new PlayerTurn(currentPlayer, inputScanner);
				currentTurn.doTurn();
				didWinGame = PlayerTurn. // Check did win
				didLoseGame = //
				if(didWinGame) {
					winGame();
				}
				if(didLoseGame) {
					loseGame();
				}
			}
		}

	}

}
