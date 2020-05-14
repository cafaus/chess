package chessPiece;

import game.Board;
import game.Coordinates;
import game.EnPassant;
import game.Game;


public abstract class ChessPiece {
	
	private boolean isWhitePiece;
	public ChessPiece(boolean isWhitePiece) {
		this.isWhitePiece = isWhitePiece;
	}
	
	public abstract boolean canMove(Coordinates coordinate, Board board);
	public abstract char getChessPieceId();
	public abstract ChessPiece clone() throws CloneNotSupportedException;
	

	// ada if yang harus di reduce(line401-406 dan case untuk Piece oppositenya)
	protected boolean validateCaptureChessPiece(Coordinates coordinate, Board board) {
		EnPassant enPassant = new EnPassant();
		Square[][] boardCopy = new Square[8][8];
		Boolean isEnPassantSafe;
		
		
		if(board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isWhitePiece()) {
			if(board.getBoard()[coordinate.getToY()][coordinate.getToX()].isBlackPiece()) {
				return board.captureChessPiece(coordinate, Game.isWhiteMove());
			}
			else if(!board.getBoard()[coordinate.getToY()][coordinate.getToX()].isChessPiece()){
				if(enPassant.captureChessPieceByEnpassant(coordinate, board) && coordinate.getToX()==coordinate.getPrevCurrPosX()){
					isEnPassantSafe=board.moveChessPiece(coordinate);
					if(isEnPassantSafe){
						boardCopy = board.getBoard();
						boardCopy[coordinate.getPrevCurrPosY()][coordinate.getPrevCurrPosX()].setChessPiece(null);
						board.setBoard(boardCopy);
						return true;
					}
				}
				return board.moveChessPiece(coordinate);
			}
		}
		else if(board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isBlackPiece()){
			if(board.getBoard()[coordinate.getToY()][coordinate.getToX()].isWhitePiece()) {
				return board.captureChessPiece(coordinate, Game.isWhiteMove());
			}
			else if(!board.getBoard()[coordinate.getToY()][coordinate.getToX()].isChessPiece()){
				if(enPassant.captureChessPieceByEnpassant(coordinate, board) && coordinate.getToX()==coordinate.getPrevCurrPosX()){
					isEnPassantSafe=board.moveChessPiece(coordinate);
					if(isEnPassantSafe){
						boardCopy = board.getBoard();
						boardCopy[coordinate.getPrevCurrPosY()][coordinate.getPrevCurrPosX()].setChessPiece(null);
						board.setBoard(boardCopy);
						return true;
					}
				}
				return board.moveChessPiece(coordinate);
			}
		}
		return false;
	}
	

	
	protected boolean isWhitePiece() {
		return isWhitePiece;
	}

	
}
