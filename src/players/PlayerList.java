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
    
    /*
     * Gets the number of players in the list
     */
    public int getNumPlayers() {
    	return playerList.size();
    }
 
    /**
     * Gets a player for a given index, -1 off it since player number starts at 1
     * @param i index
     * @return Player
     */
    public Player getPlayer(int i) {
    	return playerList.get(i-1);
    }
    
    /**
     * Get the index for a given player
     * @param player
     * @return int index
     */
    public int getPlayerIndex(Player player){
    	return playerList.indexOf(player)+1;
    }
    
    /**
     * Adds a player to  
     * @param newPlayer
     */
    public void addPlayer(Player newPlayer) {
    	playerList.add(newPlayer);
    }
    
    /**
     * Returns the Array list of players
     * @return playerList
     */
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
    
    /**
     * Gets the players for Helicopter
     * @return
     */
    public ArrayList<Player> getPlayersForHelicopter(){
    	ArrayList<Player> playersForHelicopter = new ArrayList<>(playerList);
    	return playersForHelicopter;
    }
}


