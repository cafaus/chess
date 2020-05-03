package main;

import game.Board;

public class Main {
	public Main() {
		Board chessBoard =new Board();
		
		while(!chessBoard.isWin()) {
			try {
				chessBoard.play();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

	public static void main(String[] args) {
		new Main();
	}
	
}
