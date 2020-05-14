package chessPiece;

import game.Board;
import game.Coordinates;

public class Knight extends ChessPiece{
	public Knight(boolean isWhitePiece) {
		super(isWhitePiece);
	}

	@Override
	public boolean canMove(Coordinates coordinate, Board board) {
		if(behavior(coordinate) && validateCaptureChessPiece(coordinate, board)) return true;
		else System.out.println("Invalid move for knight");
		return false;
	}
	public boolean behavior(Coordinates coordinate) {
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		if(fromY - 2 == toY && (fromX - 1 == toX || fromX + 1 == toX)) return true;
		if(fromY + 2 == toY && (fromX - 1 == toX || fromX + 1 == toX)) return true;
		if(fromX - 2 == toX && (fromY - 1 == toY || fromY + 1 == toY)) return true;
		if(fromX + 2 == toX && (fromY - 1 == toY || fromY + 1 == toY)) return true;
		
		return false;
	}
	
	@Override
	public char getChessPieceId() {
		return isWhitePiece() ? 'n' : 'N';
	}

	@Override
	public ChessPiece clone() throws CloneNotSupportedException {
		return new Knight(this.isWhitePiece());
	}

	

}
