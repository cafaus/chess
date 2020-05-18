package game;

import chessPiece.Square;

public class CheckBehaviors {
	
	
	public boolean isCheckMate(Board board) {
		int checked = 0;
		char king = board.isWhiteMove() ? 'k' : 'K';
		int kingY = getKingCoordinateY(board.getBoard(), king);
		int kingX = getKingCoordinateX(board.getBoard(), king);
		
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY - 1, kingX)) checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY - 1, kingX + 1)) checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY, kingX + 1)) checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY + 1, kingX + 1)) checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY + 1, kingX)) checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY + 1, kingX - 1)) checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY , kingX - 1)) checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY - 1, kingX - 1)) checked++;
		
		
		if(checked < 8) return false;
		return true;
	}

	private boolean isKingMoveAreaSafe(Board board, int kingY, int kingX, int toY, int toX) {
		Coordinates coordinate = new Coordinates();
		Board boardObjCopy = new Board();
		boardObjCopy.setBoard(board.getBoard());
		Square[][] boardCopy = new Square[8][8];
		
		
		coordinate.setFromY(kingY);
		coordinate.setFromX(kingX);
		
		boardCopy = boardObjCopy.getBoard();
		
		coordinate.setToY(toY);
		coordinate.setToX(toX);
		
		boardCopy[kingY][kingX].getChessPiece().canMove(coordinate, boardObjCopy);
		
		if(!isKingSafe(boardCopy, board.isWhiteMove())) return false;
		return true;
	}
	
	public boolean isKingSafe(Square[][] board, boolean isWhiteMove) {
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
	
	public int getKingCoordinateY(Square[][] board, char king) {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j].getChessPieceId() == king) return i;
			}
		}
		return -1;
	}
	
	public int getKingCoordinateX(Square[][] board, char king) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {				
				if(board[i][j].getChessPieceId() == king) return j;
			}
		}
		return -1;
	}
	
	public boolean isWhiteKingSafe(Square[][] board,int kingY, int kingX) {
		boolean isSafe = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j].getChessPieceId() == 'P')isSafe = doBlackPawnCaptureSimulation(kingY, kingX, i, j);
				else if(board[i][j].getChessPieceId() == 'R') isSafe = doRookCaptureSimulation(board,kingY, kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'N') isSafe = doKnightCaptureSimulation(kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'B') isSafe = doBishopCaptureSimulation(board,kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'Q') isSafe = doQueenCaptureSimulation(board,kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'K') isSafe = doKingCaptureSimulation(kingY,kingX,i,j);
				if(!isSafe) return false;
			}
		}
		return isSafe;
	}
	public boolean isBlackKingSafe(Square[][] board,int kingY, int kingX) {
		boolean isSafe = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j].getChessPieceId() == 'p')isSafe = doWhitePawnCaptureSimulation(kingY, kingX, i, j);
				else if(board[i][j].getChessPieceId() == 'r') isSafe = doRookCaptureSimulation(board,kingY, kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'n') isSafe = doKnightCaptureSimulation(kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'b') isSafe = doBishopCaptureSimulation(board,kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'q') isSafe = doQueenCaptureSimulation(board,kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'k') isSafe = doKingCaptureSimulation(kingY,kingX,i,j);
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
	
	private boolean doRookCaptureSimulation(Square[][] board, int kingY, int kingX, int rookY, int rookX) {
		return 	simulateToTop(board, kingY, kingX, rookY - 1, rookX) &&
				simulateToBottom(board, kingY, kingX, rookY + 1, rookX) &&
				simulateToLeft(board, kingY, kingX, rookY, rookX - 1) &&
				simulateToRight(board, kingY, kingX, rookY, rookX + 1);
	}

	private boolean simulateToTop(Square[][] board,int kingY, int kingX, int rookY, int rookX ) {
		if(rookY < 0) return true;
		if(rookY == kingY && rookX == kingX) return false;
		if(board[rookY][rookX].isChessPiece()) return true;
		
		
		return simulateToTop(board, kingY, kingX, rookY - 1, rookX);
	}
	
	private boolean simulateToBottom(Square[][] board,int kingY, int kingX, int rookY, int rookX ) {
		if(rookY > 7)return true;
		if(rookY == kingY && rookX == kingX) return false;
		
		if(board[rookY][rookX].isChessPiece())return true;
		return simulateToBottom(board, kingY, kingX, rookY + 1, rookX);
	}
	
	private boolean simulateToLeft(Square[][] board,int kingY, int kingX, int rookY, int rookX ) {
		if(rookX < 0) return true;
		if(rookY == kingY && rookX == kingX) return false;
		
		if(board[rookY][rookX].isChessPiece()) return true;
		return simulateToLeft(board, kingY, kingX, rookY, rookX - 1);
	}
	
	private boolean simulateToRight(Square[][] board,int kingY, int kingX, int rookY, int rookX ) {
		if(rookX > 7) return true;
		if(rookY == kingY && rookX == kingX) return false;
		
		if(board[rookY][rookX].isChessPiece()) return true;
		return simulateToRight(board, kingY, kingX, rookY, rookX + 1);
	}
	private boolean doKnightCaptureSimulation( int kingY, int kingX, int rookY, int rookX) {
		if(rookY - 2 == kingY && (rookX - 1 == kingX || rookX + 1 == kingX)) return false;
		if(rookY + 2 == kingY && (rookX - 1 == kingX || rookX + 1 == kingX)) return false;
		if(rookX - 2 == kingX && (rookY - 1 == kingY || rookY + 1 == kingY)) return false;
		if(rookX + 2 == kingX && (rookY - 1 == kingY || rookY + 1 == kingY)) return false;
		return true;
	}
	
	private boolean doBishopCaptureSimulation(Square[][] board, int kingY, int kingX, int bishopY, int bishopX) {
		
		return simulateToTopLeft(board, kingY, kingX, bishopY - 1, bishopX - 1) &&
			   simulateToTopRight(board, kingY, kingX, bishopY - 1, bishopX + 1) &&
			   simulateToBottomRight(board, kingY, kingX, bishopY + 1, bishopX + 1) &&
			   simulateToBottomLeft(board, kingY, kingX, bishopY + 1, bishopX - 1);
	}
	
	private boolean simulateToTopLeft(Square[][] board, int kingY, int kingX, int bishopY, int bishopX) {
		if(bishopY < 0 || bishopX < 0) return true;
		if(bishopY == kingY && bishopX == kingX) return false;
		if(board[bishopY][bishopX].isChessPiece()) return true;
		return simulateToTopLeft(board, kingY, kingX, bishopY - 1, bishopX - 1);
	}
	
	private boolean simulateToTopRight(Square[][] board, int kingY, int kingX, int bishopY, int bishopX) {
		if(bishopY < 0 || bishopX > 7) return true;
		if(bishopY == kingY && bishopX == kingX) return false;
		if(board[bishopY][bishopX].isChessPiece()) return true;
		return simulateToTopRight(board, kingY, kingX, bishopY - 1, bishopX + 1);
	}
	
	private boolean simulateToBottomRight(Square[][] board, int kingY, int kingX, int bishopY, int bishopX) {
		if(bishopY > 7 || bishopX > 7) return true;
		if(bishopY == kingY && bishopX == kingX) return false;
		if(board[bishopY][bishopX].isChessPiece()) return true;
		return simulateToBottomRight(board, kingY, kingX, bishopY + 1, bishopX + 1);
	}
	
	private boolean simulateToBottomLeft(Square[][] board, int kingY, int kingX, int bishopY, int bishopX) {
		if(bishopY > 7 || bishopX < 0) return true;
		if(bishopY == kingY && bishopX == kingX) return false;
		if(board[bishopY][bishopX].isChessPiece()) return true;
		return simulateToBottomLeft(board, kingY, kingX, bishopY + 1, bishopX -1);
	}
	
	private boolean doQueenCaptureSimulation(Square[][] board, int kingY, int kingX, int queenY, int queenX) {
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
