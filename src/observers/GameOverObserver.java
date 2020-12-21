package observers;


import players.PlayerView;
import island.board.Board;
import island.board.Tile;
import island.enums.TreasureNames;
import players.Player;
import players.PlayerList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gameLogic.GameView;


public class GameOverObserver {
	private static GameOverObserver gameOverObserver;
	private ArrayList<Tile> sunkTiles = new ArrayList<Tile>();
	
	public static GameOverObserver getInstance() {
		if(gameOverObserver==null) {
			gameOverObserver = new GameOverObserver();
		}
		return gameOverObserver;
	}
	
	public void update(Tile tile) {
		sunkTiles.add(tile);
	}
	
	public boolean update() {
		GameView.getInstance().waterLost();
		return true;
	}
	
	public ArrayList<Tile> getSunkTiles(){
		return sunkTiles;
	}
}
