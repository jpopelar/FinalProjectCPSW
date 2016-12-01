package gameStuff;

public class Missile {
	private int x, y; // locations
	
	public Missile(int startx, int starty){
		x = startx;
		y = starty;
	}
	
	public int getXLoc() {
		return x;
	}
	
	public int getYLoc() {
		return y;
	}
	
	public void move(double xLoc, double yLoc) {
		this.x = (int) xLoc;
		this.y = (int) yLoc;
	}

}
