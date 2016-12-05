package gameStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import gui.GameWindow;

public class Launcher extends JComponent {
	private int velocity, width, imageHeight, imageWidth;
	private String launcherName, imageName;
	private static BattleField field = BattleField.getInstance();
	
	public Launcher(String launcherName, int velocity, String imageName) {
		this.launcherName = launcherName;
		this.velocity = velocity;
		this.imageName = imageName;
		imageHeight = 50;
		imageWidth = 65;
		width = 5;
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
		//BufferedImage image = null;
		URL url = getClass().getResource(imageName);
		Image tempImg =  Toolkit.getDefaultToolkit().getImage(url).getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
		/*try {
			image = ImageIO.read(new File(imageName));
		} catch (IOException e) {
		}*/
		//Image tempImg = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
		g.drawImage(tempImg, x, y, null);
	}

	public String toString() {
		return launcherName;
	}
}
