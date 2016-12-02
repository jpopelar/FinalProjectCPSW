package gameStuff;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import gui.GameWindow;

public class Launcher extends JComponent {
	private int velocity;
	private String launcherName, imageName;
	private static BattleField field = BattleField.getInstance();
	
	public Launcher(String launcherName, int velocity, String imageName) {
		this.launcherName = launcherName;
		this.velocity = velocity;
		this.imageName = imageName;
	}
	
	public int getVelocity(){
		return velocity;
	}

	public String getName(){
		return this.launcherName;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(field.getCurrentLevel().getLauncherXLoc() * GameWindow.SCALE_FACTOR, field.getCurrentLevel().getLauncherYLoc() * GameWindow.SCALE_FACTOR, 2 * GameWindow.SCALE_FACTOR, 2 * GameWindow.SCALE_FACTOR);
	}

}
