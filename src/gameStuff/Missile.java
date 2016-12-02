package gameStuff;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import gui.GameWindow;

public class Missile extends JComponent {
	private int xLoc, yLoc, radius; // locations
	
	public Missile(int startx, int starty){
		xLoc = startx;
		yLoc = starty;
		radius = 1;
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
		g.setColor(Color.MAGENTA);
		int x = (xLoc - (radius/2)) * GameWindow.SCALE_FACTOR;
		int y = (BattleField.getInstance().getYDim() - yLoc - radius) * GameWindow.SCALE_FACTOR;
		
		g.fillOval(x, y, radius * GameWindow.SCALE_FACTOR, radius * GameWindow.SCALE_FACTOR);
	}
	

}
