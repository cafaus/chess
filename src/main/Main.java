package main;


import java.util.Scanner;
import game.Game;

public class Main {
	
	public Main() {
		Scanner scan = new Scanner(System.in);
		String input = new String();
		
		Game chessBoard =new Game();
		do{ 
			try {
				chessBoard.showGameInfo();
				System.out.println("insert move (from-to):..(CAPITAL ALPHABET) ex= A1-B2");
				input = scan.nextLine();
				chessBoard.play(input);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}while(!chessBoard.isGameOver());
	}

	public static void main(String[] args) {
		new Main();
	}
	
}
