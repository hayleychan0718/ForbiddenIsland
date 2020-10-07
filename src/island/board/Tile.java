package island.board;

import java.util.ArrayList;
import island.enums.Names;

public class Tile {
	private boolean isFlooded; // Tile can be "flooded" or "unflooded"
	private boolean isPresent; // Tile is present or gone
	private final Names name;  // Tile has a name
	private int[] location, up, down, left, right; 
	/* Tile is associated with treasure variable?? */
	
	
	/* When a Tile object is initialised, the tile is present, unflooded, 
	 * and a random (x,y) coordinate is passed in.	
	 */
	public Tile(Names name, int x, int y) {
		this.isFlooded = false;
		this.isPresent = true;
		this.name = name;
		this.location = new int[] {x, y};
		this.up = new int[] {x, y+1};
		this.down = new int[] {x, y-1};
		this.left = new int[] {x-1, y};
		this.right = new int[] {x+1, y};
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
	
/*	public void getDiagonal() {

	}*/
	
	
	// Setters
	public void setFlood(boolean flood) {
		this.isFlooded = flood;
	}
	
	public void setPresent(boolean present) {
		this.isPresent = present;
	}
	

}
