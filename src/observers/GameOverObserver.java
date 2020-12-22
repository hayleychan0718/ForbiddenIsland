package observers;


import island.board.Tile;
import java.util.ArrayList;
import gameLogic.GameView;

/**
 * Observer to check if game is over
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class GameOverObserver {
	/*
	 * Instance variables
	 */
	private static GameOverObserver gameOverObserver;
	private ArrayList<Tile> sunkTiles = new ArrayList<Tile>();
	
	/**
	 * Creating and instance of the game over observer
	 * @return
	 */
	public static GameOverObserver getInstance() {
		if(gameOverObserver==null) {
			gameOverObserver = new GameOverObserver();
		}
		return gameOverObserver;
	}
	
	/**
	 * When a tile is about to sink, the observer is updated
	 * @param tile The tile that is about to sink
	 */
	public void update(Tile tile) {
		sunkTiles.add(tile);
	}
	
	/**
	 * When water meter level has reached 5, observer is updated
	 * @return True: water level has reached 5.
	 */
	public boolean update() {
		GameView.getInstance().waterLost();
		return true;
	}
	
	/**
	 * Gets a list of all the tiles that are sunk
	 * @return List of sunken tiles
	 */
	public ArrayList<Tile> getSunkTiles(){
		return sunkTiles;
	}
}
