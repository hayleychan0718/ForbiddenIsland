package gameLogic;
import java.util.ArrayList;
import island.board.Tile;
import island.cards.HelicopterCard;
import island.cards.SandbagCard;
import island.cards.TreasureDeckCard;
import island.cards.WaterRiseCard;
import island.enums.TreasureNames;
import players.Player;
import players.PlayerList;

public class CardLogic {

    private static CardLogic cardLogic = null;
	private ArrayList<TreasureDeckCard> playableCards;

	public CardLogic(Player player) {
		this.playableCards = player.getHand().getPlayableCards();
	}

    public static CardLogic getInstance(Player player) {
    	if(cardLogic == null) {
    		cardLogic = new CardLogic(player);
    	}
    	return cardLogic;
    }	

	public void remove(int userInput) {
		playableCards.remove(userInput);
	}
	
	public void playHelicopter(Tile chosenTile, ArrayList<Player> chosenPlayers) {
		HelicopterCard.play(chosenTile, chosenPlayers);
	}
	
	public boolean canWinHelicopter() {
		ArrayList<Player> playersList = PlayerList.getInstance().getListOfPlayers();
        TreasureNames treasureNames[] = TreasureNames.values();
		int onFoolsLanding = 0;
		int treasuresCaptured = 0;
		for(Player player: playersList) {
			if(player.getPlayerPawnTile().getNameString() == "Fool's Landing") {
				onFoolsLanding++;
			}
		}
		for(TreasureNames treasure: treasureNames) {
			if(treasure.isCaptured())
				treasuresCaptured++;
		}
		if(onFoolsLanding == playersList.size() && treasuresCaptured == TreasureNames.values().length)
			return true;
		else 
			return false;
	}
	
	public void winHelicopter() {
		HelicopterCard.win();
	}
	
	public void playSandbag(Tile chosenTile) {
		SandbagCard.play(chosenTile);
	}
	
	public void doWaterRise() {
		WaterRiseCard.play();
	}
}