package setup;

import java.util.ArrayList;
import java.util.Scanner;

import gameLogic.GameView;
import gameLogic.*;
import island.board.Board;
import island.board.Tile;
import island.cards.CardController;
import island.cards.CardView;
import island.enums.TileNames;
import island.enums.TreasureNames;
import players.*;

/**
 * Singleton Class responsible for setting up the game
 * @author Liam FItzgerald
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
		
		//Testing win,Remove
		/*Board board = Board.getInstance();
		ArrayList<Player> players = PlayerList.getInstance().getListOfPlayers();
		Player player1 = players.get(0);
		Player player2 = players.get(1);
		player1.movePlayerPawn(board.getTile("Fool's Landing"));
		player2.movePlayerPawn(board.getTile("Fool's Landing"));
		TreasureNames.TheCrystalOfFire.captureTreasure();
		TreasureNames.TheEarthStone.captureTreasure();
		TreasureNames.TheOceansChalice.captureTreasure();
		TreasureNames.TheStatueOfTheWind.captureTreasure();*/
		//Testing beside a treasure tile sinking
		/*Tile treasureTile = Board.getInstance().getTile("Cave of Shadows");
		Tile tileBesideTreasure = treasureTile.getNorthTile();
		
		ArrayList<Player> players = PlayerList.getInstance().getListOfPlayers();
		Player player1 = players.get(0);
		player1.movePlayerPawn(tileBesideTreasure);*/
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

		WaterMeter.getinstance().increaseWaterLevel();
		WaterMeter.getinstance().increaseWaterLevel();
		WaterMeter.getinstance().increaseWaterLevel();
		
		
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
}
