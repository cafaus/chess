package game;

import chessPiece.Square;
import chessPiece.ChessPiece;

public class CheckBehaviors {
	
	public boolean isCheckMate(Board board) {
		int checked = 0;
		Coordinates coordinate = new Coordinates();
		char king = board.isWhiteMove() ? 'k' : 'K';
		int kingY = getKingCoordinateY(board.getBoard(), king); 
		int kingX = getKingCoordinateX(board.getBoard(), king);
		
		coordinate.setFrom(kingY, kingX);
		coordinate.setTo(kingY - 1, kingX);
		if(!isKingMoveAreaSafe(board, coordinate)) checked++;
		
		coordinate.setFrom(kingY, kingX);
		coordinate.setTo(kingY - 1, kingX + 1);
		if(!isKingMoveAreaSafe(board, coordinate))checked++;
		
		coordinate.setFrom(kingY, kingX);
		coordinate.setTo(kingY - 1, kingX - 1);
		if(!isKingMoveAreaSafe(board, coordinate))checked++;
		
		coordinate.setFrom(kingY, kingX);
		coordinate.setTo(kingY, kingX - 1);
		if(!isKingMoveAreaSafe(board, coordinate))checked++;
		
		coordinate.setFrom(kingY, kingX);
		coordinate.setTo(kingY, kingX + 1);
		if(!isKingMoveAreaSafe(board, coordinate))checked++;	
		
		coordinate.setFrom(kingY, kingX);
		coordinate.setTo(kingY + 1, kingX + 1);
		if(!isKingMoveAreaSafe(board, coordinate))checked++;
		
		coordinate.setFrom(kingY, kingX);
		coordinate.setTo(kingY + 1, kingX);
		if(!isKingMoveAreaSafe(board, coordinate))checked++;
		
		coordinate.setFrom(kingY, kingX);
		coordinate.setTo(kingY + 1, kingX -1);
		if(!isKingMoveAreaSafe(board, coordinate))checked++;
		
		if(checked < 8) return false;
		return true;
	}

	private boolean isKingMoveAreaSafe(Board board, Coordinates coordinate) {
		
		Board boardCopy = new Board(board.isWhiteMove(), board.isWhiteKingAndRookNeverMove(), board.isBlackKingAndRookNeverMove(), board.isKingSafe());
		boardCopy.setBoard(board.getBoard());
				
		int kingY = coordinate.getFromY();
		int kingX = coordinate.getFromX();
		int toY = coordinate.getToY();
		int toX = coordinate.getToX();
		
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
		
		Coordinates coordinate = new Coordinates();
		boolean isSafe = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				coordinate.setFrom(i, j);
				coordinate.setTo(kingY, kingX);
				if(board[i][j].getChessPieceId() == 'P')isSafe = moveSimulator.isBlackPawnCannotMoveToTarget(coordinate);	
				else if(board[i][j].getChessPieceId() == 'R')isSafe = moveSimulator.isRookCannotMoveToTarget(board,coordinate); 
				else if(board[i][j].getChessPieceId() == 'N') isSafe = moveSimulator.isKnightCannotMoveToTarget(kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'B')isSafe = moveSimulator.isBishopCannotMoveToTarget(board,coordinate);
				else if(board[i][j].getChessPieceId() == 'Q')isSafe = moveSimulator.isQueenCannotMoveToTarget(board,coordinate);
				else if(board[i][j].getChessPieceId() == 'K')isSafe = moveSimulator.isKingCannotMoveToTarget(kingY,kingX,i,j);
				if(!isSafe) return false;
			}
		}
		return isSafe;
	}
	public boolean isBlackKingSafe(Square[][] board,int kingY, int kingX) {
		MoveSimulator moveSimulator= new MoveSimulator();
		Coordinates coordinate = new Coordinates();
		boolean isSafe = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				coordinate.setFrom(i, j);
				coordinate.setTo(kingY, kingX);
				if(board[i][j].getChessPieceId() == 'p')isSafe = moveSimulator.isWhitePawnCannotCapture(coordinate);
				else if(board[i][j].getChessPieceId() == 'r')isSafe = moveSimulator.isRookCannotMoveToTarget(board,coordinate);
				else if(board[i][j].getChessPieceId() == 'n')isSafe = moveSimulator.isKnightCannotMoveToTarget(kingY,kingX,i,j);
				else if(board[i][j].getChessPieceId() == 'b')isSafe = moveSimulator.isBishopCannotMoveToTarget(board,coordinate);
				else if(board[i][j].getChessPieceId() == 'q')isSafe = moveSimulator.isQueenCannotMoveToTarget(board,coordinate);
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
	
	private boolean isWhiteKingCannotBeProtect(Board gameBoard,Square[][] board,int kingY, int kingX) {
		MoveSimulator moveSimulator= new MoveSimulator();
		boolean isSafe = true;
		Coordinates coordinate = new Coordinates();
		Board gameBoardCopy = new Board(gameBoard.isWhiteMove(), gameBoard.isWhiteKingAndRookNeverMove(), gameBoard.isBlackKingAndRookNeverMove(), gameBoard.isKingSafe());
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				gameBoardCopy.setBoard(gameBoard.getBoard());
				
				
				coordinate.setFrom(i, j);
				coordinate.setTo(kingY, kingX);
				
				if(board[i][j].getChessPieceId() == 'p' && !moveSimulator.isWhitePawnCannotProtect(board,coordinate)){
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'r' && !moveSimulator.isRookCannotMoveToTarget(board,coordinate)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'n' && !moveSimulator.isKnightCannotMoveToTarget(kingY,kingX,i,j)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'b' && !moveSimulator.isBishopCannotMoveToTarget(board,coordinate)){
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'q' && !moveSimulator.isQueenCannotMoveToTarget(board,coordinate)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'k' && !moveSimulator.isKingCannotDefend(gameBoard, coordinate)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());				
				}
				if(!isSafe)return false;
			}
		}
		
		return isSafe;
	}
	
	private boolean isBlackKingCannotBeProtect(Board gameBoard,Square[][] board,int kingY, int kingX) {
		MoveSimulator moveSimulator= new MoveSimulator();
		boolean isSafe = true;
		Coordinates coordinate = new Coordinates();
		Board gameBoardCopy = new Board(gameBoard.isWhiteMove(), gameBoard.isWhiteKingAndRookNeverMove(), gameBoard.isBlackKingAndRookNeverMove(), gameBoard.isKingSafe());

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				gameBoardCopy.setBoard(gameBoard.getBoard());
				
				coordinate.setFrom(i, j);
				coordinate.setTo(kingY, kingX);
				
				if(board[i][j].getChessPieceId() == 'P' && !moveSimulator.isBlackPawnCannotProtect(board, coordinate)){
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());			
				}
				else if(board[i][j].getChessPieceId() == 'R' && !moveSimulator.isRookCannotMoveToTarget(board,coordinate)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'N' && !moveSimulator.isKnightCannotMoveToTarget(kingY,kingX,i,j)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());	
				}
				else if(board[i][j].getChessPieceId() == 'B' && !moveSimulator.isBishopCannotMoveToTarget(board,coordinate)){
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());		
				}
				else if(board[i][j].getChessPieceId() == 'Q' && !moveSimulator.isQueenCannotMoveToTarget(board,coordinate)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());
				}
				else if(board[i][j].getChessPieceId() == 'K' && !moveSimulator.isKingCannotDefend(gameBoard,coordinate)) {
					gameBoardCopy.moveChessPiece(coordinate);
					isSafe = !isKingSafe(gameBoardCopy.getBoard(), gameBoardCopy.isWhiteMove());				
				}
				if(!isSafe)return false;
			}
		}		
		return isSafe;
	}
	
}
