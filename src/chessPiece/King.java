package chessPiece;


import game.Board;
import game.CheckBehaviors;
import game.Coordinates;

public class King extends ChessPiece{

	public King(boolean isWhiteMove) {
		super(isWhiteMove);
	}

	@Override
	public boolean canMove(Coordinates coordinate, Board board) {
		CheckBehaviors checkBehavior = new CheckBehaviors();
		
		
		if(coordinate.getToY() < 0 || coordinate.getToY() > 7) return false;
		if(coordinate.getToX() < 0 || coordinate.getToX() > 7) return false;
		if(behavior(coordinate)) {
			
			if(board.isWhiteMove()) {
				if(!checkBehavior.isWhiteKingSafe(board.getBoard(), coordinate.getToY(), coordinate.getToX())) {
					
					return false;
				}
				if(!validateCaptureChessPiece(coordinate, board)) {
					
					return false;
				}
				board.setWhiteKingAndRookNeverMove(false);
				board.setKingSafe(true);
				
				return true;
				
			}
			else {
				if(!checkBehavior.isBlackKingSafe(board.getBoard(), coordinate.getToY(), coordinate.getToX())) {
					return false;
				}
				if(!validateCaptureChessPiece(coordinate, board)) {
					
					return false;
				}
				board.setBlackKingAndRookNeverMove(false); 
				board.setKingSafe(true);
				
				return true;
				
			}
		}
		
		if(isCastling(coordinate, board)) {
			
			if(board.isWhiteMove()) {
				if(checkBehavior.isWhiteKingSafe(board.getBoard(), coordinate.getToY(), coordinate.getToX()) ) {
					board.setWhiteKingAndRookNeverMove(false);
					board.setKingSafe(true);
					doWhiteKingCastling(coordinate,board);
					return true;
				}
				return false;
			}
			else {
				if(checkBehavior.isBlackKingSafe(board.getBoard(), coordinate.getToY(), coordinate.getToX())) {
					board.setBlackKingAndRookNeverMove(false);
					board.setKingSafe(true);
					doBlackKingCastling(coordinate,board);
					return true;
				}
				return false;
			}
		}
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

	@Override
	public ChessPiece clone() throws CloneNotSupportedException {
		return new King(this.isWhitePiece());
	}

	@Override
	public String toString() {
		return "king";
	}


	private boolean isCastling(Coordinates coordinate, Board board) {
		CheckBehaviors checkBehavior = new CheckBehaviors();

		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		
		if(!board.isKingSafe()) {
			System.out.println("Cannot do castling because your king is in check condition!!");
			return false;
		}
		
		if (board.isWhiteMove() && board.isWhiteKingAndRookNeverMove() && toY == 7 && toX == 6 && !board.isChessPieceExistBetweenFromAndToInX(4, 7, 7)) {
			if(checkBehavior.isWhiteKingSafe(board.getBoard(), 7, 5)) return true;
		}
		if (board.isWhiteMove() && board.isWhiteKingAndRookNeverMove() && toY == 7 && toX == 2  && !board.isChessPieceExistBetweenFromAndToInX(0, 4, 7)) {
			if(checkBehavior.isWhiteKingSafe(board.getBoard(), 7, 3)) return true;
		}
		
		if (!board.isWhiteMove() && board.isBlackKingAndRookNeverMove() && toY == 0 && toX == 6 && !board.isChessPieceExistBetweenFromAndToInX(4, 7, 0)) {
			if(checkBehavior.isBlackKingSafe(board.getBoard(), 0, 5)) return true;
		}
		if (!board.isWhiteMove() && board.isBlackKingAndRookNeverMove() && toY == 0 && toX == 2 && !board.isChessPieceExistBetweenFromAndToInX(0, 4, 0)) {
			if(checkBehavior.isBlackKingSafe(board.getBoard(), 0, 3)) return true;
		}
		
		return false;
	}
	

	private void doWhiteKingCastling(Coordinates coordinate, Board board) {
		Coordinates rookCoordinate = new Coordinates();
		if(coordinate.getToY() == 7 && coordinate.getToX() == 6) {
			board.moveChessPiece(coordinate);
			rookCoordinate.setFrom(7, 7);
			rookCoordinate.setTo(7, 5);
			board.moveChessPiece(rookCoordinate);
		}
		else {
			board.moveChessPiece(coordinate);
			rookCoordinate.setFrom(7, 0);
			rookCoordinate.setTo(7, 3);
			board.moveChessPiece(rookCoordinate);
		}
	}
	
	public void doBlackKingCastling(Coordinates coordinate, Board board) {
		if(coordinate.getToY() == 0 && coordinate.getToX() == 6) {
			board.moveChessPiece(coordinate);
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFrom(0, 7);
			rookCoordinate.setTo(0, 5);
			board.moveChessPiece(rookCoordinate);
		}
		else {
			board.moveChessPiece(coordinate);
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFrom(0, 0);
			rookCoordinate.setTo(0, 3);
			board.moveChessPiece(rookCoordinate);
		}
	}

	
}
