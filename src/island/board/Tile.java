package island.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;

import observers.PlayerObserver;
import island.enums.TileNames;
import island.enums.TreasureNames;
import observers.GameOverObserver;
import players.Player;
import players.PlayerList;

public class Tile{
	private GameOverObserver gameOverObserver = GameOverObserver.getInstance();
	private boolean isFlooded; // Tile can be "flooded" or "unflooded"
	private boolean isPresent; // Tile is present or gone
	private final TileNames name;  // Tile has a name
	private int[] location, north, south, east, west; // Coordinates of adjacent tiles
	private int[] northEast, southEast, southWest, northWest; // Coordinates of diagonal tiles
	
	/* When a Tile object is initialised, the tile is present, unflooded, 
	 * and a random (x,y) coordinate is passed in.	
	 */
	public Tile(TileNames name, int x, int y) {
		this.isFlooded = false;
		this.isPresent = true;
		this.name = name;
		this.location = new int[] {x, y};
		this.north = new int[] {x-1, y};
		this.south = new int[] {x+1, y};
		this.west = new int[] {x, y-1};
		this.east = new int[] {x, y+1};
		this.northEast = new int[] {x-1, y+1};
		this.southEast = new int[] {x+1, y+1};
		this.southWest = new int[] {x+1, y-1};
		this.northWest = new int[] {x-1, y-1};
	}
	
	/*
	 *  Tile getters
	 */
	public Tile getNorthTile() {
		if(north[1] >= 6 || north[1] < 0 || north[0] >= 6 || north[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(north[0], north[1]);
	}
	
	public Tile getSouthTile() {
		if(south[1] >= 6 || south[1] < 0 || south[0] >= 6 || south[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(south[0], south[1]);
	}
	
	public Tile getEastTile() {
		if(east[1] >= 6 || east[1] < 0 || east[0] >= 6 || east[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(east[0], east[1]);
	}
	
	public Tile getWestTile() {
		if(west[1] >= 6 || west[1] < 0 || west[0] >= 6 || west[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(west[0], west[1]);
	}
	
	public Tile getNorthEastTile() {
		if(northEast[1] >= 6 || northEast[1] < 0 || northEast[0] >= 6 || northEast[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(northEast[0], northEast[1]);
	}
	
	public Tile getSouthEastTile() {
		if(southEast[1] >= 6 || southEast[1] < 0 || southEast[0] >= 6 || southEast[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(southEast[0], southEast[1]);
	}
	
	public Tile getSouthWestTile() {
		if(southWest[1] >= 6 || southWest[1] < 0 || southWest[0] >= 6 || southWest[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(southWest[0], southWest[1]);
	}
	
	public Tile getNorthWestTile() {
		if(northWest[1] >= 6 || northWest[1] < 0 || northWest[0] >= 6 || northWest[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(northWest[0], northWest[1]);
	}
	
	/*
	 * Coordinates getters (Might not need them)
	 */
	public int[] getLocation() {
		return location;
	}
	
	public int[] getNorth() {
		return north;
	}
	
	public int[] getSouth() {
		return south;
	}
	
	public int[] getEast() {
		return east;
	}
	
	public int[] getWest() {
		return west;
	}
	
	public int[] getNorthEast() {
		return northEast;
	}
	
	public int[] getSouthEast() {
		return southEast;
	}
	
	public int[] getSouthWest() {
		return southWest;
	}
	
	public int[] getNorthWest() {
		return northWest;
	}
	
	public ArrayList<Tile> getAdjacentTiles(){
		ArrayList<Tile> adjacentTiles = new ArrayList<Tile>();
		adjacentTiles.add(getNorthTile());
		adjacentTiles.add(getWestTile());
		adjacentTiles.add(getSouthTile());
		adjacentTiles.add(getEastTile());
		
		adjacentTiles.removeAll(Collections.singleton(null)); //Remove Null Tiles
		
		return adjacentTiles;
	}
	
	public ArrayList<Tile> getAdjacentDiagonal(){
		ArrayList<Tile> adjacentDiagonalTiles = new ArrayList<Tile>();
		adjacentDiagonalTiles.add(getNorthTile());
		adjacentDiagonalTiles.add(getNorthWestTile());
		adjacentDiagonalTiles.add(getWestTile());
		adjacentDiagonalTiles.add(getSouthWestTile());
		adjacentDiagonalTiles.add(getSouthTile());
		adjacentDiagonalTiles.add(getSouthEastTile());
		adjacentDiagonalTiles.add(getEastTile());
		adjacentDiagonalTiles.add(getNorthEastTile());
		
		adjacentDiagonalTiles.removeAll(Collections.singleton(null)); //Remove Null Tiles
		
		return adjacentDiagonalTiles;
	}
	
	public String toString() {
		return getNameString();
	}
	
	
	public boolean isFlooded() {
		return isFlooded;
	}
	
	public TileNames getName() {
		return name;
	}
	
	public String getNameString() {
		return name.getString();
	}
	
	public boolean isPresent() {
		return isPresent;
	}
	
	public boolean hasTreasure() {
		if(name==TileNames.TempleOfTheSun || name==TileNames.TempleOfTheMoon || name==TileNames.HowlingGarden ||
				name == TileNames.WhisperingGarden || name==TileNames.CaveOfShadows || name==TileNames.CaveOfEmbers ||
				name == TileNames.CoralPalace || name == TileNames.TidalPalace) {
			return true;
		} else
			return false;
	}
	
	// Use this function with hasTreasure() == true
	public TreasureNames getTreasure() {
		if(name == TileNames.TempleOfTheMoon || name == TileNames.TempleOfTheSun) {
			return TreasureNames.TheEarthStone;
		}
		else if(name == TileNames.WhisperingGarden || name == TileNames.HowlingGarden) {
			return TreasureNames.TheStatueOfTheWind;
		}
		else if(name == TileNames.CaveOfEmbers || name == TileNames.CaveOfShadows) {
			return TreasureNames.TheCrystalOfFire;
		}
		else if(name == TileNames.CoralPalace || name == TileNames.TidalPalace) {
			return TreasureNames.TheOceansChalice;
		}
		else
			return null;
	}
	
	// Setters
	public void setFlood(boolean flood) {
		this.isFlooded = flood;
	}
	
	
//	public void sinkTile() {	
//		this.isPresent = false;
//		// If a treasure tile or Fool's Landing is being sunk, update observer to notify player(s) if 
//		// able to save the tile
//		if(this.hasTreasure() || name.getString() == TileNames.FoolsLanding.getString()) {
//			gameOverObserver.update(name);
//		}
//	}
	public void sinkTile() { //Set changed
		this.isPresent=false;
		PlayerList playerList = PlayerList.getInstance();
		
		for (Player player:playerList.getListOfPlayers()) {

			if(this==player.getPlayerPawnTile()) {
				PlayerObserver.getInstance().updateSunk(player);
			}
		}
		if(this.hasTreasure() || name.getString() == TileNames.FoolsLanding.getString()) {
			gameOverObserver.update(this);
		}
	}
	
	public void saveTile() {
		this.isPresent = true;
	}
	
	public String initials() {
		String name = getNameString();
		StringBuilder initials = new StringBuilder();

		for(int i=0; i <name.length(); i++) {
			if(Character.isUpperCase(name.charAt(i))) {
				initials.append(name.charAt(i));
			}
		}
		if(hasTreasure()==true) {
			initials.append("*");
		}
		
		if(isFlooded==true) {
			initials.append("!");
		}
		if(getNameString()== "Fool's Landing") {
			return initials.toString().toUpperCase();
		}
		return initials.toString().toLowerCase();
	}
	
}
