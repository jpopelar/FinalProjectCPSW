package gameStuff;

public class Launcher {
	public final int YLOC = 0;
	private int xLoc;
	private int velocity;
	private String launcherName;
	
	public Launcher(int xLoc) {
		this.xLoc = xLoc;
	}
	
	public int getXLoc() {
		return xLoc;
	}
	
	public int getVelocity(){
		return velocity;
	}

	

}
