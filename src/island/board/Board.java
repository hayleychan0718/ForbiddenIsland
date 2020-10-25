package island.board;

import java.util.*;

import island.enums.TileNames;

/*
 * Singleton class Board
 */
public class Board{
	
	//private boolean hasTile;
    private static Board boardInstance = null; 
    private Tile[][] board = new Tile[6][6];
    private ArrayList<TileNames> tileNames = new ArrayList<TileNames>(EnumSet.allOf(TileNames.class));
    private int islandTiles[][] = {{0,2},{0,3},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},
    		{2,3},{2,4},{2,5},{3,0},{3,1},{3,2},{3,3},{3,4},{3,5},{4,1},
    		{4,2},{4,3},{4,4},{5,2},{5,3}};
    
    /*
     * Constructor - makes the board
     */
	private Board(){
        Collections.shuffle(tileNames);
        int i = 0;
        for(int[] x: islandTiles) {
        	board[x[0]][x[1]] = new Tile(tileNames.get(i), x[0], x[1]);
            i++;
        }
    }
	
	/*
	 * Creating instance of a board
	 */
    public static Board getInstance(){
        if(boardInstance == null){
            boardInstance = new Board();
        }
        return boardInstance;
    }
	
    /*
     * Just prints out the name of tile followed by coordinates of tile
     */
    public void printBoard() {
        for(int[] x: islandTiles) {
            System.out.println(board[x[0]][x[1]].getName());
            System.out.println(Arrays.toString(board[x[0]][x[1]].getLocation()));
            System.out.println("Is flooded: " + board[x[0]][x[1]].isFlooded());
            System.out.println();
        }
    }
    
    public Tile getTile(TileNames name) {
        for(int[] x: islandTiles) {
        	if(board[x[0]][x[1]].getName() == name) {
        		return board[x[0]][x[1]];
        	}
        }
        return null; // never needed
    }
    
    public Tile thisTile(int x, int y) {
    	return board[x][y];
    }
	
    /*
     * Don't think this is needed?
     * 
    public void hasTile(int x, int y) {
    	if(board[x][y] == null) {
    		this.hasTile = false;
    	} else
    		this.hasTile = true;
    }
    */
    
	
	/*
	 * Tests
	 */
	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.printBoard();
		Tile tile1 = board.getTile(TileNames.CaveOfEmbers);
		Tile tile2 = board.thisTile(0, 2);
		
		System.out.println(Arrays.toString(tile1.getLocation()));
		System.out.println("Treasure tile: " + tile1.hasTreasure());
		
		if(tile2.getNorthTile() == null) {
			System.out.println("Out of island"); 
		}else
			System.out.println(tile2.getNorthTile().getName());
		
		if(tile2.getWestTile() != null) {
			if(tile2.isPresent()) {
				System.out.println(tile2.getWestTile().getName());
				System.out.println(Arrays.toString(tile2.getWestTile().getLocation()));
			}
		}else {
			System.out.println("Out of island");
		}
	}
	
}
