package game;


import java.util.ArrayList;
import chessPiece.Bishop;
import chessPiece.ChessPiece;
import chessPiece.King;
import chessPiece.Knight;
import chessPiece.Pawn;
import chessPiece.Queen;
import chessPiece.Rook;
import chessPiece.Square;



public class Board {
	private Square[][] board;
	private Square[][] futureBoard;
	
	private ArrayList<Coordinates> prevMovePiece;
	
	private boolean isWhiteMove;
	private boolean isWhiteKingAndRookNeverMove;
	private boolean isBlackKingAndRookNeverMove;
	private boolean isKingSafe;
	
	public Board(boolean isWhiteMove, boolean isWhiteKingAndRookNeverMove, boolean isBlackKingAndRookNeverMove, boolean isKingSafe) { 
		this.isWhiteMove = isWhiteMove;
		this.isWhiteKingAndRookNeverMove = isWhiteKingAndRookNeverMove;
		this.isBlackKingAndRookNeverMove = isBlackKingAndRookNeverMove;
		this.isKingSafe = isKingSafe;
		prevMovePiece = new ArrayList<Coordinates>();
		board = new Square[8][8];
		futureBoard = new Square[8][8];
		intializeBoard();
		
	}
	
	
	private void intializeBoard() {	
		boolean isWhitePiece = true;
		
		intializePieceToBoard(!isWhitePiece);
		initializeSquareToBoard();
		intializePieceToBoard(isWhitePiece);
	}


	public void intializePieceToBoard(boolean isWhitePiece) {
		int rank = isWhitePiece? 7 : 0;
		board[rank][0] = new Square(new Rook(isWhitePiece), isWhiteTileOrBlackTile(rank, 0));
		board[rank][1] = new Square(new Knight(isWhitePiece), isWhiteTileOrBlackTile(rank, 1));
		board[rank][2] = new Square(new Bishop(isWhitePiece), isWhiteTileOrBlackTile(rank, 2));
		board[rank][3] = new Square(new Queen(isWhitePiece), isWhiteTileOrBlackTile(rank, 3));
		board[rank][4] = new Square(new King(isWhitePiece), isWhiteTileOrBlackTile(rank, 4));
		board[rank][5] = new Square(new Bishop(isWhitePiece), isWhiteTileOrBlackTile(rank, 5));
		board[rank][6] = new Square(new Knight(isWhitePiece), isWhiteTileOrBlackTile(rank, 6));
		board[rank][7] = new Square(new Rook(isWhitePiece), isWhiteTileOrBlackTile(rank, 7));
		
		initializePawnToBoard(isWhitePiece);
	}
	
	
	private void initializeSquareToBoard() {
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new Square(null, isWhiteTileOrBlackTile(i, j));
			}
		}
	}
	
	
	private void initializePawnToBoard(boolean isWhitePiece) {
		int rank = isWhitePiece ? 6 : 1;
		for (int i = 0; i < 8; i++) {		
			board[rank][i] = new Square(new Pawn(isWhitePiece), isWhiteTileOrBlackTile(rank, i));
		}
	}


	private boolean isWhiteTileOrBlackTile(int i, int j) {
		if(i % 2 == 0) {
			if(j % 2 == 0) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(j % 2 == 0) {
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	public boolean isWhiteMove() {
		return isWhiteMove;
	}

	
	public void setWhiteMove(boolean isWhiteMove) {
		this.isWhiteMove = isWhiteMove;
	}
	
	
	public  boolean isWhiteKingAndRookNeverMove() {
		return isWhiteKingAndRookNeverMove;
	}

	
	public void setWhiteKingAndRookNeverMove(boolean isWhiteKingAndRookNeverMove) {
		this.isWhiteKingAndRookNeverMove = isWhiteKingAndRookNeverMove;
	}

	
	public boolean isBlackKingAndRookNeverMove() {
		return isBlackKingAndRookNeverMove;
	}

	
	public void setBlackKingAndRookNeverMove(boolean isBlackKingAndRookNeverMove) {
		this.isBlackKingAndRookNeverMove = isBlackKingAndRookNeverMove;
	}

	
	public boolean isKingSafe() {
		return isKingSafe;
	}

	
	public  void setKingSafe(boolean isKingSafe) {
		this.isKingSafe = isKingSafe;
	}
	
	public Square[][] getBoard()  {
		return copyBoard();
	}
	
	
	private Square[][] copyBoard() {
		Square[][] boardCopy = new Square[8][8]; 
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boardCopy[i][j] = new Square(board[i][j].getChessPiece(), board[i][j].isWhiteTile());
			}
		}
		return boardCopy;
	}
	
	
	public void setBoard(Square[][] board) {
		this.board = board;
	}
	
	
	public void addPrevMovePiece(Coordinates coordinate) {
		this.prevMovePiece.add(coordinate);
	}
	
	
	protected Coordinates getPreviousMovedChessPiece(Coordinates coordinate){
		int lastMove = prevMovePiece.size();
		
		if(lastMove == 0){
			return coordinate;
		}
		else{
			lastMove = prevMovePiece.size()-1;
		}
		
		int prevPieceOriginX = prevMovePiece.get(lastMove).getFromX();
		int prevPieceOriginY = prevMovePiece.get(lastMove).getFromY();
		int prevPieceCurrPosX = prevMovePiece.get(lastMove).getToX();
		int prevPieceCurrPosY = prevMovePiece.get(lastMove).getToY();
		
		coordinate.setPrevOriginY(prevPieceOriginY);
		coordinate.setPrevOriginX(prevPieceOriginX);
		coordinate.setPrevCurrPosY(prevPieceCurrPosY);
		coordinate.setPrevCurrPosX(prevPieceCurrPosX);
			
		return coordinate;
	}
	
	public boolean isChessPieceCanMove(Coordinates coordinate) {
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		initializeFutureBoard();
		futureBoard[coordinate.getToY()][coordinate.getToX()].setChessPiece(board[coordinate.getFromY()][coordinate.getFromX()].getChessPiece());
		futureBoard[coordinate.getFromY()][coordinate.getFromX()].setChessPiece(null);
		if(!checkBehaviors.isKingSafe(futureBoard, board[coordinate.getFromY()][coordinate.getFromX()].isWhitePiece())) {
			System.out.println("your king is in check");
			return false;
		}
		return true;
	}
	
	public void moveChessPiece(Coordinates coordinate) {
		board[coordinate.getToY()][coordinate.getToX()].setChessPiece(board[coordinate.getFromY()][coordinate.getFromX()].getChessPiece());
		board[coordinate.getFromY()][coordinate.getFromX()].setChessPiece(null);
	}

	private void initializeFutureBoard() {
		futureBoard = copyBoard();
	}
	
	
	public boolean captureChessPieceByEnpassant(Board board, Coordinates coordinate) { 
		boolean isEnPassantSafe;
		Square[][] boardCopy = new Square[8][8];
		if(board.getBoard()[coordinate.getToY()][coordinate.getToX()].isChessPiece()) return false;
		isEnPassantSafe=board.isChessPieceCanMove(coordinate);
		if(!isEnPassantSafe)return false;
		
		board.moveChessPiece(coordinate);
		boardCopy = board.getBoard();
		boardCopy[coordinate.getPrevCurrPosY()][coordinate.getPrevCurrPosX()].setChessPiece(null);
		board.setBoard(boardCopy);
		return true;	
	}
	
}
