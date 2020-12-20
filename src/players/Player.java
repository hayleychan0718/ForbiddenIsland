package players;

/**
 * Abstract Class that sets up the base player class
 * Extended by Diver,Engineer,Explorer,Messenger,Navigator,Pilot
 * @author Liam Fitzgerald
 */
import java.util.*;

import org.junit.Assert;

import island.board.*;
import island.cards.*;
import island.enums.TreasureNames;
import pawns.Pawn;

public abstract class Player {


	private String playerName;
	private String symbol;
	protected int playerNumber;
	private Hand playerHand;
	protected Pawn playerPawn;
	protected int playerActions;
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
	 * Gets the current list of move able tile for the player
	 * @return moveableTiles
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
	 * @return shoreableTiles
	 */
	public ArrayList<Tile> getShoreableTiles() {  
		Tile pawnTile  =getPlayerPawnTile();
		ArrayList<Tile> adjacentTiles = pawnTile.getAdjacentTiles(); 
		adjacentTiles.add(pawnTile); //Players tile may also be flooded
		ArrayList<Tile> shoreableTiles = new ArrayList<Tile>();

		for(Tile tile: adjacentTiles) {
			if(tile.isFlooded() ==true && tile.isPresent()) {
				shoreableTiles.add(tile);
			}
		}
		return shoreableTiles;
	}

	/**
	 * Gets the list of tiles a player can move to when their tile has been sunk
	 * @return ForcedMoveableTiles
	 */
	public ArrayList<Tile> getForcedMoveableTiles(){ 
		return getStandardMoveableTiles();
	}
	
	/**
	 * Gets the list the list of Players a player can give a card to
	 * @return
	 */
	public ArrayList<Player> getPlayersForCard() { 
		ArrayList<Player> playersForCard = new ArrayList <Player>();
		PlayerList playerList = PlayerList.getInstance();

		for (Player otherPlayer:playerList.getListOfOtherPlayers(playerNumber)) { //creates a list of the other players using the current player number
			if(playerPawn.getPawnTile()==otherPlayer.getPlayerPawnTile())
				playersForCard.add(otherPlayer);
		}
		return playersForCard;
	}

	/**
	 * Checks whether a player can capture a treasure, if true captures the treasure
	 * @return true/false
	 */
	//ArrayList<Card> matchingTreasureCards
	public boolean canCaptureTreasure() {
         ArrayList<Card> matchingTreasureCards = matchingTreasureCards();
		if(!onTreasureTile()) {
			System.out.println("not on treasure tile");
			return false;
		}
		
		System.out.println(matchingTreasureCards);
		
		if(matchingTreasureCards.size()>=4) {
			captureTreasure();
			playerHand.getCards().removeAll(matchingTreasureCards);
			return true;
		}
		return false;

	}
	
	//Captures the treasure
	public void captureTreasure() {
		Tile pawnTile = getPlayerPawnTile();
		
		pawnTile.getTreasure().captureTreasure();
	}
	
	//Checks whether on treasure tile This is not working correctly
	public boolean onTreasureTile() {
		if(getPlayerPawnTile().hasTreasure()) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a list of cards from the players hand that match the current treasure tile
	 * @return matchingTreasureCards
	 */
	public ArrayList<Card> matchingTreasureCards() {
		ArrayList<Card> matchingTreasureCards = new ArrayList <Card>();

		for(Card cardInHand:playerHand.getCards()) {
			if(cardInHand.getName() == getPlayerPawnTile().getTreasure().getString()) { //may need null check before 
				matchingTreasureCards.add(cardInHand);
			}
		}
		return matchingTreasureCards;
	}
	
	/**
	 * Gives a cad to another player
	 * @param toGive
	 * @param toRecieve
	 */
	public void giveCard(Card toGive, Hand toRecieve) {
		playerHand.giveCard(toGive, toRecieve);
	}
	
	public void endTurn() {
		playerActions=0;
	}

	@Override
	public String toString() {
		return playerName + " (" + this.getClass().getSimpleName() + ")";
	}

//	public String getRole() {
//		return this.getClass().getSimpleName();
//	}

	//Returns players name
	public String getName() {
		return playerName;
	}

	public ArrayList<Card> showHand(){
		return playerHand.getCards();
	}

	public Hand getHand() {
		return playerHand;
	}

	//Needs associated pawn class
	public Tile getPlayerPawnTile() {
		return 	playerPawn.getPawnTile();
	}
	
	public TreasureNames getTreasure() {
		return getPlayerPawnTile().getTreasure();
	}
	

	public void ReStockActions() {
		playerActions +=3;
	}

	//Moves the players pawn
	public void movePlayerPawn(Tile tile) {
		playerPawn.movePawn(tile);
	}

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
	
	public String getSymbol() {
		return symbol;
	}

	
	public static void main (String[] args) {

		
        Engineer test =  Engineer.getInstance("Test1", 1,"%");
		
		PlayerList.getInstance().addPlayer(test);
		
		Tile treasureTile=Board.getInstance().getTile("Temple of the Sun");
//		
     	test.movePlayerPawn(treasureTile);
//		

//
		Board board = Board.getInstance();
		
		System.out.println(board.showBoard());
		
		for(int i=0; i<=4; i++) {
			TreasureCard treasure = new TreasureCard(TreasureNames.TheEarthStone);
			test.getHand().addCard(treasure);
		}
		
		
		
		//System.out.println(test.matchingTreasureCards());
		ArrayList<Card> matchingTreasureCards= test.matchingTreasureCards();
		System.out.println(test.getHand().getCards());
		
		System.out.println(matchingTreasureCards.size());
		System.out.println(matchingTreasureCards);
		System.out.println(test.canCaptureTreasure());
		
		System.out.println(board.showBoard());
		
		System.out.println(test.getTreasure().isCaptured());
		
		System.out.println(test.getHand().getCards());

		
		//System.out.println(test.canCaptureTreasure(test.matchingTreasureCards()));

		
		
		
	}

}




