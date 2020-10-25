package island.board;

import island.enums.TileNames;

public class Tile {
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
		this.north = new int[] {x, y+1};
		this.south = new int[] {x, y-1};
		this.west = new int[] {x-1, y};
		this.east = new int[] {x+1, y};
		this.northEast = new int[] {x+1, y+1};
		this.southEast = new int[] {x+1, y-1};
		this.southWest = new int[] {x-1, y-1};
		this.northWest = new int[] {x-1, y+1};
	}
	
	/*
	 *  Tile getters
	 */
	public Tile getNorthTile() {
		return Board.getInstance().thisTile(north[0], north[1]);
	}
	
	public Tile getSouthTile() {
		return Board.getInstance().thisTile(south[0], south[1]);
	}
	
	public Tile getEastTile() {
		return Board.getInstance().thisTile(east[0], east[1]);
	}
	
	public Tile getWestTile() {
		return Board.getInstance().thisTile(west[0], west[1]);
	}
	
	public Tile getNorthEastTile() {
		return Board.getInstance().thisTile(northEast[0], northEast[1]);
	}
	
	public Tile getSouthEastTile() {
		return Board.getInstance().thisTile(southEast[0], southEast[1]);
	}
	
	public Tile getSouthWestTile() {
		return Board.getInstance().thisTile(southWest[0], southWest[1]);
	}
	
	public Tile getNorthWestTile() {
		return Board.getInstance().thisTile(northWest[0], northWest[1]);
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
	
	
	
	public boolean isFlooded() {
		return isFlooded;
	}
	
	public TileNames getName() {
		return name;
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
	
	// Setters
	public void setFlood(boolean flood) {
		this.isFlooded = flood;
	}
	
	public void setPresent(boolean present) {
		this.isPresent = present;
	}
	
}
