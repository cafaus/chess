package game;

import java.util.Scanner;
import chessPiece.ChessPiece;



public class Game {
	private static Board board;
	public Game() { 
		board = new Board();
		
	}
	
	public void play() throws Exception {
		
		Coordinates coordinate = new Coordinates();
		BoardPrinter boardPrinter = new BoardPrinter();
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		
		boardPrinter.showBoard(board.getBoard());
		
		String turn = board.isWhiteMove() ? "White" : "Black";
		if(!checkBehaviors.isKingSafe(board.getBoard(), board.isWhiteMove())) {
			board.setKingSafe(false);
			System.out.println("CHECK!!");
		}
		else board.setKingSafe(true);
		
		System.out.println(turn + "Turn!!");
		
		coordinate = doCoordinateMoveNotation();
		coordinate = board.getPreviousMovedChessPiece(coordinate);
		
		
		if(!board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isChessPiece()) {
			throw new Exception("Invalid Move: choose a chess piece!!");
		}
		if(board.isWhiteMove() != board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isWhitePiece()) {
			String whoShouldMove = board.isWhiteMove() ? "White" : "Black"; 
			throw new Exception("Invalid Move: you should pick a " + whoShouldMove + " piece!!");
		}
		if(!doMove(coordinate)) throw new Exception("");
		
		board.setWhiteMove(board.isWhiteMove() ? false : true);
		
	}

	  
	private Coordinates doCoordinateMoveNotation() throws Exception {
		String input = new String();
		String chessPiece = new String("PRNBQ");
		Scanner scan = new Scanner(System.in);
		Coordinates coordinate = new Coordinates();
		Tools tools = new Tools();

		System.out.println("insert move (from-to):..(CAPITAL ALPHABET) ex= A1-B2");
		input = scan.nextLine();
		
		if(input.length() != 5 && input.length() != 6)throw new Exception("Invalid Move: length must be 5 or 6!!!");
		
		char fromIndexZero = input.charAt(0);
		char fromIndexOne = input.charAt(1);
		char toIndexZero = input.charAt(3);
		char toIndexOne = input.charAt(4);
		
		if(input.length() == 5) {
			if(tools.isOutside(fromIndexZero,'A','H'))throw new Exception("Invalid Move: first character!!!");
			if(tools.isOutside(fromIndexOne, '1', '8')) throw new Exception("Invalid Move: second character!!!");
			if(input.charAt(2) != '-') throw new Exception("Invalid Move: third character!!!");
			if(tools.isOutside(toIndexZero,'A','H')) throw new Exception("Invalid Move: fourth character!!!");
			if(tools.isOutside(toIndexOne, '1', '8')) throw new Exception("Invalid Move: fifth character!!!");
		}
		else if(input.length() == 6) {
			if(tools.isOutside(fromIndexZero,'A','H'))throw new Exception("Invalid Move: first character!!!");
			if(tools.isOutside(fromIndexOne, '1', '8')) throw new Exception("Invalid Move: second character!!!");
			if(input.charAt(2) != '-') throw new Exception("Invalid Move: third character!!!");
			if(tools.isOutside(toIndexZero,'A','H')) throw new Exception("Invalid Move: fourth character!!!");
			if(tools.isOutside(toIndexOne, '1', '8')) throw new Exception("Invalid Move: fifth character!!!");
			for (int i = 0; i < chessPiece.length(); i++) {
				if(input.charAt(5) == chessPiece.charAt(i)) break;
				if(i == 5)throw new Exception("Invalid Move: promotion character!!!");
			}
			coordinate.SetChessPiecePromoteCharToObject(input.charAt(5), board.isWhiteMove());
		}
			
		
		coordinate.setFromX(fromIndexZero - 65);
		coordinate.setFromY(7 - (fromIndexOne - 49));
		
		coordinate.setToX(toIndexZero - 65);
		coordinate.setToY(7 - (toIndexOne - 49));
		
		
		return coordinate;
	}

	
	private boolean doMove(Coordinates coordinate)  {
		
		ChessPiece chessPiece = board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].getChessPiece();
		if(chessPiece.canMove(coordinate, board)) {
			board.addPrevMovePiece(coordinate);
			return true;
		}
		System.out.println("Invalid Move: for " + chessPiece.toString());
		return false;		
	}

	
	public boolean isWin() {
		BoardPrinter boardPrinter = new BoardPrinter();
		if(isBlackWin()) {
			boardPrinter.showBoard(board.getBoard());
			System.out.println("0-1");
			
			return true;
		}
		if(isWhiteWin()) {
			boardPrinter.showBoard(board.getBoard());
			System.out.println("1-0");
			
			return true;
		}
		return false;
	}


	private boolean isBlackWin() {
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		
		if(board.isBlackWin()) return true;
		if(board.isWhiteMove() && checkBehaviors.isCheckMate(board)) {
			System.out.println("CHECEKMATE!!!!!!!");
			return true;
		}
		return false;
	}
	
	
	private boolean isWhiteWin() {
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		
		if(board.isWhiteWin()) return true;
		if(!board.isWhiteMove() && checkBehaviors.isCheckMate(board)) {
			System.out.println("CHECEKMATE!!!!!!!");
			return true;
		}
		return false;
	}

}
