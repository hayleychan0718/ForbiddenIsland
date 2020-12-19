package gameLogic;

import java.util.ArrayList;

import island.board.Tile;
import island.cards.TreasureDeckCard;
import players.Player;

public class CardController {
    private CardLogic model;
    private CardView view;
    private static CardController controller = null;

    public CardController(CardLogic model, CardView view){
        this.model = model;
        this.view = view;
     }
    
    public static CardController getInstance(CardLogic model, CardView view) {
    	if(controller== null)
    		controller = new CardController(model, view);
    	return controller;
    }
    
  //  public void playCard(int userInput) {
    //	model.play(userInput);
    //}
    
    public void removeCard(int userInput) {
    	model.remove(userInput);
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
