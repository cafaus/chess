package chess;

import java.util.ArrayList;
import java.util.Scanner;



public class Board {
	char[][] board;
	boolean isWhiteMove = true;
	boolean isWhiteKingAndRookNeverMove = true;
	boolean isBlackKingAndRookNeverMove = true;
	boolean isKingSafe = true;
	ArrayList<Character> whitePieceGraveyard;
	ArrayList<Character> blackPieceGraveyard;
	ChessPieceBehaviors chessPieceBehavior;
	public Board() {
		board = new char[8][8];
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
	
	
	
	
	public void doTurn() throws Exception {
		
		chessPieceBehavior = new ChessPieceBehaviors(isWhiteMove);
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		Scanner scan = new Scanner(System.in);
		
		Coordinates coordinate = new Coordinates();
		String input;
		
		String turn = isWhiteMove ? "White" : "Black";
		if(!checkBehaviors.isKingSafe(board, isWhiteMove)) {
			isKingSafe = false;
			System.out.println("CHECK!!. please move your king!!");
		}
		
		System.out.println(turn + "Turn!!");
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
		
		if(board[coordinate.getFromY()][coordinate.getFromX()] == '+' || board[coordinate.getFromY()][coordinate.getFromX()] == '-') {
			throw new Exception("Invalid Move: choose a chess piece!!");
		}
		if(isWhiteMove != chessPieceBehavior.isWhite(board[coordinate.getFromY()][coordinate.getFromX()])) {
			String whoShouldMove = isWhiteMove ? "White" : "Black"; 
			throw new Exception("Invalid Move: you should pick a " + whoShouldMove + " piece!!");
		}
		
		if(!doMove(coordinate)) throw new Exception("invalid Move: ");
		isWhiteMove = isWhiteMove ? false : true;
		
	}



	private boolean doMove(Coordinates coordinate) {
		
		char chessPiece = board[coordinate.getFromY()][coordinate.getFromX()];
		chessPiece =  toLower(chessPiece);
		
		if(!isKingSafe && chessPiece != 'k') {
			System.out.println("Your King is in danger. please move your king ");
			return false;
		}
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
		return false;
	}

	private boolean isRookMove(Coordinates coordinate) {
		if(chessPieceBehavior.isRookBehavior(coordinate, board)) {
			if(validateCaptureChessPiece(coordinate, 'r')) {
				if(isWhiteMove) isWhiteKingAndRookNeverMove = false;
				else isBlackKingAndRookNeverMove = false;
				
				return true;
			}
		}
		return false;
	}
	
	private boolean isKnightMove(Coordinates coordinate) {
		if(chessPieceBehavior.isKnightBehavior(coordinate, board)) return validateCaptureChessPiece(coordinate, 'n');
		return false;
	}
	
	private boolean isBishopMove(Coordinates coordinate) {
		if(chessPieceBehavior.isBishopBehavior(coordinate, board)) return validateCaptureChessPiece(coordinate, 'b');
		return false;
	}
	
	private boolean isQueenMove(Coordinates coordinate) {
		if(chessPieceBehavior.isQueenBehavior(coordinate, board)) return validateCaptureChessPiece(coordinate, 'q');
		return false;
	}
	
	private boolean isKingMove(Coordinates coordinate) {
		CheckBehaviors checkBehavior = new CheckBehaviors();
		if(chessPieceBehavior.isKingBehavior(coordinate, board)) {
			
			if(isWhiteMove) {
				if(checkBehavior.isWhiteKingSafe(board, coordinate.getToY(), coordinate.getToX()) && validateCaptureChessPiece(coordinate, 'k')) {
					isWhiteKingAndRookNeverMove = false;
					isKingSafe = true;
					return true;
				}
			}
			else {
				if(checkBehavior.isBlackKingSafe(board, coordinate.getToY(), coordinate.getToX()) && validateCaptureChessPiece(coordinate, 'k')) {
					isBlackKingAndRookNeverMove = false;
					isKingSafe = true;
					return true;
				}
			}
		}
		if(chessPieceBehavior.isCastling(coordinate, board, isKingSafe, isWhiteKingAndRookNeverMove, isBlackKingAndRookNeverMove)) {
			
			if(isWhiteMove) {
				if(checkBehavior.isWhiteKingSafe(board, coordinate.getToY(), coordinate.getToX())) {
					isWhiteKingAndRookNeverMove = false;
					isKingSafe = true;
					doWhiteKingCastling(coordinate);
					return true;
				}
			}
			else {
				if(checkBehavior.isBlackKingSafe(board, coordinate.getToY(), coordinate.getToX()) && validateCaptureChessPiece(coordinate, 'k')) {
					isBlackKingAndRookNeverMove = false;
					isKingSafe = true;
					doBlackKingCastling(coordinate);
					return true;
				}
			}
		}
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
				captureChessPiece(coordinate, whiteChessPiece);
				return true;
			}
			else if(!chessPieceBehavior.isChessPiece(board, coordinate.getToY(), coordinate.getToX())){
				moveChessPiece(coordinate, whiteChessPiece);
				return true;
			}
		}
		else {
			if(chessPieceBehavior.isWhite(board[coordinate.getToY()][coordinate.getToX()])) {
				captureChessPiece(coordinate, blackChessPiece);
				return true;
			}
			else if(!chessPieceBehavior.isChessPiece(board, coordinate.getToY(), coordinate.getToX())){
				
				moveChessPiece(coordinate, blackChessPiece);
				return true;
			}
		}
		return false;
	}




	private void moveChessPiece(Coordinates coordinate, char chessPiece) {
		
		board[coordinate.getToY()][coordinate.getToX()] = chessPiece;
		board[coordinate.getFromY()][coordinate.getFromX()] = isWhiteTileOrBlackTile(coordinate.getFromY(), coordinate.getFromX());
	}



	private void captureChessPiece(Coordinates coordinate, char chessPieceCapturer) {
		if(isWhiteMove)blackPieceGraveyard.add(board[coordinate.getToY()][coordinate.getToX()]);
		else whitePieceGraveyard.add(board[coordinate.getToY()][coordinate.getToX()]);
		moveChessPiece(coordinate, chessPieceCapturer);
		
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
				System.out.print(board[i][j] + " ");
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
			System.out.print(  alphabet + " ");
		}
		System.out.println();
	}
}
