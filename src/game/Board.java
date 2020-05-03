package game;


import java.util.ArrayList;
import java.util.Scanner;
import chessPiece.Bishop;
import chessPiece.ChessPiece;
import chessPiece.King;
import chessPiece.Knight;
import chessPiece.Pawn;
import chessPiece.Queen;
import chessPiece.Rook;
import chessPiece.Square;



public class Board {
	private static Square[][] board;
	private static Square[][] futureBoard;
	
	private static boolean isWhiteMove = true;
	private static boolean isWhiteKingAndRookNeverMove = true;
	private static boolean isBlackKingAndRookNeverMove = true;
	private static boolean isKingSafe = true;
	private static ArrayList<ChessPiece> whitePieceGraveyard;
	private static ArrayList<ChessPiece> blackPieceGraveyard;
	private ArrayList<Coordinates> prevMovePiece;
	
	public Board() { 
		whitePieceGraveyard = new ArrayList<ChessPiece>();
		blackPieceGraveyard = new ArrayList<ChessPiece>();
		prevMovePiece = new ArrayList<Coordinates>();
		board = new Square[8][8];
		futureBoard = new Square[8][8];
		intializeBoard();
	}
	public static boolean isWhiteMove() {
		return isWhiteMove;
	}

	public static void setWhiteMove(boolean isWhiteMove) {
		Board.isWhiteMove = isWhiteMove;
	}

	public static boolean isWhiteKingAndRookNeverMove() {
		return isWhiteKingAndRookNeverMove;
	}

	public static void setWhiteKingAndRookNeverMove(boolean isWhiteKingAndRookNeverMove) {
		Board.isWhiteKingAndRookNeverMove = isWhiteKingAndRookNeverMove;
	}

	public static boolean isBlackKingAndRookNeverMove() {
		return isBlackKingAndRookNeverMove;
	}

	public static void setBlackKingAndRookNeverMove(boolean isBlackKingAndRookNeverMove) {
		Board.isBlackKingAndRookNeverMove = isBlackKingAndRookNeverMove;
	}

	public static boolean isKingSafe() {
		return isKingSafe;
	}

	public static void setKingSafe(boolean isKingSafe) {
		Board.isKingSafe = isKingSafe;
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
	
	
	public void play() throws Exception {
		Coordinates coordinate = new Coordinates();
		BoardPrinter boardPrinter = new BoardPrinter();
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		
		boardPrinter.showBoard(board);
		
		for (ChessPiece chessPiece : blackPieceGraveyard) {
			System.out.println(chessPiece.getChessPieceId());
		}
		
		String turn = isWhiteMove ? "White" : "Black";
		if(!checkBehaviors.isKingSafe(board, isWhiteMove)) {
			setKingSafe(false);
			System.out.println("CHECK!!");
		}
		else setKingSafe(true);
		
		System.out.println(turn + "Turn!!");
		
		coordinate = doCoordinateMoveNotation();
		coordinate = getPreviousMovedChessPiece(coordinate);
		
		if(!board[coordinate.getFromY()][coordinate.getFromX()].isChessPiece()) {
			throw new Exception("Invalid Move: choose a chess piece!!");
		}
		if(isWhiteMove != board[coordinate.getFromY()][coordinate.getFromX()].isWhitePiece()) {
			String whoShouldMove = isWhiteMove ? "White" : "Black"; 
			throw new Exception("Invalid Move: you should pick a " + whoShouldMove + " piece!!");
		}
		if(!doMove(coordinate)) throw new Exception("");
		isWhiteMove = isWhiteMove ? false : true;
		
	}

	  
	private Coordinates doCoordinateMoveNotation() throws Exception {
		String input;
		Scanner scan = new Scanner(System.in);
		Coordinates coordinate = new Coordinates();
		Tools tools = new Tools();
		
		System.out.println("insert move (from-to):..(CAPITAL ALPHABET) ex= A1-B2");
		input = scan.nextLine();
		if(input.length() != 5) throw new Exception("Invalid Move: length must be 5!!");
		
		
		char fromIndexZero = input.charAt(0);
		char fromIndexOne = input.charAt(1);
		char toIndexZero = input.charAt(3);
		char toIndexOne = input.charAt(4);
		
		if(tools.isOutside(fromIndexZero,'A','H'))throw new Exception("Invalid Move: first character!!!");
		if(tools.isOutside(fromIndexOne, '1', '8')) throw new Exception("Invalid Move: second character!!!");
		if(input.charAt(2) != '-') throw new Exception("Invalid Move: third character!!!");
		if(tools.isOutside(toIndexZero,'A','H')) throw new Exception("Invalid Move: fourth character!!!");
		if(tools.isOutside(toIndexOne, '1', '8')) throw new Exception("Invalid Move: five character!!!");
		
		coordinate.setFromX(fromIndexZero - 65);
		coordinate.setFromY(7 - (fromIndexOne - 49));
		
		coordinate.setToX(toIndexZero - 65);
		coordinate.setToY(7 - (toIndexOne - 49));
		
		return coordinate;
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

	private boolean doMove(Coordinates coordinate) {
		
		ChessPiece chessPiece = board[coordinate.getFromY()][coordinate.getFromX()].getChessPiece();
		if(chessPiece.canMove(coordinate, board)) {
			prevMovePiece.add(coordinate);
			return true;
		}
		return false;		
	}
	
	
	private static void initializeFutureBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				futureBoard[i][j] = new Square(board[i][j].getChessPiece(), board[i][j].isWhiteTile());
			}
		}
	}
	
	
	public static boolean moveChessPiece(Coordinates coordinate) {
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


	
	public static boolean captureChessPiece(Coordinates coordinate) {
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
	
	public boolean isWin() {
		if(isBlackWin()) return true;
		if(isWhiteWin()) return true;
		return false;
	}


	private boolean isBlackWin() {
		for (ChessPiece chessPiece : whitePieceGraveyard) {
			 if(chessPiece.getChessPieceId() == ('k')) {
				 System.out.println("0-1");
				 return true;
			 }
		}
		return false;
	}
	private boolean isWhiteWin() {
		for (ChessPiece chessPiece : blackPieceGraveyard) {
			 if(chessPiece.getChessPieceId() == ('K')) {
				 System.out.println("1-0");
				 return true;
			 }
		}
		return false;
	}

	
}
