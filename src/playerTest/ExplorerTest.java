package playerTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import island.board.Tile;
import players.Explorer;

public class ExplorerTest {

	@Test
	public void getForcedMovementTiles() {
		Explorer explorer = Explorer.getInstance("Test", 1, "$");
		Tile pawnTile = explorer.getPlayerPawnTile();
		
		Assert.assertTrue("The forced movement Tiles of the Explorer should be equal to the adjacent and diagonal tiles",explorer.getForcedMoveableTiles().equals(pawnTile.getAdjacentDiagonal()));
		
	    pawnTile.getAdjacentTiles().get(1).sinkTile(); //Sinks a tile
	    
	    Assert.assertFalse("This sunken Tile should no longer be contained in the forced movement tile",explorer.getForcedMoveableTiles().contains(pawnTile.getAdjacentTiles().get(1)));
	}

}
