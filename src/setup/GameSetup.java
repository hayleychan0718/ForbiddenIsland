package setup;

import java.util.Scanner;

import gameLogic.GameView;
import gameLogic.*;
import island.board.Board;
import island.board.Tile;
import island.enums.TileNames;
import players.*;

public class GameSetup {

	private static GameSetup gameSetup=null;

	public static GameSetup getInstance() {
		if(gameSetup == null) {
			gameSetup = new GameSetup();
		}
		return gameSetup;
	}


	public void setupGame() {
		Scanner inputScanner = new Scanner(System.in);
		Board.getInstance();
		playerSetup(inputScanner);
		HandSetup.getInstance().startGame();
		FloodSetup.getInstance().startGame();
		playerActions();
		gameManagerSetup(inputScanner);
	}

	public void playerSetup(Scanner inputScanner) {
		PlayerSetup model = PlayerSetup.getInstance();

		PlayerSetupController controller = PlayerSetupController.getInstance(model);

		PlayerSetupView view = PlayerSetupView.getInstance();

		view.setController(controller);

		view.createPlayers(inputScanner);
	}

	public void playerActions() {
		PlayerController controller = PlayerController.getInstance();
		
		PlayerView view = PlayerView.getInstanace();
		
		view.setController(controller);
	}
	
	
	public void gameManagerSetup(Scanner inputScanner) {
		GameManager model = GameManager.getInstance();
		
		GameController controller = GameController.getInstance(model);
		
		GameView view = GameView.getInstance();
		view.setController(controller);
		view.doGame(inputScanner);
	}
	
	
	//Hand set up

//	public static void main(String[] args) {
//		GameSetUp game = new GameSetUp();
//
//		game.setupGame();
//	}
}
