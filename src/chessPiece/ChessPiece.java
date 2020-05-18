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
	

	// ada if yang harus di reduce(line401-406 dan case untuk Piece oppositenya)
	protected boolean validateCaptureChessPiece(Coordinates coordinate, Board board) {
		if(board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isWhitePiece()) {
			if(board.getBoard()[coordinate.getToY()][coordinate.getToX()].isBlackPiece()) {
				return board.captureChessPiece(coordinate, board.isWhiteMove());
			}
			else if(!board.getBoard()[coordinate.getToY()][coordinate.getToX()].isChessPiece()){
				return board.moveChessPiece(coordinate);
			}
		}
		else if(board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isBlackPiece()){
			if(board.getBoard()[coordinate.getToY()][coordinate.getToX()].isWhitePiece()) {
				return board.captureChessPiece(coordinate, board.isWhiteMove());
			}
			else if(!board.getBoard()[coordinate.getToY()][coordinate.getToX()].isChessPiece()){
				return board.moveChessPiece(coordinate);
			}
		}
		return false;
	}
	

	
	protected boolean isWhitePiece() {
		return isWhitePiece;
	}

	
}
