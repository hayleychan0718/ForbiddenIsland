package island.board;

import java.util.ArrayList;
import java.util.Collections;

import observers.PlayerObserver;
import island.enums.TileNames;
import island.enums.TreasureNames;
import observers.GameOverObserver;
import players.Player;
import players.PlayerList;

/**
 * Tile class to make the Forbidden Island board
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class Tile{
	/*
	 * Instance variables
	 */
	private GameOverObserver gameOverObserver = GameOverObserver.getInstance(); 
	private boolean isFlooded; 
	private boolean isPresent; 
	private final TileNames name; 
	private int[] location,north, south, east, west; 
	private int[] northEast, southEast, southWest, northWest;
	
	/**
	 * Tile constructor
	 * @param name Name of the tile as a TileNames 
	 * @param x X coordinate of the tile 
	 * @param y Y coordinate of the tile
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
	
	/**
	 * Gets location of current tile
	 * @return Location of current tile
	 */
	public int[] getLocation() {
		return location;
	}
	
	/**
	 * Gets the north tile
	 * @return Top tile 
	 */
	public Tile getNorthTile() {
		if(north[1] >= 6 || north[1] < 0 || north[0] >= 6 || north[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(north[0], north[1]);
	}
	
	/**
	 * Gets the south tile
	 * @return Bottom tile
	 */
	public Tile getSouthTile() {
		if(south[1] >= 6 || south[1] < 0 || south[0] >= 6 || south[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(south[0], south[1]);
	}
	
	/**
	 * Gets the east tile
	 * @return Right tile
	 */
	public Tile getEastTile() {
		if(east[1] >= 6 || east[1] < 0 || east[0] >= 6 || east[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(east[0], east[1]);
	}
	
	/**
	 * Gets the west tile
	 * @return Left tile
	 */
	public Tile getWestTile() {
		if(west[1] >= 6 || west[1] < 0 || west[0] >= 6 || west[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(west[0], west[1]);
	}
	
	/**
	 * Gets the north east tile
	 * @return Top right tile
	 */
	public Tile getNorthEastTile() {
		if(northEast[1] >= 6 || northEast[1] < 0 || northEast[0] >= 6 || northEast[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(northEast[0], northEast[1]);
	}
	
	/**
	 * Gets the south east tile
	 * @return Bottom right tile
	 */
	public Tile getSouthEastTile() {
		if(southEast[1] >= 6 || southEast[1] < 0 || southEast[0] >= 6 || southEast[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(southEast[0], southEast[1]);
	}
	
	/**
	 * Gets the south west tile
	 * @return Bottom left tile
	 */
	public Tile getSouthWestTile() {
		if(southWest[1] >= 6 || southWest[1] < 0 || southWest[0] >= 6 || southWest[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(southWest[0], southWest[1]);
	}
	
	/**
	 * Gets the north west tile 
	 * @return Top left tile
	 */
	public Tile getNorthWestTile() {
		if(northWest[1] >= 6 || northWest[1] < 0 || northWest[0] >= 6 || northWest[0] < 0) {
			return null;
		}else
			return Board.getInstance().getTile(northWest[0], northWest[1]);
	}
	
	/**
	 * Gets the tiles on top, bottom, left and right of the tile
	 * @return ArrayList of adjacent tiles
	 */
	public ArrayList<Tile> getAdjacentTiles(){
		ArrayList<Tile> adjacentTiles = new ArrayList<Tile>();
		adjacentTiles.add(getNorthTile());
		adjacentTiles.add(getWestTile());
		adjacentTiles.add(getSouthTile());
		adjacentTiles.add(getEastTile());
		
		adjacentTiles.removeAll(Collections.singleton(null)); //Remove Null Tiles
		
		return adjacentTiles;
	}
	
	/**
	 * Gets the tiles on top, bottom, left, right, and diagonal of the tile
	 * @return ArrayList of the surrounding tiles
	 */
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
	
	/**
	 * Returns String of the tile name
	 */
	public String toString() {
		return getNameString();
	}
	
	/**
	 * Checks if tile is flooded
	 * @return True if flooded and false otherwise
	 */
	public boolean isFlooded() {
		return isFlooded;
	}
	
	/**
	 * Gets name of the tile as a TileName
	 * @return Tile name
	 */
	public TileNames getName() {
		return name;
	}
	
	/**
	 * Gets the name of tile as a String
	 * @return Tile name string
	 */
	public String getNameString() {
		return name.getString();
	}
	
	/**
	 * Checks if tile is present 
	 * @return True if tile is present and false if tile is sunk
	 */
	public boolean isPresent() {
		return isPresent;
	}
	
	/**
	 * Checks if tile is a treasure tile
	 * @return True if tile is a treasure tile, false otherwise
	 */
	public boolean hasTreasure() {
		if(name==TileNames.TempleOfTheSun || name==TileNames.TempleOfTheMoon || name==TileNames.HowlingGarden ||
				name == TileNames.WhisperingGarden || name==TileNames.CaveOfShadows || name==TileNames.CaveOfEmbers ||
				name == TileNames.CoralPalace || name == TileNames.TidalPalace) {
			return true;
		} else
			return false;
	}

	/**
	 * Gets the treasure associated with tile. Must be used with hasTreasure()
	 * @return The treasure of the tile
	 */
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
	
	/**
	 * Flood the tile
	 * @param flood True if flooding tile, false otherwise
	 */
	public void setFlood(boolean flood) {
		this.isFlooded = flood;
	}
	
	/**
	 * Sink the tile
	 */
	public void sinkTile() {
		PlayerList playerList = PlayerList.getInstance();
		
		// Update observer if tile is a treasure tile or a Fool's Landing tile
		if(this.hasTreasure() || name.getString() == TileNames.FoolsLanding.getString()) {
			gameOverObserver.update(this);
			return;
		}
		this.isPresent=false;
		for (Player player:playerList.getListOfPlayers()) {
			if(this==player.getPlayerPawnTile()) {
				PlayerObserver.getInstance().updateSunk(player);
			}
		}
	}
	
	/**
	 * Sets the tile as not present. In other words, it is sunken.
	 */
	public void setNotPresent() {
		this.isPresent = false;
	}
	
	/**
	 * String of the tile as an initial
	 * @return The name of the tile as an initial
	 */
	public String initials() {
		String name = getNameString();
		StringBuilder initials = new StringBuilder();

		for(int i=0; i <name.length(); i++) {
			if(Character.isUpperCase(name.charAt(i))) {
				initials.append(name.charAt(i));
			}
		}
		if(hasTreasure()==true && !getTreasure().isCaptured()) {
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
