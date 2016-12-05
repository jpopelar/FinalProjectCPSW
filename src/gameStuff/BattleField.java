package gameStuff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import gui.GameWindow;


public class BattleField extends JPanel{
	
	private static BattleField theInstance = new BattleField();
	
	private ArrayList<Level> levelList = new ArrayList<Level>();
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private ArrayList<Launcher> launcherList = new ArrayList<Launcher>();
	private Quiz quiz;
	private Missile theMissile = new Missile(-1,-1);
	private int theLauncher, numLevels, currentLevel;
	// config file names:
	private String battleFieldFileName, questionFileName, launchersFileName; 
	
	
	private int xDim, yDim;
	private int userAngle;
	private double currentTime = 0.0;
	Timer levelTimer;
	private int timeInterval = 17;
	private double milliTimeInterval = 0.017;
	
	private BattleField() {}
	
	public static BattleField getInstance() {
		return theInstance;
	}
	
	
	public void initialize() throws BadConfigException {
		loadLaunchers();
		loadQuestions();
		loadBattleField();
		resetMissile();
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
	
	public void launch(int angle) {
		// Loop check condition so function will idle until we need to reset
		// Set time counter to 0
		currentTime = 0.0;
		// Set userAngle so it can be accesed by timer to perform calculations
		userAngle = angle;
		// Move missile to the location of the launcher - resests for each launch
		resetMissile();
		// Wait to end this method until missile is finished moving and continually performLaunch
		levelTimer = new Timer(timeInterval, new TimerListener());
		levelTimer.setRepeats(true);
		levelTimer.start();
		
	}
	
	private class TimerListener implements ActionListener {		
		public void actionPerformed(ActionEvent arg0) {
			double x = PhysicsEngine.findXPos(launcherList.get(theLauncher).getVelocity(), userAngle, currentTime, getCurrentLevel().getLauncherXLoc());
			double y = PhysicsEngine.findYPos(launcherList.get(theLauncher).getVelocity(), userAngle, currentTime, getCurrentLevel().getLauncherYLoc());
			theMissile.move(x, y);
			repaint();
			//If the projectile hits the ground, is in the air for too long or goes out of play, stop the timer
			if (theMissile.getYLoc() < 0 || theMissile.getXLoc() > xDim) {
				levelTimer.stop();
				resetMissile();
			}
			for (Target t : getCurrentLevel().getTargetList()) {
				if (t.interact(theMissile)) {
					levelTimer.stop();
					resetMissile();
				}
			}
			currentTime += milliTimeInterval;
		}
	}
	
	private void resetMissile() {
		theMissile.move(getCurrentLevel().getLauncherXLoc(), getCurrentLevel().getLauncherYLoc());
	}
	
	public boolean isLaunching() {
		if (theMissile.getXLoc() == this.getCurrentLevel().getLauncherXLoc() && theMissile.getYLoc() == this.getCurrentLevel().getLauncherYLoc()){
			return false;
		}
		return true;
	}
	
	public void incrementLevel() {
		currentLevel++;
		if (currentLevel >= numLevels) {
			JOptionPane.showMessageDialog(null, "All levels complete!");
			currentLevel--;
			return;
		}
		repaint();
	}
	
	/*************************** GUI STUFF ***************************/
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(31, 190, 214, 155));
		g.fillRect(0, 0, xDim * GameWindow.SCALE_FACTOR, yDim * GameWindow.SCALE_FACTOR);
		g.setColor(Color.YELLOW);
		g.fillOval((xDim - xDim/4) * GameWindow.SCALE_FACTOR, 5 * GameWindow.SCALE_FACTOR, 100, 100);
		getCurrentLevel().draw(g);
		launcherList.get(theLauncher).draw(g);
		theMissile.draw(g);
		for (Target t : levelList.get(currentLevel).getTargetList()) {
			t.draw(g);
		}
		
	}
	
	public void doQuiz(){
		quiz = new Quiz(questionList.get(currentLevel));
		quiz.setVisible(true);
		// quiz dialog pops up
		// user selects answer
		// loop until correct
		// close quiz
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
