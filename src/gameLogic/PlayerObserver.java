package gameLogic;

import java.util.ArrayList;

import players.Player;
import players.PlayerList;

public class PlayerObserver {

		private static PlayerObserver playerObserver;
		private ArrayList<Player> listOfPlayers;
		
	
		private PlayerObserver() {
			listOfPlayers= PlayerList.getInstance().getListOfPlayers();
		}
		
		public static PlayerObserver getInstance() {
			if(playerObserver==null) {
				playerObserver = new PlayerObserver();
			}
			return playerObserver;
		}
		
		public ArrayList<Player> sunkenPlayers(){
			ArrayList<Player> sunkenPlayers= new ArrayList<Player>();
			for(Player player:listOfPlayers) {
				if(player.update()==true) {
					sunkenPlayers.add(player);
				}
			}
			return sunkenPlayers;
		}
}
