package main;


import game.Game;

public class Main {
	public Main() {
		Game chessBoard =new Game();
		
		do{ 
			try {
				chessBoard.play();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}while(!chessBoard.isWin());
	}

	public static void main(String[] args) {
		new Main();
	}
	
}
