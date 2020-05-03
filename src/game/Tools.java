package game;

import chessPiece.Square;

public class Tools {
	public boolean isChessPieceExistBetweenFromAndToInY(Square[][] board, int from, int to, int X) {
		++from;
		for (int i = from; i < to; i++) {
			if(board[i][X].isChessPiece()) return true; 
		}
		return false;
	}

	public boolean isChessPieceExistBetweenFromAndToInX(Square[][] board, int from, int to, int Y) {
		++from;
		for (int i = from; i < to; i++) {
			if(board[Y][i].isChessPiece()) return true; 
		}
		return false;
	}
	public boolean isChessPieceExistOnDiagonalMove(Coordinates coordinate, Square[][] board, int yMovement, int xMovement){
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		int currXposition= fromX-xMovement;
		int currYposition= fromY-yMovement;
		
		while(currXposition != toX && currYposition != toY){
			if(board[currYposition][currXposition].isChessPiece() ){
				return true;
			}
			currYposition-=yMovement;
			currXposition-=xMovement;
		}
		
		return false;
	}
	
	public boolean isBetweenTheBoundary(Coordinates coordinate, int area1, int area2, int target) {
		return target >= coordinate.getTopBoundary() &&  target <= coordinate.getBottomBoundary() && area1 == area2;
	}
	public boolean isOutside(char fromIndexZero, char c1, char c2) {
		return fromIndexZero < c1 || fromIndexZero > c2;
	}
}
