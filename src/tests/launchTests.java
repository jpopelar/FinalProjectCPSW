package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import gameStuff.BadConfigException;
import gameStuff.BattleField;

public class launchTests {

	private static BattleField field;
	
	@BeforeClass
	public static void init() {
		field = BattleField.getInstance();
		// hard code the file names and load up the battleField
		field.setLaunchersFile("launcherConfig.txt");
		field.setQuestionFile("questionListTest.txt");
		field.setFieldFile("battleField.txt");
		
		try {
			field.initialize();
		} catch (BadConfigException e) {
			System.out.println("Error loading config files, please check before continuing.");
		}
	}	
	
	/**
	 * Test that the battlefield's launcher actually launches missiles
	 * Test that the missiles location gets updated correctly
	 */
	@Test
	public void testLaunch() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test the interaction between missile and target
	 */
	@Test
	public void testInteraction() {
		fail("Not yet implemented");
	}

}
