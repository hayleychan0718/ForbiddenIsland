package gameLogic;
import java.util.ArrayList;

import island.board.Tile;
import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureDeck;
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

	public ArrayList<Card> treasureDeckTurn(Hand playerHand, Player player) {
		return model.drawTreasureDeck(playerHand, player);
	}
	
	public ArrayList<Tile> floodDeckTurn() {
		return model.drawFloodDeck();
	}

	public void removeFromHand(Card card, Player player) {
		model.removeFromHand(card, player);
	}


}

