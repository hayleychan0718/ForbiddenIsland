package gameLogic;
import java.util.ArrayList;

import players.*;

public class GameController {

	private GameManager model;
	private static GameController controller = null;

	public static GameController getInstance(GameManager model) {
		if(controller == null)
			controller = new GameController(model);
		return controller;
	}

	private GameController(GameManager model) {
		this.model=model;
	}

	public ArrayList<Player> getListOfPlayers() {
		return model.getListOfPlayers();
	}

	public boolean isTurnOver(Player player) {
		return model.isTurnOver(player);
	}

	public void gameOver() {
		model.gameOver();
	}
	
	public void reStockActions(Player player) {
		player.reStockActions();
	}
	
	public boolean isGameOver() {
		return model.isGameOver();
	}


}

