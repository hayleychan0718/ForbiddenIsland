package cardTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureDeck;
import island.cards.WaterRiseCard;
import players.Engineer;

public class TreasureDeckTest {
	// Test cards are dealt into the player's hand
	@Test
	public void dealingCardsTest() {
		// Create player
		Engineer player = Engineer.getInstance("Player 1", 1, "@");
		
		// Draw 2 cards from treasure deck
		TreasureDeck treasureDeck = TreasureDeck.getInstance();
		ArrayList<Card> cardsDrawn = treasureDeck.drawCard(player);
		// Add the cards to player's hand
		Hand playerHand = player.getHand();
		ArrayList<Card> playerCards = playerHand.getCards();
		// Makes sure water rise card isn't put into player's hand
		for(Card card: cardsDrawn) {
			if(!(card instanceof WaterRiseCard))
				assertTrue("Treasure deck card drawn is in the player's hand", playerCards.contains(card));
		}
	}
}
