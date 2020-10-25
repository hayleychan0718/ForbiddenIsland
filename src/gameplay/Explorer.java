package gameplay;

import java.util.List;
import java.util.Scanner;

public class Explorer extends Pawn {
	
	public Explorer(){
		super();
		pawnTile=CopperGate;
	}
	
	//May not need to override depends on how show moveable tiles works, can move diagonally
	public void movePawn(Scanner scannerIn) {
		String playerInput;
		List<String> acceptableInput;
		
		//Maybe if conditions to check sufficient actions depends if we automatically end turn
		//printOut("You have" + actions + "actions remaining. \n");
		//printOut("The following tiles are tiles you can move to:");

	}

}
