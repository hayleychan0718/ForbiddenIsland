package observers;

import java.util.ArrayList;

import players.Player;
import players.PlayerList;

public class PlayerObserver {

		private static PlayerObserver playerObserver;
		private ArrayList<Player> sunkenPlayers;
		
	
		private PlayerObserver() {
			sunkenPlayers = new ArrayList<Player>();
		}
		
		public static PlayerObserver getInstance() {
			if(playerObserver==null) {
				playerObserver = new PlayerObserver();
			}
			return playerObserver;
		}
		
		
		public void updateSunk (Player player) {
			sunkenPlayers.add(player);
			
		}
		
		public void updateMoved () {
			sunkenPlayers.removeAll(sunkenPlayers);
		}
		
		public ArrayList<Player> getSunkenPlayers (){
			return sunkenPlayers;
		}
}
