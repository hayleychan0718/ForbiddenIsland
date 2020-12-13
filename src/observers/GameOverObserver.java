package observers;


import gameLogic.PlayerView;
import island.board.Board;
import island.board.Tile;
import island.enums.TileNames;
import island.enums.TreasureNames;
import players.Player;
import players.PlayerList;

import java.util.ArrayList;


public class GameOverObserver {
	public java.util.List<Player> playersList = PlayerList.getInstance().getListOfPlayers();
	public Board board = Board.getInstance();
	public ArrayList<Tile> sunkTiles = Board.getInstance().listOfSunkTiles();
	
	public void update(TileNames tile) {		
		for(Player player: playersList) {
			for(Tile t: player.getShoreableTiles()) {
				if(tile.getString() == t.getNameString()) {
					if(PlayerView.getInstanace().notifyPlayer(tile, player) == false) {
						if(loseCondition())
							PlayerView.getInstanace().treasureLost();
						if(tile.getString() == "Fool's Landing")
							PlayerView.getInstanace().foolsLost();
					}
				}
			}
		}
	}
	
	public void update() {
		PlayerView.getInstanace().waterLost();
	}
	
	public boolean loseCondition() {
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
