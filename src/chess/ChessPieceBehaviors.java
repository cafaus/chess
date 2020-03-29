package chess;

/**
 *	TODO
 *algebraic notation
 */
public class ChessPieceBehaviors {
	private boolean isWhiteMove;
	
	
	public ChessPieceBehaviors() {}
	public ChessPieceBehaviors(boolean isWhiteMove) {
		this.isWhiteMove = isWhiteMove;
		
	}
	
	
	
	public boolean isPawnBehavior(Coordinates coordinate, char[][] board) {		
		int fromY = coordinate.getFromY();
		
		if(isWhiteMove) {
			if(isPawnNeverMove(fromY,6)) {
				coordinate.setTopBoundary(fromY - 2);
				coordinate.setBottomBoundary(fromY);
				
				if(validatePawnOnFirstMove(coordinate, board, 1)) return true;
			}else {
				coordinate.setTopBoundary(fromY - 1);
				coordinate.setBottomBoundary(fromY);
				
				if(validatePawnAfterFirstMove(coordinate, board, 1))return true;
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
				
				if (validatePawnAfterFirstMove(coordinate, board, -1)) return true;
			}	
		}
		return false;
	}



	private boolean validatePawnAfterFirstMove(Coordinates coordinate, char[][] board, int oneTileForward) {
		int fromX = coordinate.getFromX();
		int toX = coordinate.getToX();
		int toY = coordinate.getToY();
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
		
		boolean fromXisSmallerThanToX =  fromX < toX ? true : false;
		boolean fromYisSmallerThanToY =  fromY < toY ? true : false;
		int differenceBetweenY = Math.abs(fromY - toY);
		int differenceBetweenX = Math.abs(fromX - toX); 
		
		if(differenceBetweenY == differenceBetweenX){
			if(isTopLeftDiagonal(fromYisSmallerThanToY,fromXisSmallerThanToX)) {
				return checkTopLeftDiagonal(board, fromX, fromY, differenceBetweenY);
			}
			if(isTopRightDiagonal(fromYisSmallerThanToY,fromXisSmallerThanToX)) {
				return checkTopRightDiagonal(board, fromX, fromY, differenceBetweenY);
			}
			if(isBottomRightDiagonal(fromYisSmallerThanToY, fromXisSmallerThanToX)) {
				return checkBottomRightDiagonal(board, fromX, fromY, differenceBetweenY);
			}
			if(isBottomLeftDiagonal(fromYisSmallerThanToY, fromXisSmallerThanToX)) {
				return checkBottomLeftDiagonal(board, fromX, fromY, differenceBetweenY);
			}
		}
						
		return false;
	}


	private boolean checkBottomLeftDiagonal(char[][] board, int fromX, int fromY, int differenceBetweenY) {
		int indexCheckerY = fromY;
		int indexCheckerX = fromX;
		for (int i = 1; i < differenceBetweenY; i++) {
			++indexCheckerY;
			--indexCheckerX;
			
			if(isChessPiece(board, indexCheckerY, indexCheckerX)) return false;
		}
		return true;
	}


	private boolean checkBottomRightDiagonal(char[][] board, int fromX, int fromY, int differenceBetweenY) {
		int indexCheckerY = fromY;
		int indexCheckerX = fromX;
		for (int i = 1; i < differenceBetweenY; i++) {
			++indexCheckerY;
			++indexCheckerX;
			
			if(isChessPiece(board, indexCheckerY, indexCheckerX)) return false;
		}
		return true;
	}


	private boolean checkTopRightDiagonal(char[][] board, int fromX, int fromY, int differenceBetweenY) {
		int indexCheckerY = fromY;
		int indexCheckerX = fromX;
		for (int i = 1; i < differenceBetweenY; i++) {
			--indexCheckerY;
			++indexCheckerX;
			
			if(isChessPiece(board, indexCheckerY, indexCheckerX)) return false;
		}
		return true;
	}


	private boolean checkTopLeftDiagonal(char[][] board, int fromX, int fromY, int differenceBetweenY) {
		int indexCheckerY = fromY;
		int indexCheckerX = fromX;
		for (int i = 1; i < differenceBetweenY; i++) {
			--indexCheckerY;
			--indexCheckerX;
			
			if(isChessPiece(board, indexCheckerY, indexCheckerX)) return false;
		}
		return true;
	}


	private boolean isBottomLeftDiagonal( boolean fromYisSmallerThanToY, boolean fromXisSmallerThanToX) {
		return fromYisSmallerThanToY && !fromXisSmallerThanToX;
	}


	private boolean isBottomRightDiagonal(boolean fromYisSmallerThanToY, boolean fromXisSmallerThanToX) {
		return fromYisSmallerThanToY && fromXisSmallerThanToX;
	}


	private boolean isTopRightDiagonal(boolean fromYisSmallerThanToY, boolean fromXisSmallerThanToX) {
		return !fromYisSmallerThanToY && fromXisSmallerThanToX;
	}


	private boolean isTopLeftDiagonal(boolean fromYisSmallerThanToY, boolean fromXisSmallerThanToX) {
		return !fromYisSmallerThanToY && !fromXisSmallerThanToX;
	}
	
	public boolean isQueenBehavior(Coordinates coordinate, char[][] board){
		if(isRookBehavior(coordinate, board)) return true;
		if(isBishopBehavior(coordinate, board)) return true;
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
	