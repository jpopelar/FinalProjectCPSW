package gameStuff;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
	private int launcherXLoc, launcherYLoc, numTargets, levelNum;
	private ArrayList<Target> targetList;
	private String levelName;
	
	public Level(int num) {
		targetList = new ArrayList<Target>();
		levelNum = num;
		loadLevel(num);
	}
	
	private void loadLevel(int num) {
		// NOTE: not dealing with bad config formats. These formats will be correct
		String fileName = "levels/level" + num + ".txt";
		String del = ", "; // NOTE: Delimiter is ", "
		try {
			FileReader openFile = new FileReader(fileName);
			Scanner tmpScanner = new Scanner(openFile);
			// Load first line String as the name of the level
			levelName = tmpScanner.nextLine();
			// Load up the number of targets (second line)
			numTargets = Integer.parseInt(tmpScanner.nextLine());
			// Third line is the one and only launcher's location
			String[] locs = new String[2];
			String tmp = tmpScanner.nextLine();
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
			tmpScanner.close();
		} catch (FileNotFoundException e) {
			// TODO: implement prompt to user
			levelNum = -1;
			System.out.println("File not found - level " + levelNum + " does not exist");		
		}
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

	
}
