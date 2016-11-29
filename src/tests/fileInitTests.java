package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import gameStuff.BadConfigException;
import gameStuff.BattleField;
import gameStuff.Launcher;
import gameStuff.Question;

public class fileInitTests {
	
	private static BattleField field;
	
	@BeforeClass
	public static void init() {
		BattleField field = BattleField.getInstance();
		// load config files for testing
		
		try {
			field.setLaunchersFile("LauncherConfig.txt");
			field.setQuestionFile("questionListTest.txt");
			field.loadBattleField();
		} catch (BadConfigException e) {
			System.out.println("Error loading config files, please check before continuing.");
		}
		
		// harcode the file names and load up the battleField
		// use setter to load config files
	}	
	
	@Test
	public void testDims() {
		// assertEquals board dims are same as specified in config file
		assertEquals(field.getXDim(), 100);
		assertEquals(field.getYDim(), 25);
	}
	
	@Test
	public void testLocs(){
		// test target and launcher locs as specified in the config file
		// Target Location
		assertEquals(field.theTarget.getXLoc(), 75);
		assertEquals(field.theTarget.getYLoc(), 0);
		
		// Launcher Location
		// Ensure all launchers have same location
		assertEquals(field.getLaunchers().get(0).getXLoc(), 10);
		assertEquals(field.getLaunchers().get(1).getXLoc(), 10);
		// Note that the launcher is hard coded at 0 and not able to be accessed
		
	}
	
	@Test
	public void testQuestions() {
		// make sure there are the correct number of qs
		// ensure correct and wrong answers are recognized as such
		ArrayList<Question> tempQuestionList = field.getQuestions();
		
		// Make sure there are 4 question
		assertEquals(tempQuestionList.size(),4);
		// Make sure questions were loaded correctly
		assertEquals(tempQuestionList.get(0).toString(), "What angle is complementary to 30 degrees?");
		assertEquals(tempQuestionList.get(1).toString(), "What angle throws the projectile farthest?");
		assertEquals(tempQuestionList.get(2).toString(), "How many angles throw the projectile the same distance?");
		assertEquals(tempQuestionList.get(3).toString(), "All of the launcher angles are...");
		// Make sure those ^ questions have these answers...
		assertEquals(tempQuestionList.get(0).getAnswer(), "60 degrees");
		assertEquals(tempQuestionList.get(1).getAnswer(), "45 degrees");
		assertEquals(tempQuestionList.get(2).getAnswer(), "2");
		assertEquals(tempQuestionList.get(3).getAnswer(), "Acute");
	}
	
	@Test
	public void testLaunchers(){
		ArrayList<Launcher> tmpLauncherList = field.getLaunchers();
		// Make sure there are only 3 launchers
		assertEquals(tmpLauncherList.size(), 3);
		// Test each launchers given throwing velocity as specified in config file
		assertEquals(tmpLauncherList.get(0).getVelocity(), 5);
		assertEquals(tmpLauncherList.get(1).getVelocity(), 3);
		assertEquals(tmpLauncherList.get(2).getVelocity(), 10);
		// Test the names of the launchers
		assertEquals(tmpLauncherList.get(0).getName(), "Ballista");
		assertEquals(tmpLauncherList.get(1).getName(), "Catapult");
		assertEquals(tmpLauncherList.get(2).getName(), "Trebuchet");

	}

	
	
}
