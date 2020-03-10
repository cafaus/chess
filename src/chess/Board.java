package chess;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	char[][] board;
	boolean isWhiteMove = true;
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
		String buildBoardWhite = "rnbkqbnr";
		
		
		
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
		Scanner scan = new Scanner(System.in);
		
		String input;
		Coordinates coordinate = new Coordinates();
		
		
		String turn = isWhiteMove ? "White" : "Black";
		
		System.out.println(turn + "Turn!!");
		
		System.out.println("insert move (from-to):..(CAPITAL ALPHABET) ex= A1-B2");
		input = scan.nextLine();
		if(input.length() != 5) throw new Exception("Invalid Move: length must be 5!!");
		
		char fromIndexZero = input.charAt(0);
		char fromIndexOne = input.charAt(1);
		char toIndexZero = input.charAt(3);
		char toIndexOne = input.charAt(4);
		
		if(isOutside(fromIndexZero,'A','Z'))throw new Exception("Invalid Move: first character!!!");
		if(isOutside(fromIndexOne, '1', '8')) throw new Exception("Invalid Move: second character!!!");
		if(input.charAt(2) != '-') throw new Exception("Invalid Move: third character!!!");
		if(isOutside(toIndexZero,'A','Z')) throw new Exception("Invalid Move: fourth character!!!");
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
		
		if(!doMove(coordinate)) throw new Exception("invalid Move: doesn't fit the chess piece behavior!!!");

		isWhiteMove = isWhiteMove ? false : true;
		
	}



	private boolean doMove(Coordinates coordinate) {
		
		char chessPiece = board[coordinate.getFromY()][coordinate.getFromX()];
		chessPiece =  toLower(chessPiece);
		
		
		if(chessPiece == 'p') {
			return isPawnMove(coordinate);
		}
		if(chessPiece == 'r')return true;
		if(chessPiece == 'n')return true;
		if(chessPiece == 'b')return true;
		if(chessPiece == 'q')return true;
		if(chessPiece == 'k')return true;
		
		return false;
		
	}



	private boolean isPawnMove(Coordinates coordinate) {
		
		if(chessPieceBehavior.isPawnBehavior(coordinate, board)) {
			return validateCaptureChessPiece(coordinate, 'p');	
		}
		
		return false;
	}



	private boolean validateCaptureChessPiece(Coordinates coordinate, char whiteChessPiece) {
		char blackChessPiece = (char)(whiteChessPiece - 32);
		System.out.println(blackChessPiece);
		if(isWhiteMove) {
			if(chessPieceBehavior.isBlack(board[coordinate.getToY()][coordinate.getToX()])) {
				captureChessPiece(coordinate, 'p');
				return true;
			}
			else if(!chessPieceBehavior.isChessPiece(board, coordinate.getToY(), coordinate.getToX())){
				moveChessPiece(coordinate,'p');
				return true;
			}
		}
		else {
			if(chessPieceBehavior.isWhite(board[coordinate.getToY()][coordinate.getToX()])) {
				captureChessPiece(coordinate, 'P');
				return true;
			}
			else if(!chessPieceBehavior.isChessPiece(board, coordinate.getToY(), coordinate.getToX())){
				
				moveChessPiece(coordinate, 'P');
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
		blackPieceGraveyard.add(board[coordinate.getToY()][coordinate.getToX()]);
		moveChessPiece(coordinate, chessPieceCapturer);
		
	}



	private char toLower(char chessPiece) {
		if(isOutside(chessPiece, 'A', 'Z')) return chessPiece;
		return (char) (chessPiece + 32);
		
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
