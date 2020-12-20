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
	private List<Player> playersList = PlayerList.getInstance().getListOfPlayers();
	private ArrayList<Tile> sunkTiles = new ArrayList<Tile>();
	private Player player;
	private Scanner inputScanner = new Scanner(System.in);
	
	public static GameOverObserver getInstance() {
		if(gameOverObserver==null) {
			gameOverObserver = new GameOverObserver();
		}
		return gameOverObserver;
	}
	
	public void update(Tile tile) {
		PlayerView playerView = PlayerView.getInstanace();

		if(playerBeside(tile)) {
			if(!playerView.notifyPlayer(inputScanner , tile, player)) {
				sunkTiles.add(tile);
				invokeLoseGame(tile);
			}
		}
		else {
			sunkTiles.add(tile);
			invokeLoseGame(tile);
		}
	}
	
	public void invokeLoseGame(Tile tile) {
		if(loseCondition())
			GameView.getInstance().treasureLost();
		if(tile.getNameString() == "Fool's Landing")
			GameView.getInstance().foolsLost();
	}
	
	public boolean playerBeside(Tile tile) {
		for(Player player: playersList) {
			for(Tile t: player.getShoreableTiles()) {
				if(t == tile) {
					this.player = player;
					return true;
				}
			}
		}
		return false;
	}
	
	public void update() {
		GameView.getInstance().waterLost();
	}
	
	public boolean loseCondition() {
		Board board = Board.getInstance();
		if(sunkTiles.contains(board.getTile("Temple of The Sun")) && sunkTiles.contains(board.getTile("Temple of The Moon")) && !TreasureNames.TheEarthStone.isCaptured() ||
				sunkTiles.contains(board.getTile("Cave of Shadows")) && sunkTiles.contains(board.getTile("Cave of Embers")) && !TreasureNames.TheCrystalOfFire.isCaptured() ||
				sunkTiles.contains(board.getTile("Coral Palace")) && sunkTiles.contains(board.getTile("Tidal Palace")) && !TreasureNames.TheOceansChalice.isCaptured() ||
				sunkTiles.contains(board.getTile("Howling Garden")) && sunkTiles.contains(board.getTile("Whispering Garden")) && !TreasureNames.TheStatueOfTheWind.isCaptured()){
			return true;
		}
		else 
			return false;
	}
	
	public ArrayList<Tile> getSunkTiles(){
		return sunkTiles;
	}
}
