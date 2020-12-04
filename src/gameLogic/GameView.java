package gameLogic;

public class GameView {
	
	//private GameView gameView;
	private static GameView gameView = null;
	
	public static GameView getInstance() {
		if(gameView==null)
			gameView = new GameView();
		return gameView;
	}

	public  void startGame() {
		System.out.println("Welcome to Forbidden Island");
	}
	
	public void gameOver() {
		System.out.println("Game Over");
	}
	
	public void playerTurn(Player player) {
		System.out.println("It is now " + player + " Turn \n" );
	}
}
