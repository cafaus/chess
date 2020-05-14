package game;

import java.util.ArrayList;
import java.util.Scanner;
import chessPiece.ChessPiece;


public class Game {
	private static Board board;
	
	
	private static boolean isWhiteMove = true;
	private static boolean isWhiteKingAndRookNeverMove = true;
	private static boolean isBlackKingAndRookNeverMove = true;
	private static boolean isKingSafe = true;

	
	public Game() { 
		board = new Board();
		
	}
	public static boolean isWhiteMove() {
		return isWhiteMove;
	}

	public static void setWhiteMove(boolean isWhiteMove) {
		Game.isWhiteMove = isWhiteMove;
	}

	public static boolean isWhiteKingAndRookNeverMove() {
		return isWhiteKingAndRookNeverMove;
	}

	public static void setWhiteKingAndRookNeverMove(boolean isWhiteKingAndRookNeverMove) {
		Game.isWhiteKingAndRookNeverMove = isWhiteKingAndRookNeverMove;
	}

	public static boolean isBlackKingAndRookNeverMove() {
		return isBlackKingAndRookNeverMove;
	}

	public static void setBlackKingAndRookNeverMove(boolean isBlackKingAndRookNeverMove) {
		Game.isBlackKingAndRookNeverMove = isBlackKingAndRookNeverMove;
	}

	public static boolean isKingSafe() {
		return isKingSafe;
	}

	public static void setKingSafe(boolean isKingSafe) {
		Game.isKingSafe = isKingSafe;
	}
	
	
	
	public void play() throws Exception {
		
		Coordinates coordinate = new Coordinates();
		BoardPrinter boardPrinter = new BoardPrinter();
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		
		boardPrinter.showBoard(board.getBoard());
		
		String turn = isWhiteMove ? "White" : "Black";
		if(!checkBehaviors.isKingSafe(board.getBoard(), isWhiteMove)) {
			setKingSafe(false);
			System.out.println("CHECK!!");
		}
		else setKingSafe(true);
		
		System.out.println(turn + "Turn!!");
		
		coordinate = doCoordinateMoveNotation();
		coordinate = board.getPreviousMovedChessPiece(coordinate);
		
		if(!board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isChessPiece()) {
			throw new Exception("Invalid Move: choose a chess piece!!");
		}
		if(isWhiteMove != board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isWhitePiece()) {
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

	

	private boolean doMove(Coordinates coordinate) throws CloneNotSupportedException {
		
		ChessPiece chessPiece = board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].getChessPiece();
		if(chessPiece.canMove(coordinate, board)) {
			board.addPrevMovePiece(coordinate);
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
		ArrayList<ChessPiece> whitePieceGraveyard = board.getWhitePieceGraveyard();
		for (ChessPiece chessPiece : whitePieceGraveyard) {
			 if(chessPiece.getChessPieceId() == ('k')) {
				 System.out.println("0-1");
				 return true;
			 }
		}
		return false;
	}
	private boolean isWhiteWin() {
		ArrayList<ChessPiece> blackPieceGraveyard = board.getBlackPieceGraveyard();
		for (ChessPiece chessPiece : blackPieceGraveyard) {
			 if(chessPiece.getChessPieceId() == ('K')) {
				 System.out.println("1-0");
				 return true;
			 }
		}
		return false;
	}

}
