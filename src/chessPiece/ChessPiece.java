package chessPiece;

import game.Board;
import game.Coordinates;




public abstract class ChessPiece {
	
	private boolean isWhitePiece;
	public ChessPiece(boolean isWhitePiece) {
		this.isWhitePiece = isWhitePiece;
	}
	
	public abstract boolean canMove(Coordinates coordinate, Board board);
	public abstract char getChessPieceId();
	public abstract ChessPiece clone() throws CloneNotSupportedException;
	public abstract String toString();
	

		
	public boolean validateCaptureChessPiece(Coordinates coordinate, Board board) {
		if(board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isWhitePiece()) {
			if((board.getBoard()[coordinate.getToY()][coordinate.getToX()].isBlackPiece() || !board.getBoard()[coordinate.getToY()][coordinate.getToX()].isChessPiece()) && board.isChessPieceCanMove(coordinate)) {
				board.moveChessPiece(coordinate);
				return true;
			}
		}
		else if(board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isBlackPiece()){
			if((board.getBoard()[coordinate.getToY()][coordinate.getToX()].isWhitePiece() || !board.getBoard()[coordinate.getToY()][coordinate.getToX()].isChessPiece()) && board.isChessPieceCanMove(coordinate)) {
				board.moveChessPiece(coordinate);
				return true;
			}

		}
		return false;
	}

	
	protected boolean isWhitePiece() {
		return isWhitePiece;
	}

	
}
