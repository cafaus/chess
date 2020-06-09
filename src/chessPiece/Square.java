package chessPiece;

public class Square {
	private ChessPiece chessPiece;
	private boolean isWhiteTile;
	
	public Square(ChessPiece chessPiece, boolean isWhiteTile) {
		this.chessPiece = chessPiece;
		this.isWhiteTile = isWhiteTile;		
	}

	public char getChessPieceId() {
		if(chessPiece == null) return '0';
		return chessPiece.getChessPieceId();
	}
	
	public ChessPiece getChessPiece() {
		ChessPiece chessPieceCopy = null;
		try {
			if (chessPiece == null) return null;
			chessPieceCopy = chessPiece.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return chessPieceCopy;
	}
	
	public void setChessPiece(ChessPiece chessPiece) {
		this.chessPiece = chessPiece;
	}
	public boolean isChessPiece()  {
		if(this.chessPiece == null) return false;
		return true;
	}
	
	public boolean isBlackPiece() {
		if(!isChessPiece())return false;
		if(chessPiece.isWhitePiece()) return false;
		return true;
	}
	
	public boolean isWhitePiece() {
		if(!isChessPiece())return false;
		return chessPiece.isWhitePiece();
	}
	
	public boolean isWhiteTile() {
		return isWhiteTile;
	}
}
