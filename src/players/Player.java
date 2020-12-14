package players;

/**
 * Abstract Class that sets up the base player class
 */
import java.util.*;

import javax.management.MBeanTrustPermission;

import island.board.*;
import island.cards.*;
import island.enums.TreasureNames;
import pawns.Pawn;

public class Player {


	private String playerName;
	protected int playerNumber;
	private Hand playerHand;
	protected Pawn playerPawn;
	protected int playerActions;
	protected Board board;
	protected ArrayList<String> playerTreasures;
	private Observer observer;



	//Maybe add getter for shoreable tiles and give card so don't have to recreate.

	//constructor
	public Player(String playerName, int playerNumber) { //Duplicte player number
		this.playerName=playerName;
		this.playerNumber=playerNumber;
		this.playerHand = new Hand();
		playerActions=0;
		board=Board.getInstance();
		//player pawn
		//Randomly select player role
	}
	/**
	 * Method the returns the available tiles for standard movement
	 * 
	 */
	public ArrayList<Tile> getStandardMoveableTiles() {  //returns the moveable tiles //having order makes it easier to understand were tiles areList
		Tile pawnTile  = getPlayerPawnTile();
		ArrayList<Tile> adjcacent = pawnTile.getAdjacentTiles();
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		
		for(Tile tile: adjcacent) {	//Checks if the tiles are present if not removes them
			if(tile.isPresent()==true) { // Why or null
				moveableTiles.add(tile); //Not sure has this been fixed, amy remove tile mid loop
			}
		}
		return moveableTiles;
	}

	public ArrayList<Tile> getShoreableTiles() {  //returns the shoreable tiles
		Tile pawnTile  =getPlayerPawnTile();
		ArrayList<Tile> adjacentTiles = pawnTile.getAdjacentTiles(); //Can also shore up tile player is standing on
		adjacentTiles.add(pawnTile); //Players tile may also be flooded
		ArrayList<Tile> shoreableTiles = new ArrayList<Tile>();

		for(Tile tile: adjacentTiles) {
			if(tile.isFlooded() ==true) {
				shoreableTiles.add(tile);
			}
		}
		return shoreableTiles;
	}

	public ArrayList<Tile> getFocredMoveableTiles(){ //occurs when the player is one a oceantile
		return getStandardMoveableTiles();
	}
	//Method Returns the list of Players the current player can give cards to
	public ArrayList<Player> getPlayersForTreasureCard() { 
		ArrayList<Player> playersForTreasureCard = new ArrayList <Player>();
		PlayerList playerList = PlayerList.getInstance();

		for (Player otherPlayer:playerList.getListOfOtherPlayers(playerNumber)) { //creates a list of the other players using the current player number
			if(playerPawn.getPawnTile()==otherPlayer.getPlayerPawnTile())
				playersForTreasureCard.add(otherPlayer);
		}
		return playersForTreasureCard;
	}

	//Method returns whether a treasure was captured or not//What about if no teasure on tile
	public boolean canCaptureTreasure() {
		ArrayList<TreasureDeckCard> matchingTreasureCards = matchingTreasureCards();
		if(!onTreasureTile()) return false;
		
		if(matchingTreasureCards.size()>=4) {
			CaptureTreasure();
			playerHand.getCards().removeAll(matchingTreasureCards);
			return true;
		}
		return false;

	}
	
	public void CaptureTreasure() {
		Tile pawnTile = getPlayerPawnTile();
		
		pawnTile.getTreasure().captureTreasure();
	}
	
	public boolean onTreasureTile() {
		if(getTreasure()==null) {
			return false;
		}
		else {System.out.println(getTreasure());}
		return true;
	}

	public ArrayList<TreasureDeckCard> matchingTreasureCards() {
		ArrayList<TreasureDeckCard> matchingTreasureCards = new ArrayList <TreasureDeckCard>();

		for(TreasureDeckCard cardInHand:playerHand.getCards()) {
			if(cardInHand.getName() == getPlayerPawnTile().getTreasure().getString()) { //checks if card in hand matches treasure associated with the tile the player is on 
				matchingTreasureCards.add(cardInHand);
			}
		}
		return matchingTreasureCards;
	}

	public boolean canShoreUp() {
		ArrayList<Tile> shoreableTiles = getShoreableTiles();

		if(shoreableTiles.isEmpty()) {
			System.out.println("There are no tiles available to shore up \n");
			return false;
		}
		else {
			return true;
		}
	}
	
	public void giveCard(TreasureDeckCard toGive, Hand toRecieve) {
		playerHand.giveCard(toGive, toRecieve);
	}
	
	public void endTurn() {
		playerActions=0;
	}
	
	public boolean update() {
		if(!getPlayerPawnTile().isPresent()) {
			return true;
		}
		else return false;
	}

	@Override
	public String toString() {
		return playerName + " (" + this.getClass().getSimpleName() + ")";
	}

	public String getRole() {
		return this.getClass().getSimpleName();
	}

	//Returns players name
	public String getName() {
		return playerName;
	}

	public ArrayList<TreasureDeckCard> showHand(){
		return playerHand.getCards();
	}

	public Hand getHand() {
		return playerHand;
	}

	//Needs associated pawn class
	public Tile getPlayerPawnTile() {
		return 	playerPawn.getPawnTile();
		//	return  ///
	}
	
	public TreasureNames getTreasure() {
		return getPlayerPawnTile().getTreasure();
	}

	public void ReStockActions() {
		playerActions +=3;
	}

	//Need to make attribute in player role

	//Moves the players pawn
	public void movePlayerPawn(Tile tile) {
		playerPawn.movePawn(tile);
		//Maybe pawn moves links with player role
	}
	//Returns the players number
	public int getPlayerNumber() {
		return playerNumber;
	}

	public void decrementPlayerActions() {
		playerActions--;
	}

	public void reStockActions() {
		playerActions=3;
	}
	
	public void emptyActions() {
		playerActions=0;
	}

	public int getPlayerActions() {
		return playerActions;
	}


}




