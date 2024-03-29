package observerTests;

import org.junit.Assert;
import org.junit.Test;
import observers.PlayerObserver;
import players.*;
import players.PlayerList;

/**
 * Tests the functionality of the PlayerObserver, the other observers tests are integrate in the gameOver test
 * @author Liam Fitzgerald and Hayley Chan
 *
 */

public class playerObserverTest {

	@Test
	/**
	 * Tests the PlayerObserver
	 */
	public void testPlayerObserver() {
		PlayerList playerList = PlayerList.getInstance();

		Messenger tester1 =  Messenger.getInstance("Test1", 1 ,"");
		Diver tester2 =  Diver.getInstance("Test2", 2, "");
		Engineer tester3 =  Engineer.getInstance("Test3", 2, "");
		Navigator tester4 =  Navigator.getInstance("Test3", 2, "");
		
		playerList.addPlayer(tester1);
		playerList.addPlayer(tester2);
		playerList.addPlayer(tester3);
		playerList.addPlayer(tester4);

		PlayerObserver playerObserver = PlayerObserver.getInstance();

		Assert.assertTrue("List of sunken Players should be originally empty", playerObserver.getSunkenPlayers().isEmpty());

		tester1.getPlayerPawnTile().sinkTile();;

		Assert.assertTrue("Tester1 has now been sunk and should be in the list of sunken players", playerObserver.getSunkenPlayers().contains(tester1));
		
		tester2.getPlayerPawnTile().sinkTile();
		tester3.getPlayerPawnTile().sinkTile();
		tester4.getPlayerPawnTile().sinkTile();
		
		Assert.assertTrue("Tester2 has now been sunk and should be in the list of sunken players", playerObserver.getSunkenPlayers().contains(tester2));
		Assert.assertTrue("Tester3 has now been sunk and should be in the list of sunken players", playerObserver.getSunkenPlayers().contains(tester3));
		Assert.assertTrue("Tester4 has now been sunk and should be in the list of sunken players", playerObserver.getSunkenPlayers().contains(tester4));
		
		playerObserver.updateMoved();
		Assert.assertTrue("List should be empty after observer updates moved", playerObserver.getSunkenPlayers().isEmpty());
	}

}


