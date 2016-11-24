package gameStuff;

public class Launcher {
	public final int YLOC = 0;
	private int xLoc;
	private int velocity;
	private String launcherName, imageName;
	
	public Launcher(String launcherName, int xLoc, int velocity, String imageName) {
		this.launcherName = launcherName;
		this.xLoc = xLoc;
		this.velocity = velocity;
		this.imageName = imageName;
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

}
