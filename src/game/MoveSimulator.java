package game;

import chessPiece.ChessPiece;
import chessPiece.Square;

public class MoveSimulator {
	
	// long parameter, data clump
		protected boolean isWhitePawnCannotProtect(Square[][] board,int kingYProtectArea, int kingXProtectArea, int pawnY, int pawnX) {
			if(board[kingYProtectArea][kingXProtectArea].isBlackPiece()){
				if(pawnY - 1 == kingYProtectArea && pawnX + 1 == kingXProtectArea) return false;
				if(pawnY - 1 == kingYProtectArea && pawnX - 1 == kingXProtectArea) return false;
			}else if(!board[kingYProtectArea][kingXProtectArea].isChessPiece()){
				if(pawnY - 1 == kingYProtectArea && pawnX == kingXProtectArea) return false;
			}
			return true;
		}
		//long parameter, data clump
		protected boolean isBlackPawnCannotProtect(Square[][] board,int kingYProtectArea, int kingXProtectArea, int pawnY, int pawnX) {
			if(board[kingYProtectArea][kingXProtectArea].isWhitePiece()){
				if(pawnY + 1 == kingYProtectArea && pawnX + 1 == kingXProtectArea) return false;
				if(pawnY + 1 == kingYProtectArea && pawnX - 1 == kingXProtectArea) return false;
			}else if(!board[kingYProtectArea][kingXProtectArea].isChessPiece()){
			    if(pawnY + 1 == kingYProtectArea && pawnX == kingXProtectArea) return false;
			}
			return true;
		}
		
		protected boolean isKingCannotDefend(Board gameBoard,int kingYProtectArea, int kingXProtectArea, int kingY, int kingX){	
			int yCalculationMaterial = kingY - kingYProtectArea;
			int xCalculationMaterial = kingX - kingXProtectArea;
			boolean isKingDefMoveSafe = false;

			if((Math.abs(yCalculationMaterial) == 0 || Math.abs(yCalculationMaterial) == 1) &&  (Math.abs(xCalculationMaterial) == 0 ||  Math.abs(xCalculationMaterial) == 1)){
				isKingDefMoveSafe = isKingDefendMoveSafe(gameBoard,kingYProtectArea,kingXProtectArea,kingY,kingX);
			}
			 if(isKingDefMoveSafe)return false;
			return true;
		} 
		
		protected boolean isKingDefendMoveSafe(Board gameBoard,int kingYProtectArea, int kingXProtectArea, int kingY, int kingX){
			Coordinates coordinate = new Coordinates();
			Board boardCopy = new Board(gameBoard.isWhiteMove(), gameBoard.isWhiteKingAndRookNeverMove(), gameBoard.isBlackKingAndRookNeverMove(), gameBoard.isKingSafe());
			
			boardCopy.setBoard(gameBoard.getBoard());
			
			coordinate.setFromY(kingY);
			coordinate.setFromX(kingX);		
			coordinate.setToY(kingYProtectArea);
			coordinate.setToX(kingXProtectArea);		
			ChessPiece chessPiece = boardCopy.getBoard()[kingY][kingX].getChessPiece();
			boolean canMove = chessPiece.validateCaptureChessPiece(coordinate, boardCopy);
			
			return canMove;
		}
		
		protected boolean isWhitePawnCannotCapture(int targetY, int targetX, int pawnY, int pawnX) {
			if(pawnY - 1 == targetY && pawnX + 1 == targetX) return false;
			if(pawnY - 1 == targetY && pawnX - 1 == targetX) return false;
			return true;
		}
		protected boolean isBlackPawnCannotMoveToTarget(int targetY, int targetX, int pawnY, int pawnX) {
			if(pawnY + 1 == targetY && pawnX + 1 == targetX) return false;
			if(pawnY + 1 == targetY && pawnX - 1 == targetX) return false;
			return true;
		}
		
		// data clump (Square[][] board, int kingY, int kingX
		// long prameter
		protected boolean isRookCannotMoveToTarget(Square[][] board, int targetY, int targetX, int rookY, int rookX) {
			return 	simulateToTop(board, targetY, targetX, rookY - 1, rookX) &&
					simulateToBottom(board, targetY, targetX, rookY + 1, rookX) &&
					simulateToLeft(board, targetY, targetX, rookY, rookX - 1) &&
					simulateToRight(board, targetY, targetX, rookY, rookX + 1);
		}

		protected boolean simulateToTop(Square[][] board,int targetY, int targetX, int rookY, int rookX ) {
			if(rookY < 0) return true;
			if(rookY == targetY && rookX == targetX) return false;
			if(board[rookY][rookX].isChessPiece()) return true;
			
			
			return simulateToTop(board, targetY, targetX, rookY - 1, rookX);
		}
		
		protected boolean simulateToBottom(Square[][] board,int targetY, int targetX, int rookY, int rookX ) {
			if(rookY > 7)return true;
			if(rookY == targetY && rookX == targetX) return false;
			
			if(board[rookY][rookX].isChessPiece())return true;
			return simulateToBottom(board, targetY, targetX, rookY + 1, rookX);
		}
		
		protected boolean simulateToLeft(Square[][] board,int targetY, int targetX, int rookY, int rookX ) {
			if(rookX < 0) return true;
			if(rookY == targetY && rookX == targetX) return false;
			
			if(board[rookY][rookX].isChessPiece()) return true;
			return simulateToLeft(board, targetY, targetX, rookY, rookX - 1);
		}
		
		protected boolean simulateToRight(Square[][] board,int targetY, int targetX, int rookY, int rookX ) {
			if(rookX > 7) return true;
			if(rookY == targetY && rookX == targetX) return false;
			
			if(board[rookY][rookX].isChessPiece()) return true;
			return simulateToRight(board, targetY, targetX, rookY, rookX + 1);
		}
		protected boolean isKnightCannotMoveToTarget( int targetY, int targetX, int rookY, int rookX) {
			if(rookY - 2 == targetY && (rookX - 1 == targetX || rookX + 1 == targetX)) return false;
			if(rookY + 2 == targetY && (rookX - 1 == targetX || rookX + 1 == targetX)) return false;
			if(rookX - 2 == targetX && (rookY - 1 == targetY || rookY + 1 == targetY)) return false;
			if(rookX + 2 == targetX && (rookY - 1 == targetY || rookY + 1 == targetY)) return false;
			return true;
		}
		
		protected boolean isBishopCannotMoveToTarget(Square[][] board, int targetY, int targetX, int bishopY, int bishopX) {
			
			return simulateToTopLeft(board, targetY, targetX, bishopY - 1, bishopX - 1) &&
				   simulateToTopRight(board, targetY, targetX, bishopY - 1, bishopX + 1) &&
				   simulateToBottomRight(board, targetY, targetX, bishopY + 1, bishopX + 1) &&
				   simulateToBottomLeft(board, targetY, targetX, bishopY + 1, bishopX - 1);
		}
		
		protected boolean simulateToTopLeft(Square[][] board, int targetY, int targetX, int bishopY, int bishopX) {
			if(bishopY < 0 || bishopX < 0) return true;
			if(bishopY == targetY && bishopX == targetX) return false;
			if(board[bishopY][bishopX].isChessPiece()) return true;
			return simulateToTopLeft(board, targetY, targetX, bishopY - 1, bishopX - 1);
		}
		
		protected boolean simulateToTopRight(Square[][] board, int targetY, int targetX, int bishopY, int bishopX) {
			if(bishopY < 0 || bishopX > 7) return true;
			if(bishopY == targetY && bishopX == targetX) return false;
			if(board[bishopY][bishopX].isChessPiece()) return true;
			return simulateToTopRight(board, targetY, targetX, bishopY - 1, bishopX + 1);
		}
		
		protected boolean simulateToBottomRight(Square[][] board, int targetY, int targetX, int bishopY, int bishopX) {
			if(bishopY > 7 || bishopX > 7) return true;
			if(bishopY == targetY && bishopX == targetX) return false;
			if(board[bishopY][bishopX].isChessPiece()) return true;
			return simulateToBottomRight(board, targetY, targetX, bishopY + 1, bishopX + 1);
		}
		
		protected boolean simulateToBottomLeft(Square[][] board, int targetY, int targetX, int bishopY, int bishopX) {
			if(bishopY > 7 || bishopX < 0) return true;
			if(bishopY == targetY && bishopX == targetX) return false;
			if(board[bishopY][bishopX].isChessPiece()) return true;
			return simulateToBottomLeft(board, targetY, targetX, bishopY + 1, bishopX -1);
		}
		
		protected boolean isQueenCannotMoveToTarget(Square[][] board, int targetY, int targetX, int queenY, int queenX) {
			return isRookCannotMoveToTarget(board, targetY, targetX, queenY, queenX) && isBishopCannotMoveToTarget(board, targetY, targetX, queenY, queenX);
		}
		
		protected boolean isKingCannotMoveToTarget(int targetY, int targetX, int enemyKingY, int enemyKingX) {
			if(enemyKingY - 1 == targetY && enemyKingX == targetX) return false;
			if(enemyKingY == targetY && enemyKingX + 1 == targetX) return false;
			if(enemyKingY + 1 == targetY && enemyKingX == targetX) return false;
			if(enemyKingY == targetY && enemyKingX - 1 == targetX) return false;
			if(enemyKingY - 1 == targetY && enemyKingX - 1 == targetX) return false;
			if(enemyKingY - 1 == targetY && enemyKingX + 1 == targetX) return false;
			if(enemyKingY + 1 == targetY && enemyKingX + 1 == targetX) return false;
			if(enemyKingY + 1 == targetY && enemyKingX - 1 == targetX) return false;
			return true;
		}
		
}
