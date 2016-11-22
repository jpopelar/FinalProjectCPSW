package gameStuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BattleField {
	
	private static BattleField theInstance = new BattleField();
	
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private ArrayList<Launcher> launcherList = new ArrayList<Launcher>();
	private ArrayList<Missile> missileList = new ArrayList<Missile>();
	private int theLaunch, theMissile;
	private static Target theTarget;
	// config file names:
	private String battleFieldFileName, questionsFileName; 
	
	private int xDim, yDim;
	
	public BattleField() {
		
	}
	public static BattleField getInstance() {
		return theInstance;
	}
	
	public void loadBattleField() {
		// handles loading up the dimension of the board
		// 	and the target / launcher locats
	}
	public void loadQuestions(){
		// 
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
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Launcher> getLaunchers(){
		return launcherList;
	}
	


}
