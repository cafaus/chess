package chessPiece;

import game.Board;
import game.CheckBehaviors;
import game.Coordinates;
import game.EnPassant;


public abstract class ChessPiece {
	
	private boolean isWhitePiece;
	public ChessPiece(boolean isWhitePiece) {
		this.isWhitePiece = isWhitePiece;
	}
	
	public abstract boolean canMove(Coordinates coordinate, Square[][] board);
	public abstract char getChessPieceId();
	

	// ada if yang harus di reduce(line401-406 dan case untuk Piece oppositenya)
	protected boolean validateCaptureChessPiece(Coordinates coordinate, Square[][] board) {
		EnPassant enPassant = new EnPassant();
		Boolean isEnPassantSafe;
		
		
		if(board[coordinate.getFromY()][coordinate.getFromX()].isWhitePiece()) {
			if(board[coordinate.getToY()][coordinate.getToX()].isBlackPiece()) {
				return Board.captureChessPiece(coordinate);
			}
			else if(!board[coordinate.getToY()][coordinate.getToX()].isChessPiece()){
				if(enPassant.captureChessPieceByEnpassant(coordinate, board) && coordinate.getToX()==coordinate.getPrevCurrPosX()){
					isEnPassantSafe=Board.moveChessPiece(coordinate);
					if(isEnPassantSafe){
						board[coordinate.getPrevCurrPosY()][coordinate.getPrevCurrPosX()].setChessPiece(null);
						return true;
					}
				}
				return Board.moveChessPiece(coordinate);
			}
		}
		else if(board[coordinate.getFromY()][coordinate.getFromX()].isBlackPiece()){
			if(board[coordinate.getToY()][coordinate.getToX()].isWhitePiece()) {
				return Board.captureChessPiece(coordinate);
			}
			else if(!board[coordinate.getToY()][coordinate.getToX()].isChessPiece()){
				if(enPassant.captureChessPieceByEnpassant(coordinate, board) && coordinate.getToX()==coordinate.getPrevCurrPosX()){
					isEnPassantSafe=Board.moveChessPiece(coordinate);
					if(isEnPassantSafe){
						board[coordinate.getPrevCurrPosY()][coordinate.getPrevCurrPosX()].setChessPiece(null);
						return true;
					}
				}
				return Board.moveChessPiece(coordinate);
			}
		}
		return false;
	}
	
	protected boolean isWhitePiece() {
		return isWhitePiece;
	}

	
}
