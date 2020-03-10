package chess;

public class ChessPieceBehaviors {
	private boolean isWhiteMove;
	
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
				System.out.println("fuck");
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
		if( isBetweenTheBoundary(coordinate, fromX, toX, toY) && !isChessPiece(board, toY, toX)) {
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
		System.out.println(from + " " + to);
		for (int i = from; i < to; i++) {
			if(board[Y][i] != '-' && board[Y][i] != '+') return true; 
		}
		return false;
	}
}
	