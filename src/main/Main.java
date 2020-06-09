package main;


import game.Game;
/*
to do
-unit test coverage 90% 
-reduce code smell
*/
public class Main {
	public Main() {
		Game chessBoard =new Game();

		do{ 
			try {
				chessBoard.play();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}while(!chessBoard.isGameOver());
	}

	public static void main(String[] args) {
		new Main();
	}
	
}
