package chessPiece;

import game.Board;
import game.Coordinates;

public  class Rook extends ChessPiece{

	public Rook(boolean isWhitePiece) {
		super(isWhitePiece);
	}
	@Override
	public boolean canMove(Coordinates coordinate, Board board) {
		if(behavior(coordinate, board) && validateCaptureChessPiece(coordinate, board)) {
			if(board.isWhiteMove()) board.setWhiteKingAndRookNeverMove(false);
			else  board.setBlackKingAndRookNeverMove(false);
			return true;
		}

		return false;
	}
	
	public boolean behavior(Coordinates coordinate, Board board) {
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		boolean fromXisSmallerThanToX =  fromX < toX ? true : false;
		boolean fromYisSmallerThanToY =  fromY < toY ? true : false;

		
		if(toY == fromY) {
			
			if(fromXisSmallerThanToX ) {
				if(board.isChessPieceExistBetweenFromAndToInX(fromX, toX, toY))return false;
			}
			else {
				if(board.isChessPieceExistBetweenFromAndToInX(toX ,fromX , toY))return false;
			}
			return true;
		}
		if( toX == fromX) {
			
			if( fromYisSmallerThanToY ) {
				if(board.isChessPieceExistBetweenFromAndToInY(fromY, toY, toX))return false;
			}
			else {
				if(board.isChessPieceExistBetweenFromAndToInY(toY ,fromY, toX))return false;
			}
			return true;

		}
		
		return false;
	}
	
	@Override
	public char getChessPieceId() {
		return isWhitePiece() ? 'r' : 'R';
	
	}
	
	@Override
	public ChessPiece clone() throws CloneNotSupportedException {
		return new Rook(this.isWhitePiece());
	}
		
	@Override
	public String toString() {
		return "rook";
	}
		
}
