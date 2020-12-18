package observers;

import java.util.ArrayList;

import players.Player;
import players.PlayerList;

public class PlayerObserver {

		private static PlayerObserver playerObserver;
		private ArrayList<Player> listOfPlayers;
		private ArrayList<Player> sunkenPlayers;
		
	
		private PlayerObserver() {
			listOfPlayers= PlayerList.getInstance().getListOfPlayers();
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
		
//		public ArrayList<Player> sunkenPlayers(){
//			ArrayList<Player> sunkenPlayers= new ArrayList<Player>();
//			for(Player player:listOfPlayers) {
//				if(player.update()==true) {
//					sunkenPlayers.add(player);
//				}
//			}
//			return sunkenPlayers;
//		}
//		
//		public ArrayList<Player> sunkenPlayers(){
//			ArrayList<Player> sunkenPlayers= new ArrayList<Player>();
//			for(Player player:listOfPlayers) {
//				if(player.update()==true) {
//					sunkenPlayers.add(player);
//				}
//			}
//			return sunkenPlayers;
//		}
}
