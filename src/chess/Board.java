package chess;

import java.util.Scanner;

public class Board {
	char[][] board;
	boolean isWhiteMove = true;
	public Board() {
		board = new char[8][8];
		intializeBoard();
	}



	private void intializeBoard() {
		String buildBoardBlack = "RKBQKBKR";
		String buildBoardWhite = "rkbkqbkr";
		
		char whiteSquare = '+';
		char blackSquare = '-';
		
		for (int i = 0; i < 8; i++) {
			board[0][i] = buildBoardBlack.charAt(i);
			board[1][i] = 'P';
		}
		
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				if(i % 2 == 0) {
					if(j % 2 == 0) {
						board[i][j] = whiteSquare;
					}
					else {
						board[i][j] = blackSquare;
					}
				}
				else {
					if(j % 2 == 0) {
						board[i][j] = blackSquare;
					}
					else {
						board[i][j] = whiteSquare;
					}
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			board[6][i] = 'p';
			board[7][i] = buildBoardWhite.charAt(i);
			
		}
	}
	
	
	
	
	public void doTurn() throws Exception {
		Scanner scan = new Scanner(System.in);
		
		String input;
		int fromX,fromY, toX, toY;
		
		
		String turn = isWhiteMove ? "White" : "Black";
		
		System.out.println(turn + "Turn!!");
		
		System.out.println("insert move (from-to):..(CAPITAL ALPHABET) ex= A1-B2");
		input = scan.nextLine();
		if(input.length() != 5) throw new Exception("Invalid Move: length must be 5!!");
		
		char fromIndexZero = input.charAt(0);
		char fromIndexOne = input.charAt(1);
		char toIndexZero = input.charAt(3);
		char toIndexOne = input.charAt(4);
		
		if(isOutside(fromIndexZero,'A','Z')) {
			throw new Exception("Invalid Move: first character!!!");
		}
		
		if(isOutside(fromIndexOne, '1', '8')) {
			throw new Exception("Invalid Move: second character!!!");
		}
		if(input.charAt(2) != '-') {
			throw new Exception("Invalid Move: third character!!!");
		}
		if(isOutside(toIndexZero,'A','Z')) {
			throw new Exception("Invalid Move: fourth character!!!");
		}
		if(isOutside(toIndexOne, '1', '8')) {
			throw new Exception("Invalid Move: five character!!!");
		}
		
		
		
		fromX = fromIndexZero - 65;
		fromY = 7 - (fromIndexOne - 49);
		
		toX = toIndexZero - 65;
		toY = 7 - (toIndexOne - 49);
		
		if(board[fromY][fromX] == '+' || board[fromY][fromX] == '-') {
			throw new Exception("Invalid Move: choose a chess piece!!");
		}
		
		
		if(isWhiteMove != isWhite(board[fromY][fromX])) {
			String whoShouldMove = isWhiteMove ? "White" : "Black"; 
			throw new Exception("Invalid Move: you should pick a " + whoShouldMove + " piece!!");
		}
		
		isWhiteMove = isWhiteMove ? false : true;
		
	}



	private boolean isOutside(char fromIndexZero, char c1, char c2) {
		return fromIndexZero < c1 || fromIndexZero > c2;
	}
	
	
	public boolean isWhite(char chessPiece) {
		if(chessPiece < 'a' || chessPiece > 'z') return false;
		return true;
	}
	
	public void showBoard() {
		
		showFile();
		
		System.out.println();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(board[i][j]+ " ");
			}
			System.out.println();
		}

		System.out.println();
		
		showFile();

	}



	private void showFile() {
		char alphabet = 'A' - 1;
		for (int i = 0; i < 8; i++) {
			alphabet += 1; 
			System.out.print(alphabet + " ");
		}
		System.out.println();
	}
}
