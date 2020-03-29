package chess;



public class CheckBehaviors {
	ChessPieceBehaviors chessPieceBehavior = new ChessPieceBehaviors();
	
	public boolean isKingSafe(char[][] board, boolean isWhiteMove) {
		int kingY = 0,kingX = 0;
		
		if(isWhiteMove) { 
			kingY = getKingCoordinateY(board, 'k');
			kingX = getKingCoordinateX(board, 'k');
			
			return isWhiteKingSafe(board, kingY, kingX);
		}
		kingY = getKingCoordinateY(board, 'K');
		kingX = getKingCoordinateX(board, 'K');
		
		return isBlackKingSafe(board, kingY, kingX);
	}
	
	public int getKingCoordinateY(char[][] board, char king) {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j] == king) return i;
			}
		}
		return -1;
	}
	
	public int getKingCoordinateX(char[][] board, char king) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j] == king) return j;
			}
		}
		return -1;
	}
	
	public boolean isWhiteKingSafe(char[][] board,int kingY, int kingX) {
		boolean isSafe = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j] == 'P')isSafe = doBlackPawnCaptureSimulation(kingY, kingX, i, j);
				else if(board[i][j] == 'R') isSafe = doRookCaptureSimulation(board,kingY, kingX,i,j);
				else if(board[i][j] == 'N') isSafe = doKnightCaptureSimulation(kingY,kingX,i,j);
				else if(board[i][j] == 'B') isSafe = doBishopCaptureSimulation(board,kingY,kingX,i,j);
				else if(board[i][j] == 'Q') isSafe = doQueenCaptureSimulation(board,kingY,kingX,i,j);
				else if(board[i][j] == 'K') isSafe = doKingCaptureSimulation(kingY,kingX,i,j);
				if(!isSafe)return false;
			}
		}
		return isSafe;
	}
	public boolean isBlackKingSafe(char[][] board,int kingY, int kingX) {
		boolean isSafe = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j] == 'p')isSafe = doWhitePawnCaptureSimulation(kingY, kingX, i, j);
				else if(board[i][j] == 'r') isSafe = doRookCaptureSimulation(board,kingY, kingX,i,j);
				else if(board[i][j] == 'n') isSafe = doKnightCaptureSimulation(kingY,kingX,i,j);
				else if(board[i][j] == 'b') isSafe = doBishopCaptureSimulation(board,kingY,kingX,i,j);
				else if(board[i][j] == 'q') isSafe = doQueenCaptureSimulation(board,kingY,kingX,i,j);
				else if(board[i][j] == 'k') isSafe = doKingCaptureSimulation(kingY,kingX,i,j);
				if(!isSafe)return false;
			}
		}
		return isSafe;
	}
	


	private boolean doWhitePawnCaptureSimulation(int kingY, int kingX, int pawnY, int pawnX) {
		if(pawnY - 1 == kingY && pawnX + 1 == kingX) return false;
		if(pawnY - 1 == kingY && pawnX - 1 == kingX) return false;
		return true;
	}
	private boolean doBlackPawnCaptureSimulation(int kingY, int kingX, int pawnY, int pawnX) {
		if(pawnY + 1 == kingY && pawnX + 1 == kingX) return false;
		if(pawnY + 1 == kingY && pawnX - 1 == kingX) return false;
		return true;
	}
	
	private boolean doRookCaptureSimulation(char[][] board, int kingY, int kingX, int rookY, int rookX) {
		return 	simulateToTop(board, kingY, kingX, rookY - 1, rookX) &&
				simulateToBottom(board, kingY, kingX, rookY + 1, rookX) &&
				simulateToLeft(board, kingY, kingX, rookY, rookX - 1) &&
				simulateToRight(board, kingY, kingX, rookY, rookX + 1);
	}

	private boolean simulateToTop(char[][] board,int kingY, int kingX, int rookY, int rookX ) {
		if(rookY < 0) return true;
		if(rookY == kingY && rookX == kingX) return false;
		if(chessPieceBehavior.isChessPiece(board, rookY, rookX)) return true;
		
		
		return simulateToTop(board, kingY, kingX, rookY - 1, rookX);
	}
	
	private boolean simulateToBottom(char[][] board,int kingY, int kingX, int rookY, int rookX ) {
		if(rookY > 7)return true;
		if(rookY == kingY && rookX == kingX) return false;
		
		if(chessPieceBehavior.isChessPiece(board, rookY, rookX))return true;
		return simulateToBottom(board, kingY, kingX, rookY + 1, rookX);
	}
	
	private boolean simulateToLeft(char[][] board,int kingY, int kingX, int rookY, int rookX ) {
		if(rookX < 0) return true;
		if(rookY == kingY && rookX == kingX) return false;
		
		if(chessPieceBehavior.isChessPiece(board, rookY, rookX)) return true;
		return simulateToLeft(board, kingY, kingX, rookY, rookX - 1);
	}
	
	private boolean simulateToRight(char[][] board,int kingY, int kingX, int rookY, int rookX ) {
		if(rookX > 7) return true;
		if(rookY == kingY && rookX == kingX) return false;
		
		if(chessPieceBehavior.isChessPiece(board, rookY, rookX)) return true;
		return simulateToRight(board, kingY, kingX, rookY, rookX + 1);
	}
	private boolean doKnightCaptureSimulation( int kingY, int kingX, int rookY, int rookX) {
		if(rookY - 2 == kingY && (rookX - 1 == kingX || rookX + 1 == kingX)) return false;
		if(rookY + 2 == kingY && (rookX - 1 == kingX || rookX + 1 == kingX)) return false;
		if(rookX - 2 == kingX && (rookY - 1 == kingY || rookY + 1 == kingY)) return false;
		if(rookX + 2 == kingX && (rookY - 1 == kingY || rookY + 1 == kingY)) return false;
		return true;
	}
	
	private boolean doBishopCaptureSimulation(char[][] board, int kingY, int kingX, int bishopY, int bishopX) {
		
		return simulateToTopLeft(board, kingY, kingX, bishopY - 1, bishopX - 1) &&
			   simulateToTopRight(board, kingY, kingX, bishopY - 1, bishopX + 1) &&
			   simulateToBottomRight(board, kingY, kingX, bishopY + 1, bishopX + 1) &&
			   simulateToBottomLeft(board, kingY, kingX, bishopY + 1, bishopX - 1);
	}
	
	private boolean simulateToTopLeft(char[][] board, int kingY, int kingX, int bishopY, int bishopX) {
		if(bishopY < 0 || bishopX < 0) return true;
		if(bishopY == kingY && bishopX == kingX) return false;
		if(chessPieceBehavior.isChessPiece(board, bishopY, bishopX)) return true;
		return simulateToTopLeft(board, kingY, kingX, bishopY - 1, bishopX - 1);
	}
	
	private boolean simulateToTopRight(char[][] board, int kingY, int kingX, int bishopY, int bishopX) {
		if(bishopY < 0 || bishopX > 7) return true;
		if(bishopY == kingY && bishopX == kingX) return false;
		if(chessPieceBehavior.isChessPiece(board, bishopY, bishopX)) return true;
		return simulateToTopRight(board, kingY, kingX, bishopY - 1, bishopX + 1);
	}
	
	private boolean simulateToBottomRight(char[][] board, int kingY, int kingX, int bishopY, int bishopX) {
		if(bishopY > 7 || bishopX > 7) return true;
		if(bishopY == kingY && bishopX == kingX) return false;
		if(chessPieceBehavior.isChessPiece(board, bishopY, bishopX)) return true;
		return simulateToBottomRight(board, kingY, kingX, bishopY + 1, bishopX + 1);
	}
	
	private boolean simulateToBottomLeft(char[][] board, int kingY, int kingX, int bishopY, int bishopX) {
		if(bishopY > 7 || bishopX < 0) return true;
		if(bishopY == kingY && bishopX == kingX) return false;
		if(chessPieceBehavior.isChessPiece(board, bishopY, bishopX)) return true;
		return simulateToBottomLeft(board, kingY, kingX, bishopY + 1, bishopX -1);
	}
	
	private boolean doQueenCaptureSimulation(char[][] board, int kingY, int kingX, int queenY, int queenX) {
		return doRookCaptureSimulation(board, kingY, kingX, queenY, queenX) && doBishopCaptureSimulation(board, kingY, kingX, queenY, queenX);
	}
	
	private boolean doKingCaptureSimulation(int kingY, int kingX, int enemyKingY, int enemyKingX) {
		if(enemyKingY - 1 == kingY && enemyKingX == kingX) return false;
		if(enemyKingY == kingY && enemyKingX + 1 == kingX) return false;
		if(enemyKingY + 1 == kingY && enemyKingX == kingX) return false;
		if(enemyKingY == kingY && enemyKingX - 1 == kingX) return false;
		
		if(enemyKingY - 1 == kingY && enemyKingX - 1 == kingX) return false;
		if(enemyKingY - 1 == kingY && enemyKingX + 1 == kingX) return false;
		if(enemyKingY + 1 == kingY && enemyKingX + 1 == kingX) return false;
		if(enemyKingY + 1 == kingY && enemyKingX - 1 == kingX) return false;
		return true;
	}
	
	
}
