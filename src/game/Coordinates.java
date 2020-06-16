package game;

import chessPiece.Bishop;
import chessPiece.ChessPiece;
import chessPiece.Knight;
import chessPiece.Queen;
import chessPiece.Rook;

public class Coordinates {
	private int fromX,fromY, toX, toY;
	private int topBoundary, bottomBoundary;
	private int prevOriginY,prevOriginX,prevCurrPosY, prevCurrPosX;
	private ChessPiece chessPiecePromote;
	
	public int getPrevOriginY() {
		return prevOriginY;
	}

	public void setPrevOriginY(int prevOriginY) {
		this.prevOriginY = prevOriginY;
	}

	public int getPrevOriginX() {
		return prevOriginX;
	}

	public void setPrevOriginX(int prevOriginX) {
		this.prevOriginX = prevOriginX;
	}

	public int getPrevCurrPosY() {
		return prevCurrPosY;
	}

	public void setPrevCurrPosY(int prevCurrPosY) {
		this.prevCurrPosY = prevCurrPosY;
	}

	public int getPrevCurrPosX() {
		return prevCurrPosX;
	}

	public void setPrevCurrPosX(int prevCurrPosX) {
		this.prevCurrPosX = prevCurrPosX;
	}

	public int getFromX() {
		return fromX;
	}

	public void setFrom(int fromY, int fromX) {
		this.fromY = fromY;
		this.fromX = fromX;
	}

	public int getToX() {
		return toX;
	}

	public int getToY() {
		return toY;
	}
	public void setTo(int toY,int toX) {
		this.toY = toY;
		this.toX = toX;
	}


	public int getTopBoundary() {
		return topBoundary;
	}

	public void setTopBoundary(int topBoundary) {
		this.topBoundary = topBoundary;
	}

	public int getBottomBoundary() {
		return bottomBoundary;
	}

	public void setBottomBoundary(int bottomBoundary) {
		this.bottomBoundary = bottomBoundary;
	}

	public int getFromY() {
		return fromY;
	}
 

	public ChessPiece getChessPiecePromote() {
		return chessPiecePromote;
	}

	public void setChessPiecePromote(ChessPiece chessPiecePromote) {
		this.chessPiecePromote = chessPiecePromote;
	}
	
	public void SetChessPiecePromoteCharToObject (char id, boolean isWhitePiece) {
		ChessPiece chessPiece = null;   
		if(id == 'R') chessPiece = new Rook(isWhitePiece);
		else if(id == 'N') chessPiece = new Knight(isWhitePiece);
		else if(id == 'B') chessPiece = new Bishop(isWhitePiece);
		else if(id == 'Q') chessPiece = new Queen(isWhitePiece);

		this.chessPiecePromote = chessPiece;
	}
	
}
