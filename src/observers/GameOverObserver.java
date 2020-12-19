package observers;


import players.PlayerView;
import island.board.Board;
import island.board.Tile;
import island.enums.TileNames;
import island.enums.TreasureNames;
import players.Player;
import players.PlayerList;

import java.util.ArrayList;
import java.util.Scanner;


public class GameOverObserver {
	public java.util.List<Player> playersList = PlayerList.getInstance().getListOfPlayers();
	public ArrayList<Tile> sunkTiles = new ArrayList<Tile>();
	public Player player;
	public Scanner inputScanner;
	
	public void update(Tile tile) {
		PlayerView playerView = PlayerView.getInstanace();

		sunkTiles.add(tile);
		if(playerBeside(tile)) {
			if(!playerView.notifyPlayer(inputScanner ,tile, player)) {
				if(loseCondition())
					PlayerView.getInstanace().treasureLost();
				if(tile.getNameString() == "Fool's Landing")
					PlayerView.getInstanace().foolsLost();
			}
		}
	}
	
	public boolean playerBeside(Tile tile) {
		for(Player player: playersList) {
			for(Tile t: player.getShoreableTiles()) {
				if(tile == t) {
					this.player = player;
					return true;
				}
			}
		}
		return false;
	}
	
	public void update() {
		PlayerView.getInstanace().waterLost();
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
	
}
