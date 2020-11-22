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
            System.out.println("Coord: " + Arrays.toString(board[x[0]][x[1]].getLocation()));
            System.out.println("Flooded: " + board[x[0]][x[1]].isFlooded());
            System.out.println("Present: " + board[x[0]][x[1]].isPresent());
            System.out.println();
        }
    	
        
        /*for(int i=0; i<6; i++) {
        	System.out.println();
        	for(int j=0; j<6; j++) {
        		if(board[i][j] == null) 
        			System.out.printf("%25s", "blank");
        		else 
        			System.out.printf("%25s", board[i][j].getName().getString());
        	}
        } */   	
    }
    
    public Tile getTile(TileNames name) {
        for(int[] x: islandTiles) {
        	if(board[x[0]][x[1]].getName() == name) {
        		return board[x[0]][x[1]];
        	}
        }
        return null; // never needed
    }
    
	public Tile getTile(String name) {
        for(int[] x: islandTiles) {
        	if(board[x[0]][x[1]].getNameString() == name) {
        		return board[x[0]][x[1]];
        	}
        }
        return null; // never needed
    }
    
    public Tile getTile(int x, int y) {
    	return board[x][y];
    }
	
    
    public LinkedList<Tile> listOfTiles(){
    	LinkedList<Tile> list = new LinkedList<Tile>();
        for(int[] x: islandTiles) {
        	list.add(board[x[0]][x[1]]);
        }
        return list;
    }
    
	/*
	 * Tests
	 */
	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.printBoard();
		
		Tile tile1 = board.getTile(2,5);
		if(tile1.getEastTile() != null) {
			System.out.println(Arrays.toString(tile1.getEastTile().getLocation()));
		}
		
		System.out.println(board.listOfTiles().get(1).getNameString());
	}

	
}
