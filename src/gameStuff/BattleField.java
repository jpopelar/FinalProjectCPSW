package gameStuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.Timer;


public class BattleField {
	
	private static BattleField theInstance = new BattleField();
	
	private ArrayList<Level> levelList = new ArrayList<Level>();
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private ArrayList<Launcher> launcherList = new ArrayList<Launcher>();
	private Missile theMissile = new Missile(0,0);
	private int theLauncher, numLevels, currentLevel;
	// config file names:
	private String battleFieldFileName, questionFileName, launchersFileName; 
	
	private int xDim, yDim;
	private int userAngle;
	private int currentTime = 0;
	
	private boolean launchOver = false;
	
	private BattleField() {}
	
	public static BattleField getInstance() {
		return theInstance;
	}
	
	
	public void initialize() throws BadConfigException {
		loadLaunchers();
		loadQuestions();
		loadBattleField();
	}
	
	
	/*************************** FILE LOADERS ***************************/
	
	public void loadBattleField() {
		// TODO: implement
		// handles loading up the dimension of the board
		// 	and the target / launcher locations
		// all the different launchers will be loaded in separate function from separate file
		try {
			FileReader launchFile = new FileReader(battleFieldFileName);
			Scanner tmpScanner = new Scanner(launchFile);
			String tmp = tmpScanner.nextLine();
			
			String[] data = new String[2]; // constant
			data = tmp.split(", "); // NOTE: Delimiter is ", "
			xDim = Integer.parseInt(data[0]);
			yDim = Integer.parseInt(data[1]);
			
			tmp = tmpScanner.nextLine();
			data = tmp.split(", "); // NOTE: Delimiter is ", "
			numLevels = Integer.parseInt(data[0]);
			
			tmpScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Load battlefield config error");		
		}
		
		// Load up levels into an arrayList
		for (int i = 1; i <= numLevels; i++){
			levelList.add(new Level(i));
		}
	}
	
	
	public void loadLaunchers() throws BadConfigException {
		try {
			FileReader launchFile = new FileReader(launchersFileName);
			Scanner tmpScanner = new Scanner(launchFile);
			while (tmpScanner.hasNextLine()) {
				String tmp = tmpScanner.nextLine();
				String[] launcherData = new String[3]; // constant
				launcherData = tmp.split(":"); // NOTE: Delimiter is ":"
				Launcher tmpLauncher = new Launcher(launcherData[0], Integer.parseInt(launcherData[1]), launcherData[2]);
				launcherList.add(tmpLauncher);
			}
			tmpScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Load launcher config error");		}
	}
	
	public void loadQuestions()throws BadConfigException {
		try {
			FileReader questionFile = new FileReader(questionFileName);
			Scanner tempScanner = new Scanner(questionFile);
			while (tempScanner.hasNextLine()) {
				String tempQA = tempScanner.nextLine();
				String[] questAns = new String[5]; // constant
				questAns = tempQA.split(":"); // NOTE: Delimiter is ":"
				String[] wrongA = new String[3];
				for(int i = 0; i < 3; i++) {
					wrongA[i] = questAns[i+2];
				}
				Question tmpQ = new Question(questAns[0], questAns[1], wrongA);
				questionList.add(tmpQ);
			}
			tempScanner.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Load question config error");		}
	}
	
	/*************************** GAMEPLAY ***************************/
	
	private class TimerListener implements ActionListener {
		Missile currentMissile = theMissile;
		Launcher currentLauncher = launcherList.get(theLauncher);
		Level currentLevelPlayed = levelList.get(currentLevel);
		ArrayList<Target> currentTargets = currentLevelPlayed.getTargetList();		
		public void actionPerformed(ActionEvent arg0) {
			currentMissile.move(PhysicsEngine.findXPos(currentLauncher.getVelocity(), userAngle, currentTime), PhysicsEngine.findYPos(currentLauncher.getVelocity(), userAngle, currentTime));
			if (currentMissile.getXLoc() == PhysicsEngine.findXEnd(currentLauncher.getVelocity(), userAngle)) {
				launchOver = true;
			}
			for (Target t : currentTargets) {
				t.interact(currentMissile);
				if (t.wasHit()) {
					launchOver = true;
				}
			}
		}
		
	}
	
	public void launch(double angle) {
		launchOver = false;	//Loop check condition so function will idle until we need to reset
		theMissile.move(launcherList.get(theLauncher).getXLoc(), launcherList.get(theLauncher).getYLoc());	//Resets the missile's location
		Timer levelTimer = new Timer(17, new TimerListener());
		levelTimer.start();
		while (!launchOver) {
		}
	
	}
	
	public void incrementLevel() {
		currentLevel++;
	}
	
	/*************************** SETTERS ***************************/
	
	//set config files
	public void setQuestionFile(String questionFile){
		this.questionFileName = questionFile;
	}
	public void setFieldFile(String n) {
		this.battleFieldFileName = n;
	}
	public void setLaunchersFile(String n) {
		this.launchersFileName = n;
	}
	public void setLauncher(int i) {
		theLauncher = i;
	}
	
	

	
	/*************************** GETTERS ***************************/
	public int getXDim() {
		return xDim;
	}
	
	public int getYDim() {
		return yDim;
	}
	
	public ArrayList<Question> getQuestions() {
		return questionList;
	}

	public ArrayList<Launcher> getLaunchers(){
		return launcherList;
	}

	public ArrayList<Level> getLevelList() {
		return levelList;
	}
	
	public Level getCurrentLevel() {
		return levelList.get(currentLevel);
	}
	


}
