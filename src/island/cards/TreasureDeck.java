package island.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import island.enums.TreasureNames;
import players.Player;
import players.PlayerView;


public class TreasureDeck extends Deck{
	/*
	 * Instance variables
	 */
    private static TreasureDeck treasureDeckInstance = null; 
    private static Stack<Card> treasureStack = new Stack<Card>();
    private static Stack<Card> discardStack = new Stack<Card>();

    /*
     * TreasureDeck constructor
     */
    /**
     * Creates the treasure deck 
     */
    public TreasureDeck() {
    	// Adding 3 helicopter cards
   	 	treasureStack.push(new HelicopterCard());
   	 	treasureStack.push(new HelicopterCard());
   	 	treasureStack.push(new HelicopterCard());

   	 	// Adding 2 sandbag cards
   	 	treasureStack.push(new SandbagCard());
   	 	treasureStack.push(new SandbagCard());

   	 	// Adding 3 water rise cards
   	 	treasureStack.push(new WaterRiseCard());
   	 	treasureStack.push(new WaterRiseCard());
   	 	treasureStack.push(new WaterRiseCard());
   	 	
   	 	// Adding 5 treasure cards for each treasure
   	 	for(int i=0;i<5;i++) {
   	   	 	treasureStack.push(new TreasureCard(TreasureNames.TheCrystalOfFire));
   	   	 	treasureStack.push(new TreasureCard(TreasureNames.TheEarthStone));
   	   	 	treasureStack.push(new TreasureCard(TreasureNames.TheOceansChalice));
   	   	 	treasureStack.push(new TreasureCard(TreasureNames.TheStatueOfTheWind));
   	 	} 	
   	 	// Shuffle the deck
        Collections.shuffle(treasureStack);
    }
    
	/**
	 * Gets the singleton instance of the TreasureDeck object.
	 * @return the single TreasureDeck object.
	 */
	public static TreasureDeck getInstance(){
        if(treasureDeckInstance == null){
        	treasureDeckInstance = new TreasureDeck();
        }
        return treasureDeckInstance;
    }
	
	/**
	 * Draw a card from the treasure deck and add to hand
	 * @param hand The player's hand 
	 */
	public ArrayList<Card> drawCard(Player player) {
		ArrayList<Card> drawnCardList = new ArrayList<Card>();
		for(int i=1;i<=2;i++) {
			if(treasureStack.isEmpty()) {
				super.reshuffle(treasureStack, discardStack);
			}
			Card drawnCard = treasureStack.pop();
			if(drawnCard instanceof WaterRiseCard) {
				PlayerView.getInstanace().doWaterRise(player);
				discardStack.add(drawnCard);
			}
			else {
				player.getHand().addCard(drawnCard);
				drawnCardList.add(drawnCard);
			}				
		}
		return drawnCardList;
	}
	
	/**
	 * Gets the main treasure deck
	 * @return The main treasure stack
	 */
	public Stack<Card> getStack(){
		return treasureStack;
	}
	
	/**
	 * Gets the treasure discard pile
	 * @return The treasure discard stack
	 */
	public Stack<Card> getDiscardPile(){
		return discardStack;
	}
}
