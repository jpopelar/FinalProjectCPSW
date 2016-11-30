package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import gameStuff.*;

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
		Missile m = new Missile(10, 10);
		Target t1 = new Target(20,20);
		// NOTE: target width is 5
		t1.interact(m);
		assertFalse(t1.wasHit());
		m.move(19, 21);
		t1.interact(m);
		assertTrue(t1.wasHit());
		
		Target t2 = new Target(45,45);
		t2.interact(m);
		assertFalse(t2.wasHit());
		m.move(20, 20);
		t2.interact(m);
		assertFalse(t2.wasHit());
		m.move(45, 45);
		t2.interact(m);
		assertTrue(t2.wasHit());
	}

}
