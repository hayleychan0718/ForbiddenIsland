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
	
    
    public ArrayList<Tile> listOfTiles(){
    	ArrayList<Tile> list = new ArrayList<Tile>();
        for(int[] x: islandTiles) {
        	if(board[x[0]][x[1]].isPresent() == true)
        	list.add(board[x[0]][x[1]]);
        }
        return list;
    }
    
//    public ArrayList<Tile> listOfFloodedTiles(){
//    	ArrayList<Tile> list = new ArrayList<Tile>();
//    	for(int i=0; i<listOfTiles().size(); i++) {
//    		if(listOfTiles().get(i).isFlooded() == true) {
//    			list.add(listOfTiles().get(i));
//    		}
//    	}
//    	return list;
//    }
    //How to loop using object
    public ArrayList<Tile> listOfFloodedTiles(){
    	ArrayList<Tile> list = new ArrayList<Tile>();
    	for(Tile tile: listOfTiles()) {
    		if(tile.isFlooded() == true) {
    			list.add(tile);
    		}
    	}
    	return list;
    }
    
	public ArrayList<Tile> listOfNearestTiles(Tile tile){
    	int loc[] = tile.getLocation();
    	ArrayList<Tile> nearestTiles = new ArrayList<Tile>();
    	int iter = 0;
    
    	while(nearestTiles.isEmpty()) {
    		iter++;
    		for(int i = loc[0]-iter; i<=loc[0]+iter; i++) {
    			for(int j = loc[1]-iter; j<=loc[1]+iter; j++) {
    				if(getTile(i,j) != null && getTile(i,j).isPresent() && getTile(i,j)!=tile) 
    					nearestTiles.add(getTile(i,j));
    			}
    		}
    	}
    	return nearestTiles;
    }
	
	public int[][] getIslandTiles() {
		return islandTiles;
	}
	
	public Tile[][] getBoard() {
		return board;
	}
    
	/*
	 * Tests
	 */
//	public static void main(String[] args) {
//		Board board = Board.getInstance();
//		Tile tile = board.getTile(3, 3);
//		LinkedList<Tile> surrounding = tile.getAdjacentDiagonal();
//
//		for(int i=0; i<surrounding.size(); i++) {
//			surrounding.get(i).setFlood(true);
//			surrounding.get(i).setPresent(false);
//		}
//		
//		ArrayList<Tile> list = board.listOfNearestTiles(tile);		
//
//		for(int i=0; i<list.size();i++) {
//			System.out.println(list.get(i).getNameString());
//			System.out.println(Arrays.toString(list.get(i).getLocation()));
//		}
//	}

	// Commit test comment
	
}