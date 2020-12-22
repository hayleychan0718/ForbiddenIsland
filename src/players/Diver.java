package players;

import java.util.ArrayList;
import island.board.Board;
import island.board.Tile;
import pawns.Pawn;

/**
 * Singleton class to implement the diver extends player
 * @author Liam Fitzgerald and Hayley Chan
 *
 */
public class Diver extends Player {

	private static Diver theDiver;

	/**
	 * Constructed using base Player class and Pawn is constructed on "Iron Gate"
	 * @param playerName
	 * @param playerNumber
	 * @param symbol represents player on the board
	 */
	private Diver(String playerName, int playerNumber, String symbol) {
		super(playerName,playerNumber,symbol); 
		playerPawn=new Pawn(board.getTile("Iron Gate")); 

	}

	/**
	 * Gets instance of the diver
	 * @param playerName
	 * @param playerNumber
	 * @param symbol
	 * @return theDiver
	 */
	public static Diver getInstance(String playerName, int playerNumber, String symbol) {
		if(theDiver == null) {
			theDiver = new Diver(playerName ,playerNumber,symbol);
		}
		return theDiver;
	}

	@Override
	/**
	 * Overridden to allow diver to swim to nearest tile
	 * @return
	 */
	public ArrayList<Tile> getForcedMoveableTiles(){
		ArrayList<Tile> presentTiles = board.listOfPresentTiles();
		ArrayList<Tile> closestTiles = new ArrayList<Tile>();
		ArrayList<Double> listOfDistances = new ArrayList<Double>();
		int i =0;
		double minDistance =  100.0; //initialized larger than any minimum distance possible

		presentTiles.remove(getPlayerPawnTile());
		for(Tile tilej: presentTiles) { 
			minDistance = Math.min(Board.getDistance(getPlayerPawnTile(), tilej), minDistance); //Stores the minimum distance
			listOfDistances.add(Board.getDistance(getPlayerPawnTile(), tilej)); //Creates a list of the distances from the players tile to all the other
		}

		for(Tile tilej: presentTiles){ //Creates the list of closest tiles
			if(listOfDistances.get(i)==minDistance)
				closestTiles.add(tilej);
			i++;
		}
	
		return closestTiles;
	}
	
}



