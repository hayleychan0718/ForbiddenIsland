package gameTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import gameLogic.GameController;
import gameLogic.GameManager;
import gameLogic.GameView;
import gameLogic.WaterMeter;
import island.board.Board;
import island.board.Tile;
import island.enums.TreasureNames;
import observers.GameOverObserver;
import players.Engineer;


/**
 * Unit testing the game over conditions
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class GameOverTest {
	GameOverObserver gameOverObserver = GameOverObserver.getInstance();
	GameView gameView = GameView.getInstance();
	GameManager gameManager = GameManager.getInstance();
	GameController gameController = GameController.getInstance(gameManager);
	Board board = Board.getInstance();
	ArrayList<Tile> sunkTilesList = gameOverObserver.getSunkTiles();

	// Tests that game is over when Fool's Landing Tile is sunk
	@Test
	public void foolsSunkOverTest() {
		Tile foolsLandingTile = board.getTile("Fool's Landing");
		foolsLandingTile.setFlood(true);
		assertFalse("Observer not notified when Fool's Landing not sunk", sunkTilesList.contains(foolsLandingTile));
		
		foolsLandingTile.sinkTile();
		assertEquals("Observer updated when Fool's Landing sinks", foolsLandingTile, sunkTilesList.get(sunkTilesList.size()-1));
		assertTrue(gameController.loseCondition());
	}

	// Tests that game is over when both Treasure tiles have been sunk before the associated
	// treasure is captured
	@Test
	public void treasureSunkOverTest() {
		Tile caveOfEmbers = board.getTile("Cave of Embers");
		Tile caveOfShadows = board.getTile("Cave of Shadows");
		
		caveOfEmbers.setFlood(true);
		assertFalse("Observer not notified when Cave of Ember's is not sunk", sunkTilesList.contains(caveOfEmbers));
		caveOfEmbers.sinkTile();
		assertTrue("Observer updated when Cave of Embers sinks", sunkTilesList.contains(caveOfEmbers));

		caveOfShadows.setFlood(true);
		assertFalse("Observer not notified when Cave of Shadow's is not sunk", sunkTilesList.contains(caveOfShadows));
		caveOfShadows.sinkTile();
		assertTrue("Observer updated when Cave of Shadows sinks", sunkTilesList.contains(caveOfShadows));
		
		assertTrue("Losing condition when both treasure tiles have sunk and treasure is not captured", gameController.loseCondition());
	}
	
	// Tests that the game continues even if both treasure tiles have sunk if the treasure has been captured already
	public void treasureSunkTreasureCapturedTest() {
		Tile caveOfEmbers = board.getTile("Cave of Embers");
		Tile caveOfShadows = board.getTile("Cave of Shadows");
		
		TreasureNames.TheCrystalOfFire.captureTreasure();
		caveOfEmbers.setFlood(true);
		caveOfEmbers.sinkTile();
		caveOfShadows.setFlood(true);
		caveOfShadows.sinkTile();
		
		assertFalse("Treasure is captured before both tiles have sunk.", gameController.loseCondition());
	}
	
	// Tests that game is over when water meter level reaches 5
	public void waterLevelReachFiveTest() {
		WaterMeter.getinstance().increaseWaterLevel();
		WaterMeter.getinstance().increaseWaterLevel();
		assertFalse("GameOverObserver is not notified when water level reaches 3", gameOverObserver.update());
		WaterMeter.getinstance().increaseWaterLevel();
		WaterMeter.getinstance().increaseWaterLevel();
		assertTrue("GameOverObserver is notified when water level reaches 5", gameOverObserver.update());
	}
	
	//Tests sunkenPlayers method
	public void canSunkenPlayerMove() {

		Engineer engineer = Engineer.getInstance("Test", 1, "$");
		
		Assert.assertTrue("Should be able to move, so should be true",gameManager.canSunkenPlayerMove(engineer));
		
		for(Tile tile: engineer.getForcedMoveableTiles()) {
			tile.setNotPresent();
		}
		
		Assert.assertFalse("All of the forced movement tiles are sunk, this should fail", gameManager.canSunkenPlayerMove(engineer));
	}
}
