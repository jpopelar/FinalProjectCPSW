package gameStuff;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import gui.GameWindow;

public class Launcher extends JComponent {
	private int velocity, width;
	private String launcherName, imageName;
	private static BattleField field = BattleField.getInstance();
	
	public Launcher(String launcherName, int velocity, String imageName) {
		this.launcherName = launcherName;
		this.velocity = velocity;
		this.imageName = imageName;
		width = 3;
	}
	
	public int getVelocity(){
		return velocity;
	}

	public String getName(){
		return this.launcherName;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		int x = (field.getCurrentLevel().getLauncherXLoc() - (width/2)) * GameWindow.SCALE_FACTOR;
		int y = (BattleField.getInstance().getYDim() - field.getCurrentLevel().getLauncherYLoc() - width) * GameWindow.SCALE_FACTOR;
		
		g.fillRect(x, y, width * GameWindow.SCALE_FACTOR, width * GameWindow.SCALE_FACTOR);
	}

	public String toString() {
		return launcherName;
	}
}
