package gameLogic;

import java.util.ArrayList;
import java.util.Scanner;

import island.board.Board;
import players.Player;
import players.PlayerList;

public class PlayerTurn {

	private Player player;
	private Scanner inputScanner;
	private Board board;
	private PlayerAction action;
	private PlayerController playerController;
	private PlayerView playerView;
	private boolean gameOver;


	public PlayerTurn(Player player, Scanner inputScanner ) {
		this.player=player;
		this.inputScanner=inputScanner;
		this.board= Board.getInstance();
		action=new PlayerAction(player);
	}

	//Printout the available options for a players turn




}
