package gameStuff;

public class Target {
	private int xLoc, yLoc;
	private boolean wasHit;
	// TODO: deal with size of target
	private int width = 5;
	
	public Target(int x, int y) {
		xLoc = x;
		yLoc = y;
		wasHit = false;
	}
	
	public int getXLoc() {
		return xLoc;
	}
	
	public int getYLoc() {
		return yLoc;
	}
	public void interact(Missile m) {
		// create a space around target
		// test if in x-range
		// test if in y-range
		if (Math.abs(xLoc - m.getXLoc()) <= width / 2 &&
			Math.abs(yLoc - m.getYLoc()) <= width / 2	) {
			this.wasHit = true;
		}
	}
	public boolean wasHit() {
		return wasHit;
	}
}
