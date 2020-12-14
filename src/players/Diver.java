package players;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import island.board.Board;
import island.board.Tile;
import pawns.Pawn;
import setup.PlayerSetup;
//Singlton class
public class Diver extends Player {

	private static Diver theDiver;

	private Diver(String playerName, int playerNumber) {
		super(playerName,playerNumber); //player class constructor
		playerPawn=new Pawn(board.getTile("Iron Gate")); //Gets the Bronze Gate Tile and sets it as pawn starter tile
		//Hand constructed in super class(player)

	}



	public static Diver getInstance(String playerName, int playerNumber) {
		if(theDiver == null) {
			theDiver = new Diver(playerName ,playerNumber);
		}
		return theDiver;
	}

	public static Diver returnInstance() {
		return theDiver;
	}
	
//	public static void main(String[] args) {
//		Board board = Board.getInstance();
//		board.printBoard();
//		Scanner inputScanner = new Scanner(System.in);	//Close scanner possibly
//		PlayerSetup playerSetup = new PlayerSetup();
//		playerSetup.createPlayers(inputScanner);
//		PlayerList pList = PlayerList.getInstance();
//		PlayerList.getInstance().printListOfPlayers();
//
//		
//		Player liam = pList.getPlayer(1);
//		Player hayley = pList.getPlayer(2);
//		//liam.getShoreableTiles();
//		System.out.println("Can first player give treasure card on different tiles:" +	liam.getPlayersForTreasureCard());
//		
//		//liam.canShoreUp();
//		liam.playerPawn=hayley.playerPawn;
//		System.out.println("Change so pawns are on the same tile");
//		System.out.println(	liam.getPlayersForTreasureCard());
//		System.out.println(liam.getFocredMoveableTiles());
//		System.out.println(liam.getFocredMoveableTiles());
//		System.out.println(liam.getShoreableTiles());
//		System.out.println(liam.canCaptureTreasure());
//		//Diver diver = new Diver("Liam",2);

		//System.out.println(diver.getPlayerPawnTile().getNameString());
		//LinkedList<Tile> moveableTiles = diver.getStandardMoveableTiles(); //Test 1
		//LinkedList<Tile> moveableTiles=diver.getStandardMoveableTiles();
		//moveableTiles.get(1).setFlood(true);
		//System.out.println(moveableTiles.get(1).getNameString());
		//System.out.println(moveableTiles.get(1).isFlooded());
		//diver.getStandardMoveableTiles();
		//LinkedList<Tile> shoreableTiles=diver.getShoreableTiles();
		
		
		
		
		
		
	}



