package setup;

import java.util.Scanner;
import gameLogic.GameView;
import gameLogic.*;
import island.board.Board;
import island.cards.CardController;
import island.cards.CardView;
import players.*;
import utility.Utility;

/**
 * Singleton Class responsible for setting up the game
 * @author Liam Fitzgerald and Hayley Chan
 *
 */

public class GameSetup {

	private static GameSetup gameSetup=null;

	public static GameSetup getInstance() {
		if(gameSetup == null) {
			gameSetup = new GameSetup();
		}
		return gameSetup;
	}

	/**
	 * Sets up and begins the game
	 */
	public void setupGame() {
		Scanner inputScanner = new Scanner(System.in);
		Board.getInstance();
		playerSetup(inputScanner);
		setWaterLevel(inputScanner);
		HandSetup.getInstance().startGame();
		FloodSetup.getInstance().startGame();
		playerActions();
		playableCardsSetup();
		gameManagerSetup(inputScanner);
	}

	
	/**
	 * Sets up the players list
	 * @param inputScanner
	 */
	public void playerSetup(Scanner inputScanner) {
		PlayerSetup model = PlayerSetup.getInstance();

		PlayerSetupController controller = PlayerSetupController.getInstance(model);

		PlayerSetupView view = PlayerSetupView.getInstance();

		view.setController(controller);

		view.createPlayers(inputScanner);
	}

	/**
	 * Sets up the player MVC
	 */
	public void playerActions() {
		PlayerController controller = PlayerController.getInstance();
		
		PlayerView view = PlayerView.getInstanace();
		
		view.setController(controller);
	}
	
	/**
	 * Sets up the game Manager
	 * @param inputScanner
	 */
	public void gameManagerSetup(Scanner inputScanner) {
		GameManager model = GameManager.getInstance();
		
		GameController controller = GameController.getInstance(model);
		
		GameView view = GameView.getInstance();
		view.setController(controller);
		
		view.doGame(inputScanner);
	}
	
	/**
	 * Sets up the playable Cards MVC
	 */
	public void playableCardsSetup() {	
		
		
		CardController controller = CardController.getInstance();
		
		CardView view = CardView.getInstance();
		
		view.setController(controller);
	}
	
	/**
	 * Sets the game water level at the beginning of the game
	 * @param inputScanner
	 */
	public void setWaterLevel(Scanner inputScanner) {
		System.out.println("\nChoose the water level you want for the game between (1-4) ");
		
		
		int userInput =Utility.acceptableInput(1, 4, inputScanner);
		
		WaterMeter.getinstance().setWaterLevel(userInput);
	}
}
