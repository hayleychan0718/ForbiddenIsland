package playerTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import island.board.Board;
import island.board.Tile;
import players.Pilot;

/**
 * J unit test to test the extra ability for the Pilot
 * @author liamf
 *
 */

public class PilotTest {

	@Test
	public void getForcedMOveableTiles() {
		Pilot test = Pilot.getInstance("test", 1, "");
		
		ArrayList<Tile> presentTiles = Board.getInstance().listOfTiles();
		
		ArrayList<Tile> forcedTiles = test.getForcedMoveableTiles();
		
		Assert.assertTrue("Should be equal to the present board", forcedTiles.equals(presentTiles));
		
		Tile testTile =  presentTiles.get(1);
		presentTiles.get(1).setNotPresent();
		
		forcedTiles = test.getForcedMoveableTiles();
		
		Assert.assertFalse("Tile Should not be contained since it is no longer present", forcedTiles.contains(testTile));
	}

}
