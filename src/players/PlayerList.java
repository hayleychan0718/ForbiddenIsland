package players;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Singleton Class to manage the list of Players
 * @author Liam Fitzgerald
 *
 */
public class PlayerList {

	
	private static PlayerList thePlayerList;
	private ArrayList<Player> playerList;
	
	
	
	 private PlayerList() { 
	    	this.playerList = new ArrayList<Player>(); 
	    }
	
    public static PlayerList getInstance(){
        if(thePlayerList == null){
        	thePlayerList = new PlayerList();
        }
        return thePlayerList;
    }
    
    public int getNumPlayers() {
    	return playerList.size();
    }
    //Since player number starts at 1
    public Player getPlayer(int i) {
    	return playerList.get(i-1);
    }
    
    public int getPlayerIndex(Player player){
    	return playerList.indexOf(player)+1;
    }
    
    public void addPlayer(Player newPlayer) {
    	playerList.add(newPlayer);
    }
    
    public ArrayList<Player> getListOfPlayers(){
    	return playerList;
    }
    
   /**
    * For inputed player get the other players
    * @param playerNumber
    * @return otherListOfPlayers
    */
    public ArrayList<Player> getListOfOtherPlayers(int playerNumber){
    	ArrayList<Player> otherListOfPlayers= new ArrayList<Player>(); 
    	for(Player player: playerList) {
    		if (player.playerNumber!=playerNumber) {
    			otherListOfPlayers.add(player);
    		}
    	}
    	return otherListOfPlayers;
    	
    }
    
    //Why is this need?
    public ArrayList<Player> getPlayersForHelicopter(){
    	ArrayList<Player> playersForHelicopter = new ArrayList<>(playerList);
    	return playersForHelicopter;
    }
}


