package chess;

import java.util.Scanner;

public class Main {
	
	
	
	

	public Main() {
		Board chessBoard =new Board();
		
		
		
		
		
		
		

		while(true) {
			
			chessBoard.showBoard();
			try {
				chessBoard.doTurn();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			
		}

		
	}



	

	public static void main(String[] args) {
		new Main();
	}
	
}
