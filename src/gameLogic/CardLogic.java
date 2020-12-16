package gameLogic;

import java.util.ArrayList;
import java.util.Scanner;

import island.board.Board;
import island.board.Tile;
import island.cards.Hand;
import island.cards.TreasureDeckCard;
import players.Player;
import players.PlayerList;
import utility.Utility;

public class CardLogic {
	
	private Player player;
	private Hand playerHand;
	ArrayList<TreasureDeckCard> playableCards;
	
	
	public CardLogic(Player player) {
		this.player=player;
		this.playerHand=player.getHand();
		this.playableCards = playerHand.getCards();
	}
	
	public void play(int userInput) {
		playableCards.get(userInput).play();
	}

	
	public void doHelicopter() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		ArrayList<Tile> listOfTiles = Board.getInstance().listOfTiles();
		PlayerList playerList = PlayerList.getInstance();
		ArrayList<Player> chosenPlayers = new ArrayList<Player>();
		String yn = "y";
		boolean repeat = true;
				
		System.out.println("Which tile do you want to move to? ");
		int tileIndex = Utility.acceptableInput(0, listOfTiles.size());

		while(repeat == true){
			System.out.println("Who do you want to move to " + listOfTiles.get(tileIndex).getNameString() + "?");
			playerList.printListOfPlayers();
			int playerIndex = Utility.acceptableInput(0, playerList.getListOfPlayers().size());
			if(chosenPlayers.contains(playerList.getPlayer(playerIndex))) {
				System.out.println(playerList.getPlayer(playerIndex).getName() + " is already chosen.");
			} //could create a new list and remove player each time so no option of other player
			else
				chosenPlayers.add(playerList.getPlayer(playerIndex));			
			System.out.println("Do you want to choose another player? (Y/N)");
		    yn = s.nextLine();
		    if(yn.equals("y") || yn.equals("Y")) 
		    	repeat = true;
		    else if(yn.equals("n") || yn.equals("N"))
		    	repeat = false;
		}
		
		for(int i=0; i<chosenPlayers.size();i++) {
			System.out.print("Moving " + chosenPlayers.get(i).getName() + " to " + listOfTiles.get(tileIndex).getNameString() +  "...\n");
			chosenPlayers.get(i).movePlayerPawn(listOfTiles.get(tileIndex));
		}		
	}
	
	
	public void doSandbag() {
		ArrayList<Tile> tiles = Board.getInstance().listOfFloodedTiles();
		if(tiles.size()==0) {
			System.out.println("Choose another card.");
			pickCard();
		}
		else {
			System.out.println("\nChoose the tile: ");
			int tileIndex = Utility.acceptableInput(0, tiles.size());
			Tile tileToShoreUp = Board.getInstance().getTile(tiles.get(tileIndex).getNameString()); // Ask Robert
			tileToShoreUp.setFlood(false);
			System.out.println("Shored up " + tileToShoreUp.getNameString());
		}
	}
}
