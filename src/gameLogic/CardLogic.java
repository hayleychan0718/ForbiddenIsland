package gameLogic;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import island.board.Board;
import island.board.Tile;
import island.cards.Hand;
import island.cards.HelicopterCard;
import island.cards.SandbagCard;
import island.cards.TreasureCard;
import island.cards.TreasureDeckCard;
import island.cards.WaterRiseCard;
import island.enums.TreasureNames;
import players.Player;
import players.PlayerList;

public class CardLogic {

	private Player player;
	private Hand playerHand;
	ArrayList<TreasureDeckCard> playableCards;

	
	public CardLogic(Player player) {
		this.player=player;
		this.playerHand=player.getHand();
		this.playableCards = playerHand.getCards();
	}
	
	public void pickCard() {
	//	int userInput();
		int picking = 0;

		do{
			System.out.println("Pick one of the cards you wish to play");
		//	System.out.println("/n enter " + playableCards.size() + "[Return] to cancel action"); 
			playerHand.printHand();
			int userInput = PlayerAction.acceptableInput(0, playableCards.size());
			
		//	if(userInput==playableCardsPlayers.size()) return; 	//cancels action // Hayley: not sure what this does
			
			switch(playableCards.get(userInput).getName()) {
			case "Water Rise":
				playableCards.get(userInput).play();
				playerHand.removeCard(playableCards.get(userInput));		
				break;
			case "Helicopter Lift":
				System.out.println("Do you want to:\n[0] Move player(s) to another tile? or\n[1] Lift off Fool's Landing?");
				int in = PlayerAction.acceptableInput(0, 1);
				if(in == 0) {
					doHelicopter(userInput);
				}
				else
					// TODO: Implement Lift Off
				playerHand.removeCard(playableCards.get(userInput));		
				break;
			case "Sandbag":
				playableCards.get(userInput).play();
				doSandbag();
				playerHand.removeCard(playableCards.get(userInput));		
				break;
			case "The Crystal of Fire":
				playableCards.get(userInput).play();
				picking = 1;
				break;
			case "The Oceans Chalice":
				playableCards.get(userInput).play();
				picking = 1;
				break;
			case "The Statue of The Wind":
				playableCards.get(userInput).play();
				picking = 1;
				break;
			case "The Earth Stone":
				playableCards.get(userInput).play();
				picking = 1;
				break;
			}
		}while(picking != 0);
	}
	
	public void doHelicopter(int userInput) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		ArrayList<Tile> listOfTiles = Board.getInstance().listOfTiles();
		PlayerList playerList = PlayerList.getInstance();
		ArrayList<Player> chosenPlayers = new ArrayList<Player>();
		String yn = "y";
		boolean repeat = true;
		
		playableCards.get(userInput).play();
		
		System.out.println("Which tile do you want to move to? ");
		int tileIndex = PlayerAction.acceptableInput(0, listOfTiles.size());

		while(repeat == true){
			System.out.println("Who do you want to move to " + listOfTiles.get(tileIndex).getNameString() + "?");
			playerList.printListOfPlayers();
			int playerIndex = PlayerAction.acceptableInput(0, playerList.getListOfPlayers().size());
			if(chosenPlayers.contains(playerList.getPlayer(playerIndex))) {
				System.out.println(playerList.getPlayer(playerIndex).getName() + " is already chosen.");
			}
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
		
	}
	
	
	/*
	 * Tests
	 */
	public static void main (String args[]) {
		Player player = new Player("Hayley", 1);
		Player player2 = new Player("Ben", 2);
		PlayerList playerList = PlayerList.getInstance();
		playerList.addPlayer(player);
		playerList.addPlayer(player2);
		SandbagCard card = new SandbagCard();
		Hand theHand = player.getHand();
		
		theHand.addCard(new HelicopterCard());
		theHand.addCard(card);
		theHand.addCard(new WaterRiseCard());
		theHand.addCard(new TreasureCard(TreasureNames.TheCrystalOfFire));
		theHand.addCard(new TreasureCard(TreasureNames.TheEarthStone));

		CardLogic cl = new CardLogic(player);
		cl.pickCard();
	}
}
