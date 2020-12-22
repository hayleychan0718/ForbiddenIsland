package cardTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import gameLogic.WaterMeter;
import island.board.Board;
import island.board.Tile;
import island.cards.FloodDeck;

/**
 * Unit tests for the flood deck 
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class FloodDeckTest {
	// Testing that the number of flood cards drawn is equal to the water level
	@Test
	public void drawFloodCardsTest() {
		FloodDeck floodDeck = FloodDeck.getInstance();
		// Draw one card from flood deck
		ArrayList<Tile> tileToFlood = floodDeck.drawCard();
		Board board = Board.getInstance();
		ArrayList<Tile> floodedTilesList = board.listOfFloodedTiles();
	
		assertEquals("Cards drawn from the flood deck has flooded the corresponding tile", floodedTilesList, tileToFlood);
		assertEquals("Number of tiles that are flooded have increased by one", 1, floodedTilesList.size());

		// Increases the water level to two
		WaterMeter waterMeter = WaterMeter.getinstance();
		waterMeter.increaseWaterLevel();
		// Draw from flood deck
		tileToFlood = floodDeck.drawCard();
		floodedTilesList = board.listOfFloodedTiles();
		assertEquals("Number of tiles that are flooded have increased by two", 3, floodedTilesList.size());
	}
	
}
