package game;

import chessPiece.Square;
import chessPiece.ChessPiece;

public class CheckBehaviors {
	
	public boolean isCheckMate(Board board) {
		int checked = 0;
		char king = board.isWhiteMove() ? 'k' : 'K';
		int kingY = getKingCoordinateY(board.getBoard(), king); 
		int kingX = getKingCoordinateX(board.getBoard(), king);
		
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY - 1, kingX)) checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY - 1, kingX + 1))checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY - 1, kingX - 1))checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY , kingX - 1))checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY, kingX + 1))checked++;	
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY + 1, kingX + 1))checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY + 1, kingX))checked++;
		if(!isKingMoveAreaSafe(board, kingY, kingX, kingY + 1, kingX - 1))checked++;
		
		if(checked < 8) return false;
		return true;
	}

		
	// long parameter, dataclump
	private boolean isKingMoveAreaSafe(Board board, int kingY, int kingX, int toY, int toX) {
		Coordinates coordinate = new Coordinates();
		Board boardCopy = new Board(board.isWhiteMove(), board.isWhiteKingAndRookNeverMove(), board.isBlackKingAndRookNeverMove(), board.isKingSafe());
		boardCopy.setBoard(board.getBoard());
				
		coordinate.setFromY(kingY);
		coordinate.setFromX(kingX);		
		coordinate.setToY(toY);
		coordinate.setToX(toX);
		
		boardCopy.getBoard()[kingY][kingX].getChessPiece().canMove(coordinate, boardCopy);
		
		if(!isKingSafe(boardCopy.getBoard(), board.isWhiteMove())){
				
			if(isKingAreaAvailableToProtect(boardCopy.getBoard(),toY,toX, board.isWhiteMove())){
				int ycalculationMaterial = kingY - toY;
				int xcalculationMaterial = kingX - toX;
				int areaYToProtect= toY;
				int areaXToProtect = toX;

				while(!boardCopy.getBoard()[areaYToProtect][areaXToProtect].isChessPiece() && (areaYToProtect > 0 && areaYToProtect <7) && (areaXToProtect > 0 && areaXToProtect <7)){	
					if (isOtherPieceCanProtectKing(board, boardCopy.getBoard(), areaYToProtect,areaXToProtect))return true;
					areaYToProtect-= ycalculationMaterial;
					areaXToProtect-= xcalculationMaterial;
				}
				if (isOtherPieceCanProtectKing(board, boardCopy.getBoard(), areaYToProtect,areaXToProtect))return true;
			}
			return false;
		}
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
		MoveSimulator moveSimulator= new MoveSimulator();
		boolean isSafe = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j].getChessPieceId() == 'P')isSafe = moveSimulator.isBlackPawnCannotMoveToTarget(kingY, kingX, i, j);	
				else if(board[i][j].getChessPieceId() == 'R')isSafe = moveSimulator.isRookCannotMoveToTarget(board,kingY, kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'N') isSafe = moveSimulator.isKnightCannotMoveToTarget(kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'B')isSafe = moveSimulator.isBishopCannotMoveToTarget(board,kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'Q')isSafe = moveSimulator.isQueenCannotMoveToTarget(board,kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'K')isSafe = moveSimulator.isKingCannotMoveToTarget(kingY,kingX,i,j);
				if(!isSafe) return false;
			}
		}
		return isSafe;
	}
	public boolean isBlackKingSafe(Square[][] board,int kingY, int kingX) {
		MoveSimulator moveSimulator= new MoveSimulator();
		boolean isSafe = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j].getChessPieceId() == 'p')isSafe = moveSimulator.isWhitePawnCannotCapture(kingY, kingX, i, j);
				else if(board[i][j].getChessPieceId() == 'r')isSafe = moveSimulator.isRookCannotMoveToTarget(board,kingY, kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'n')isSafe = moveSimulator.isKnightCannotMoveToTarget(kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'b')isSafe = moveSimulator.isBishopCannotMoveToTarget(board,kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'q')isSafe = moveSimulator.isQueenCannotMoveToTarget(board,kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'k')isSafe = moveSimulator.isKingCannotMoveToTarget(kingY,kingX,i,j);
				if(!isSafe)return false;
			}
		}
		return isSafe;
	}
	
	private boolean isOtherPieceCanProtectKing(Board gameBoard,Square[][] board, int surroundKingYSquare, int surroundKingXSquare){
		if(gameBoard.isWhiteMove()){
			return !isWhiteKingCannotBeProtect(gameBoard,board, surroundKingYSquare, surroundKingXSquare);
		}else if (!gameBoard.isWhiteMove()){
			return !isBlackKingCannotBeProtect(gameBoard,board, surroundKingYSquare, surroundKingXSquare);
		}
		
		return false;
	}

	private boolean isKingAreaAvailableToProtect(Square[][] board,  int surroundKingYSquare, int surroundKingXSquare, boolean isWhiteMove){
		if((surroundKingYSquare>= 0 && surroundKingXSquare >=0 && surroundKingYSquare <=7 && surroundKingXSquare <=7)){
			if(isWhiteMove && (!board[surroundKingYSquare][surroundKingXSquare].isChessPiece() 
				|| !board[surroundKingYSquare][surroundKingXSquare].isWhitePiece())){
				return true;
			}else if(!isWhiteMove && (!board[surroundKingYSquare][surroundKingXSquare].isChessPiece() 
					|| !board[surroundKingYSquare][surroundKingXSquare].isBlackPiece())){
				return true;
			}
		}
		
		return false;
	}
	
	// long parameter di king, data clump
	private boolean isWhiteKingCannotBeProtect(Board gameBoard,Square[][] board,int kingY, int kingX) {
		MoveSimulator moveSimulator= new MoveSimulator();
		boolean isSafe = true;
		Coordinates coordinate = new Coordinates();
		Board gameBoardCopy = new Board(gameBoard.isWhiteMove(), gameBoard.isWhiteKingAndRookNeverMove(), gameBoard.isBlackKingAndRookNeverMove(), gameBoard.isKingSafe());
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				gameBoardCopy.setBoard(gameBoard.getBoard());
				
				coordinate.setFromY(i);
				coordinate.setFromX(j);
				coordinate.setToY(kingY);
				coordinate.setToX(kingX);
				
				if(board[i][j].getChessPieceId() == 'p' && !moveSimulator.isWhitePawnCannotProtect(board,kingY, kingX, i, j)){
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'r' && !moveSimulator.isRookCannotMoveToTarget(board,kingY, kingX,i,j)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'n' && !moveSimulator.isKnightCannotMoveToTarget(kingY,kingX,i,j)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'b' && !moveSimulator.isBishopCannotMoveToTarget(board,kingY,kingX,i,j)){
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'q' && !moveSimulator.isQueenCannotMoveToTarget(board,kingY,kingX,i,j)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'k' && !moveSimulator.isKingCannotDefend(gameBoard,kingY,kingX,i,j)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());				
				}
				if(!isSafe)return false;
			}
		}
		
		return isSafe;
	}
	
	// long parameter di king, data clump
	private boolean isBlackKingCannotBeProtect(Board gameBoard,Square[][] board,int kingY, int kingX) {
		MoveSimulator moveSimulator= new MoveSimulator();
		boolean isSafe = true;
		Coordinates coordinate = new Coordinates();
		Board gameBoardCopy = new Board(gameBoard.isWhiteMove(), gameBoard.isWhiteKingAndRookNeverMove(), gameBoard.isBlackKingAndRookNeverMove(), gameBoard.isKingSafe());

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				gameBoardCopy.setBoard(gameBoard.getBoard());
				
				coordinate.setFromY(i);
				coordinate.setFromX(j);
				coordinate.setToY(kingY);
				coordinate.setToX(kingX);
				
				if(board[i][j].getChessPieceId() == 'P' && !moveSimulator.isBlackPawnCannotProtect(board,kingY, kingX, i, j)){
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());			
				}
				else if(board[i][j].getChessPieceId() == 'R' && !moveSimulator.isRookCannotMoveToTarget(board,kingY, kingX,i,j)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'N' && !moveSimulator.isKnightCannotMoveToTarget(kingY,kingX,i,j)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());	
				}
				else if(board[i][j].getChessPieceId() == 'B' && !moveSimulator.isBishopCannotMoveToTarget(board,kingY,kingX,i,j)){
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());		
				}
				else if(board[i][j].getChessPieceId() == 'Q' && !moveSimulator.isQueenCannotMoveToTarget(board,kingY,kingX,i,j)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'K' && !moveSimulator.isKingCannotDefend(gameBoard,kingY,kingX,i,j)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());				
				}
				if(!isSafe)return false;
			}
		}		
		return isSafe;
	}
	
}
