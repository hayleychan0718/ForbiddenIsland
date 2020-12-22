package island.board;

import java.util.*;
import java.util.stream.Collectors;

import island.enums.TileNames;
import players.Player;
import players.PlayerList;

/**
 * Singleton class of the board
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class Board{
	/*
	 * Instance variables
	 */
    private static Board boardInstance = null; 
    private Tile[][] board = new Tile[6][6];
    private ArrayList<TileNames> tileNames = new ArrayList<TileNames>(EnumSet.allOf(TileNames.class));
    private int islandTiles[][] = {{0,2},{0,3},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},
    		{2,3},{2,4},{2,5},{3,0},{3,1},{3,2},{3,3},{3,4},{3,5},{4,1},
    		{4,2},{4,3},{4,4},{5,2},{5,3}};
    
    /**
     * Board constructor. Makes the island.
     */
	private Board(){
        Collections.shuffle(tileNames);
        int i = 0;
        for(int[] x: islandTiles) {
        	board[x[0]][x[1]] = new Tile(tileNames.get(i), x[0], x[1]);
            i++;
        }
    }
	
	/**
	 * Creating an instance of the board
	 * @return boardInstance
	 */
    public static Board getInstance(){
        if(boardInstance == null){
            boardInstance = new Board();
        }
        return boardInstance;
    }
	
    /**
     * Gets a tile from the board
     * @param name String name of the tile you want to retrieve
     * @return Return the tile on the board
     */
    public Tile getTile(TileNames name) {
        for(int[] x: islandTiles) {
        	if(board[x[0]][x[1]].getName() == name) { 
        		return board[x[0]][x[1]];
        	}
        }
        return null; 
    }
    
	public Tile getTile(String name) {
        for(int[] x: islandTiles) {
        	if(board[x[0]][x[1]].getNameString() == name) {
        		return board[x[0]][x[1]];
        	}
        }
        return null; 
    }
    
	/**
	 * Gets a tile on the board 
	 * @param x X coordinate of the tile
	 * @param y Y coordinate of the tile
	 * @return The tile on the board
	 */
    public Tile getTile(int x, int y) {
    	return board[x][y];
    }
	
    /**
     * Gets list of tiles on the board
     * @return List of present tiles
     */
    public ArrayList<Tile> listOfTiles(){
    	ArrayList<Tile> list = new ArrayList<Tile>();
        for(int[] x: islandTiles) {
        	if(board[x[0]][x[1]].isPresent() == true)
        	list.add(board[x[0]][x[1]]);
        }
        return list;
    }
    
    /**
     * Get list of flooded tiles
     * @return List of tiles that are flooded and present
     */
    public ArrayList<Tile> listOfFloodedTiles(){
    	ArrayList<Tile> list = new ArrayList<Tile>();
    	for(Tile tile: listOfTiles()) {
    		if(tile.isFlooded() == true) {
    			list.add(tile);
    		}
    	}
    	return list;
    }
    
    /**
     * Returns a list of all the present tiles
     * @return presentTiles List of tiles that are present 
     */
    public ArrayList<Tile> listOfPresentTiles(){
    	ArrayList<Tile> presentTiles = new ArrayList<Tile>();
    	for(Tile tile: listOfTiles()) {
    		if(tile.isPresent() == true) {
    			presentTiles.add(tile);
    		}
    	}
    	return presentTiles;
    }
    
    /**
     * Returns the distance as double between two tiles
     * @param currentTile
     * @param otherTile
     * @return distance
     */
    public static Double getDistance(Tile currentTile, Tile otherTile) {
		int locInput[] = currentTile.getLocation();
		int locCurrent[] = otherTile.getLocation();

		int dx = locInput[0] - locCurrent[0];
		int dy = locInput[1] - locCurrent[1];

		return Math.sqrt(dx*dx + dy*dy);

	}
   
    /**
     * Gets the island tile coordinates
     * @return Coordinates of the island tiles
     */
	public int[][] getIslandTiles() {
		return islandTiles;
	}
	
	/**
	 * Gets the board coordinates
	 * @return Coordinates of the board
	 */
	public Tile[][] getBoard() {
		return board;
	}
	
	/**
	 * String function to print the board
	 * @return String of the board
	 */
	public String showBoard() {
		List<String> stringTiles = new ArrayList<String>();

		for(Tile[] row : board) {
			if(row !=null) {
				for(Tile col : row) {

					if(col!=null) {
						stringTiles.add("[  " +tileForBoard(col) + "  ]");
					}
					else
						stringTiles.add("        ");	
				}
				stringTiles.add("\n");
			}
		}
		String concatenated=stringTiles.stream().collect(Collectors.joining(""));
		return concatenated;
	}

	/**
	 * String of the board's information
	 * @param col
	 * @return
	 */
	public String tileForBoard(Tile col) {
		ArrayList<Player> playerList = PlayerList.getInstance().getListOfPlayers();

		for(Player player: playerList) {
			if(player.getPlayerPawnTile()==col) {
				if(col.isFlooded())
					return player.getSymbol().concat("!")  ;
				if(col.hasTreasure() && !col.getTreasure().isCaptured())  
					return player.getSymbol().concat("*")  ;

				return player.getSymbol();
			}
		}

		if(col.isPresent()==false) {
			String out;
			return out = "  ";
		}

		return col.initials();

	}
	
}