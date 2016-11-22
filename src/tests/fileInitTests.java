package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import gameStuff.BattleField;
import gameStuff.Launcher;
import gameStuff.Question;

public class fileInitTests {
	BattleField field = BattleField.getInstance();

	@BeforeClass
	public void init() {
		field.loadBattleField();
		field.loadQuestions();
		field.loadLaunchers();
		
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
		ArrayList<Question> tempQuestionList = field.getQuestions();
		
		//Todo: Test incorrect answers for each key
		
		assertEquals(tempQuestionList.size(),4);
		
		assertEquals(tempQuestionList.get(0).toString(), "temp0");
		assertEquals(tempQuestionList.get(1).toString(), "temp1");
		assertEquals(tempQuestionList.get(2).toString(), "temp2");
		assertEquals(tempQuestionList.get(3).toString(), "temp3");
		
		assertEquals(tempQuestionList.get(0).getAnswer(), "A1");
		assertEquals(tempQuestionList.get(1).getAnswer(), "A2");
		assertEquals(tempQuestionList.get(2).getAnswer(), "A3");
		assertEquals(tempQuestionList.get(3).getAnswer(), "A4");
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
