package island.cards;

import java.util.ArrayList;

import gameLogic.CardLogic;
import island.board.Tile;
import players.Player;

public class CardController {
	private static CardController controller = null;
	private CardLogic model;
	
    private CardController(CardLogic model) {
    	this.model = model;        
    }
	
	public static CardController getInstance(CardLogic model) {
		if(controller == null)
			controller = new CardController(model);
		return controller;
	}
	
	public Hand getPlayerHand(Player player) {
		return player.getHand();
	}

    public void removeCard(Card card, Player player) {
		player.getHand().removeCard(card);
    }
	
    public void doHelicopter(Tile chosenTile, ArrayList<Player> chosenPlayers){
    	model.playHelicopter(chosenTile, chosenPlayers);
    }
    
	public void winHelicopter() {
		model.winHelicopter();
	}
    
	public boolean canWinHelicopter() {
		return model.canWinHelicopter();
	}
	
	public void doSandbag(Tile chosenTile) {
		model.playSandbag(chosenTile);
	}
	
    public void doWaterRise() {
    	model.doWaterRise();
    }
}
