package cardTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import island.cards.Card;
import island.cards.Hand;
import island.cards.TreasureDeck;
import players.Engineer;

public class TreasureDeckTest {
	// Test cards are dealt into the player's hand
	@Test
	public void dealingCardsTest() {
		// Create player
		Engineer player = Engineer.getInstance("Player 1", 1, "@");
		
		// Draw 2 cards from treasure deck
		TreasureDeck treasureDeck = TreasureDeck.getInstance();
		ArrayList<Card> cardDrawn = treasureDeck.drawCard(player);
		// Add the cards to player's hand
		Hand playerHand = player.getHand();
		ArrayList<Card> playerCards = playerHand.getCards();
		
		assertEquals("The card drawn from treasure deck is in Player 1's hand", cardDrawn, playerCards);
	}
}
