package island.board;

import java.util.*;

import island.enums.Names;

/*
 * Singleton class Board
 */
public class Board{
	
	//private boolean hasTile;
    private static Board boardInstance = null; 
    private Tile[][] board = new Tile[6][6];
    private ArrayList<Names> tileNames = new ArrayList<Names>(EnumSet.allOf(Names.class));
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
            System.out.println(board[x[0]][x[1]].getName());
            board[x[0]][x[1]].getTileCoordinates();
            i++;
        }
    }
	
    public static Board getInstance(){
        if(boardInstance == null){
            boardInstance = new Board();
        }
        return boardInstance;
    }
	
	/*
	public boolean hasTile() {
		return hasTile;
	}
	
	public void setTile(boolean setTile) {
		this.hasTile = setTile; 
	}*/
	
	
	/*
	 * Test
	 */
	public static void main(String[] args) {
		Board board = new Board();
		board.makeBoard();
	}
	
}
