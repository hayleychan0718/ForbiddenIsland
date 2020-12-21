package gameLogic;
import java.util.ArrayList;
import java.util.Scanner;

import island.board.Board;
import island.board.Tile;
import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureDeck;
import island.enums.TreasureNames;
import observers.GameOverObserver;
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

	public ArrayList<Card> treasureDeckTurn(Player player) {
		return model.drawTreasureDeck(player);
	}
	
	public ArrayList<Tile> floodDeckTurn() {
		return model.drawFloodDeck();
	}

	public void removeFromHand(Card card, Player player) {
		model.removeFromHand(card, player);
	}
	
	public Hand getHand(Player player) {
		return player.getHand();
	}

	public Board getBoard() {
		return model.getBoard();
	}

	public boolean playerBeside(Tile tile) {
		return model.playerBeside(tile);
	}

	public Player playerToBeNotified() {
		return model.getPlayerToNotify();
	}

	public boolean loseCondition() {
		return model.loseCondition();
	}
	
	public boolean canSunkenPlayerMove(Player player) {
		return model.canSunkenPlayerMove(player);
	}
}

