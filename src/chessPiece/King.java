package chessPiece;


import game.Board;
import game.Castling;
import game.CheckBehaviors;
import game.Coordinates;
import game.Game;

public class King extends ChessPiece{

	public King(boolean isWhiteMove) {
		super(isWhiteMove);
	}

	@Override
	public boolean canMove(Coordinates coordinate, Board board) {
		CheckBehaviors checkBehavior = new CheckBehaviors();
		Castling castling = new Castling();
		if(behavior(coordinate)) {
			
			if(Game.isWhiteMove()) {
				if(!checkBehavior.isWhiteKingSafe(board.getBoard(), coordinate.getToY(), coordinate.getToX())) {
					System.out.println("your king is not safe if you move it there");
					return false;
				}
				if(!validateCaptureChessPiece(coordinate, board)) {
					System.out.println("Invalid move for king");
					return false;
				}
				Game.setWhiteKingAndRookNeverMove(false);
				Game.setKingSafe(true);
				return true;
				
			}
			else {
				if(!checkBehavior.isBlackKingSafe(board.getBoard(), coordinate.getToY(), coordinate.getToX())) {
					System.out.println("your king is not safe if you move it there");
					return false;
				}
				if(!validateCaptureChessPiece(coordinate, board)) {
					System.out.println("Invalid move for king");
					return false;
				}
				Game.setBlackKingAndRookNeverMove(false); 
				Game.setKingSafe(true);
				return true;
				
			}
		}
		if(castling.isCastling(coordinate, board)) {
			
			if(Game.isWhiteMove()) {
				if(checkBehavior.isWhiteKingSafe(board.getBoard(), coordinate.getToY(), coordinate.getToX()) ) {
					Game.setWhiteKingAndRookNeverMove(false);
					Game.setKingSafe(true);
					castling.doWhiteKingCastling(coordinate,board);
					return true;
				}
				return false;
			}
			else {
				if(checkBehavior.isBlackKingSafe(board.getBoard(), coordinate.getToY(), coordinate.getToX())) {
					Game.setBlackKingAndRookNeverMove(false);
					Game.setKingSafe(true);
					castling.doBlackKingCastling(coordinate,board);
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

	@Override
	public ChessPiece clone() throws CloneNotSupportedException {
		return new King(this.isWhitePiece());
	}


	

	
}
