package gameStuff;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import gui.GameWindow;

public class Launcher extends JComponent {
	private int velocity, width, imageHeight, imageWidth;
	private String launcherName, imageName;
	private static BattleField field = BattleField.getInstance();
	private Image image;
	
	public Launcher(String launcherName, int velocity, String imageName) {
		this.launcherName = launcherName;
		this.velocity = velocity;
		this.imageName = "/images/" + imageName;
		imageHeight = 50;
		imageWidth = 65;
		width = 5;
		loadImage();
	}
	
	public int getVelocity(){
		return velocity;
	}

	public String getName(){
		return this.launcherName;
	}
	
	private void loadImage() {
		BufferedImage buffImg = null;
		URL url = getClass().getResource(imageName);
		
		try {
			buffImg = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		image = buffImg.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
		
	}
	
	public void draw(Graphics g) {
		int x = (field.getCurrentLevel().getLauncherXLoc() - (width/2)) * GameWindow.SCALE_FACTOR;
		int y = (BattleField.getInstance().getYDim() - field.getCurrentLevel().getLauncherYLoc() - width) * GameWindow.SCALE_FACTOR;
		g.drawImage(image, x, y, null);	
	}

	public String toString() {
		return launcherName;
	}
}
