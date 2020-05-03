package chessPiece;

import game.Coordinates;

public class Queen extends ChessPiece{

	public Queen(boolean isWhitePiece) {
		super(isWhitePiece);
	}

	@Override
	public boolean canMove(Coordinates coordinate, Square[][] board) {
		if(behavior(coordinate, board) && validateCaptureChessPiece(coordinate, board)) return true;
		else System.out.println("Invalid move for queen");
		return false;
	}
	public boolean behavior(Coordinates coordinate, Square[][] board) {
		Rook rook = new Rook(isWhitePiece());
		Bishop bishop = new Bishop(isWhitePiece());
		if(rook.behavior(coordinate, board) || bishop.behavior(coordinate, board)) return true;
		return false;	
	}
	@Override
	public char getChessPieceId() {
		return isWhitePiece() ? 'q' : 'Q';
	}
}
