package game;

import chessPiece.Square;

public class EnPassant {
	public boolean captureChessPieceByEnpassant(Coordinates coordinate, Square[][] board) {

		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		if(Math.abs(fromY - toY) == 1 && Math.abs(fromX - toX) == 1 && validateEnpassant(coordinate,board)){
			return true;
		}
		return false;
	}
 
	private boolean validateEnpassant(Coordinates coordinate, Square[][] board){
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX= coordinate.getToX();
		int prevOriginY =  coordinate.getPrevOriginY();
		int prevPieceCurrPosY = coordinate.getPrevCurrPosY();
		int prevPieceCurrPosX = coordinate.getPrevCurrPosX();
		Square prevMovedPiece= board[coordinate.getPrevCurrPosY()][coordinate.getPrevCurrPosX()];
		int numOfStep= Math.abs(prevPieceCurrPosY-prevOriginY);
		if(board[fromY][fromX].isWhitePiece() && fromY==3 && prevMovedPiece.getChessPiece().getChessPieceId() == 'P'){
			if(prevPieceCurrPosY == 3 && (prevPieceCurrPosX == fromX-1 || prevPieceCurrPosX == fromX+1) && toX==prevPieceCurrPosX && numOfStep==2){
				return true;
			}
		}else if(!board[fromY][fromX].isWhitePiece() && fromY == 4 && prevMovedPiece.getChessPiece().getChessPieceId() == 'p'){
			if(prevPieceCurrPosY == 4 && (prevPieceCurrPosX == fromX-1 || prevPieceCurrPosX == fromX+1) && toX==prevPieceCurrPosX && numOfStep==2){
				return true;
			}
		}
			
		return false;
	}
}
