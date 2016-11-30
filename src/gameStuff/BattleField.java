package gameStuff;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class BattleField {
	
	private static BattleField theInstance = new BattleField();
	
	private ArrayList<Level> levelList = new ArrayList<Level>();
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private ArrayList<Launcher> launcherList = new ArrayList<Launcher>();
	private ArrayList<Missile> missileList = new ArrayList<Missile>();
	private int theLauncher, theMissile, numLevels;
	// config file names:
	private String battleFieldFileName, questionFileName, launchersFileName; 
	
	private int xDim, yDim;
	
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
	
	public void launch() {
		// get the missile and set its location
		// update the missiles location at some time interval
		// continually call the interact method for all available targets in the level with that missile
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
	


}
