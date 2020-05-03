package chessPiece;

import game.Board;
import game.Castling;
import game.CheckBehaviors;
import game.Coordinates;

public class King extends ChessPiece{

	public King(boolean isWhiteMove) {
		super(isWhiteMove);
	}

	@Override
	public boolean canMove(Coordinates coordinate, Square[][] board) {
		CheckBehaviors checkBehavior = new CheckBehaviors();
		Castling castling = new Castling();
		if(behavior(coordinate)) {
			
			if(Board.isWhiteMove()) {
				if(!checkBehavior.isWhiteKingSafe(board, coordinate.getToY(), coordinate.getToX())) {
					System.out.println("your king is not safe if you move it there");
					return false;
				}
				if(!validateCaptureChessPiece(coordinate, board)) {
					System.out.println("Invalid move for king");
					return false;
				}
				Board.setWhiteKingAndRookNeverMove(false);
				Board.setKingSafe(true);
				return true;
				
			}
			else {
				if(!checkBehavior.isBlackKingSafe(board, coordinate.getToY(), coordinate.getToX())) {
					System.out.println("your king is not safe if you move it there");
					return false;
				}
				if(!validateCaptureChessPiece(coordinate, board)) {
					System.out.println("Invalid move for king");
					return false;
				}
				Board.setBlackKingAndRookNeverMove(false); 
				Board.setKingSafe(true);
				return true;
				
			}
		}
		if(castling.isCastling(coordinate, board)) {
			
			if(Board.isWhiteMove()) {
				if(checkBehavior.isWhiteKingSafe(board, coordinate.getToY(), coordinate.getToX()) ) {
					Board.setWhiteKingAndRookNeverMove(false);
					Board.setKingSafe(true);
					castling.doWhiteKingCastling(coordinate);
					return true;
				}
				return false;
			}
			else {
				if(checkBehavior.isBlackKingSafe(board, coordinate.getToY(), coordinate.getToX())) {
					Board.setBlackKingAndRookNeverMove(false);
					Board.setKingSafe(true);
					castling.doBlackKingCastling(coordinate);
					return true;
				}
				return false;
			}
		}
		else System.out.println("Invalid move for king");
		return false;
	}

	public boolean behavior(Coordinates coordinate) {
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		int differenceX = Math.abs(fromX-toX);
		int differenceY = Math.abs(fromY-toY);
		
		if(differenceX < 2 && differenceY < 2) return true; 
		return false;
	}
	
	@Override
	public char getChessPieceId() {
		return isWhitePiece() ? 'k' : 'K';
	}
	

	
}
