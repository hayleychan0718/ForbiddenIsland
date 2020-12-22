package cardTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import island.board.Board;
import island.board.Tile;
import island.cards.HelicopterCard;
import players.Engineer;
import players.Navigator;
import players.Player;

/**
 * Unit test for playing helicopter card
 * @author Hayley Chan and Liam Fitzgerald
 *
 */
public class HelicopterTest {
	@Test
	public void movePlayersToTileTest() {
		// Tile to move to 
		Board board = Board.getInstance();
		Tile tile = board.getTile("Watch Tower");
		// Creating players
		Engineer player1 = Engineer.getInstance("Player 1", 1, "$");
		Navigator player2 = Navigator.getInstance("Player 2", 2, "%");
		ArrayList<Player> chosenPlayers = new ArrayList<Player>();
		chosenPlayers.add(player1);
		chosenPlayers.add(player2);
		
		// Play helicopter card
		HelicopterCard.play(tile, chosenPlayers);
		
		assertEquals("Player 1 is on chosen tile", tile, player1.getPlayerPawnTile());
		assertEquals("Player 2 is on chosen tile", tile, player2.getPlayerPawnTile());
	}
}
