package gameStuff;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import gui.GameWindow;

public class Target extends JComponent {
	private int xLoc, yLoc;
	private boolean wasHit;
	// TODO: deal with size of target
	private int width = 2;
	
	public Target(int x, int y) {
		xLoc = x;
		yLoc = y;
		wasHit = false;
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
			Math.abs(yLoc - m.getYLoc()) <= width / 2	) {
			this.wasHit = true;
			return true;
		}
		return false;
	}
	public boolean wasHit() {
		return wasHit;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		int x = (xLoc - (width/2)) * GameWindow.SCALE_FACTOR;
		int y = (BattleField.getInstance().getYDim() - yLoc - width) * GameWindow.SCALE_FACTOR;
		if (wasHit){
			g.setColor(Color.GRAY);
		}
		g.fillRect(x, y, width * GameWindow.SCALE_FACTOR, width * GameWindow.SCALE_FACTOR);
	}
}
