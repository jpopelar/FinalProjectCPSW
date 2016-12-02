package gameStuff;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import gui.GameWindow;

public class Missile extends JComponent {
	private int xLoc, yLoc; // locations
	
	public Missile(int startx, int starty){
		xLoc = startx;
		yLoc = starty;
	}
	
	public int getXLoc() {
		return xLoc;
	}
	
	public int getYLoc() {
		return yLoc;
	}
	
	public void move(double x, double y) {
		this.xLoc = (int) x;
		this.yLoc = (int) y;
	}
	
	public void move(int x, int y) {
		this.xLoc = x;
		this.yLoc = y;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawOval(xLoc * GameWindow.SCALE_FACTOR, yLoc * GameWindow.SCALE_FACTOR, 1 * GameWindow.SCALE_FACTOR, 1 * GameWindow.SCALE_FACTOR);
	}
	

}
