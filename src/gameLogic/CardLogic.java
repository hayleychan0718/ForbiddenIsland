package gameLogic;
import java.util.ArrayList;

import island.board.Board;
import island.board.Tile;
import island.cards.HelicopterCard;
import island.cards.SandbagCard;
import island.cards.WaterRiseCard;
import island.enums.TreasureNames;
import players.Player;
import players.PlayerList;

public class CardLogic {

    private static CardLogic cardLogic = null;

    public static CardLogic getInstance() {
    	if(cardLogic == null) {
    		cardLogic = new CardLogic();
    	}
    	return cardLogic;
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
		GameView.getInstance().gameWin();
	}
	
	public void playSandbag(Tile chosenTile) {
		SandbagCard.play(chosenTile);
	}
	
	public void doWaterRise() {
		WaterRiseCard.play();
	}

	public ArrayList<Tile> getListOfTiles() {
		return Board.getInstance().listOfTiles();
	}

	public ArrayList<Tile> getListOfFloodedTiles() {
		return Board.getInstance().listOfFloodedTiles();
	}
}