package unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import game.CheckBehaviors;
import game.Game;
import game.StalemateBehaviors;

public class TestGamePlay {

	@Test
	public void testInvalidInput() throws Exception{
		Game game = new Game();
		
		game.showGameInfo();
		game.play("A2-A5");
		game.play("A2-A3");
		assertEquals(false,game.isGameOver());
	}
	
	
	@Test
	public void testCheckMateOnBlackByGameSimulation() throws Exception {
		Game game = new Game();
		CheckBehaviors cb = new CheckBehaviors();
		
		game.play("E2-E3");
		game.play("F7-F6");
		game.play("F1-E2");
		game.play("G8-H6");
		game.play("E2-H5");
		game.play("G7-G6");
		game.play("C2-C4");
		game.play("D7-D6");
		game.play("H5-G6");
		game.play("H7-G6");
		game.play("D1-A4");
		game.play("C7-C6");
		game.play("A4-C6");
		game.play("B8-C6");
		game.play("H2-H4");
		game.play("E7-E5");
		game.play("H1-H3");
		game.play("D6-D5");
		game.play("E3-E4");
		game.play("E8-E7");
		game.play("E4-D5");
		game.play("H8-G8");
		game.play("H3-E3");
		game.play("G6-G5");
		game.play("B2-B3");
		game.play("G5-H4");
		game.play("C1-A3");
		game.play("E7-D7");
		game.play("E3-E5");
		game.play("F8-E7");
		game.play("E5-F5");
		game.play("B7-B5");
		game.play("A3-E7");
		game.play("C6-B8");
		game.play("E7-D8");
		game.play("H6-G4");
		game.play("F5-F6");
		game.play("G8-G5");
		game.play("G1-F3");
		game.play("H4-H3");
		game.play("G2-H3");
		game.play("G5-H5");
		game.play("E1-E2");
		game.play("H5-G5");
		game.play("F3-G5");
		game.play("B5-B4");
		game.play("E2-F1");
		game.play("C8-A6");
		game.play("B1-A3");
		game.play("B8-C6");
		game.play("A1-E1");
		game.play("B4-A3");
		game.play("D5-C6");
		game.play("D7-D8");
		game.play("F6-F8");
		cb.isCheckMate(game.getGameBoard());
		game.play("D8-C7");
		cb.isCheckMate(game.getGameBoard());
		game.play("F8-A8");
		cb.isCheckMate(game.getGameBoard());
		game.play("C7-C6");
		cb.isCheckMate(game.getGameBoard());
		game.play("A8-A7");
		cb.isCheckMate(game.getGameBoard());
		game.play("A6-C8");
		cb.isCheckMate(game.getGameBoard());
		game.play("A7-A3");
		cb.isCheckMate(game.getGameBoard());
		game.play("G4-H6");
		cb.isCheckMate(game.getGameBoard());
		game.play("G5-E6");
		cb.isCheckMate(game.getGameBoard());
		game.play("C8-E6");
		cb.isCheckMate(game.getGameBoard());
		game.play("E1-E6");
		cb.isCheckMate(game.getGameBoard());
     	game.play("C6-C7");
     	cb.isCheckMate(game.getGameBoard());
		game.play("A3-A7");
		cb.isCheckMate(game.getGameBoard());
		game.play("C7-C8");
		cb.isCheckMate(game.getGameBoard());
		game.play("E6-E8");

		boolean isGameOver = game.isGameOver();
		boolean isCheckMate =cb.isCheckMate(game.getGameBoard());

		assertEquals(true,isCheckMate);
		assertEquals(true,isGameOver);
	}
	
	@Test
	public void testCheckMateOnBlackByBishopAndQueen() throws Exception {
		Game game = new Game();
		CheckBehaviors cb = new CheckBehaviors();
		
		game.play("E2-E4");
		game.play("F7-F5");
		game.play("F1-C4");
		game.play("H7-H6");
		game.play("D1-H5");
		cb.isCheckMate(game.getGameBoard());
		game.play("G7-G6");
		cb.isCheckMate(game.getGameBoard());
		game.play("H5-G6");

		boolean isGameOver = game.isGameOver();
		boolean isCheckMate =cb.isCheckMate(game.getGameBoard());

		assertEquals(true,isCheckMate);
		assertEquals(true,isGameOver);
	}
	
	@Test
	public void testCheckOnBlack() throws Exception {
		Game game = new Game();
		CheckBehaviors cb = new CheckBehaviors();
		
		game.play("E2-E4");
		game.play("A7-A5");
		game.play("D1-H5");
		game.play("A5-A4");
		game.play("H5-F7");
		cb.isCheckMate(game.getGameBoard());
		game.play("E8-F7");

		boolean isGameOver = game.isGameOver();
		boolean isCheckMate =cb.isCheckMate(game.getGameBoard());
		game.printBoard();
		assertEquals(false,isCheckMate);
		assertEquals(false,isGameOver);
	}

	@Test
	public void testCheckMateOnWhiteByGameSimulation() throws Exception {
		Game game = new Game();
		CheckBehaviors cb = new CheckBehaviors();
		
		game.play("E2-E4");
		game.play("H7-H5");
		game.play("F2-F4");
		game.play("H5-H4");
		game.play("G2-G4");
		game.play("D7-D5");
		game.play("C2-C4");
		game.play("D5-C4");
		game.play("D2-D3");
		game.play("C4-D3");
		game.play("B1-C3");
		game.play("B7-B5");
		game.play("C3-B1");
		game.play("B5-B4");
		game.play("A2-A4");
		game.play("B4-A3");
		game.play("B1-C3");
		game.play("A3-B2");
		game.play("C3-B1");
		game.play("B2-C1Q");
		game.play("E1-F2");
		game.play("C1-B1");
		game.play("A1-A7");
		game.play("B1-D1");
		game.play("A7-A8");
		game.play("D8-D4");
		cb.isCheckMate(game.getGameBoard());
		game.play("F2-G2");
		cb.isCheckMate(game.getGameBoard());
		game.play("D4-E3");
		cb.isCheckMate(game.getGameBoard());
		game.play("A8-B8");
		cb.isCheckMate(game.getGameBoard());
		game.play("D1-D2");
		cb.isCheckMate(game.getGameBoard());
		game.play("G1-E2");
		cb.isCheckMate(game.getGameBoard());
		game.play("D2-E2");
		cb.isCheckMate(game.getGameBoard());
		game.play("F1-E2");
		cb.isCheckMate(game.getGameBoard());
		game.play("D3-E2");
		cb.isCheckMate(game.getGameBoard());
		game.play("B8-C8"); 
		cb.isCheckMate(game.getGameBoard());
		game.play("E8-D7");
		cb.isCheckMate(game.getGameBoard());
		game.play("C8-D8");
		cb.isCheckMate(game.getGameBoard());
		game.play("D7-E6");
		cb.isCheckMate(game.getGameBoard());
		game.play("D8-F8");
		cb.isCheckMate(game.getGameBoard());
		game.play("H4-H3");
		
		boolean isGameOver = game.isGameOver();
		boolean isCheckMate =cb.isCheckMate(game.getGameBoard());
		assertEquals(true,isCheckMate);
		assertEquals(true,isGameOver);
	}
	
	@Test
	public void testCheckOnWhite() throws Exception {
		Game game = new Game();
		CheckBehaviors cb = new CheckBehaviors();
		
		game.play("D2-D4");
		game.play("C7-C5");
		game.play("H2-H4");
		game.play("D8-A5");
		cb.isCheckMate(game.getGameBoard());
		game.play("B2-B4");
		game.play("A5-B4");
		cb.isCheckMate(game.getGameBoard());
		game.play("C2-C3");
		game.play("B4-C3");
		cb.isCheckMate(game.getGameBoard());
		game.play("B1-D2");
		game.play("C3-D2");
		cb.isCheckMate(game.getGameBoard());
		game.play("E1-D2");
		
		boolean isGameOver = game.isGameOver();
		boolean isCheckMate =cb.isCheckMate(game.getGameBoard());
		game.printBoard();
		assertEquals(false,isCheckMate);
		assertEquals(false,isGameOver);
	}
	
	@Test
	public void testCheckOnWhite2() throws Exception{
		Game game = new Game();
		CheckBehaviors cb = new CheckBehaviors();
		
		game.play("A2-A3");
		game.play("E7-E5");
		game.play("A3-A4");
		game.play("D8-E7");
		game.play("D2-D4");
		game.play("E5-D4");
		game.play("A4-A5");
		game.play("E7-E2");
		cb.isCheckMate(game.getGameBoard());
		game.play("D1-E2");
		
		boolean isGameOver = game.isGameOver();
		boolean isCheckMate =cb.isCheckMate(game.getGameBoard());
		game.printBoard();
		assertEquals(false,isCheckMate);
		assertEquals(false,isGameOver);
	}
		
	@Test
	public void testDrawOnStalemateAndSeveralLegalMove() throws Exception {
		Game game = new Game();
		CheckBehaviors cb = new CheckBehaviors();
		StalemateBehaviors sb = new StalemateBehaviors();
		
		game.play("D2-D4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C7-C5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D4-C5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D7-D6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C5-D6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D8-D6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F2-F4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B7-B5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C2-C3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B5-B4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C3-B4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H7-H5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H2-H3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G7-G5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F4-G5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B8-C6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G1-F3");	
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H5-H4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F3-H4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A8-B8");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B1-C3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F8-H6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C3-B5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H6-G5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B5-A7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G5-C1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D1-C1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H8-H4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A7-C6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D6-C6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G2-G3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H4-H3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C1-F4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G8-H6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F4-B8");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H3-G3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F1-G2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E8-D8");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H1-H6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C6-H6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B8-E5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G3-E3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E5-F4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E3-E2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E1-E2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F7-F5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E2-D2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H6-H2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D2-C3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H2-G2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C3-B3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G2-G4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B3-A4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G4-G6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A1-C1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D8-D7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B2-B3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D7-D8");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C1-C3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E7-E5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F4-E5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G6-G5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A2-A3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G5-G4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C3-C5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F5-F4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C5-B5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C8-D7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E5-F4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G4-F4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A4-A5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F4-F6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A5-A4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F6-B6");
		
		boolean isGameOver = game.isGameOver();
		boolean isDraw= sb.isDrawOnStalemate(game.getGameBoard());
		boolean isCheckMate =cb.isCheckMate(game.getGameBoard());

		assertEquals(true, isDraw);
		assertEquals(false,isCheckMate);
		assertEquals(true,isGameOver);
	}
	
	@Test
	public void testLegalMoveForSeveralPiece() throws Exception{
		Game game = new Game();
		StalemateBehaviors sb = new StalemateBehaviors();
		
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B2-B4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A7-A5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B4-A5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A8-A6");
		game.play("C2-C4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A6-G6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C4-C5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B8-C6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A5-A6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C6-A5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C5-C6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G8-F6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B1-C3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H7-H5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C3-D5");
		sb.isDrawOnStalemate(game.getGameBoard()); 
		game.play("H8-H6"); 
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D1-B3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G6-G4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D5-B6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G4-A4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A6-A7");
		sb.isDrawOnStalemate(game.getGameBoard()); 
		game.play("A5-C4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C1-B2");
		sb.isDrawOnStalemate(game.getGameBoard());  
		game.play("A4-A7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B3-B4");
		sb.isDrawOnStalemate(game.getGameBoard());  
		game.play("A7-A6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B4-B5");
		sb.isDrawOnStalemate(game.getGameBoard());  
		game.play("A6-A5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B5-G5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A5-A4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B6-D5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A4-A3");
		sb.isDrawOnStalemate(game.getGameBoard()); 
		game.play("C6-D7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C8-D7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B2-F6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C7-C5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E2-E4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D8-A5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D5-B4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E8-D8");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H2-H4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D8-C7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G5-D5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E7-E6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D5-E6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B7-B6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E6-G4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C7-B7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E4-E5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B7-A7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E5-E6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F8-D6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E6-E7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H6-H8");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A1-C1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H8-E8");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F6-G7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E8-E7");
		sb.isDrawOnStalemate(game.getGameBoard()); 
		game.play("G4-E2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D7-C6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G7-B2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A3-C3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A2-A3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A5-A4");
		sb.isDrawOnStalemate(game.getGameBoard()); 
		game.play("D2-C3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E7-E4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B4-C2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F7-F6");
		sb.isDrawOnStalemate(game.getGameBoard()); 
		game.play("H1-H2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E4-H4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G2-G3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H4-G4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H2-G2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H5-H4");
		sb.isDrawOnStalemate(game.getGameBoard());  
		game.play("E2-D1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H4-G3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G1-E2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C4-D2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F2-F3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D2-B3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G2-H2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B3-A1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F1-G2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F6-F5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E2-G1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("F5-F4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D1-E2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A1-B3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C1-A1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C6-B5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A1-C1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("G4-H4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E2-D1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C5-C4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E1-E2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A7-B7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B2-A1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A4-A5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E2-E1");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D6-C5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("D1-D2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B7-C6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E1-D1");
		
		boolean isDraw= sb.isDrawOnStalemate(game.getGameBoard());
		assertEquals(false, isDraw);
	}
	
	@Test
	public void testRemainingLegalMoveForKnight() throws Exception{
		Game game = new Game();
		StalemateBehaviors sb = new StalemateBehaviors();
		
		game.play("B1-C3");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A7-A5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A2-A4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E7-E5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E2-E4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("H7-H6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A1-A2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("A8-A7");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("E1-E2");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B8-C6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B2-B4");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B7-B6");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("B4-B5");
		sb.isDrawOnStalemate(game.getGameBoard());
		game.play("C6-E7");
		game.play("H2-H3");
		sb.isDrawOnStalemate(game.getGameBoard());
 	
		boolean isDraw= sb.isDrawOnStalemate(game.getGameBoard());
		assertEquals(false, isDraw);
	}
} 