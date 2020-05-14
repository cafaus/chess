package main;


import game.Game;

public class Main {
	public Main() {
		Game chessBoard =new Game();
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
