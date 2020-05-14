package game;

import chessPiece.Square;

public class Castling {
	public boolean isCastling(Coordinates coordinate, Board board) {
		Tools tools = new Tools();
		CheckBehaviors checkBehavior = new CheckBehaviors();

		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		
		if(!Game.isKingSafe()) {
			System.out.println("Cannot do castling because your king is in check condition!!");
			return false;
		}
		
		if (Game.isWhiteMove() && Game.isWhiteKingAndRookNeverMove() && toY == 7 && toX == 6 && !tools.isChessPieceExistBetweenFromAndToInX(board, 4, 7, 7)) {
			if(checkBehavior.isWhiteKingSafe(board.getBoard(), 7, 5)) return true;
		}
		if (Game.isWhiteMove() && Game.isWhiteKingAndRookNeverMove() && toY == 7 && toX == 2  && !tools.isChessPieceExistBetweenFromAndToInX(board, 0, 4, 7)) {
			if(checkBehavior.isWhiteKingSafe(board.getBoard(), 7, 3)) return true;
		}
		
		if (!Game.isWhiteMove() && Game.isBlackKingAndRookNeverMove() && toY == 0 && toX == 6 && !tools.isChessPieceExistBetweenFromAndToInX(board, 4, 7, 0)) {
			if(checkBehavior.isBlackKingSafe(board.getBoard(), 0, 5)) return true;
		}
		if (!Game.isWhiteMove() && Game.isBlackKingAndRookNeverMove() && toY == 0 && toX == 2 && !tools.isChessPieceExistBetweenFromAndToInX(board, 0, 4, 0)) {
			if(checkBehavior.isBlackKingSafe(board.getBoard(), 0, 3)) return true;
		}
		
		return false;
	}
	

	public void doWhiteKingCastling(Coordinates coordinate, Board board) {
		if(coordinate.getToY() == 7 && coordinate.getToX() == 6) {
			board.moveChessPiece(coordinate);
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(7);
			rookCoordinate.setFromX(7);
			rookCoordinate.setToY(7);
			rookCoordinate.setToX(5);
			
			board.moveChessPiece(rookCoordinate);
		}
		else {
			board.moveChessPiece(coordinate);
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(7);
			rookCoordinate.setFromX(0);
			rookCoordinate.setToY(7);
			rookCoordinate.setToX(3);
			
			board.moveChessPiece(rookCoordinate);
		}
	}
	
	public void doBlackKingCastling(Coordinates coordinate, Board board) {
		if(coordinate.getToY() == 0 && coordinate.getToX() == 6) {
			board.moveChessPiece(coordinate);
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(0);
			rookCoordinate.setFromX(7);
			rookCoordinate.setToY(0);
			rookCoordinate.setToX(5);
			
			board.moveChessPiece(rookCoordinate);
		}
		else {
			board.moveChessPiece(coordinate);
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(0);
			rookCoordinate.setFromX(0);
			rookCoordinate.setToY(0);
			rookCoordinate.setToX(3);
			
			board.moveChessPiece(rookCoordinate);
		}
	}
}
