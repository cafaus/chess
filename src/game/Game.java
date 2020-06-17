package game;


import chessPiece.ChessPiece;

public class Game {
	private static Board board;
	public Game() { 
		board =new Board(true,true,true,true);
		
	}
	public Board getGameBoard()  {
		return board;
	}
	
	public void printBoard()  {
		BoardPrinter boardPrinter = new BoardPrinter();
		boardPrinter.showBoard(board.getBoard());
	}
	
	public void showGameInfo() {
		BoardPrinter boardPrinter = new BoardPrinter();
		boardPrinter.showBoard(board.getBoard());
		
		
		String turn = board.isWhiteMove() ? "White" : "Black";
		if(!board.isKingSafe()) System.out.println("CHECK!!");
	
		System.out.println(turn + "Turn!!");
	}
	
	public void play(String input) throws Exception {
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		Coordinates coordinate = new Coordinates();
		
		
		coordinate = doCoordinateMoveNotation(input);
		coordinate = board.getPreviousMovedChessPiece(coordinate);
				
		if(!board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isChessPiece()) {
			throw new Exception("Invalid Move: choose a chess piece!!");
		}
		
		if(board.isWhiteMove() != board.getBoard()[coordinate.getFromY()][coordinate.getFromX()].isWhitePiece()) {
			String whoShouldMove = board.isWhiteMove() ? "White" : "Black"; 
			throw new Exception("Invalid Move: you should pick a " + whoShouldMove + " piece!!");
		}
		if(!doMove(coordinate)) return;
			
		board.setWhiteMove(board.isWhiteMove() ? false : true);
		
		if(!checkBehaviors.isKingSafe(board.getBoard(), board.isWhiteMove())) {
			board.setKingSafe(false);
		}else {
			board.setKingSafe(true);
		}
	}

	  
	private Coordinates doCoordinateMoveNotation(String input) throws Exception {
		String chessPiece = new String("RNBQ"); 
		
		Coordinates coordinate = new Coordinates();
		
		if(input.length() != 5 && input.length() != 6)throw new Exception("Invalid Move: length must be 5 or 6!!!");
		
		char fromIndexZero = input.charAt(0);
		char fromIndexOne = input.charAt(1);
		char toIndexZero = input.charAt(3);
		char toIndexOne = input.charAt(4);
		
		isValidInput(input, chessPiece);
		
		if(input.length() == 6) {
			coordinate.SetChessPiecePromoteCharToObject(input.charAt(5), board.isWhiteMove());
		}
		
		coordinate.setFrom(7 - (fromIndexOne - 49), fromIndexZero - 65);
		coordinate.setTo(7 - (toIndexOne - 49), toIndexZero - 65);
			
		return coordinate;
	}
	
	private void isValidInput(String input, String chessPiece) throws Exception {
		char fromIndexZero = input.charAt(0);
		char fromIndexOne = input.charAt(1);
		char toIndexZero = input.charAt(3);
		char toIndexOne = input.charAt(4);
		
		if(input.length() == 5) {
			if(isOutside(fromIndexZero,'A','H'))throw new Exception("Invalid Move: first character!!!");
			if(isOutside(fromIndexOne, '1', '8')) throw new Exception("Invalid Move: second character!!!");
			if(input.charAt(2) != '-') throw new Exception("Invalid Move: third character!!!");
			if(isOutside(toIndexZero,'A','H')) throw new Exception("Invalid Move: fourth character!!!");
			if(isOutside(toIndexOne, '1', '8')) throw new Exception("Invalid Move: fifth character!!!");
		}
		else if(input.length() == 6) {
			if(isOutside(fromIndexZero,'A','H'))throw new Exception("Invalid Move: first character!!!");
			if(isOutside(fromIndexOne, '1', '8')) throw new Exception("Invalid Move: second character!!!");
			if(input.charAt(2) != '-') throw new Exception("Invalid Move: third character!!!");
			if(isOutside(toIndexZero,'A','H')) throw new Exception("Invalid Move: fourth character!!!");
			if(isOutside(toIndexOne, '1', '8')) throw new Exception("Invalid Move: fifth character!!!");
			for (int i = 0; i < chessPiece.length(); i++) {
				if(input.charAt(5) == chessPiece.charAt(i)) break;
				if(i == 4)throw new Exception("Invalid Move: promotion character!!!");
			}
			
		}
	}

	private boolean isOutside(char fromIndexZero, char c1, char c2) {
		return fromIndexZero < c1 || fromIndexZero > c2;
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

	
	public boolean isGameOver() {
		BoardPrinter boardPrinter = new BoardPrinter();
		if(isBlackWin()) {
			boardPrinter.showBoard(board.getBoard());
			System.out.println("0-1 (BlackWin)");
			
			return true;
		}
		if(isWhiteWin()) {
			boardPrinter.showBoard(board.getBoard());
			System.out.println("1-0 (WhiteWin)");
			
			return true;
		}
		if(isEndedDraw()){
			boardPrinter.showBoard(board.getBoard());
			System.out.println("1/2-1/2 (Draw)");
			return true;
		}	
		return false;
	}

	private boolean isBlackWin() {
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		
		if(board.isWhiteMove() && checkBehaviors.isCheckMate(board)) { 
			System.out.println("CHECKMATE!!!!!!!");
			return true;
		}
		return false;
	}
	
	
	private boolean isWhiteWin() {
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		
		if(!board.isWhiteMove() && checkBehaviors.isCheckMate(board)) {	
			System.out.println("CHECKMATE!!!!!!!");
			return true;
		}
		return false;
	}
	
	private boolean isEndedDraw(){
		CheckBehaviors checkBehaviors = new CheckBehaviors();
		StalemateBehaviors stalemateBehavior= new StalemateBehaviors();
		if(checkBehaviors.isKingSafe(board.getBoard(), board.isWhiteMove()) && stalemateBehavior.isDrawOnStalemate(board)) {	
			System.out.println("game ended (stalemate)!!!!!!!");
			return true;
		}
		return false;
	}

}
