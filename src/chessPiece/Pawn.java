package chessPiece;

import game.Board;
import game.Coordinates;
import game.Tools;

public class Pawn extends ChessPiece{
	
	
	public Pawn(boolean isWhitePiece) {
		super(isWhitePiece);
	}
	
	
	@Override
	public boolean canMove(Coordinates coordinate, Board board) {
		return behavior(coordinate, board);
	}
	
	
	public boolean behavior(Coordinates coordinate, Board board) {
		int fromY = coordinate.getFromY();
		int toY = coordinate.getToY();
		int toX = coordinate.getToX();
		if(board.isWhiteMove()) {
			if(isPawnNeverMove(fromY,6)) {
				coordinate.setTopBoundary(fromY - 2);
				coordinate.setBottomBoundary(fromY);
				
				if(validatePawnOnFirstMove(coordinate, board, 1)) return validateCaptureChessPiece(coordinate, board);
			}else {
				coordinate.setTopBoundary(fromY - 1);
				coordinate.setBottomBoundary(fromY);
				
				if(isPawnPromotion(toY, fromY, board.isWhiteMove())) {
					if(coordinate.getChessPiecePromote() == null) return false;
					if(isMoveOneTileForward(coordinate, 1) && board.getBoard()[toY][toX].isChessPiece()) return false;
					board.setBoard(changeChessPieceIntoPromotedChessPiece(board, coordinate));
				}
				if(validatePawnAfterFirstMove(coordinate,board, 1))return true;
			}	
		}
		else {
			if(isPawnNeverMove(fromY, 1)) {
				coordinate.setTopBoundary(fromY);
				coordinate.setBottomBoundary(fromY + 2);
				
				if(validatePawnOnFirstMove(coordinate, board, -1)) return validateCaptureChessPiece(coordinate, board);
			}else {
				coordinate.setTopBoundary(fromY );
				coordinate.setBottomBoundary(fromY + 1);
				
				if(isPawnPromotion(toY, fromY, board.isWhiteMove())) {
					if(coordinate.getChessPiecePromote() == null) return false;
					if(isMoveOneTileForward(coordinate, -1) && board.getBoard()[toY][toX].isChessPiece()) return false;
					board.setBoard(changeChessPieceIntoPromotedChessPiece(board, coordinate));
				}
				if (validatePawnAfterFirstMove(coordinate,board, -1)) return true;
			}	
		}
		
		return false;
	}
	
	
	@Override
	public char getChessPieceId() {
		return isWhitePiece() ? 'p' : 'P';
	}
	
	
	@Override
	public ChessPiece clone() throws CloneNotSupportedException {
		return new Pawn(this.isWhitePiece());
	}
	
	@Override
	public String toString() {
		return "pawn";
	}
	
	private boolean validatePawnOnFirstMove(Coordinates coordinate, Board board,int oneTileForward) {
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		Tools tools = new Tools();
		
		if(captureChessPieceDiagonal(coordinate, board, oneTileForward)) return true;
		if(isMoveOneTileForward(coordinate, oneTileForward) && !board.getBoard()[toY][toX].isChessPiece()) return true;
		if(tools.isBetweenTheBoundary(coordinate, fromX, toX, toY) && !board.getBoard()[toY][toX].isChessPiece()) {
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
	
	
	private boolean validatePawnAfterFirstMove(Coordinates coordinate, Board board, int oneTileForward) {
		Tools tools = new Tools();
		int fromX = coordinate.getFromX();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		
		if(isCaptureChessPieceByEnpassant(coordinate, board)) return board.captureChessPieceByEnpassant(board, coordinate);
		if(captureChessPieceDiagonal(coordinate, board,  oneTileForward)) return  validateCaptureChessPiece(coordinate, board);
		if(tools.isBetweenTheBoundary(coordinate, fromX, toX, toY) && !board.getBoard()[toY][toX].isChessPiece()) return  validateCaptureChessPiece(coordinate, board);
		return false;
	}
	
	
	private boolean isCaptureChessPieceByEnpassant(Coordinates coordinate, Board board) {

		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		if(Math.abs(fromY - toY) == 1 && Math.abs(fromX - toX) == 1 && validateEnpassant(coordinate,board)){
			return true;
		}
		return false;
	}
 
	
	private boolean validateEnpassant(Coordinates coordinate, Board board){
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX= coordinate.getToX();
		int prevOriginY =  coordinate.getPrevOriginY();
		int prevPieceCurrPosY = coordinate.getPrevCurrPosY();
		int prevPieceCurrPosX = coordinate.getPrevCurrPosX();
		Square prevMovedPiece= board.getBoard()[prevPieceCurrPosY][prevPieceCurrPosX];
		int numOfStep= Math.abs(prevPieceCurrPosY-prevOriginY);
		
		if(board.getBoard()[fromY][fromX].isWhitePiece() && fromY==3 && prevMovedPiece.getChessPiece().getChessPieceId() == 'P'){
			if(prevPieceCurrPosY == 3 && (prevPieceCurrPosX == fromX-1 || prevPieceCurrPosX == fromX+1) && toX==prevPieceCurrPosX && numOfStep==2){
				return true;
			}
		}else if(!board.getBoard()[fromY][fromX].isWhitePiece() && fromY == 4 && prevMovedPiece.getChessPiece().getChessPieceId() == 'p'){
			if(prevPieceCurrPosY == 4 && (prevPieceCurrPosX == fromX-1 || prevPieceCurrPosX == fromX+1) && toX==prevPieceCurrPosX && numOfStep==2){
				return true;
			}
		}
			
		return false;
	}
	

	private boolean isPawnNeverMove(int fromY, int PawnFirstRow) {
		return fromY == PawnFirstRow;
	}



	private boolean captureChessPieceDiagonal(Coordinates coordinate, Board board ,int oneTileForward) {
		
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
	
		if((fromY - toY) == oneTileForward && Math.abs(fromX - toX) == 1 && board.getBoard()[toY][toX].isChessPiece())  {
			return true;
		}
		return false;
	}
	
	
	private boolean isPawnPromotion(int toY, int fromY, boolean isWhiteMove) {
		if(isWhiteMove) {
			if(toY == 0 && fromY == 1) return true;
		}
		else {
			if(toY == 7 && fromY == 6) return true;
		}
		return false;
	}
	
	
	private Square[][] changeChessPieceIntoPromotedChessPiece(Board board,Coordinates coordinate) {
		int fromY = coordinate.getFromY();
		int fromX = coordinate.getFromX();

		
		Square[][] boardCopy= new Square[8][8];
		boardCopy = board.getBoard();
		boardCopy[fromY][fromX].setChessPiece(coordinate.getChessPiecePromote());
		
		return boardCopy;
	}
	
}
