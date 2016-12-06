package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import gameStuff.*;

public class launchTests {

	private static BattleField field;
	
	@BeforeClass
	public static void init() {
		field = BattleField.getInstance();
		// hard code the file names and load up the battleField
		field.setLaunchersFile("/data/launcherConfig.txt");
		field.setQuestionFile("/data/questionListTest.txt");
		field.setFieldFile("/data/battleField.txt");
		
		field.initialize();
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
	
	/**
	 * Test that the battlefield's launcher actually launches missiles
	 * Test that the missiles location gets updated correctly
	 */
	@Test
	public void testLaunch() {
		field.launch(90); // launch at angle that will not hit a single target in level 1
		ArrayList<Target> targs = field.getCurrentLevel().getTargetList();
		for (Target t: targs) {
			assertFalse(t.wasHit());
		}
		field.setLauncher(0);
		field.launch(0); // launch at angle that will hit specific target in level 1
		targs = field.getCurrentLevel().getTargetList();
		//System.out.println(targs.get(0).wasHit());
		assertTrue(targs.get(0).wasHit());
		
		// Now do level 2 targets
		field.incrementLevel();
		targs = field.getCurrentLevel().getTargetList();
		field.launch(45); // launch at angle that will not hit a single target in level 2
		for (Target t: targs) {
			assertFalse(t.wasHit());
		}
		field.setLauncher(1);
		field.launch(80); // launch at angle that will hit specific target in level 2
		assertTrue(targs.get(0).wasHit());
		assertFalse(targs.get(1).wasHit());
		
		field.setLauncher(2);
		field.launch(5); // launch at angle that will hit specific target in level 2
		// make sure both targets are now seen as hit
		assertTrue(targs.get(0).wasHit());
		assertTrue(targs.get(1).wasHit());
	}
	

}
