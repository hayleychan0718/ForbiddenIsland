package gameLogic;

import java.util.ArrayList;
import java.util.Scanner;

import island.board.Tile;
import island.cards.Card;
import island.cards.FloodDeck;
import island.cards.Hand;
import island.cards.TreasureDeck;
import players.Player;
import players.PlayerList;

public class GameManager {


	private static GameManager theGameManager;

	private boolean isGameOver;
	private PlayerList playerList;

	
	
	private GameManager() {
		isGameOver=false;
		playerList= PlayerList.getInstance();
	}

	public static GameManager getInstance() {
		if(theGameManager == null) {
			theGameManager = new GameManager();
		}
		return theGameManager;
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

	public void gameOver() {
		isGameOver=true;
	}
	
	//May remove
	public ArrayList<Player> getListOfPlayers(){
		return playerList.getListOfPlayers();
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}

	public ArrayList<Card> drawTreasureDeck(Hand playerHand, Player player) {
		return TreasureDeck.getInstance().drawCard(playerHand, player);
	}

	public ArrayList<Tile> drawFloodDeck() {
		return FloodDeck.getInstance().drawCard();
	}

	public void removeFromHand(Card card, Player player) {
		player.getHand().removeCard(card);
	}
}
