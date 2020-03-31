package chess;

import java.util.Scanner;

public class Main {
	public Main() {
		Scanner scan = new Scanner(System.in);
		Board chessBoard =new Board();
		int chooseMoveNotation = 0;
		
		System.out.println("1. Algebraic");
		System.out.println("2. Coordinate");
		System.out.println("Choose move notation [1-2]:");
		chooseMoveNotation = scan.nextInt();scan.nextLine();
		while(!chessBoard.isWin()) {
			chessBoard.showBoard();
			try {
				chessBoard.doTurn(chooseMoveNotation);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}
	
}
