package gameStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

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
		int x = (field.getCurrentLevel().getLauncherXLoc() - (width/2)) * GameWindow.SCALE_FACTOR;
		int y = (BattleField.getInstance().getYDim() - field.getCurrentLevel().getLauncherYLoc() - width) * GameWindow.SCALE_FACTOR;
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("catapult.png"));
		} catch (IOException e) {
			System.out.println("not working");
		}
		g.drawImage(image, x, y, null);
	}

	public String toString() {
		return launcherName;
	}
}
