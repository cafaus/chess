package game;

public class Coordinates {
	private int fromX,fromY, toX, toY;
	private int  topBoundary, bottomBoundary;
	private int prevOriginY,prevOriginX,prevCurrPosY, prevCurrPosX;
	
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

	public void setFromX(int fromX) {
		this.fromX = fromX;
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

	public void setFromY(int fromY) {
		this.fromY = fromY;
	}

	public int getToX() {
		return toX;
	}

	public void setToX(int toX) {
		this.toX = toX;
	}

	public int getToY() {
		return toY;
	}

	public void setToY(int toY) {
		this.toY = toY;
	}
	
}
