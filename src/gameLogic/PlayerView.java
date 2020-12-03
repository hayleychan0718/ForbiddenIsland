package gameLogic;

import java.util.ArrayList;

import utility.Utility;

//Class to implement the player view
public class PlayerView {
	
	private PlayerController controller;
	private static PlayerView playerView = null;
	
	public static PlayerView getInstanace() {
		if(playerView == null)
			playerView = new PlayerView();
			return playerView;
	}
	}
