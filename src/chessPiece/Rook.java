package chessPiece;

import game.Board;
import game.Coordinates;
import game.Game;
import game.Tools;

public  class Rook extends ChessPiece{

	public Rook(boolean isWhitePiece) {
		super(isWhitePiece);
	}
	@Override
	public boolean canMove(Coordinates coordinate, Board board) {
		if(behavior(coordinate, board) && validateCaptureChessPiece(coordinate, board)) {
			if(Game.isWhiteMove()) Game.setWhiteKingAndRookNeverMove(false);
			else  Game.setBlackKingAndRookNeverMove(false);
			return true;
		}
		else System.out.println("Invalid move for rook");
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
		
	
	public boolean behavior(Coordinates coordinate, Board board) {
		Tools tools = new Tools();
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		boolean fromXisSmallerThanToX =  fromX < toX ? true : false;
		boolean fromYisSmallerThanToY =  fromY < toY ? true : false;

		
		if(toY == fromY) {
			
			if( fromXisSmallerThanToX ) {
				if(tools.isChessPieceExistBetweenFromAndToInX(board, fromX, toX, toY))return false;
			}
			else {
				if(tools.isChessPieceExistBetweenFromAndToInX(board, toX ,fromX , toY))return false;
			}
			return true;
		}
		if( toX == fromX) {
			
			if( fromYisSmallerThanToY ) {
				if(tools.isChessPieceExistBetweenFromAndToInY(board, fromY, toY, toX))return false;
			}
			else {
				if(tools.isChessPieceExistBetweenFromAndToInY(board, toY  ,fromY, toX))return false;
			}
			return true;

		}
		
		return false;
	}
	

	
	


	
}
