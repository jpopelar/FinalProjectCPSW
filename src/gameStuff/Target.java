package gameStuff;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import gui.GameWindow;

public class Target extends JComponent {
	private int xLoc, yLoc;
	private boolean wasHit;
	// TODO: deal with size of target
	private int width = 2;
	private String unhitFileName = "/images/target.png";
	private String hitFileName = "/images/hitTarget.png";
	private Image unhit, hit;
	
	public Target(int x, int y) {
		xLoc = x;
		yLoc = y;
		wasHit = false;
		loadImages();
	}
	
	public int getXLoc() {
		return xLoc;
	}
	
	public int getYLoc() {
		return yLoc;
	}
	public boolean interact(Missile m) {
		// create a space around target
		// test if in x-range
		// test if in y-range
		if (Math.abs(xLoc - m.getXLoc()) <= width / 2 &&
			Math.abs(yLoc - m.getYLoc()) <= width / 2 &&
			!this.wasHit()) {
			this.wasHit = true;
			return true;
		}
		return false;
	}
	public boolean wasHit() {
		return wasHit;
	}
	
	private void loadImages() {
		BufferedImage buffImageUnhit = null;
		BufferedImage buffImageHit = null;
		URL unhitURL = getClass().getResource(unhitFileName);
		URL hitURL = getClass().getResource(hitFileName);
		
		
		try {
			buffImageHit = ImageIO.read(hitURL);
			buffImageUnhit = ImageIO.read(unhitURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		hit = buffImageHit.getScaledInstance(width * GameWindow.SCALE_FACTOR, width * GameWindow.SCALE_FACTOR, Image.SCALE_DEFAULT);
		unhit = buffImageUnhit.getScaledInstance(width * GameWindow.SCALE_FACTOR, width * GameWindow.SCALE_FACTOR, Image.SCALE_DEFAULT);
		
	}
	
	public void draw(Graphics g) {
		int x = (xLoc - (width/2)) * GameWindow.SCALE_FACTOR;
		int y = (BattleField.getInstance().getYDim() - yLoc - width) * GameWindow.SCALE_FACTOR;
		
		if (wasHit){
			g.drawImage(hit, x, y, null);
		}
		else {
			g.drawImage(unhit, x, y, null);
		}
		
	}
}
