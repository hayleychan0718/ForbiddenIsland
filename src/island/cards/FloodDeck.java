package island.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Stack;

import gameplay.WaterMeter;
import island.board.Board;
import island.board.Tile;
import island.enums.TileNames;


public class FloodDeck{
    private static FloodDeck floodDeckInstance = null; 
    private Stack<FloodCard> floodStack = new Stack<FloodCard>();
    private Stack<FloodCard> discardStack = new Stack<FloodCard>();
    
    /*
     * Flood deck in the beginning
     */
    public FloodDeck() {
        ArrayList<TileNames> tileNames = new ArrayList<TileNames>(EnumSet.allOf(TileNames.class));
        Collections.shuffle(tileNames);
        for(int i=0;i<tileNames.size();i++) {
       	 	floodStack.push(new FloodCard(tileNames.get(i)));
        }
    }
    
    /*
     * Create an instance of FloodDeck
     */
	public static FloodDeck getInstance(){
        if(floodDeckInstance == null){
            floodDeckInstance = new FloodDeck();
        }
        return floodDeckInstance;
    }
	
	/*
	 * Flooding at start of game
	 */
	public void startFlood() {
		Board board = Board.getInstance();
		for(int i=0;i<6;i++) {
			FloodCard card = floodStack.pop();
			Tile tileToFlood = board.getTile(card.getName());
			tileToFlood.setFlood(true);
			discardStack.push(card);
		}
	}
	
	/*
	 * Drawing number of flood cards equal to water meter level
	 */
	// TODO: Double check how to "get" water level with Liam
	public void drawCard() {
		Board board = Board.getInstance();
		if(floodStack.isEmpty()) {
			Collections.shuffle(discardStack);
			while(!discardStack.isEmpty()){
				floodStack.push(discardStack.pop());
			}
		}
		for(int i=0;i<WaterMeter.getWaterLevel();i++) {
			FloodCard card = floodStack.pop();
			Tile tileToFlood = board.getTile(card.getName());
			if(!tileToFlood.isFlooded() && tileToFlood.isPresent()) {
				tileToFlood.setFlood(true);
			}
			else if(tileToFlood.isFlooded()) {
				tileToFlood.setPresent(false);
			}
			discardStack.push(card);
		}
	}
	
	
	/*
	 * Tests
	 */
	public static void main(String[] args) {
		FloodDeck floodDeck = FloodDeck.getInstance();
		Board board = Board.getInstance();
		board.printBoard();
		System.out.println("Discard pile: " + floodDeck.discardStack.size());
		floodDeck.startFlood();
		System.out.println("AFTER FLOODING\n");
		board.printBoard();
		floodDeck.drawCard();
		System.out.println("Flood from water meter");
		board.printBoard();
	}

}
