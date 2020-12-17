package playerTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import island.board.Board;
import island.board.Tile;
import players.Diver;
import players.Engineer;
import players.Messenger;
import players.Player;
import players.PlayerList;

public class PlayerTest {

	@Test
	public void getStandardMovementTest() {
		Engineer test = Engineer.getInstance("Test", 1);
		Tile playerTile = test.getPlayerPawnTile();
		
		Assert.assertEquals(playerTile.getAdjacentTiles(), test.getStandardMoveableTiles()); //Test that standard moveable tiles is equal to adjacent if all present
		
		Tile testTile = test.getStandardMoveableTiles().get(1);
		test.getStandardMoveableTiles().get(1).sinkTile();
		
		Assert.assertFalse(test.getStandardMoveableTiles().contains(testTile)); //Tests that if an adjacent tile is sunk it is no longer in standard moveable Tiles
	}
	
	@Test
	public void getShoreableTiles() {
		Engineer test = Engineer.getInstance("Test", 1);
		Tile playerTile = test.getPlayerPawnTile();
		
		Assert.assertTrue("Originally should be no shoreble Tiles", test.getShoreableTiles().isEmpty());
		
		for(Tile tile: test.getStandardMoveableTiles()) {
			tile.setFlood(true);
		}
		
		Assert.assertTrue("Now all adjacent tiles are flooded, so they should be equal", test.getShoreableTiles().equals(test.getStandardMoveableTiles()));
	}
	
	@Test
	public void getPlayersForTreasureCard() {
		Engineer tester1 =  Engineer.getInstance("Test1", 1);
		Diver tester2 =  Diver.getInstance("Test2", 2);
		Messenger tester3 =  Messenger.getInstance("Test3", 2);
		
		PlayerList playerList = PlayerList.getInstance();
		
		playerList.addPlayer(tester1);
		playerList.addPlayer(tester2);
		playerList.addPlayer(tester3);
		
		ArrayList testlist = playerList.getListOfOtherPlayers(1);
		
		Assert.assertTrue("Should be empty since no other player on tile", tester1.getPlayersForTreasureCard().isEmpty());
		
		tester2.movePlayerPawn(tester1.getPlayerPawnTile());
		tester3.movePlayerPawn(tester1.getPlayerPawnTile());

		Assert.assertTrue("Should now contain the two players as they are all on the same tile", testlist.equals(tester1.getPlayersForTreasureCard()) );
		
	}
	
	@Test
	public void captureTreasure() {
		Engineer test =  Engineer.getInstance("Test1", 1);
		
		Assert.assertFalse("Not on Treasrure Tile so can't capture treasure", test.canCaptureTreasure());
		
		Tile treasureTile=Board.getInstance().getTile("Temple of The Sun");
		
		test.movePlayerPawn(treasureTile);
		
		Assert.assertFalse("On Treasure Tile but does not have the required Cards", test.canCaptureTreasure());
		
		//Need to have cards to test this
	}
	
	
	
	
	
	
	
	
	

}
