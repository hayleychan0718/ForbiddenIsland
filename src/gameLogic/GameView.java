package gameLogic;

import java.util.ArrayList;
import java.util.Scanner;

import players.Player;
import utility.Utility;

public class GameView {

	//private GameView gameView;
	private GameController controller;
	private static GameView gameView = null;

	public static GameView getInstance() {
		if(gameView==null)
			gameView = new GameView();
		return gameView;
	}


	public  void startGame() {
		System.out.println("Welcome to Forbidden Island");
	}

	public void gameOver() {
		System.out.println("Game Over!");
	}

	public void playerTurn(Player player) {
		System.out.println("\nIt is now " + player + " Turn" );
	}

	public void sunkenPlayerEnding(Player player) {
		System.out.println(player + " has been sunk and cannot move \n");
		gameOver();

	}


	public void doTurn(Scanner inputScanner) {
		boolean isTurnOver;
		ArrayList<Player> playerList = controller.getListOfPlayers();
		PlayerView playerView = PlayerView.getInstanace();

		for(Player player:playerList) { //Loops through the players
			isTurnOver=false;
			controller.reStockActions(player);
			sunkenPlayers(inputScanner); //Takes care of sunken Players
			playerTurn(player);
			Utility.sleep(1500);
		
			while (!isTurnOver) { //Does the turn
				playerView.printOptions();
				playerView.selectOption(inputScanner, player);	//Select One of the printed options
				Utility.sleep(1500);
				isTurnOver=controller.isTurnOver(player);	//View uses controller to check if the player still has actions
			} 
		}
	}

	public void sunkenPlayers(Scanner inputScanner) {	//SunkenPlayers
		ArrayList<Player> listSunkenPlayers = PlayerObserver.getInstance().getSunkenPlayers();
		PlayerView playerView = PlayerView.getInstanace();
		PlayerController playerController = PlayerController.getInstance();

		if(listSunkenPlayers.isEmpty()) return; //No players are sunk
		System.out.println("Test");
		System.out.println(listSunkenPlayers);
		for(Player player: listSunkenPlayers) {
			if(playerController.getForcedMovementTiles(player).isEmpty()) { //This is talking to model
				sunkenPlayerEnding(player);
				gameOver();
				controller.gameOver();
			}
			playerView.doForcedMovement(inputScanner, player);
		}
		PlayerObserver.getInstance().updateMoved();
		return;
	} 

	public void doGame(Scanner inputScanner) {

		while(!controller.isGameOver()) { //Will keep looping over tile game is over
			doTurn(inputScanner);
		}
	}

	public void setController(GameController controller) {
		this.controller=controller;
	}

	public void gameWin() {
		System.out.println("Lifting off Fool's Landing...\nYou win!");
	}

}
