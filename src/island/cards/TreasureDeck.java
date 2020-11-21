package island.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import island.enums.TreasureNames;


public class TreasureDeck extends Deck{
    private static TreasureDeck treasureDeckInstance = null; 
    private static Stack<TreasureDeckCard> treasureStack = new Stack<TreasureDeckCard>();
    private static Stack<TreasureDeckCard> discardStack = new Stack<TreasureDeckCard>();

    public TreasureDeck() {
   	 	treasureStack.push(new HelicopterCard());
   	 	treasureStack.push(new HelicopterCard());
   	 	treasureStack.push(new HelicopterCard());

   	 	treasureStack.push(new SandbagCard());
   	 	treasureStack.push(new SandbagCard());

   	 	treasureStack.push(new WaterRiseCard());
   	 	treasureStack.push(new WaterRiseCard());
   	 	treasureStack.push(new WaterRiseCard());
   	 	
   	 	for(int i=0;i<5;i++) {
   	   	 	treasureStack.push(new TreasureCard(TreasureNames.TheCrystalOfFire));
   	   	 	treasureStack.push(new TreasureCard(TreasureNames.TheEarthStone));
   	   	 	treasureStack.push(new TreasureCard(TreasureNames.TheOceansChalice));
   	   	 	treasureStack.push(new TreasureCard(TreasureNames.TheStatueOfTheWind));
   	 	}
   	 	
        Collections.shuffle(treasureStack);

    }
    
	public static TreasureDeck getInstance(){
        if(treasureDeckInstance == null){
        	treasureDeckInstance = new TreasureDeck();
        }
        return treasureDeckInstance;
    }
	
	public void drawCard() {
		
	}
	
	public void startGame() {
		
	}
	
	/*
	 * Tests
	 */
	public static void main(String[] args) {
		TreasureDeck treasureDeck = TreasureDeck.getInstance();
		System.out.println(treasureStack);
	}

}
