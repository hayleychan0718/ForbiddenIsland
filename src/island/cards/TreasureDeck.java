package island.cards;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import island.enums.TreasureNames;
import players.Player;
import players.PlayerList;

public class TreasureDeck extends Deck{
	/*
	 * Instance variables
	 */
    private static TreasureDeck treasureDeckInstance = null; 
    private static Stack<TreasureDeckCard> treasureStack = new Stack<TreasureDeckCard>();
    private static Stack<TreasureDeckCard> discardStack = new Stack<TreasureDeckCard>();

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
	public void drawCard(Hand hand) {
		if(treasureStack.isEmpty()) {
			super.reshuffle(treasureStack, discardStack);
		}
		hand.addCard(treasureStack.pop());
	}
	
	/**
	 * Give 2 cards to each player at the beginning of the game
	 */
	public void startGame() {
		List<Player> listOfPlayers = PlayerList.getInstance().getListOfPlayers();
		for(int i=0;i<listOfPlayers.size();i++) {
			for(int j=0; j<2; j++) {
				TreasureDeckCard card = treasureStack.pop();
				listOfPlayers.get(i).getHand().addCard(card);
			}
		}
	}
}
