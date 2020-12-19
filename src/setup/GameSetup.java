package setup;

import java.util.Scanner;

import gameLogic.GameView;
import gameLogic.*;
import island.board.Board;
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
		playerActions();
		
		gameManagerSetup(inputScanner);
	}

	public void playerSetup(Scanner inputScanner) {
		PlayerSetup model = PlayerSetup.getInstance();

		PlayerSetupController controller = PlayerSetupController.getInstance(model);

		PlayerSetupView view = PlayerSetupView.getInstance();

		view.setController(controller);

		view.createPlayers(inputScanner);
		PlayerList.getInstance().getPlayer(1).getPlayerPawnTile().sinkTile();
		PlayerList.getInstance().getPlayer(2).getPlayerPawnTile().sinkTile();
		//Observer test here, run in the main. Sink the treasure , sink fool landing , one with player able to save and one without
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
