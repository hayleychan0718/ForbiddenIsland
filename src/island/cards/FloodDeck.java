package island.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Stack;

import gameLogic.WaterMeter;
import island.board.Board;
import island.board.Tile;
import island.enums.TileNames;


public class FloodDeck extends Deck{
    private static FloodDeck floodDeckInstance = null; 
    private static Stack<FloodCard> floodStack = new Stack<FloodCard>();
    private static Stack<FloodCard> discardStack = new Stack<FloodCard>();
    
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
	public void startGame() {
		Board board = Board.getInstance();
		for(int i=0;i<6;i++) {
			FloodCard card = floodStack.pop();
			Tile tileToFlood = board.getTile(card.getName());
			tileToFlood.setFlood(true);
			discardStack.push(card);
		}
	}
	
	/*
	 * Drawing number of flood cards equal to water meter level and flooding those tiles
	 */
	public void drawCard() {
		Board board = Board.getInstance();
		WaterMeter waterMeter = WaterMeter.getinstance();
		for(int i=1;i<=waterMeter.getWaterLevel();i++) {
			if(floodStack.isEmpty()) {
				super.reshuffle(floodStack, discardStack);
			}
			FloodCard card = (FloodCard) floodStack.pop();
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
	
	public Stack<FloodCard> getStack(){
		return floodStack;
	}
	
	public Stack<FloodCard> getDiscardPile(){
		return discardStack;
	}
}
