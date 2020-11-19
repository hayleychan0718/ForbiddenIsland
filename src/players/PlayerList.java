package players;

import java.util.ArrayList;
import java.util.List;

//Do we need insertion order for list are all these methods necessary.
//Need to figure out player List and how to deal with statics.

//Singleton Class for managing the list of Players in a game of Cluedo
//Write a static method that has return type object of this singleton class. Here, the concept of Lazy initialization is used to write this static method.
public class PlayerList {

	
	private static PlayerList thePlayerList;
	private List<Player> playerList;
	
	
	
	 private PlayerList() { //Constructor private since singleton
	    	this.playerList = new ArrayList<Player>(); //May need insertion order
	    }
	 //===========================================================
    // Get Instance of Singleton
    //===========================================================
    /**
     * Get the single instance of the List of Players in the game
     * @return The PlayerList object
     */
	//Static method to create instance of Singleton Class
    public static PlayerList getInstance(){
        if(thePlayerList == null){
        	thePlayerList = new PlayerList();
        }
        return thePlayerList;
    }
    
    public int getNumPlayers() {
    	return playerList.size();
    }
    
    public Player getPlayer(int i) {
    	return playerList.get(i-1);
    }
    
    public int getPlayerIndex(Player player){
    	return playerList.indexOf(player)+1;
    }
    
    public void addPlayer(Player newPlayer) {
    	playerList.add(newPlayer);
    }
    
    public List<Player> getListOfPlayers(){
    	return playerList;
    }
    
    
	//===========================================================
	// Singleton destroyer for unit testing ONLY
	//===========================================================
	//Not sure what this is for
	public void destroyMe() {
	    playerList = null;
	}
}


