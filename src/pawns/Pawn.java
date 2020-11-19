package pawns;


import island.board.Tile;
import island.enums.TileNames;

public class Pawn {

	private char mapChar; //How should we do this
	private Tile pawnTile;
	
	//Need tiles using names
	public Pawn(Tile startingTile) {
		pawnTile=startingTile;
		}
		//automatically take first character from player role
	
	
	
	public char getChar() {
		return mapChar;
	}
	
	public Tile getPawnTile() {
		return pawnTile;
	}
	
	//Maybe location co-ordinate but Tile class could take care of this
	
	public void setPawnTile(Tile newPawnTile) { //Sets the tile the pawn is on
		pawnTile = newPawnTile;
	} 
	
	//Move pawn on board
	public void movePawn(Tile tile) {
		pawnTile = tile;
	}
	
	public void onOceanTile() { //Is pawn tile on ocean tile
	}
	
	public boolean isPawnTilePresent() {  //Checks if the pawn is on a present tile
		return pawnTile.isPresent();
	}


}
