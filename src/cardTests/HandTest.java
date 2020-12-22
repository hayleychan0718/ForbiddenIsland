package cardTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import gameLogic.GameManager;
import island.board.Board;
import island.board.Tile;
import island.cards.Card;
import island.cards.Hand;
import island.cards.HelicopterCard;
import island.cards.SandbagCard;
import island.cards.TreasureCard;
import island.enums.TreasureNames;
import players.Engineer;

/**
 * Unit testing the player hand
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
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
	
	@Test
	public void matchingTreasureCards() {
		Board board = Board.getInstance();
		
		
		Hand testHand = new Hand ();
		
		Tile treasureTile = board.getTile("Temple of the Sun");
		
		Assert.assertTrue("This should be empty since hand has no cards", testHand.matchingTreasureCards(treasureTile).isEmpty());
		
		for(int i=0; i<=4; i++) {
			TreasureCard treasure = new TreasureCard(TreasureNames.TheEarthStone);
			testHand.addCard(treasure);
		}
		
	
		int amountOfMatchingCards= testHand.matchingTreasureCards(treasureTile).size();
		
		Assert.assertTrue("This should be empty since hand has no cards", amountOfMatchingCards==5);
		
	}
	
	@Test
	public void giveCard() {
		
		Hand testHand1 = new Hand ();
		Hand testHand2= new Hand ();
		
		TreasureCard treasure = new TreasureCard(TreasureNames.TheEarthStone);
		
		testHand1.addCard(treasure);
		
		testHand1.giveCard(treasure, testHand2);
		
		Assert.assertFalse("Card should no longer be in testHand1", testHand1.getCards().contains(treasure));
		
		Assert.assertTrue("Card should be longer be in testHand2", testHand2.getCards().contains(treasure));
	}

}
