package gameManagerTests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import gameLogic.GameManager;
import island.board.Board;
import island.board.Tile;
import players.Diver;
import players.Engineer;

/**
 * J unit test for gameManager
 * @author Liam Fitzgerald
 *
 */

public class gameManagerTest {

	@Test
	public void canSunkenPlayerMove() {

		Engineer engineer = Engineer.getInstance("Test", 1, "$");
		GameManager gameManager = GameManager.getInstance();
		
		Assert.assertTrue("Should be able to move, so should be true",gameManager.canSunkenPlayerMove(engineer));
		
		for(Tile tile: engineer.getForcedMoveableTiles()) {
			tile.setNotPresent();
		}
		
		Assert.assertFalse("All of the forced movement tiles are sunk, this should fail", gameManager.canSunkenPlayerMove(engineer));
	}

}
