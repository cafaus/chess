package chessPiece;

import game.Board;
import game.Coordinates;
import game.Tools;

public class Bishop extends ChessPiece{

	public Bishop(boolean isWhitePiece) {
		super(isWhitePiece);
		
	}
	@Override
	public boolean canMove(Coordinates coordinate, Board board) {
		if(behavior(coordinate, board) && validateCaptureChessPiece(coordinate, board)) return true;
		else System.out.println("Invalid move for bishop");
		return false;
	}
	
	public boolean behavior(Coordinates coordinate, Board board) {
		Tools tools = new Tools();
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		if(Math.abs(fromY - toY) == Math.abs(fromX - toX)){
			if(fromY - toY > 0 && fromX - toX > 0 && !tools.isChessPieceExistOnDiagonalMove(coordinate,board,1,1)) return true;  
			if(fromY - toY > 0 && fromX - toX < 0 && !tools.isChessPieceExistOnDiagonalMove(coordinate,board,1,-1)) return true;
			if(fromY - toY < 0 && fromX - toX > 0 && !tools.isChessPieceExistOnDiagonalMove(coordinate,board,-1,1)) return true; 
			if(fromY - toY < 0 && fromX - toX < 0 && !tools.isChessPieceExistOnDiagonalMove(coordinate,board,-1,-1)) return true;
		}
		
		
		return false;
	}
	
	@Override
	public char getChessPieceId() {
		return isWhitePiece() ? 'b' : 'B';
	}
	
	@Override
	public ChessPiece clone() throws CloneNotSupportedException {
		return new Bishop(this.isWhitePiece());
	}
	
	

	

}
