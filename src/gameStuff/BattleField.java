package gameStuff;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class BattleField {
	
	private static BattleField theInstance = new BattleField();
	
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private ArrayList<Launcher> launcherList = new ArrayList<Launcher>();
	private ArrayList<Missile> missileList = new ArrayList<Missile>();
	private int theLauncher, theMissile;
	public static Target theTarget;
	// config file names:
	private String battleFieldFileName, questionFileName, launchersFileName; 
	
	private int xDim, yDim;
	
	private BattleField() {}
	
	public static BattleField getInstance() {
		return theInstance;
	}
	
	
	
	
	
	/*************************** FILE LOADERS ***************************/
	public void loadBattleField() {
		// `TODO: implement
		// handles loading up the dimension of the board
		// 	and the target / launcher locations
		// all the different launchers will be loaded in separate function from separate file
	}
	
	public void loadLaunchers() {
		// `TODO: implement
		
	}
	
	public void loadQuestions()throws BadConfigException {
		try {
			FileReader questionFile = new FileReader("questionListTest.txt");
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
		} catch (FileNotFoundException e) {
			System.out.println("Load question config error");		}
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
	


}
