package playerTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import island.board.Board;
import island.board.Tile;
import players.Diver;

public class DiverTests {

	@Test
	public void getFocedMoveableTiles() {
		Board.getInstance();
		Diver diver = Diver.getInstance("Test", 1, "$");
		
		Assert.assertEquals("Shold be equal as these are the closest tiles",diver.getForcedMoveableTiles() , diver.getStandardMoveableTiles()); 
		
		System.out.println(diver.getStandardMoveableTiles());
		
		for(Tile tile: diver.getStandardMoveableTiles()) {
			tile.sinkTile();
		}
		diver.getForcedMoveableTiles();
		
		System.out.println(diver.getForcedMoveableTiles());
		System.out.println(diver.getStandardMoveableTiles());
		//System.out.println(diver.getPlayerPawnTile().getAdjacentDiagonal());
	}

}
