package chessPiece;

import game.Board;
import game.Coordinates;
import game.EnPassant;
import game.Tools;

public class Pawn extends ChessPiece{
	public Pawn(boolean isWhitePiece) {
		super(isWhitePiece);
	}
	@Override
	public boolean canMove(Coordinates coordinate, Square[][] board) {
		if(behavior(coordinate, board))return validateCaptureChessPiece(coordinate, board);
		else System.out.println("Invalid move for pawn");
		return false;
	}
	public boolean behavior(Coordinates coordinate, Square[][] board) {
		int fromY = coordinate.getFromY();
		
		if(Board.isWhiteMove()) {
			
			if(isPawnNeverMove(fromY,6)) {
				coordinate.setTopBoundary(fromY - 2);
				coordinate.setBottomBoundary(fromY);
				
				if(validatePawnOnFirstMove(coordinate, board, 1)) return true;
			}else {
				coordinate.setTopBoundary(fromY - 1);
				coordinate.setBottomBoundary(fromY);
				
				if(validatePawnAfterFirstMove(coordinate,board, 1))return true;
			}	
		}
		else {
			if(isPawnNeverMove(fromY, 1)) {
				coordinate.setTopBoundary(fromY);
				coordinate.setBottomBoundary(fromY + 2);
				
				if(validatePawnOnFirstMove(coordinate, board, -1)) return true;
			}else {
				coordinate.setTopBoundary(fromY );
				coordinate.setBottomBoundary(fromY + 1);
				
				if (validatePawnAfterFirstMove(coordinate,board, -1)) return true;
			}	
		}
		
		return false;
	}
	
	@Override
	public char getChessPieceId() {
		return isWhitePiece() ? 'p' : 'P';
	}
	
	private boolean validatePawnAfterFirstMove(Coordinates coordinate, Square [][] board, int oneTileForward) {
		EnPassant enPassant = new EnPassant();
		int fromX = coordinate.getFromX();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		Tools tools = new Tools();
		
		if(enPassant.captureChessPieceByEnpassant(coordinate, board)) return true;
		if(captureChessPieceDiagonal(coordinate, board,  oneTileForward)) return true;
		if(tools.isBetweenTheBoundary(coordinate, fromX, toX, toY) && !board[toY][toX].isChessPiece()) return true;
		return false;
	}


	private boolean validatePawnOnFirstMove(Coordinates coordinate, Square[][] board,int oneTileForward) {
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		Tools tools = new Tools();
		
		if(captureChessPieceDiagonal(coordinate, board, oneTileForward)) return true;
		if(isMoveOneTileForward(coordinate, oneTileForward) && !board[toY][toX].isChessPiece()) return true;
		if(tools.isBetweenTheBoundary(coordinate, fromX, toX, toY) && !board[toY][toX].isChessPiece()) {
			if(tools.isChessPieceExistBetweenFromAndToInY(board, toY  ,fromY, toX)) return false;
			return true;
		}
		
		return false;
	}

	

	private boolean isMoveOneTileForward(Coordinates coordinate, int OneTileForward) {
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		return (fromY - toY) == OneTileForward && toX == fromX;
	}



	private boolean isPawnNeverMove(int fromY, int PawnFirstRow) {
		return fromY == PawnFirstRow;
	}



	private boolean captureChessPieceDiagonal(Coordinates coordinate, Square[][] board ,int oneTileForward) {
		
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
	
		if( (fromY - toY) == oneTileForward && Math.abs(fromX - toX) == 1 && board[toY][toX].isChessPiece())  {
			return true;
		}
		return false;
	}

	
	
	
	
	
}
