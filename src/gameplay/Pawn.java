/**
 \* This class implements the general functionalities needed for the base pawn
 * There will be six subclasses for the Engineer, Explorer, Diver, Pilot, Messenger, and Navigator
 * Each instace will be able to use polymorphism 
 * 
 */


//
package gameplay;
import java.util.*;

import island.board.Tile; 

public abstract class Pawn {


	protected Tile pawnTile;	//Current Tile Pawn is on
	protected int pawnActions;	//Since it is abstract and i will never create an instance of pawn , using protected
	protected boolean pawnTilePresent;  //To know whether the pawn is on a present tile
	private LinkedList<String> inputList;	//
	private String playerInput;


	public Pawn() {	// Constructor for pawns, each spawn with 3 actions and on a specific tile
		pawnActions= 3;	
		pawnTilePresent=true;
		this.inputList = new LinkedList<String>();
		inputList.add("1"); // for player numbers
		inputList.add("2"); 
		inputList.add("3"); 
		inputList.add("4"); 
		//Maybe b for back input input
	}

	public void setPawnTile(Tile newPawnTile) { //Sets the tile the pawn is on
		pawnTile = newPawnTile;
	} 


	public Tile getPawnTile() { //Gets the current pawn tile
		return pawnTile;
	}

	public void onOceanTile(Scanner scannerIn) { //Method to move pawn if on ocean tile
		System.out.println("You must move your pawn, as the tile it is on has been removed");
		movePawn(scannerIn);	//uses move pawn but has no option to back out of movement 
	}

	public void isPawnTilePresent() {  //Checks if the pawn is on a present tile
		pawnTilePresent=pawnTile.isPresent();
	}

	public LinkedList<Tile> moveableTiles() {  //returns the moveable tiles //list of objects
		int i =0;
		LinkedList<Tile> moveableTiles = new LinkedList<Tile>();
		moveableTiles.add(pawnTile.getNorthTile());	//Maybe preferable to have adjacent tiles method in tile class
		moveableTiles.add(pawnTile.getWestTile());
		moveableTiles.add(pawnTile.getSouthTile());
		moveableTiles.add(pawnTile.getEastTile());
		for(Tile tile: moveableTiles) {	//Checks if the tiles are present if not removes them
			if(tile.isPresent()==false || tile==null ) {
				moveableTiles.remove(tile);
			}
			else {
				System.out.println("Tiles you can move to:" + tile.getName() + i +"("+i+")");	//prints the tiles you can move to
				i++;
			}
		}
		return moveableTiles;
	}

	public LinkedList<Tile> shoreableTiles() {  //returns the shoreable tiles
		int i =0;
		LinkedList<Tile> shoreableTiles = new LinkedList<Tile>();
		shoreableTiles.add(pawnTile.getNorthTile());
		shoreableTiles.add(pawnTile.getWestTile());
		shoreableTiles.add(pawnTile.getSouthTile());
		shoreableTiles.add(pawnTile.getEastTile());

		for(Tile tile: shoreableTiles) {
			if(tile.isFlooded() ==false || tile==null ) {
				shoreableTiles.remove(tile);
			}
			else {
				System.out.println("Tiles you can shore up:" + tile.getName() + i +"("+i+")");
				i++;
			}
		}
		return shoreableTiles;

	}

	public ArrayList<String> acceptableInput(int sizeOf) {	//Adds to acceptable list of inputs, acceptable inputs can be calculated by the size of list inputed
		ArrayList<String> acceptableInput = new ArrayList<String>();

		for(int i=0; i<sizeOf; i++) {  //why need to rename
			acceptableInput.add(inputList.get(i));
		}

		return acceptableInput;
	}

	public void validAction(ArrayList<String> acceptableInput, String userInput, Scanner scannerIn) { //Checks if valid input
		if(pawnTilePresent == true ) {
			acceptableInput.add("b");}	//gives back option for other options, removes it for OCEAN situation
		while (userInput.length()==0 && acceptableInput.contains(userInput)!=true) { // Make sure user has entered at least 1 character is entered and it is valid
			userInput = scannerIn.nextLine();
		}
	}

	// May change scanner to ints so no need to parase the string to int, how to reuse code
	public void movePawn(Scanner scannerIn) { //This could go in player class, this moves a pawn to a tile
		String userInput;

		System.out.println("You have" + pawnActions + "actions remaining. \n");
		LinkedList<Tile> moveableTiles = moveableTiles();
		ArrayList<String> acceptableInput = acceptableInput( moveableTiles.size());
		System.out.println("Select the tile you want to move to by inputing the number inside () beside the tile + [return], select any other key to cancel");
		userInput = scannerIn.nextLine();

		validAction(acceptableInput,userInput,scannerIn);	//Ensures they enter a valid action
		if(userInput=="b") {
			System.out.println("You have choosen to return \n");
			return;
		}
		else {
			setPawnTile(moveableTiles.get(Integer.parseInt(playerInput))); //Needs an integer to get the pawn tile
			System.out.println("Your pawn has been moved to" + moveableTiles.get(Integer.parseInt(playerInput)) +"\n");
			System.out.println("Your pawn has" + pawnActions + "remaining.");
			pawnActions--;
		}

	}


	public void shoreUp(Scanner scannerIn) {  //This could go in player class, this shores up a tile
		String userInput;

		System.out.println("You have" + pawnActions + "actions remaining. \n");
		LinkedList<Tile> shoreableTiles = shoreableTiles();
		ArrayList<String> acceptableInput = acceptableInput(shoreableTiles.size());
		System.out.println("Select the tile you want to shore up to by inputing the number inside () beside the tile + [return], select any other key to cancel");
		userInput = scannerIn.nextLine();

		validAction(acceptableInput,userInput,scannerIn);	//probably just parase once
		if(userInput=="b") {
			System.out.println("You have choosen to return \n");
			return;
		}
		else {
			setPawnTile(shoreableTiles.get(Integer.parseInt(playerInput))); //Needs an integer to get the pawn tile
			System.out.println("Your pawn has shored up" + shoreableTiles.get(Integer.parseInt(playerInput)) +"\n");
			System.out.println("Your pawn has" + pawnActions + "remaining.");
			pawnActions--;
		}

	}

	public void giveTreasureCard(Scanner scannerIn) { //need hand and list of pawns
		String playerInput;
		List<String> acceptableInput;

		//Need to use player hand
		//else if(method to check if there isn't another player present on the tile )
		//printOut("There is no other players on your tile, choose another action")
		//return;
		//else {
		//System.out.println("You have" + pawnActions + "actions remaining. \n");
		//	System.out.println("You can give treasure cards to the following players");
		// method that gets the other players on your current tile
		//}	
	}

	public void captureTreasure(Scanner scannerIn) { // need hand
		String playerInput;
		List<String> acceptableInput;

		//if(pawntile !=  treasuretile)
		//printOut("You cannot capture a treasure unless you are on a island tile with a treasure")

		//if() checks the players treasure cards, checks does it have four matching treasure cards
		//if it does give player treasure.
		//printOut("if not you need four treasure cards with the current tiles associated treasure)

	}
}