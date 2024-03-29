package players;

/**
 * Abstract Class that sets up the base player class
 * Extended by Diver,Engineer,Explorer,Messenger,Navigator,Pilot
 * @author Liam Fitzgerald and Hayley Chan
 */
import java.util.*;
import island.board.*;
import island.cards.*;
import island.enums.TreasureNames;
import pawns.Pawn;

public abstract class Player {
	/*
	 * Instance variables
	 */
	private String playerName;
	private String symbol;
	protected int playerNumber;
	private Hand playerHand;
	protected Pawn playerPawn;
	private int playerActions;
	protected Board board;

	/**
	 * Creates everything needed for Player class
	 * @param playerName
	 * @param playerNumber
	 * @param symbol that the player wants to be represented by on the board
	 */
	public Player(String playerName, int playerNumber, String symbol) { 
		this.playerName=playerName;
		this.playerNumber=playerNumber;
		this.playerHand = new Hand();
		this.symbol=symbol;
		playerActions=0;
		board=Board.getInstance();
	}
	
	/**
	 * Gets the current list of move able tile for the player's standard movement
	 * @return List of standard move able Tiles
	 */
	public ArrayList<Tile> getStandardMoveableTiles() {  
		Tile pawnTile  = getPlayerPawnTile();
		ArrayList<Tile> adjcacent = pawnTile.getAdjacentTiles();
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		
		for(Tile tile: adjcacent) {	
			if(tile.isPresent()==true) {
				moveableTiles.add(tile); 
			}
		}
		return moveableTiles;
	}

	/**
	 * Gets the current list of tiles that a player can shore up
	 * @return List of Shore able Tiles
	 */
	public ArrayList<Tile> getShoreableTiles() {  
		Tile pawnTile  =getPlayerPawnTile();
		ArrayList<Tile> adjacentTiles = pawnTile.getAdjacentTiles(); 
		adjacentTiles.add(pawnTile); 
		ArrayList<Tile> shoreableTiles = new ArrayList<Tile>();

		for(Tile tile: adjacentTiles) {
			if(tile.isFlooded() ==true && tile.isPresent()) { //Checks if flooded and present
				shoreableTiles.add(tile);
			}
		}
		return shoreableTiles;
	}
	
	/**
	 * Gets the list of tiles a player can move to when their tile has been sunk
	 * @return List of Forced Moveable Tiles
	 */
	public ArrayList<Tile> getForcedMoveableTiles(){ 
		return getStandardMoveableTiles();
	}
	
	/**
	 * Gets the list the list of Players a player can give a card to
	 * @return List of Possible Players for Card
	 */
	public ArrayList<Player> getPlayersForCard() { 
		ArrayList<Player> playersForCard = new ArrayList <Player>();
		PlayerList playerList = PlayerList.getInstance();

		for (Player otherPlayer:playerList.getListOfOtherPlayers(playerNumber)) { //creates a list of the other players using the current player number
			if(playerPawn.getPawnTile()==otherPlayer.getPlayerPawnTile()) //Checks if they are on the same tile
				playersForCard.add(otherPlayer);
		}
		return playersForCard;
	}

	/**
	 * Checks whether a player can capture a treasure, if true captures the treasure
	 * @return true/false
	 */
	public boolean canCaptureTreasure() {
  
		if(!onTreasureTile()) {
			return false;
		}
	      ArrayList<Card> matchingTreasureCards = playerHand.matchingTreasureCards(getPlayerPawnTile());
		
		if(matchingTreasureCards.size()>=4) { //Has the required number of treasure cards
			captureTreasure();
			playerHand.getCards().removeAll(matchingTreasureCards);  //Discards all the matchign cards as treasure has been captured
			return true;
		}
		return false;

	}
	
	/**
	 * Capture the treasure a players pawn is on
	 */
	public void captureTreasure() {
		Tile pawnTile = getPlayerPawnTile();
		
		pawnTile.getTreasure().captureTreasure();
	}
	
	/**
	 * Checks if the players is on a treasure tile
	 * @return true/false, true if on a Treasure Tile
	 */
	public boolean onTreasureTile() {
		if(getPlayerPawnTile().hasTreasure()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gives a card to another player
	 * @param toGive Card to give
	 * @param to Receive hand of receiving player
	 */
	public void giveCard(Card toGive, Hand toRecieve) {
		playerHand.giveCard(toGive, toRecieve);
	}
	
	/**
	 * Ends the players turn by setting actions to zero
	 */
	public void endTurn() {
		playerActions=0;
	}

	@Override
	public String toString() {
		return playerName + " (" + this.getClass().getSimpleName() + ")";
	}

	/**
	 * Gets the the player's name
	 * @return String of player's name
	 */
	public String getName() {
		return playerName;
	}

	/**
	 * Get the cards of a player's hand
	 * @return List of cards in the player's hand
	 */
	public ArrayList<Card> getCards(){
		return playerHand.getCards();
	}

	/**
	 * Gets the player's hand 
	 * @return The player's hand
	 */
	public Hand getHand() {
		return playerHand;
	}

	/**
	 * Gets the tile that the player's pawn is on
	 * @return
	 */
	public Tile getPlayerPawnTile() {
		return 	playerPawn.getPawnTile();
	}
	
	/**
	 * Gets the name of the treasure the pawn is on
	 * @return TreasureNames
	 */
	public TreasureNames getTreasure() {
		return getPlayerPawnTile().getTreasure();
	}
	
	/**
	 * Re stocks the players actions by adding 3 actions, a player may start turn on two if the saved a tile or gave a card just after their turn
	 */
	public void ReStockActions() {
		playerActions +=3;
	}

	/**
	 * moves the player pawn
	 * @param tile
	 */
	public void movePlayerPawn(Tile tile) {
		playerPawn.movePawn(tile);
	}

	/**
	 * Decrease player's actions by one
	 */
	public void decrementPlayerActions() {
		playerActions--;
	}
	
	/**
	 * Returns players actions
	 * @return players actions
	 */
	public int getPlayerActions() {
		return playerActions;
	}
	
	/**
	 * Returns the chosen symbol to represent the player on the board
	 * @return the chosen symbol
	 */
	public String getSymbol() {
		return symbol;
	}

}




