package cardsTests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import island.board.Board;
import island.board.Tile;
import island.cards.Hand;
import island.cards.TreasureCard;
import island.enums.TreasureNames;
import players.Player;

public class handTests {

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
