package game;

import chessPiece.Square;

public class BoardPrinter {
	public void showBoard(Square[][]board) {
		
		for (int i = 0; i < 8; i++) {
			
			for (int j = 0; j < 8; j++) {
				if(board[i][j].getChessPieceId() == '0') {
					if(board[i][j].isWhiteTile()) System.out.print("-"+ " " + " ");
					else System.out.print("+"+ " " + " ");
				}
				else System.out.print(board[i][j].getChessPieceId() + " " + " ");
			}
			System.out.println(" " + " " + (8 - i) );
		}

		System.out.println();
		
		showFile();
	}



	private void showFile() {
		char alphabet = 'A' - 1;
		
		for (int i = 0; i < 8; i++) {
			alphabet += 1; 
			System.out.print(  alphabet + " " + " ");
		}
		System.out.println();
	}
}
