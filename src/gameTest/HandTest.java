package gameTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import gameLogic.GameManager;
import island.cards.Card;
import island.cards.Hand;
import island.cards.HelicopterCard;
import island.cards.SandbagCard;
import island.cards.TreasureCard;
import island.enums.TreasureNames;
import players.Engineer;

public class HandTest {	
	@Test
	public void playerHandTest() {
		Engineer player1 =  Engineer.getInstance("Player Name", 1,"%");
		Hand playerHand = player1.getHand();
		
		// Adding 5 cards to player's hand
		playerHand.addCard(new HelicopterCard());
		playerHand.addCard(new HelicopterCard());
		playerHand.addCard(new SandbagCard());
		playerHand.addCard(new TreasureCard(TreasureNames.TheCrystalOfFire));
		playerHand.addCard(new SandbagCard());
		assertEquals("Player1 has 5 cards", 5, playerHand.getCards().size());
		// Adding a sixth card
		Card sixthCard = new TreasureCard(TreasureNames.TheCrystalOfFire);
		playerHand.addCard(sixthCard);
		assertTrue("Number of cards in hand is 6", GameManager.getInstance().checksHand(playerHand));
	}
}
