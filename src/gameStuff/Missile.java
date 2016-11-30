package gameStuff;

public class Missile {
	private double mass;
	int x, y; // locations
	
	public Missile(double m, int startx, int starty){
		mass = m;
		x = startx;
		y = starty;
	}
	
	public double getMass(){
		return mass;
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
