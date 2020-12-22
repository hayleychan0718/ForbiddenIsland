package cardTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import island.board.Board;
import island.board.Tile;
import island.cards.SandbagCard;

/**
 * Unit testing the sandbag card
 * @author Hayley Chan and Liam Fitzgerald
 * 
 */
public class SandbagTest {
	// Tests that sandbag card shores up the tile
	@SuppressWarnings("static-access")
	@Test
	public void testSandbagCard() {
		Board board = Board.getInstance();
		Tile tile = board.getTile("Whispering Garden");
		tile.setFlood(true);
		assertTrue("The tile is flooded", board.listOfFloodedTiles().contains(tile));

		// Use the sandbag card to shore up the tile
		SandbagCard sandbagCard = new SandbagCard();
		sandbagCard.play(tile);
		assertFalse("The tile is not flooded anymore", board.listOfFloodedTiles().contains(tile));
	}
}
