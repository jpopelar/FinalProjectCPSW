package gameStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;

import gui.GameWindow;

public class Level extends JComponent {
	private int launcherXLoc, launcherYLoc, numTargets, levelNum, numBkgRects;
	private ArrayList<Target> targetList;
	private String levelName;
	private ArrayList<Rectangle> rects;
	private ArrayList<Color> rectColors;
	
	public Level(int num) {
		targetList = new ArrayList<Target>();
		rects = new ArrayList<Rectangle>();
		rectColors = new ArrayList<Color>();
		levelNum = num;
		loadLevel(num);
	}
	
	private void loadLevel(int num) {
		// NOTE: not dealing with bad config formats. These formats will be correct
		String fileName = "/data/level" + num + ".txt";
		String del = ", "; // NOTE: Delimiter is ", "
		InputStream rdr = getClass().getResourceAsStream(fileName);
		Scanner tmpScanner = new Scanner(rdr);
		String[] locs = new String[5];
		// Load first line String as the name of the level
		levelName = tmpScanner.nextLine();
		// Load up the number of targets (second line)
		String tmp = tmpScanner.nextLine();
		locs = tmp.split(del);
		numTargets = Integer.parseInt(locs[0]);
		numBkgRects = Integer.parseInt(locs[1]);
		// Third line is the one and only launcher's location
		tmp = tmpScanner.nextLine();
		locs = tmp.split(del);
		launcherXLoc = Integer.parseInt(locs[0]);
		launcherYLoc = Integer.parseInt(locs[1]);
		
		// All following lines are targets locations
		// only loads specified number of targets
		for(int i = 0; i < numTargets; i++) {
			tmp = tmpScanner.nextLine();
			locs = tmp.split(del);
			int x = Integer.parseInt(locs[0]);
			int y = Integer.parseInt(locs[1]);
			targetList.add(new Target(x, y));
		}
		
		for (int i = 0; i < numBkgRects; i++) {
			tmp = tmpScanner.nextLine();
			locs = tmp.split(del);
			int x = Integer.parseInt(locs[0]);
			int y = Integer.parseInt(locs[1]);
			int w = Integer.parseInt(locs[2]);
			int h = Integer.parseInt(locs[3]);
			Color c = convertColor(locs[4]);
			rects.add(new Rectangle(x,y,w,h));
			rectColors.add(c);
		}
		
		tmpScanner.close();
	}

	public int getLauncherXLoc() {
		return launcherXLoc;
	}

	public int getLauncherYLoc() {
		return launcherYLoc;
	}

	public int getLevelNum() {
		return levelNum;
	}

	public ArrayList<Target> getTargetList() {
		return targetList;
	}

	public String getLevelName() {
		return levelName;
	}

	public void draw(Graphics g) {
		int yDim = BattleField.getInstance().getYDim();
		int xDim = BattleField.getInstance().getXDim();
		int i = 0;
		for (Rectangle r : rects) {
			g.setColor(rectColors.get(i));
			g.fillRect((int)r.getX() * GameWindow.SCALE_FACTOR, (int)r.getY() * GameWindow.SCALE_FACTOR, (int)r.getWidth() * GameWindow.SCALE_FACTOR, (int)r.getHeight() * GameWindow.SCALE_FACTOR);
			i++;
		}
	}
	
	// Color string conversion copied from CR copied from StackOverflow
	private Color convertColor(String strColor) {
	    Color color; 
	    try {     
	        // We can use reflection to convert the string to a color
	        Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
	        color = (Color)field.get(null); 
	    } catch (Exception e) {  
	        color = null; // Not defined  
	    }
	    return color;
	}
	
}
