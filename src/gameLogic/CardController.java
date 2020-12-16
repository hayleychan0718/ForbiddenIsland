package gameLogic;

public class CardController {
    private CardLogic model;
    private CardView view;
    private static CardController controller = null;

    public CardController(CardLogic model, CardView view){
        this.model = model;
        this.view = view;
     }
    
    public static CardController getInstance(CardLogic model, CardView view) {
    	if(controller== null)
    		controller = new CardController(model, view);
    	return controller;
    }
    
    public void playCard(int userInput) {
    	model.play(userInput);
    }
}
