package gameStuff;

public class Missile {
	int x, y; // locations
	
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
	
	public void move(int xLoc, int yLoc) {
		this.x = xLoc;
		this.y = yLoc;
	}

}
