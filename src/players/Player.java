package players;

/**
 * Abstract Class that sets up the base player class
 */
import java.util.*;
import island.board.*;
import island.cards.*;
import pawns.Pawn;

public class Player {


	private String playerName;
	protected int playerNumber;
	private Hand playerHand;
	private boolean canTakeTurn; //Not sure needed if one person out game is over
	protected Pawn playerPawn;
	protected int playerActions;
	protected Board board;
	protected ArrayList<String> playerTreasures;



	//Maybe add getter for shoreable tiles and give card so don't have to recreate.

	//constructor
	public Player(String playerName, int playerNumber) { //Duplicte player number
		this.playerName=playerName;
		this.playerNumber=playerNumber;
		this.playerHand = new Hand();
		this.canTakeTurn = true;
		playerActions=3;
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
		ArrayList<Tile> moveableTiles = pawnTile.getAdjacentTiles();

		for(Tile tile: moveableTiles) {	//Checks if the tiles are present if not removes them
			if(tile.isPresent()==false) { // Why or null
				moveableTiles.remove(tile); //Not sure has this been fixed, amy remove tile mid loop
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

	//Method returns whether a treasure was captured or not
	public boolean canCaptureTreasure() {
		ArrayList<TreasureDeckCard> matchingTreasureCards = matchingTreasureCards();

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
	
	

//	public boolean canGiveTreausreCard() {
//		ArrayList<Player> playersForTreasureCard = giveTreasureCard();
//
//		if(playersForTreasureCard.isEmpty()) {
//			System.out.println("There is no players to give a treasure card to \n");
//			return false;
//		}
//		else {
//			return true;
//		}
//	}

	@Override
	public String toString() {
		return playerName;
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


	//Need to make attribute in player role

	public boolean CanTakeTurn() {
		return canTakeTurn;
	}
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

	public void restockPlayerActions() {
		playerActions=3;
	}

	public int getPlayerActions() {
		return playerActions;
	}


}




