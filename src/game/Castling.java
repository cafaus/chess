package game;

import chessPiece.Square;

public class Castling {
	public boolean isCastling(Coordinates coordinate, Square[][] board) {
		Tools tools = new Tools();
		CheckBehaviors checkBehavior = new CheckBehaviors();
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		
		if(!Board.isKingSafe()) {
			System.out.println("Cannot do castling because your king is in check condition!!");
			return false;
		}
		
		if (Board.isWhiteMove() && Board.isWhiteKingAndRookNeverMove() && toY == 7 && toX == 6 && !tools.isChessPieceExistBetweenFromAndToInX(board, 4, 7, 7)) {
			if(checkBehavior.isWhiteKingSafe(board, 7, 5)) return true;
		}
		if (Board.isWhiteMove() && Board.isWhiteKingAndRookNeverMove() && toY == 7 && toX == 2  && !tools.isChessPieceExistBetweenFromAndToInX(board, 0, 4, 7)) {
			if(checkBehavior.isWhiteKingSafe(board, 7, 3)) return true;
		}
		
		if (!Board.isWhiteMove() && Board.isBlackKingAndRookNeverMove() && toY == 0 && toX == 6 && !tools.isChessPieceExistBetweenFromAndToInX(board, 4, 7, 0)) {
			if(checkBehavior.isBlackKingSafe(board, 0, 5)) return true;
		}
		if (!Board.isWhiteMove() && Board.isBlackKingAndRookNeverMove() && toY == 0 && toX == 2 && !tools.isChessPieceExistBetweenFromAndToInX(board, 0, 4, 0)) {
			if(checkBehavior.isBlackKingSafe(board, 0, 3)) return true;
		}
		
		return false;
	}
	

	public void doWhiteKingCastling(Coordinates coordinate) {
		if(coordinate.getToY() == 7 && coordinate.getToX() == 6) {
			Board.moveChessPiece(coordinate);
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(7);
			rookCoordinate.setFromX(7);
			rookCoordinate.setToY(7);
			rookCoordinate.setToX(5);
			
			Board.moveChessPiece(rookCoordinate);
		}
		else {
			Board.moveChessPiece(coordinate);
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(7);
			rookCoordinate.setFromX(0);
			rookCoordinate.setToY(7);
			rookCoordinate.setToX(3);
			
			Board.moveChessPiece(rookCoordinate);
		}
	}
	
	public void doBlackKingCastling(Coordinates coordinate) {
		if(coordinate.getToY() == 0 && coordinate.getToX() == 6) {
			Board.moveChessPiece(coordinate);
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(0);
			rookCoordinate.setFromX(7);
			rookCoordinate.setToY(0);
			rookCoordinate.setToX(5);
			
			Board.moveChessPiece(rookCoordinate);
		}
		else {
			Board.moveChessPiece(coordinate);
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(0);
			rookCoordinate.setFromX(0);
			rookCoordinate.setToY(0);
			rookCoordinate.setToX(3);
			
			Board.moveChessPiece(rookCoordinate);
		}
	}
}
