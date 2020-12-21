package playerTest;

import static org.junit.Assert.*;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import island.board.Board;
import island.board.Tile;
import island.cards.TreasureCard;
import island.enums.TreasureNames;
import players.*;

/**
 * J unit tests for the Player Class, Engineer used since it acts the same for the tests
 * @author Liam Fitzgerald
 *
 */

public class PlayerTest {

	@Test
	public void getStandardMovementTest() {
		Engineer test = Engineer.getInstance("Test", 1,"");
		Tile playerTile = test.getPlayerPawnTile();

		Assert.assertEquals(playerTile.getAdjacentTiles(), test.getStandardMoveableTiles()); //Test that standard moveable tiles is equal to adjacent if all present

		Tile testTile = test.getStandardMoveableTiles().get(1);
		test.getStandardMoveableTiles().get(1).sinkTile();

		Assert.assertFalse(test.getStandardMoveableTiles().contains(testTile)); //Tests that if an adjacent tile is sunk it is no longer in standard moveable Tiles
	}

	@Test
	public void getShoreableTiles() {
		Engineer test = Engineer.getInstance("Test", 1,"");
		Tile playerTile = test.getPlayerPawnTile();

		Board board = Board.getInstance();

		System.out.println(test.getShoreableTiles());

		Assert.assertTrue("Originally should be no shoreble Tiles", test.getShoreableTiles().isEmpty());

		for(Tile tile: test.getStandardMoveableTiles()) {
			tile.setFlood(true);
		}

		Assert.assertTrue("Now all adjacent tiles are flooded, so they should be equal", test.getShoreableTiles().equals(test.getStandardMoveableTiles()));
	}

	@Test
	public void getPlayersForTreasureCard() {
		Engineer tester1 =  Engineer.getInstance("Test1", 1,"");
		Diver tester2 =  Diver.getInstance("Test2", 2,"");
		Messenger tester3 =  Messenger.getInstance("Test3", 2,"");

		PlayerList playerList = PlayerList.getInstance();

		playerList.addPlayer(tester1);
		playerList.addPlayer(tester2);
		playerList.addPlayer(tester3);

		ArrayList testlist = playerList.getListOfOtherPlayers(1);

		Assert.assertTrue("Should be empty since no other player on tile", tester1.getPlayersForCard().isEmpty());

		tester2.movePlayerPawn(tester1.getPlayerPawnTile());
		tester3.movePlayerPawn(tester1.getPlayerPawnTile());

		Assert.assertTrue("Should now contain the two players as they are all on the same tile", testlist.equals(tester1.getPlayersForCard()) );

	}

	@Test
	public void captureTreasure() {
		Engineer test =  Engineer.getInstance("Test1", 1,"");
		
		PlayerList.getInstance().addPlayer(test);
		
		Assert.assertFalse("Not on Treasure Tile should be false",test.canCaptureTreasure());
		
		Tile treasureTile=Board.getInstance().getTile("Temple of the Sun");

     	test.movePlayerPawn(treasureTile);
     	Assert.assertFalse("Treasure Tile but doesn't have required cards",test.canCaptureTreasure());

     	TreasureCard treasure1 = new TreasureCard(TreasureNames.TheEarthStone);
		test.getHand().addCard(treasure1);
     	
		Assert.assertFalse("Treasure Tile , only has one card",test.canCaptureTreasure());
		
		for(int i=0; i<=4; i++) {
			TreasureCard treasure = new TreasureCard(TreasureNames.TheEarthStone);
			test.getHand().addCard(treasure);
		}
		
		Assert.assertTrue("Treasure Tile and has required cards",test.canCaptureTreasure());

	}
}
