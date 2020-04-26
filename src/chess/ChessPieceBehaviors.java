package chess;

/**
 *	TODO
 *  pawn promotion 
 *  checkmate 
 *  draw on stalemate 
 *  position unit tests: test coverage min. 90% 
 *  reduce code smells 
 */
public class ChessPieceBehaviors {
	private boolean isWhiteMove;
	
	public ChessPieceBehaviors() {}
	public ChessPieceBehaviors(boolean isWhiteMove) {
		this.isWhiteMove = isWhiteMove;
		
	}
	
	
	
	public boolean isPawnBehavior(Coordinates coordinate,Coordinates prevMovePieceCoordinate,char[][] board) {		
		int fromY = coordinate.getFromY();
		
		if(isWhiteMove) {
			if(isPawnNeverMove(fromY,6)) {
				coordinate.setTopBoundary(fromY - 2);
				coordinate.setBottomBoundary(fromY);
				
				if(validatePawnOnFirstMove(coordinate, board, 1)) return true;
			}else {
				coordinate.setTopBoundary(fromY - 1);
				coordinate.setBottomBoundary(fromY);
				
				if(validatePawnAfterFirstMove(coordinate,prevMovePieceCoordinate,board, 1))return true;
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
				
				if (validatePawnAfterFirstMove(coordinate,prevMovePieceCoordinate, board, -1)) return true;
			}	
		}
		return false;
	}

	
	private boolean validatePawnAfterFirstMove(Coordinates coordinate,Coordinates prevMoveCoordinate, char[][] board, int oneTileForward) {
		int fromX = coordinate.getFromX();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		if(captureChessPieceByEnpassant(coordinate,prevMoveCoordinate, board)) return true;
		if(captureChessPieceDiagonal(coordinate, board,  oneTileForward)) return true;
		if( isBetweenTheBoundary(coordinate, fromX, toX, toY) && !isChessPiece(board, toY, toX) ) return true;
		return false;
	}



	private boolean validatePawnOnFirstMove(Coordinates coordinate, char[][] board,int oneTileForward) {
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		if(captureChessPieceDiagonal(coordinate, board, oneTileForward)) return true;
		if(isMoveOneTileForward(coordinate, oneTileForward) && !isChessPiece(board, toY, toX)) return true;
		if(isBetweenTheBoundary(coordinate, fromX, toX, toY) && !isChessPiece(board, toY, toX)) {
			if(isExistBetweenFromAndToInY(board, toY  ,fromY, toX)) return false;
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



	private boolean captureChessPieceDiagonal(Coordinates coordinate, char[][] board ,int oneTileForward) {
		
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
	
		if( (fromY - toY) == oneTileForward && Math.abs(fromX - toX) == 1 && isChessPiece(board,  toY, toX) )  {
			return true;
		}
		return false;
	}

	protected boolean captureChessPieceByEnpassant(Coordinates coordinate,Coordinates prevMovePieceCoordinate, char[][] board) {

		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
	
		if(Math.abs(fromY - toY) == 1 && Math.abs(fromX - toX) == 1 && validateEnpassant(coordinate,prevMovePieceCoordinate,board)){
			return true;
		}
		return false;
	}
 
	private boolean validateEnpassant(Coordinates coordinate,Coordinates prevMovePieceCoordinate, char[][] board){
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX= coordinate.getToX();
		int prevOriginY =  prevMovePieceCoordinate.getPrevOriginY();
		int prevPieceCurrPosY = prevMovePieceCoordinate.getPrevCurrPosY();
		int prevPieceCurrPosX = prevMovePieceCoordinate.getPrevCurrPosX();
		char prevMovedPiece= board[prevMovePieceCoordinate.getPrevCurrPosY()][prevMovePieceCoordinate.getPrevCurrPosX()];
		int numOfStep= Math.abs(prevPieceCurrPosY-prevOriginY);
		
		if(isWhiteMove && fromY==3 && prevMovedPiece == 'P'){
			if(prevPieceCurrPosY == 3 && (prevPieceCurrPosX == fromX-1 || prevPieceCurrPosX == fromX+1) && toX==prevPieceCurrPosX && numOfStep==2){
				return true;
			}
		}else if(!isWhiteMove && fromY == 4 && prevMovedPiece == 'p'){
			if(prevPieceCurrPosY == 4 && (prevPieceCurrPosX == fromX-1 || prevPieceCurrPosX == fromX+1) && toX==prevPieceCurrPosX && numOfStep==2){
				return true;
			}
		}
			
		return false;
	}

	

	
	private boolean isBetweenTheBoundary(Coordinates coordinate, int area1, int area2, int target) {
		return target >= coordinate.getTopBoundary() &&  target <= coordinate.getBottomBoundary() && area1 == area2;
	}

	

	public boolean isChessPiece(char[][] board,  int toY,int toX)  {
		return isWhite(board[toY][toX]) || isBlack(board[toY][toX]);
	}
	
	public boolean isWhite(char chessPiece) {
		if(chessPiece < 'a' || chessPiece > 'z') return false;
		return true;
	}
	
	public boolean isBlack(char chessPiece) {
		if(chessPiece < 'A' || chessPiece > 'Z') return false;
		return true;
	}

	private boolean isExistBetweenFromAndToInY(char[][] board, int from, int to, int X) {
		++from;
		for (int i = from; i < to; i++) {
			if(board[i][X] != '-' && board[i][X] != '+') return true; 
		}
		return false;
	}

	private boolean isExistBetweenFromAndToInX(char[][] board, int from, int to, int Y) {
		++from;
		for (int i = from; i < to; i++) {
			if(board[Y][i] != '-' && board[Y][i] != '+') return true; 
		}
		return false;
	}
	
	public boolean isRookBehavior(Coordinates coordinate, char[][] board) {
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		boolean fromXisSmallerThanToX =  fromX < toX ? true : false;
		boolean fromYisSmallerThanToY =  fromY < toY ? true : false;

		
		if(toY == fromY) {
			
			if( fromXisSmallerThanToX ) {
				if(isExistBetweenFromAndToInX(board, fromX, toX, toY))return false;
			}
			else {
				if(isExistBetweenFromAndToInX(board, toX ,fromX , toY))return false;
			}
			return true;
		}
		if( toX == fromX) {
			
			if( fromYisSmallerThanToY ) {
				if(isExistBetweenFromAndToInY(board, fromY, toY, toX))return false;
			}
			else {
				if(isExistBetweenFromAndToInY(board, toY  ,fromY, toX))return false;
			}
			return true;

		}
		
		return false;
	}
	
	public boolean isKnightBehavior(Coordinates coordinate, char[][] board) {
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		if(fromY - 2 == toY && (fromX - 1 == toX || fromX + 1 == toX)) return true;
		if(fromY + 2 == toY && (fromX - 1 == toX || fromX + 1 == toX)) return true;
		if(fromX - 2 == toX && (fromY - 1 == toY || fromY + 1 == toY)) return true;
		if(fromX + 2 == toX && (fromY - 1 == toY || fromY + 1 == toY)) return true;
		
		return false;
	}
	
public boolean isBishopBehavior(Coordinates coordinate, char[][] board) {
		
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		if(Math.abs(fromY - toY) == Math.abs(fromX - toX)){
			if(fromY - toY > 0 && fromX - toX > 0 && !isExistOnDiagonalMove(coordinate,board,1,1)) return true;  
			if(fromY - toY > 0 && fromX - toX < 0 && !isExistOnDiagonalMove(coordinate,board,1,-1)) return true;
			if(fromY - toY < 0 && fromX - toX > 0 && !isExistOnDiagonalMove(coordinate,board,-1,1)) return true; 
			if(fromY - toY < 0 && fromX - toX < 0 && !isExistOnDiagonalMove(coordinate,board,-1,-1)) return true;
		}
		
		
		return false;
	}
	
	public boolean isExistOnDiagonalMove(Coordinates coordinate, char[][] board, int yMovement, int xMovement){
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		int currXposition= fromX-xMovement;
		int currYposition= fromY-yMovement;
		
		while(currXposition != toX && currYposition != toY){
			if(board[currYposition][currXposition] != '+' && board[currYposition][currXposition] != '-' ){
				return true;
			}
			currYposition-=yMovement;
			currXposition-=xMovement;
		}
		
		return false;
	}
	
	
	public boolean isQueenBehavior(Coordinates coordinate, char[][] board){
		if(isRookBehavior(coordinate, board) || isBishopBehavior(coordinate, board)) return true;
		return false;
	}
	
	public boolean isKingBehavior(Coordinates coordinate, char[][] board){
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		int differenceX = Math.abs(fromX-toX);
		int differenceY = Math.abs(fromY-toY);
		
		if(differenceX < 2 && differenceY < 2) return true; 
		return false;
	}
	
	public boolean isCastling(Coordinates coordinate, char[][] board, boolean isKingSafe, boolean isWhiteKingAndRookNeverMove, boolean isBlackKingAndRookNeverMove) {
		CheckBehaviors checkBehavior = new CheckBehaviors();
		int fromX = coordinate.getFromX();
		int fromY = coordinate.getFromY();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
		
		
		/*	CASTLING RULE
		 
		 * 	The king and the chosen rook are on the player's first rank.[3]-
		 *	Neither the king nor the chosen rook has previously moved.-
		 *	There are no pieces between the king and the chosen rook.-
		 *	The king is not currently in check.-
		 *	The king does not pass through a square that is attacked by an enemy piece.[4]-
		 *	The king does not end up in check. (True of any legal move.)
	
		 */
		if(!isKingSafe) {
			System.out.println("Cannot do castling because your king is in check condition!!");
			return false;
		}
		
		
		
		if (isWhiteMove && isWhiteKingAndRookNeverMove && toY == 7 && toX == 6 && !isExistBetweenFromAndToInX(board, 4, 7, 7)) {
			if(checkBehavior.isWhiteKingSafe(board, 7, 5)) return true;
		}
		if (isWhiteMove && isWhiteKingAndRookNeverMove && toY == 7 && toX == 2  && !isExistBetweenFromAndToInX(board, 0, 4, 7)) {
			if(checkBehavior.isWhiteKingSafe(board, 7, 3)) return true;
		}
		
		if (!isWhiteMove && isBlackKingAndRookNeverMove && toY == 0 && toX == 6 && !isExistBetweenFromAndToInX(board, 4, 7, 0)) {
			if(checkBehavior.isBlackKingSafe(board, 0, 5)) return true;
		}
		if (!isWhiteMove && isBlackKingAndRookNeverMove && toY == 0 && toX == 2 && !isExistBetweenFromAndToInX(board, 0, 4, 0)) {
			if(checkBehavior.isBlackKingSafe(board, 0, 3)) return true;
		}
		
		return false;
	}
	
	
}
	