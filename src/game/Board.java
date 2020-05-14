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
	
	private ArrayList<ChessPiece> whitePieceGraveyard;
	private ArrayList<ChessPiece> blackPieceGraveyard;
	private ArrayList<Coordinates> prevMovePiece;
	
	
	public Board() { 
		whitePieceGraveyard = new ArrayList<ChessPiece>();
		blackPieceGraveyard = new ArrayList<ChessPiece>();
		prevMovePiece = new ArrayList<Coordinates>();
		board = new Square[8][8];
		futureBoard = new Square[8][8];
		intializeBoard();
		
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
		}else{
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

	
	public ArrayList<ChessPiece> getWhitePieceGraveyard() {
		ArrayList<ChessPiece> whitePieceGraveyardCopy = new ArrayList<>();
	
	for (ChessPiece chessPiece : whitePieceGraveyard) {
		try {
			whitePieceGraveyardCopy.add((ChessPiece) chessPiece.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	return whitePieceGraveyardCopy;
		
	}
	
	public ArrayList<ChessPiece> getBlackPieceGraveyard() {
		ArrayList<ChessPiece> blackPieceGraveyardCopy = new ArrayList<>();
	
	for (ChessPiece chessPiece : blackPieceGraveyard) {
		try {
			blackPieceGraveyardCopy.add((ChessPiece) chessPiece.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	return blackPieceGraveyardCopy;
		
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


	protected boolean isWhiteTileOrBlackTile(int i, int j) {
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
	
	
	private void initializeFutureBoard() {
		futureBoard = copyBoard();
	}
	
	
	public  boolean moveChessPiece(Coordinates coordinate) {
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		initializeFutureBoard();
		futureBoard[coordinate.getToY()][coordinate.getToX()].setChessPiece(board[coordinate.getFromY()][coordinate.getFromX()].getChessPiece());
		futureBoard[coordinate.getFromY()][coordinate.getFromX()].setChessPiece(null);
		if(checkBehaviors.isKingSafe(futureBoard, board[coordinate.getFromY()][coordinate.getFromX()].isWhitePiece())) {
			board[coordinate.getToY()][coordinate.getToX()].setChessPiece(board[coordinate.getFromY()][coordinate.getFromX()].getChessPiece());
			board[coordinate.getFromY()][coordinate.getFromX()].setChessPiece(null);
			return true;
		}
		System.out.println("your king is in check");
		return false;
	}


	
	public boolean captureChessPiece(Coordinates coordinate, boolean isWhiteMove) {
		ChessPiece diedChessPiece = board[coordinate.getToY()][coordinate.getToX()].getChessPiece();
		if(moveChessPiece(coordinate)) {
			if(diedChessPiece.getChessPieceId() == 'P' || diedChessPiece.getChessPieceId() == 'p')return true;
			
			if(isWhiteMove) {blackPieceGraveyard.add(diedChessPiece);
			}
			else whitePieceGraveyard.add(diedChessPiece);
			return true;
		}
		return false;
	}
	
	

	
}
