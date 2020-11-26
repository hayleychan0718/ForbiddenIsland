package island.cards;

import java.util.Collections;
import java.util.Stack;

public abstract class Deck {
	// Abstract method 
	public abstract void startGame();
	
	/**
	 * Reshuffles the discard stack and places into the main stack
	 * @param mainStack The main deck of cards
	 * @param discardStack The discard pile
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void reshuffle(Stack mainStack, Stack discardStack) {
		Collections.shuffle(discardStack);
		while(!discardStack.isEmpty()){
			mainStack.push(discardStack.pop());
		}
	}
}
