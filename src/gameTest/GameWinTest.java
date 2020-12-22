package gameTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gameLogic.CardLogic;
import island.board.Board;
import island.board.Tile;
import island.enums.TreasureNames;
import players.Engineer;
import players.Pilot;

public class GameWinTest {
	@Test
	public void winGameTest() {
		CardLogic cardLogic = CardLogic.getInstance();
		Board board = Board.getInstance();
		Tile foolsLandingTile = board.getTile("Fool's Landing");
		// Create players
		Pilot player1 = Pilot.getInstance("Player 1", 1, "$");
		Engineer player2 = Engineer.getInstance("Player 2", 2, "%");
		// Capture 3 treasures
		TreasureNames.TheCrystalOfFire.captureTreasure();
		TreasureNames.TheEarthStone.captureTreasure();
		TreasureNames.TheOceansChalice.captureTreasure();
		// Move players to fool's landing
		player1.movePlayerPawn(foolsLandingTile);
		player2.movePlayerPawn(foolsLandingTile);
		
		assertFalse("Can't win game yet since only 3 treasures are captured", cardLogic.canWinHelicopter());
		
		// Move one player to a different tile
		player2.movePlayerPawn(board.getTile("Observatory"));
		assertFalse("Can't win game yet since all players not on Fool's Landing", cardLogic.canWinHelicopter());
		
		// Capture last treasure and all players on Fool's Landing
		player2.movePlayerPawn(board.getTile("Fool's Landing"));
		TreasureNames.TheStatueOfTheWind.captureTreasure();
		assertTrue("Win the game when all treasures captured and both players on Fool's Landing", cardLogic.canWinHelicopter());
	}
}
