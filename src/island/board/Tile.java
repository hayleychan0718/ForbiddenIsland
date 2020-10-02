package island.board;

import island.enums.Names;

public class Tile {
	// Tile can be "flooded" or "unflooded"
	private boolean isFlooded;
	// Tile is present or gone
	private boolean isPresent;
	// Tile has x and y coordinates
	private final int x, y;
	// Tile has a name
	private final Names name;
	/* Tile is associated with treasure variable?? */
	
	/* When a Tile object is initialised, the tile is present, unflooded, 
	 * and a random (x,y) coordinate is passed in.	
	 */
	public Tile(Names name, int x, int y) {
		this.isFlooded = false;
		this.isPresent = true;
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	// Getters
	public void getTileCoordinates() {
		System.out.println("(" + x + ", " + y + ")");
	}
	
	public boolean isFlooded() {
		return isFlooded;
	}
	
	public Names getName() {
		return name;
	}
	
	public boolean isPresent() {
		return isPresent;
	}
/*	
	public void getDiagonal() {

	}
	
	public void getAdjacent() {

	}*/
	
	// Setters
	public void setFlood(boolean flood) {
		this.isFlooded = flood;
	}
	
	public void setPresent(boolean present) {
		this.isPresent = present;
	}
	

}
