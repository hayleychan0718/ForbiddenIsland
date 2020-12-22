package playerTest;

import org.junit.Assert;
import org.junit.Test;
import players.*;

/**
 * Unit test to test the functionality of the Messenger
 * @author Liam Fitzgerald and Hayley Chan
 *
 */

public class MessengerTest {

	@Test
	public void playersForTreasureCard() {
		Messenger tester1 =  Messenger.getInstance("Test1", 1 ,"");
		Diver tester2 =  Diver.getInstance("Test2", 2, "");
		Engineer tester3 =  Engineer.getInstance("Test3", 2, "");
		
		PlayerList playerList = PlayerList.getInstance();
		
		playerList.addPlayer(tester1);
		playerList.addPlayer(tester2);
		playerList.addPlayer(tester3);
		
		Assert.assertTrue("The list of players for treasure card should be all the other players", tester1.getPlayersForCard().equals(playerList.getListOfOtherPlayers(1)));
		
	}

}
