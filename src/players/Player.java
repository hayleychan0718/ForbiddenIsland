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
	public LinkedList<Tile> getStandardMoveableTiles() {  //returns the moveable tiles //having order makes it easier to understand were tiles areList
		int i =0;
		Tile pawnTile  = getPlayerPawnTile();
		LinkedList<Tile> moveableTiles = pawnTile.getAdjacentTiles();
		System.out.println("Tile you can move to:");
		for(Tile tile: moveableTiles) {	//Checks if the tiles are present if not removes them
			if(tile.isPresent()==false || tile==null ) { // Why or null
				moveableTiles.remove(tile);
			}
			else {
				System.out.println( tile.getName() + "["+ i + "]");	//prints the tiles you can move to, Is this bad practice
				i++;
			}
		}
		return moveableTiles;
	}

	public LinkedList<Tile> getShoreableTiles() {  //returns the shoreable tiles
		int i =0;
		Tile pawnTile  =getPlayerPawnTile();
		LinkedList<Tile> shoreableTiles = pawnTile.getAdjacentTiles();
		System.out.println("Tile you can shore up to:");

		for(Tile tile: shoreableTiles) {
			if(tile.isFlooded() ==false || tile==null ) {
				shoreableTiles.remove(tile);
			}
			else {
				System.out.println( tile.getName() + "[" + i + "]");
				i++;
			}
		}
		return shoreableTiles;

	}

	public LinkedList<Tile> getFocredMoveableTile(){ //occurs when the player is one a oceantile
		System.out.println("You are forced to move as the tile you are on is no longer present\n");
		return getStandardMoveableTiles();
	}

	//Method Returns the list of Players the current player can give cards to
	public LinkedList<Player> giveTreasureCard() { 
		int i =0;
		LinkedList<Player> playersForTreasureCard = new LinkedList <Player>();
		PlayerList playerList = PlayerList.getInstance();

		for (Player otherPlayer:playerList.getListOfOtherPlayers(playerNumber)) { //creates a list of the other players using the current player number
			if(playerPawn.getPawnTile()==otherPlayer.getPlayerPawnTile())
				playersForTreasureCard.add(otherPlayer);
		}
		return playersForTreasureCard;
	}

	//Method returns whether a treasure was captured or not
	public boolean captureTreasure() { // need hand  WILL STAY //SMELL
		ArrayList<TreasureDeckCard> cardsToDiscard = new ArrayList <TreasureDeckCard>();

		for(TreasureDeckCard cardInHand:playerHand.getCards()) {
			if(cardInHand.getName() == getPlayerPawnTile().getTreasure().getString()) { //checks if card in hand matches treasure associated with the tile the player is on 
				cardsToDiscard.add(cardInHand);
			}
		}
		if(cardsToDiscard.size()>=4) {     //can it be greater, im guessing you would discard them all even if 5 //maybe in another class
			for(TreasureDeckCard card:cardsToDiscard) { //Remove this with move card list
				playerHand.removeCard(card);
			}
			playerTreasures.add(getPlayerPawnTile().getTreasure().getString()); //Gets the string representation of the treasure
			return true;	//The player has captured a treasure
		}
		else return false; //The player was unable to capture a treasure
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



