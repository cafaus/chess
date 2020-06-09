package game;

import chessPiece.ChessPiece;

public class StalemateBehaviors {

	public boolean isDrawOnStalemate(Board board){
		boolean isCanDoLegalMove=false;
		if(board.isWhiteMove()){
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if(board.getBoard()[i][j].getChessPieceId() == 'p')isCanDoLegalMove = isWhitePawnCanDoMove(board,i,j);	
					else if(board.getBoard()[i][j].getChessPieceId() == 'r')isCanDoLegalMove = isRookCanDoMove(board,i,j);
					else if(board.getBoard()[i][j].getChessPieceId() == 'n')isCanDoLegalMove = isKnightCanDoMove(board,i,j);
					else if(board.getBoard()[i][j].getChessPieceId() == 'b')isCanDoLegalMove = isBishopCanDoMove(board,i,j);
					else if(board.getBoard()[i][j].getChessPieceId() == 'q')isCanDoLegalMove = isQueenCanDoMove(board,i,j);
					else if(board.getBoard()[i][j].getChessPieceId() == 'k')isCanDoLegalMove = isKingCanDoMove(board,i,j);
					if(isCanDoLegalMove) return false;
				}
			}
		}else{
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if(board.getBoard()[i][j].getChessPieceId() == 'P')isCanDoLegalMove = isBlackPawnCanDoMove(board,i,j);	
					else if(board.getBoard()[i][j].getChessPieceId() == 'R')isCanDoLegalMove = isRookCanDoMove(board,i,j);
					else if(board.getBoard()[i][j].getChessPieceId() == 'N')isCanDoLegalMove = isKnightCanDoMove(board,i,j);
					else if(board.getBoard()[i][j].getChessPieceId() == 'B')isCanDoLegalMove = isBishopCanDoMove(board,i,j);
					else if(board.getBoard()[i][j].getChessPieceId() == 'Q')isCanDoLegalMove = isQueenCanDoMove(board,i,j);
					else if(board.getBoard()[i][j].getChessPieceId() == 'K')isCanDoLegalMove = isKingCanDoMove(board,i,j);
					if(isCanDoLegalMove) return false;
				}
			}
		}
		return true;
	}
	
	private boolean isWhitePawnCanDoMove(Board board,int pawnY,int pawnX){	
		if(isChessPieceCanMove(board,pawnY-1,pawnX,pawnY,pawnX)) return true;
		if(isChessPieceCanMove(board,pawnY-1,pawnX-1,pawnY,pawnX)) return true;
		if(isChessPieceCanMove(board,pawnY-1,pawnX+1,pawnY,pawnX)) return true;
		return false;
	}
	
	private boolean isBlackPawnCanDoMove(Board board,int pawnY,int pawnX){	
		if(isChessPieceCanMove(board,pawnY+1,pawnX,pawnY,pawnX)) return true;
		if(isChessPieceCanMove(board,pawnY+1,pawnX-1,pawnY,pawnX)) return true;
		if(isChessPieceCanMove(board,pawnY+1,pawnX+1,pawnY,pawnX)) return true;
		return false;
	}
	
	private boolean isRookCanDoMove(Board board, int rookY, int rookX){
		if(isChessPieceCanMove(board,rookY-1,rookX,rookY,rookX))return true;
		if(isChessPieceCanMove(board,rookY+1,rookX,rookY,rookX))return true;
		if(isChessPieceCanMove(board,rookY,rookX-1,rookY,rookX))return true;
		if(isChessPieceCanMove(board,rookY,rookX+1,rookY,rookX))return true;
		
		return false;
	}
	
	private boolean isKnightCanDoMove(Board board, int knightY, int knightX){
		if(isChessPieceCanMove(board,knightY-2,knightX-1,knightY,knightX))return true;
		if(isChessPieceCanMove(board,knightY-2,knightX+1,knightY,knightX))return true;
		if(isChessPieceCanMove(board,knightY+2,knightX-1,knightY,knightX))return true;
		if(isChessPieceCanMove(board,knightY+2,knightX+1,knightY,knightX))return true;
		if(isChessPieceCanMove(board,knightY-1,knightX-2,knightY,knightX))return true;
		if(isChessPieceCanMove(board,knightY-1,knightX+2,knightY,knightX))return true;
		if(isChessPieceCanMove(board,knightY+1,knightX-2,knightY,knightX))return true;
		if(isChessPieceCanMove(board,knightY+1,knightX+2,knightY,knightX))return true;
		
		return false;
	}
	
	private boolean isBishopCanDoMove(Board board, int bishopY, int bishopX){
		if(isChessPieceCanMove(board,bishopY-1,bishopX-1,bishopY,bishopX))return true;
		if(isChessPieceCanMove(board,bishopY-1,bishopX+1,bishopY,bishopX))return true;
		if(isChessPieceCanMove(board,bishopY+1,bishopX-1,bishopY,bishopX))return true;
		if(isChessPieceCanMove(board,bishopY+1,bishopX-1,bishopY,bishopX))return true;
		
		return false;
	}
	
	private boolean isQueenCanDoMove(Board board, int queenY, int queenX){
		if(isRookCanDoMove(board,queenY,queenX) || isBishopCanDoMove(board,queenY,queenX))return true;
		
		return false;
	}
	
	private boolean isKingCanDoMove(Board board, int kingY, int kingX){
		if(isChessPieceCanMove(board,kingY-1,kingX-1,kingY,kingX))return true;
		if(isChessPieceCanMove(board,kingY-1,kingX,kingY,kingX))return true;
		if(isChessPieceCanMove(board,kingY-1,kingX+1,kingY,kingX))return true;
		if(isChessPieceCanMove(board,kingY,kingX-1,kingY,kingX))return true;
		if(isChessPieceCanMove(board,kingY,kingX+1,kingY,kingX))return true;
		if(isChessPieceCanMove(board,kingY+1,kingX-1,kingY,kingX))return true;
		if(isChessPieceCanMove(board,kingY+1,kingX,kingY,kingX))return true;
		if(isChessPieceCanMove(board,kingY+1,kingX+1,kingY,kingX))return true;
		
		return false;
	}
	
	private boolean isChessPieceCanMove(Board gameBoard,int targetY, int targetX, int fromY, int fromX){
		Coordinates coordinate = new Coordinates();
		Board boardCopy = new Board(gameBoard.isWhiteMove(), gameBoard.isWhiteKingAndRookNeverMove(), gameBoard.isBlackKingAndRookNeverMove(), gameBoard.isKingSafe());
		
		boardCopy.setBoard(gameBoard.getBoard());
		
		coordinate.setFromY(fromY);
		coordinate.setFromX(fromX);		
		coordinate.setToY(targetY);
		coordinate.setToX(targetX);	
		if(coordinate.getToY()<0 || coordinate.getToY()>7 || coordinate.getToX()<0 || coordinate.getToX()>7){
			return false;
		}
		ChessPiece chessPiece = boardCopy.getBoard()[fromY][fromX].getChessPiece();
		boolean canMove = chessPiece.canMove(coordinate, boardCopy);
		
		return canMove;
	}
}
