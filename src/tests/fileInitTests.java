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
		field = BattleField.getInstance();

		field.loadBattleField();
		field.loadLaunchers();

		
		try {
			field.setConfigFiles("questionListTest.txt");
			field.loadQuestions();
		} catch (BadConfigException e) {
			System.out.println("Load question config error");
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
		
	}
	
	@Test
	public void testQuestions() {
		
		
		ArrayList<Question> tempQuestionList = field.getQuestions();
		
		//Todo: Test incorrect answers for each key
		
		assertEquals(tempQuestionList.size(),4);
		
		assertEquals(tempQuestionList.get(0).toString(), "What angle is complementary to 30 degrees?");
		assertEquals(tempQuestionList.get(1).toString(), "What angle throws the projectile farthest?");
		assertEquals(tempQuestionList.get(2).toString(), "How many angles throw the projectile the same distance?");
		assertEquals(tempQuestionList.get(3).toString(), "All of the launcher angles are...");
		
		assertEquals(tempQuestionList.get(0).getAnswer(), "60 degrees");
		assertEquals(tempQuestionList.get(1).getAnswer(), "45 degrees");
		assertEquals(tempQuestionList.get(2).getAnswer(), "2");
		assertEquals(tempQuestionList.get(3).getAnswer(), "Acute");
		// make sure there are the correct number of qs
		// ensure correct and wrong answers are recognized as such
		
	}
	
	@Test
	public void testLaunchers(){
		ArrayList<Launcher> tmpLaunch = field.getLaunchers();
		
		assertEquals(tmpLaunch.size(), 3);
		assertEquals(tmpLaunch.get(0).getVelocity(), 1);
		assertEquals(tmpLaunch.get(1).getVelocity(), 2);
		assertEquals(tmpLaunch.get(2).getVelocity(), 3);
		
		assertEquals(tmpLaunch.get(0).toString(), "Trebuchet");
		assertEquals(tmpLaunch.get(1).toString(), "Catapult");
		assertEquals(tmpLaunch.get(2).toString(), "Thwatcha");
	}

	
	
}
