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
	private int theLaunch, theMissile;
	private static Target theTarget;
	// config file names:
	private String battleFieldFileName, questionFile; 
	
	private int xDim, yDim;
	
	private BattleField() {}
	
	public static BattleField getInstance() {
		return theInstance;
	}
	
	public void loadBattleField() {
		// handles loading up the dimension of the board
		// 	and the target / launcher locats
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
	
	
	//set config files
	public void setConfigFiles(String questionFile){
		this.questionFile = questionFile;
	}
	
	

	
	
	public int getXDim() {
		return 0;
	}
	
	public int getYDim() {
		return 0;
	}
	
	public ArrayList<Question> getQuestions() {
		return questionList;
	}
	public void loadLaunchers() {
		// `
		
	}
	public ArrayList<Launcher> getLaunchers(){
		return launcherList;
	}
	


}
