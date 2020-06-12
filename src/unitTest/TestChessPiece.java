package unitTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

import chessPiece.Square;
import game.BoardPrinter;
import game.Game;

public class TestChessPiece {

	@Test
	public void checkAllValidPawnMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'Q' ,'K' ,'n' ,'N' ,'R' },
			{'P', '0', '0', '0', '0', '0', '0', '0' },
			{'0' ,'0' ,'0' ,'p' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'P' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'p' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'p' },
			{'r' ,'n' ,'b' ,'q' ,'k' ,'B' ,'n' ,'r' },
		};
		
		
		

		game.play("D2-D4");
		game.play("C7-C5");
		game.play("E2-E3");
		game.play("B7-B6");
		game.play("D4-C5");
		game.play("B6-C5");
		game.play("F2-F4");
		game.play("C5-C4");
		game.play("B2-B4");
		game.play("C4-B3");
		game.play("A2-B3");
		game.play("G7-G5");
		game.play("C2-C4");
		game.play("H7-H6");
		game.play("C4-C5");
		game.play("G5-F4");
		game.play("G2-G4");
		game.play("D7-D5");
		game.play("C5-D6");
		game.play("H6-H5");
		game.play("G4-G5");
		game.play("F7-F5");
		game.play("G5-F6");
		game.play("F4-E3");
		game.play("F6-E7");
		game.play("E3-E2");
		game.play("E7-F8N");
		game.play("E2-F1B");
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		
		assertArrayEquals(expectedBoard, board);
	}

	
	@Test
	public void checkAllInvalidPawnMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'0' ,'r' ,'Q' ,'B' ,'N' ,'R' },
			{'P', '0', 'P', '0', 'K', 'P', 'P', '0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'P' ,'P' ,'0' ,'0' ,'P' },
			{'p' ,'0' ,'0' ,'0' ,'p' ,'p' ,'0' ,'p' },
			{'0' ,'0' ,'0' ,'p' ,'0' ,'0' ,'p' ,'0' },
			{'0' ,'p' ,'P' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'r' ,'n' ,'b' ,'0' ,'k' ,'b' ,'n' ,'r' },
		};
		
		game.play("D2-E1");
		game.play("D2-D3");
		game.play("H7-H6");
		game.play("D3-C4");
		game.play("D3-E4");
		game.play("D3-D2");
		game.play("H2-H3");
		game.play("D7-E6");
		game.play("D7-C6");
		game.play("D7-D8");
		game.play("D7-D6");
		game.play("E2-E4");
		game.play("E7-E5");
		game.play("D3-E4");
		game.play("H3-H4");
		game.play("D6-E5");
		game.play("H6-H5");
		game.play("D3-D2");
		game.play("C2-C4");
		game.play("B7-B5");
		game.play("C4-C5");
		game.play("B5-B4");
		game.play("C5-C6");
		game.play("B4-B3");
		game.play("B2-B4");
		game.play("A2-A4");
		game.play("C7-C5");
		game.play("D6-D5");
		game.play("D1-C2");
		game.play("C8-D7");
		game.play("C6-D7");
		game.play("E8-E7");
		game.play("D7-D8");
		game.play("F2-F4");
		game.play("D8-E8");
		game.play("D7-D8");
		game.play("D7-C8B");
		game.play("D7-D8R");
		game.play("B3-C2");
		game.play("G2-G3");
		game.play("C2-C1");
		game.play("C2-D1");
		game.play("C2-D1B");

		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	@Test
	public void checkAllValidRookMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'Q' ,'K' ,'B' ,'N' ,'0' },
			{'P', 'P', 'r', '0', 'P', 'P', 'P', '0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'P' },
			{'p' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'p' ,'p' ,'p' ,'p' ,'0' ,'R' ,'p' },
			{'0' ,'n' ,'b' ,'q' ,'k' ,'b' ,'n' ,'r' },
		};
		
		game.play("A2-A4");
		game.play("H7-H5");
		game.play("A1-A3");
		game.play("H8-H6");
		game.play("A3-D3");
		game.play("H6-F6");
		game.play("D3-D7");
		game.play("F6-F2");
		game.play("D7-C7");
		game.play("F2-G2");
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	
	@Test
	public void checkAllInvalidRookMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'Q' ,'K' ,'B' ,'N' ,'0' },
			{'P', 'P', 'P', 'P', 'P', 'P', '0', 'R' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'P' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'P' },
			{'p' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'p' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'r' ,'0' ,'p' ,'p' ,'p' ,'p' ,'p' ,'p' },
			{'0' ,'n' ,'b' ,'q' ,'k' ,'b' ,'n' ,'r' }
		};

		
		game.play("A1-A2");
		game.play("A1-A3");
		game.play("A2-A4");
		game.play("H8-H7");
		game.play("H8-H6");
		game.play("H7-H5");
		game.play("A1-A3");
		game.play("H8-H6");
		game.play("B2-B3");
		game.play("G7-G6");
		game.play("A3-C3");
		game.play("A3-A2");
		game.play("H6-F6");
		game.play("H6-H7");
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	
	@Test
	public void checkAllValidKnightMove() throws Exception{
		Game game = new Game();
		BoardPrinter print = new BoardPrinter();
		
		char[][] board = new char[8][8]; 
		
		char[][] expectedBoard= {
			{'R' ,'N' ,'0' ,'Q' ,'K' ,'B' ,'0' ,'R' },
			{'n', 'P', 'P', 'P', '0', 'P', 'P', 'P' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'p' ,'p' ,'p' ,'0' ,'p' ,'p' ,'p' ,'N' },
			{'r' ,'0' ,'b' ,'q' ,'k' ,'0' ,'n' ,'r' },
		};

		game.play("B1-A3");
		game.play("G8-H6");
		game.play("A3-B5");
		game.play("H6-G4");
		game.play("B5-D4");
		game.play("G4-E5");
		game.play("D4-F5");
		game.play("E5-C4");
		game.play("F5-E7");
		game.play("C4-D2");
		game.play("E7-C8");
		game.play("D2-F1");
		game.play("C8-A7");
		game.play("F1-H2");
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	
	@Test
	public void checkAllInvalidKnightMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'Q' ,'K' ,'B' ,'0' ,'R' },
			{'n', 'P', 'P', 'P', 'P', 'P', 'P', 'P' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'N' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'p' ,'p' ,'p' ,'p' ,'p' ,'p' ,'p' ,'p' },
			{'r' ,'0' ,'b' ,'q' ,'k' ,'b' ,'n' ,'r' }
		};

		game.play("B1-D2");
		game.play("B1-B3");
		game.play("B1-C3");
		game.play("G8-E7");
		game.play("G8-G6");
		game.play("G8-F6");
		game.play("C3-E3");
		game.play("C3-A3");
		game.play("C3-C1");
		game.play("C3-C6");
		game.play("C3-B5");
		game.play("F6-D6");
		game.play("F6-H6");
		game.play("F6-F8");
		game.play("F6-F3");
		game.play("F6-G4");
		game.play("B5-B7");
		game.play("B5-B8");
		game.play("B5-A7");
		game.play("G4-G2");
		game.play("G4-G1");
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	
	@Test
	public void checkAllValidBishopMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'0' ,'0' ,'K' ,'B' ,'N' ,'R' },
			{'P', '0', 'b', 'P', '0', 'P', 'P', 'P' },
			{'0' ,'P' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'p' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'p' ,'0' ,'B' ,'p' ,'0' ,'p' ,'p' ,'p' },
			{'r' ,'n' ,'0' ,'0' ,'k' ,'b' ,'n' ,'r' }
		};

		
		game.play("B2-B3");
		game.play("B7-B6");
		game.play("C1-A3");
		game.play("C8-A6");
		game.play("A3-E7");
		game.play("A6-E2");
		game.play("E7-D8");
		game.play("E2-D1");
		game.play("D8-C7");
		game.play("D1-C2");
		
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		
		assertArrayEquals(expectedBoard, board);
	}
	
	
	@Test
	public void checkAllInvalidBishopMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'Q' ,'K' ,'0' ,'N' ,'R' },
			{'P', 'P', 'P', 'P', 'P', 'P', '0', 'P' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'P' ,'0' },
			{'0' ,'0' ,'b' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'p' ,'0' ,'0' ,'B' ,'0' ,'0' ,'0' },
			{'p' ,'0' ,'p' ,'p' ,'p' ,'p' ,'p' ,'p' },
			{'r' ,'n' ,'0' ,'q' ,'k' ,'b' ,'n' ,'r' }
		};

		game.play("C1-B2");
		game.play("C1-C2");
		game.play("C1-D2");
		game.play("C1-A3");
		game.play("C1-C3");
		game.play("C1-E3");
		game.play("B2-B3");
		game.play("F8-G7");
		game.play("F8-F7");
		game.play("F8-E7");
		game.play("F8-G6");
		game.play("F8-F6");
		game.play("F8-D6");
		game.play("G7-G6");
		game.play("C1-A3");
		game.play("F8-H6");
		game.play("A3-D6");
		game.play("H6-E3");
		game.play("D6-B8");
		game.play("D6-C5");
		game.play("E3-G1");
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	
	@Test
	public void checkAllValidQueenMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'K' ,'0' ,'B' ,'q' ,'0' },
			{'P', 'P', '0', 'P', 'P', '0', '0', '0' },
			{'0' ,'0' ,'P' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'p' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'p' ,'p' ,'0' ,'p' ,'p' ,'p' ,'0' ,'0' },
			{'r' ,'n' ,'b' ,'0' ,'k' ,'b' ,'Q' ,'0' }
		};

		
		
		game.play("C2-C3");
		game.play("C7-C6");
		game.play("D1-A4");
		game.play("D8-A5");
		game.play("A4-F4");
		game.play("A5-G5");
		game.play("F4-F7");
		game.play("G5-G2");
		game.play("E8-D8");
		game.play("F7-G7");
		game.play("G5-G2");
		game.play("G7-H8");
		game.play("G2-H1");
		game.play("H8-H7");
		game.play("H1-H2");
		game.play("H7-G8");
		game.play("H2-G1");
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	
	@Test
	public void checkAllInvalidQueenMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'0' ,'K' ,'B' ,'N' ,'R' },
			{'P', 'P', '0', 'P', 'P', 'P', 'P', 'P' },
			{'0' ,'0' ,'P' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'Q' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'q' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'p' ,'p' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'p' ,'0' ,'0' ,'p' ,'p' ,'p' ,'p' ,'p' },
			{'r' ,'n' ,'b' ,'0' ,'k' ,'b' ,'n' ,'r' }
		};

		
		game.play("D1-C2");
		game.play("D1-D2");
		game.play("D1-E2");
		game.play("D1-B3");
		game.play("D1-D3");
		game.play("D1-F3");
		game.play("C2-C3");
		game.play("D8-E7");
		game.play("D8-D7");
		game.play("D8-C7");
		game.play("D8-F6");
		game.play("D8-D6");
		game.play("D8-B6");
		game.play("C7-C6");
		game.play("D1-D7");
		game.play("D1-A4");
		game.play("D8-D2");
		game.play("D8-A5");
		game.play("A4-D7");
		game.play("B2-B3");
		game.play("A5-D2");
		
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	
	@Test
	public void checkAllValidKingMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'Q' ,'0' ,'B' ,'N' ,'R' },
			{'P', 'P', 'P', 'P', '0', 'P', 'P', 'P' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'P' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'k' ,'0' ,'K' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'p' ,'0' ,'0' ,'0' ,'0' },
			{'p' ,'p' ,'p' ,'0' ,'0' ,'p' ,'p' ,'p' },
			{'r' ,'n' ,'b' ,'q' ,'0' ,'b' ,'n' ,'r' }
		};

		
		game.play("E2-E4");
		game.play("E7-E5");
		game.play("E1-E2");
		game.play("E8-E7");
		game.play("E2-D3");
		game.play("E7-E6");
		game.play("D3-C3");
		game.play("E6-F6");
		game.play("C3-C4");
		game.play("F6-G5");
		game.play("C4-D5");
		game.play("G5-F4");
		game.play("D5-E5");
		game.play("D5-C4");
		game.play("F4-E4");
		game.play("D2-D3");
		game.play("E4-F4");
		
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	
	@Test
	public void checkAllInvalidKingMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'Q' ,'0' ,'B' ,'N' ,'R' },
			{'P', 'P', 'P', 'P', '0', 'P', 'P', 'P' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'k' ,'P' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'p' ,'K' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'p' ,'p' ,'p' ,'p' ,'0' ,'p' ,'p' ,'p' },
			{'r' ,'n' ,'b' ,'q' ,'0' ,'b' ,'n' ,'r' }
		};
		
		game.play("E1-D2");
		game.play("E1-E2");
		game.play("E1-F2");
		game.play("E1-C3");
		game.play("E1-E3");
		game.play("E1-G3");
		game.play("E2-E4");
		game.play("E8-F8");
		game.play("E8-E7");
		game.play("E8-D7");
		game.play("E8-G6");
		game.play("E8-E6");
		game.play("E8-C6");
		game.play("E7-E5");
		game.play("E1-E2");
		game.play("E8-E7");
		game.play("E2-D3");
		game.play("E7-F6");
		game.play("D3-D4");
		game.play("D3-C4");
		game.play("F6-F5");
		game.play("F6-G5");
		game.play("C4-D5");
		game.play("G5-F4");
		game.play("D5-E5");
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	@Test
	public void checkAllValidCastlingQueenSideMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'0' ,'0' ,'K' ,'R' ,'0' ,'B' ,'N' ,'R' },
			{'P', 'P', 'P', 'Q', '0', 'P', 'P', 'P' },
			{'N' ,'0' ,'0' ,'P' ,'P' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'q' },
			{'n' ,'0' ,'0' ,'p' ,'p' ,'0' ,'0' ,'B' },
			{'p' ,'p' ,'p' ,'b' ,'0' ,'p' ,'p' ,'p' },
			{'0' ,'0' ,'k' ,'r' ,'0' ,'b' ,'n' ,'r' }
		};

		
		game.play("E2-E3");
		game.play("B8-A6");
		game.play("D1-G4");
		game.play("D7-D6");
		game.play("G4-H4");
		game.play("C8-H3");
		game.play("B1-A3");
		game.play("D8-D7");
		game.play("D2-D3");
		game.play("E8-C8");
		game.play("C1-D2");
		game.play("E7-E6");
		game.play("E1-C1");
		
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
		
	}
	
	@Test
	public void checkAllInvalidCastlingQueenSideMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'0' ,'0' ,'0' ,'K' ,'0' ,'N' ,'R' },
			{'P', '0', 'P', 'B', 'P', 'P', '0', 'P' },
			{'N' ,'P' ,'q' ,'0' ,'0' ,'0' ,'P' ,'B' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'p' ,'0' },
			{'n' ,'p' ,'Q' ,'0' ,'0' ,'0' ,'0' ,'b' },
			{'p' ,'0' ,'p' ,'b' ,'p' ,'p' ,'0' ,'p' },
			{'r' ,'0' ,'0' ,'0' ,'k' ,'0' ,'n' ,'r' }
		};

		game.play("E1-C1");
		game.play("B1-A3");
		game.play("E8-C8");
		game.play("B8-A6");
		game.play("D2-D4");
		game.play("D7-D5");
		game.play("C1-F4");
		game.play("C8-F5");
		game.play("G2-G3");
		game.play("G7-G6");
		game.play("F1-H3");
		game.play("F8-H6");
		game.play("F4-E5");
		game.play("F5-E4");
		game.play("D1-D3");
		game.play("D8-D6");
		game.play("E1-C1");
		game.play("E5-F4");
		game.play("E8-C8");
		game.play("E4-F5");
		game.play("D3-E4");
		game.play("D6-C5");
		game.play("E4-D5");
		game.play("C5-D4");
		game.play("E1-C1");
		game.play("B2-B3");
		game.play("E8-C8");
		game.play("D4-C3");
		game.play("E1-C1");
		game.play("F4-D2");
		game.play("B7-B6");
		game.play("D5-C6");
		game.play("E8-C8");
		game.play("F5-D7");
		game.play("E1-F1");
		game.play("A8-B8");
		game.play("F1-E1");
		game.play("B8-A8");
		game.play("E1-C1");
		game.play("G3-G4");
		game.play("E8-C8");
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);
	}
	
	@Test
	public void checkAllValidCastlingKingSideMove() throws Exception{
		Game game = new Game();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'Q' ,'0' ,'R' ,'K' ,'0' },
			{'P', 'P', 'P', 'P', '0', 'P', 'P', 'P' },
			{'0' ,'0' ,'0' ,'B' ,'P' ,'0' ,'0' ,'N' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' ,'0' },
			{'0' ,'0' ,'0' ,'b' ,'p' ,'0' ,'0' ,'n' },
			{'p' ,'p' ,'p' ,'p' ,'0' ,'p' ,'p' ,'p' },
			{'r' ,'n' ,'b' ,'q' ,'0' ,'r' ,'k' ,'0' }
		};

		
		game.play("G1-H3");
		game.play("G8-H6");
		game.play("E2-E3");
		game.play("E7-E6");
		game.play("F1-D3");
		game.play("F8-D6");
		game.play("E1-G1");
		game.play("E8-G8");
		
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		
		assertArrayEquals(expectedBoard, board);

	}
	
	
	@Test
	public void checkAllInvalidCastlingKingSideMove() throws Exception{
		Game game = new Game();
		BoardPrinter print = new BoardPrinter();
		char[][] board = new char[8][8]; 
		char[][] expectedBoard= {
			{'R' ,'N' ,'B' ,'0' ,'K' ,'0' ,'0' ,'R' },
			{'0', 'P', 'P', '0', '0', '0', 'P', 'P' },
			{'0' ,'Q' ,'0' ,'0' ,'0' ,'P' ,'0' ,'N' },
			{'P' ,'0' ,'0' ,'P' ,'P' ,'0' ,'0' ,'0' },
			{'p' ,'0' ,'0' ,'p' ,'p' ,'0' ,'0' ,'0' },
			{'0' ,'q' ,'0' ,'0' ,'0' ,'p' ,'0' ,'n' },
			{'0' ,'p' ,'p' ,'0' ,'0' ,'0' ,'p' ,'p' },
			{'r' ,'n' ,'b' ,'0' ,'k' ,'0' ,'0' ,'r' }
		};

		
		game.play("E1-G1");
		game.play("E2-E4");
		game.play("E8-G8");
		game.play("E7-E5");
		game.play("F1-C4");
		game.play("F8-C5");
		game.play("G1-H3");
		game.play("G8-H6");
		game.play("F2-F3");
		game.play("F7-F6");
		game.play("E1-G1");
		game.play("D2-D4");
		game.play("E8-G8");
		game.play("D7-D5");
		game.play("A2-A3");
		game.play("C5-B4");
		game.play("E1-G1");
		game.play("D1-D2");
		game.play("A7-A6");
		game.play("C4-B5");
		game.play("E8-G8");
		game.play("D8-D7");
		game.play("D2-B4");
		game.play("D7-B5");
		game.play("E1-G1");
		game.play("A3-A4");
		game.play("E8-G8");
		game.play("B5-B6");
		game.play("B4-B3");
		game.play("E8-D8");
		game.play("H1-G1");
		game.play("D8-E8");
		game.play("G1-H1");
		game.play("E8-G8");
		game.play("A6-A5");
		game.play("E1-G1");
		
		
		Square[][] gameBoard = game.getGameBoard().getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = gameBoard[i][j].getChessPieceId();
			}
		}
		print.showBoard(game.getGameBoard().getBoard());
		
		assertArrayEquals(expectedBoard, board);
	}
}



	