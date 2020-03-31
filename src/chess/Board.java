package chess;

import java.util.ArrayList;
import java.util.Scanner;



public class Board {
	char[][] board;
	char[][] futureBoard;
	boolean isWhiteMove = true;
	boolean isWhiteKingAndRookNeverMove = true;
	boolean isBlackKingAndRookNeverMove = true;
	boolean isKingSafe = true;
	ArrayList<Character> whitePieceGraveyard;
	ArrayList<Character> blackPieceGraveyard;
	ChessPieceBehaviors chessPieceBehavior;
	public Board() { 
		board = new char[8][8];
		futureBoard = new char[8][8];
		whitePieceGraveyard = new ArrayList<Character>();
		blackPieceGraveyard = new ArrayList<Character>();
		intializeBoard();
	}



	private void intializeBoard() {
		String buildBoardBlack = "RNBQKBNR";
		String buildBoardWhite = "rnbqkbnr";
		
		
		
		for (int i = 0; i < 8; i++) {
			board[0][i] = buildBoardBlack.charAt(i);
			board[1][i] = 'P';
		}
		
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				 board[i][j] = isWhiteTileOrBlackTile( i, j);
			}
		}
		for (int i = 0; i < 8; i++) {
			board[6][i] = 'p';
			board[7][i] = buildBoardWhite.charAt(i);
			
		}
	}


	private char isWhiteTileOrBlackTile(int i, int j) {
		char whiteTile = '-';
		char blackTile = '+';
		if(i % 2 == 0) {
			if(j % 2 == 0) {
				return whiteTile;
			}
			else {
				return blackTile;
			}
		}
		else {
			if(j % 2 == 0) {
				return blackTile;
			}
			else {
				return whiteTile;
			}
		}
	}
	
	private void initializeFutureBoard(char[][] board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				futureBoard[i][j] = board[i][j];
			}
		}
	}
	
	
	public void doTurn(int chooseMoveNotation) throws Exception {
		initializeFutureBoard(board);
		chessPieceBehavior = new ChessPieceBehaviors(isWhiteMove);
		Coordinates coordinate = new Coordinates();
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		
		String input;
		
		String turn = isWhiteMove ? "White" : "Black";
		if(!checkBehaviors.isKingSafe(board, isWhiteMove)) {
			isKingSafe = false;
			System.out.println("CHECK!!");
		}
		else isKingSafe = true;
		
		System.out.println(turn + "Turn!!");
		if(chooseMoveNotation == 1) {
			coordinate = doAlgebraicMoveNotation();
		}
		else if(chooseMoveNotation == 2) {
			coordinate = doCoordinateMoveNotation();
		}
		
		
		if(board[coordinate.getFromY()][coordinate.getFromX()] == '+' || board[coordinate.getFromY()][coordinate.getFromX()] == '-') {
			throw new Exception("Invalid Move: choose a chess piece!!");
		}
		if(isWhiteMove != chessPieceBehavior.isWhite(board[coordinate.getFromY()][coordinate.getFromX()])) {
			String whoShouldMove = isWhiteMove ? "White" : "Black"; 
			throw new Exception("Invalid Move: you should pick a " + whoShouldMove + " piece!!");
		}
		if(!doMove(coordinate)) throw new Exception("");
		isWhiteMove = isWhiteMove ? false : true;
		
	}



	private Coordinates doAlgebraicMoveNotation() throws Exception{
		String input;
		Scanner scan = new Scanner(System.in);
		Coordinates coordinate = new Coordinates();
		System.out.println("insert move (Algebraic):");
		input = scan.nextLine();
		//cara buat:
		//liat contoh doCoordinateMoveNotation
		return coordinate;
	}



	private Coordinates doCoordinateMoveNotation() throws Exception {
		String input;
		Scanner scan = new Scanner(System.in);
		Coordinates coordinate = new Coordinates();
		
		System.out.println("insert move (from-to):..(CAPITAL ALPHABET) ex= A1-B2");
		input = scan.nextLine();
		if(input.length() != 5) throw new Exception("Invalid Move: length must be 5!!");
		
		
		char fromIndexZero = input.charAt(0);
		char fromIndexOne = input.charAt(1);
		char toIndexZero = input.charAt(3);
		char toIndexOne = input.charAt(4);
		
		if(isOutside(fromIndexZero,'A','H'))throw new Exception("Invalid Move: first character!!!");
		if(isOutside(fromIndexOne, '1', '8')) throw new Exception("Invalid Move: second character!!!");
		if(input.charAt(2) != '-') throw new Exception("Invalid Move: third character!!!");
		if(isOutside(toIndexZero,'A','H')) throw new Exception("Invalid Move: fourth character!!!");
		if(isOutside(toIndexOne, '1', '8')) throw new Exception("Invalid Move: five character!!!");

		
		coordinate.setFromX(fromIndexZero - 65);
		coordinate.setFromY(7 - (fromIndexOne - 49));
		
		coordinate.setToX(toIndexZero - 65);
		coordinate.setToY(7 - (toIndexOne - 49));
		return coordinate;
	}



	private boolean doMove(Coordinates coordinate) {
		
		char chessPiece = board[coordinate.getFromY()][coordinate.getFromX()];
		chessPiece =  toLower(chessPiece);
		
		
		if(chessPiece == 'p')return isPawnMove(coordinate);
		if(chessPiece == 'r')return isRookMove(coordinate);
		if(chessPiece == 'n')return isKnightMove(coordinate);
		if(chessPiece == 'b')return isBishopMove(coordinate);
		if(chessPiece == 'q')return isQueenMove(coordinate);
		if(chessPiece == 'k')return isKingMove(coordinate);;
		
		return false;
		
	}

	 

	private boolean isPawnMove(Coordinates coordinate) {
		if(chessPieceBehavior.isPawnBehavior(coordinate, board))return validateCaptureChessPiece(coordinate, 'p');
		else System.out.println("Invalid move for pawn");
		
		return false;
	}

	private boolean isRookMove(Coordinates coordinate) {
		if(chessPieceBehavior.isRookBehavior(coordinate, board) && validateCaptureChessPiece(coordinate, 'r')) {
			if(isWhiteMove) isWhiteKingAndRookNeverMove = false;
			else isBlackKingAndRookNeverMove = false;
				
			return true;
		}
		else System.out.println("Invalid move for rook");
		return false;
	}
	
	private boolean isKnightMove(Coordinates coordinate) {
		if(chessPieceBehavior.isKnightBehavior(coordinate, board) && validateCaptureChessPiece(coordinate, 'n')) return true;
		else System.out.println("Invalid move for knight");
		return false;
	}
	
	private boolean isBishopMove(Coordinates coordinate) {
		if(chessPieceBehavior.isBishopBehavior(coordinate, board) && validateCaptureChessPiece(coordinate, 'b')) return true;
		else System.out.println("Invalid move for bishop");
		return false;
	}
	
	private boolean isQueenMove(Coordinates coordinate) {
		if(chessPieceBehavior.isQueenBehavior(coordinate, board) && validateCaptureChessPiece(coordinate, 'q')) return true;
		else System.out.println("Invalid move for queen");
		return false;
	}
	
	private boolean isKingMove(Coordinates coordinate) {
		CheckBehaviors checkBehavior = new CheckBehaviors();
		if(chessPieceBehavior.isKingBehavior(coordinate, board)) {
			
			if(isWhiteMove) {
				if(!checkBehavior.isWhiteKingSafe(board, coordinate.getToY(), coordinate.getToX())) {
					System.out.println("your king is not safe if you move it there");
					return false;
				}
				if(!validateCaptureChessPiece(coordinate, 'k')) {
					System.out.println("Invalid move for king");
					return false;
				}
				isWhiteKingAndRookNeverMove = false;
				isKingSafe = true;
				return true;
				
			}
			else {
				if(!checkBehavior.isBlackKingSafe(board, coordinate.getToY(), coordinate.getToX())) {
					System.out.println("your king is not safe if you move it there");
					return false;
				}
				if(!validateCaptureChessPiece(coordinate, 'k')) {
					System.out.println("Invalid move for king");
					return false;
				}
				isBlackKingAndRookNeverMove = false;
				isKingSafe = true;
				return true;
				
			}
		}
		if(chessPieceBehavior.isCastling(coordinate, board, isKingSafe, isWhiteKingAndRookNeverMove, isBlackKingAndRookNeverMove)) {
			
			if(isWhiteMove) {
				if(checkBehavior.isWhiteKingSafe(board, coordinate.getToY(), coordinate.getToX()) ) {
					isWhiteKingAndRookNeverMove = false;
					isKingSafe = true;
					doWhiteKingCastling(coordinate);
					return true;
				}
				return false;
			}
			else {
				if(checkBehavior.isBlackKingSafe(board, coordinate.getToY(), coordinate.getToX())) {
					isBlackKingAndRookNeverMove = false;
					isKingSafe = true;
					doBlackKingCastling(coordinate);
					return true;
				}
				return false;
			}
		}
		else System.out.println("Invalid move for king");
		return false;
	}




	private void doWhiteKingCastling(Coordinates coordinate) {
		if(coordinate.getToY() == 7 && coordinate.getToX() == 6) {
			moveChessPiece(coordinate, 'k');
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(7);
			rookCoordinate.setFromX(7);
			rookCoordinate.setToY(7);
			rookCoordinate.setToX(5);
			
			moveChessPiece(rookCoordinate, 'r');
		}
		else {
			moveChessPiece(coordinate, 'k');
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(7);
			rookCoordinate.setFromX(0);
			rookCoordinate.setToY(7);
			rookCoordinate.setToX(3);
			
			moveChessPiece(rookCoordinate, 'r');
		}
	}
	
	private void doBlackKingCastling(Coordinates coordinate) {
		if(coordinate.getToY() == 0 && coordinate.getToX() == 6) {
			moveChessPiece(coordinate, 'K');
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(0);
			rookCoordinate.setFromX(7);
			rookCoordinate.setToY(0);
			rookCoordinate.setToX(5);
			
			moveChessPiece(rookCoordinate, 'R');
		}
		else {
			moveChessPiece(coordinate, 'K');
			
			Coordinates rookCoordinate = new Coordinates();
			rookCoordinate.setFromY(0);
			rookCoordinate.setFromX(0);
			rookCoordinate.setToY(0);
			rookCoordinate.setToX(3);
			
			moveChessPiece(rookCoordinate, 'R');
		}
	}



	private boolean validateCaptureChessPiece(Coordinates coordinate, char whiteChessPiece) {
		char blackChessPiece = toUpper(whiteChessPiece); 
		
		if(isWhiteMove) {
			if(chessPieceBehavior.isBlack(board[coordinate.getToY()][coordinate.getToX()])) {
				return captureChessPiece(coordinate, whiteChessPiece);
				
			}
			else if(!chessPieceBehavior.isChessPiece(board, coordinate.getToY(), coordinate.getToX())){
				return moveChessPiece(coordinate, whiteChessPiece);
				
			}
		}
		else {
			if(chessPieceBehavior.isWhite(board[coordinate.getToY()][coordinate.getToX()])) {
				return captureChessPiece(coordinate, blackChessPiece);
				
			}
			else if(!chessPieceBehavior.isChessPiece(board, coordinate.getToY(), coordinate.getToX())){
				return moveChessPiece(coordinate, blackChessPiece);
				
			}
		}
		return false;
	}

	


	private boolean moveChessPiece(Coordinates coordinate, char chessPiece) {
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		futureBoard[coordinate.getToY()][coordinate.getToX()] = chessPiece;
		futureBoard[coordinate.getFromY()][coordinate.getFromX()] = isWhiteTileOrBlackTile(coordinate.getFromY(), coordinate.getFromX());
		if(checkBehaviors.isKingSafe(futureBoard, isWhiteMove)) {
			board[coordinate.getToY()][coordinate.getToX()] = chessPiece;
			board[coordinate.getFromY()][coordinate.getFromX()] = isWhiteTileOrBlackTile(coordinate.getFromY(), coordinate.getFromX());
			return true;
		}
		System.out.println("your king is in check");
		return false;
	}



	private boolean captureChessPiece(Coordinates coordinate, char chessPieceCapturer) {
		
		char chessPiece = board[coordinate.getToY()][coordinate.getToX()];

		
		if(moveChessPiece(coordinate, chessPieceCapturer)) {
			if(isWhiteMove)blackPieceGraveyard.add(chessPiece);
			else whitePieceGraveyard.add(chessPiece);
			
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
		for (Character character : whitePieceGraveyard) {
			 if(character.equals('k')) {
				 System.out.println("0-1");
				 return true;
			 }
		}
		return false;
	}
	private boolean isWhiteWin() {
		for (Character character : blackPieceGraveyard) {
			 if(character.equals('K')) {
				 System.out.println("1-0");
				 return true;
			 }
		}
		return false;
	}


	private char toLower(char letter) {
		if(isOutside(letter, 'A', 'Z')) return letter;
		return (char) (letter + 32);
		
	}
	private char toUpper(char letter) {
		if(isOutside(letter, 'a', 'z')) return letter;
		return (char)(letter - 32);
	}


	private boolean isOutside(char fromIndexZero, char c1, char c2) {
		return fromIndexZero < c1 || fromIndexZero > c2;
	}
	
	
	
	
	public void showBoard() {
		
		for (int i = 0; i < 8; i++) {
			
			for (int j = 0; j < 8; j++) {
				System.out.print(board[i][j] + " " + " ");
			}
			System.out.println(" " + " " + (8 - i) );
		}

		System.out.println();
		
		showFile();

	}



	private void showFile() {
		char alphabet = 'A' - 1;
		
		for (int i = 0; i < 8; i++) {
			alphabet += 1; 
			System.out.print(  alphabet + " " + " ");
		}
		System.out.println();
	}
}
