package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import gameStuff.*;

public class fileInitTests {
	
	private static BattleField field;
	
	@BeforeClass
	public static void init() {
		field = BattleField.getInstance();
		// harcode the file names and load up the battleField
		field.setLaunchersFile("/data/launcherConfig.txt");
		field.setQuestionFile("/data/questionListTest.txt");
		field.setFieldFile("/data/battleField.txt");
		
		
		field.initialize();
		
	}	
	
	/**
	 * Test the dimensions of the battlefield and ensures the proper amount of levels are present
	 */
	@Test
	public void testBattleFieldConfig() {
		// assertEquals board dims are same as specified in config file
		assertEquals(100, field.getXDim());
		assertEquals(50, field.getYDim());
		assertEquals(3, field.getLevelList().size());
	}
	
	/**
	 * Test target and launcher locs as specified in the level config files
	 */
	@Test
	public void testLevels(){
		// LEVEL 1
		Level l1 = field.getLevelList().get(0);
		// Test launcher location as specified in config file
		assertEquals(5, l1.getLauncherXLoc());
		assertEquals(0, l1.getLauncherYLoc());
		// Test the one target for this levels location
		ArrayList<Target> tmpTargs = l1.getTargetList();
		assertEquals(20, tmpTargs.get(0).getXLoc());
		assertEquals(0, tmpTargs.get(0).getYLoc());
		
		// LEVEL 2
		Level l2 = field.getLevelList().get(1);
		// Test launcher location as specified in config file
		assertEquals(8, l2.getLauncherXLoc());
		assertEquals(3, l2.getLauncherYLoc());
		// Test the one target for this levels location
		tmpTargs = l2.getTargetList();
		// Test first target
		assertEquals(10, tmpTargs.get(0).getXLoc());
		assertEquals(15, tmpTargs.get(0).getYLoc());
		// Test second target
		assertEquals(25, tmpTargs.get(1).getXLoc());
		assertEquals(0, tmpTargs.get(1).getYLoc());
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
		assertEquals(tmpLauncherList.get(0).getVelocity(), 31);
		assertEquals(tmpLauncherList.get(1).getVelocity(), 39);
		assertEquals(tmpLauncherList.get(2).getVelocity(), 22);
		// Test the names of the launchers
		assertEquals(tmpLauncherList.get(0).getName(), "Ballista");
		assertEquals(tmpLauncherList.get(1).getName(), "Catapult");
		assertEquals(tmpLauncherList.get(2).getName(), "Trebuchet");

	}

	
	
}
