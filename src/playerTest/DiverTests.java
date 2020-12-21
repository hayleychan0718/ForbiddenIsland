package playerTest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import island.board.Board;
import island.board.Tile;
import players.Diver;

/**
 * J unit test to test the extra ability of the Diver
 * @author liamf
 *
 */

public class DiverTests {

	@Test
	public void getFocedMoveableTiles() {

		Board board =Board.getInstance();
		Diver diver = Diver.getInstance("Test", 1, "$");
		
		//System.out.println(diver.getStandardMoveableTiles());
		
		ArrayList<Tile> listOfPresentTiles = board.listOfTiles();
		
		Tile diverTile = diver.getPlayerPawnTile();
		Tile remainingTile1 = board.getTile("Lost Lagoon");
		Tile remainingTile2 = board.getTile("Gold Gate");
		
		ArrayList<Tile> closestTiles = new ArrayList<>();
		
		for(Tile tile: listOfPresentTiles) {
			if(tile!=remainingTile1 && tile!=remainingTile2)
				tile.setNotPresent();
		}
		diver.getForcedMoveableTiles();
		
		System.out.println("Reamaining Present Tiles " +board.listOfPresentTiles());
		//Gets distance from players tile to the remaining tiles
		Double distance1 = Board.getDistance(diverTile, remainingTile1);
		Double distance2 = Board.getDistance(diverTile, remainingTile2);
		
		System.out.println("Distance: 1" +distance1);
		System.out.println("Distance 2" +distance2);
		
		if(distance1<distance2) {
			closestTiles.add(remainingTile1);
		}
		else if(distance2<distance1){
			closestTiles.add(remainingTile2);
		}
		else {
			closestTiles.add(remainingTile2);
			closestTiles.add(remainingTile1);
		}
		
		System.out.println("Forced Moveable Tiles:" +diver.getForcedMoveableTiles());
		System.out.println("Closest Tiles:" + closestTiles);
		
		Assert.assertTrue("Should be able to swim to the one remaining tile whereever it is", diver.getForcedMoveableTiles().equals(closestTiles));
	}

}
