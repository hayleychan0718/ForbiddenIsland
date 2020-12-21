package island.cards;

import java.util.ArrayList;

import gameLogic.CardLogic;
import island.board.Tile;
import players.Player;

public class CardController {
	private static CardController controller = null;
	
	public static CardController getInstance() {
		if(controller == null)
			controller = new CardController();
		return controller;
	}
	
	public Hand getPlayerHand(Player player) {
		return player.getHand();
	}

    public void removeCard(Card card, Player player) {
		player.getHand().removeCard(card);
    }
	
    public void doHelicopter(Tile chosenTile, ArrayList<Player> chosenPlayers, Player player){
    	CardLogic.getInstance(player).playHelicopter(chosenTile, chosenPlayers);
    }
    
	public void winHelicopter(Player player) {
		CardLogic.getInstance(player).winHelicopter();
	}
    
	public boolean canWinHelicopter(Player player) {
		return CardLogic.getInstance(player).canWinHelicopter();
	}
	
	public void doSandbag(Tile chosenTile, Player player) {
		CardLogic.getInstance(player).playSandbag(chosenTile);
	}
	
    public void doWaterRise(Player player) {
    	CardLogic.getInstance(player).doWaterRise();
    }
}
