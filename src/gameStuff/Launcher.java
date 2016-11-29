package gameStuff;

public class Launcher {
	private int xLoc, yLoc;
	private int velocity;
	private String launcherName, imageName;
	
	public Launcher(String launcherName, int velocity, String imageName) {
		this.launcherName = launcherName;
		this.velocity = velocity;
		this.imageName = imageName;
		// default location
		this.xLoc = 5;
		this.yLoc = 0;
	}
	
	public int getXLoc() {
		return xLoc;
	}
	
	public int getVelocity(){
		return velocity;
	}

	public String getName(){
		return this.launcherName;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

}
