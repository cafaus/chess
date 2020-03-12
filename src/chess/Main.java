package chess;


public class Main {
	public Main() {
		Board chessBoard =new Board();
		while(!chessBoard.isWin()) {
			
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
