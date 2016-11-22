package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import gameStuff.BattleField;
import gameStuff.Missile;
import gameStuff.PhysicsEngine;

public class physicsEngineTests {
	BattleField field  = BattleField.getInstance();
	
	@BeforeClass
	public void init() {
		field.loadQuestions();
		field.loadLaunchers();
		
		// harcode the file names and load up the battleField
		// use setter to load config files
	}	

	@Test
	public void testXEnd() {
		final double DELTA = .25;
		//Get a launcher and missile
		//Explicitly calculate where the missile ends up given angle
		//PhysicsEngine will contain static methods to generate and get data 
		
		double endX1 = 55.17, endX2 = 138.75;
		double v01 = 25, v02 = 38.6;
		double theta1 = .5235, theta2 = .9948;
		assertEquals(PhysicsEngine.findXEnd(v01, theta1), endX1, DELTA);
		assertEquals(PhysicsEngine.findXEnd(v02, theta2), endX2, DELTA);
	}
	
	@Test
	public void testXGivenT() {
		final double DELTA = .25;
		//Get a launcher and missile
		//Explicitly calculate where the missile ends up given angle
		//PhysicsEngine will contain static methods to generate and get data 
		
		//Input values selected arbitrarily, outputs explicitly calculated
		double x1 = 0, x2 = 21.65, x3 = 43.30, x4 = 47.63;
		double v0 = 25;
		double theta = .5235;
		double t1 = 0, t2 = 1, t3 = 2, t4 = 2.2;
		assertEquals(PhysicsEngine.findXPos(v0, theta, t1), x1, DELTA);
		assertEquals(PhysicsEngine.findXPos(v0, theta, t2), x2, DELTA);
		assertEquals(PhysicsEngine.findXPos(v0, theta, t3), x3, DELTA);
		assertEquals(PhysicsEngine.findXPos(v0, theta, t4), x4, DELTA);
		
		double x5 = 0, x6 = 21.02, x7 = 42.05, x8 = 46.25;
		double v1 = 38.6;
		double phi = .9948;
		assertEquals(PhysicsEngine.findXPos(v1, phi, t1), x5, DELTA);
		assertEquals(PhysicsEngine.findXPos(v1, phi, t2), x6, DELTA);
		assertEquals(PhysicsEngine.findXPos(v1, phi, t3), x7, DELTA);
		assertEquals(PhysicsEngine.findXPos(v1, phi, t4), x8, DELTA);
	}
	
	@Test
	public void testYGivenT() {
		final double DELTA = .25;
		//Get a launcher and missile
		//Explicitly calculate where the missile ends up given angle
		//PhysicsEngine will contain static methods to generate and get data 
		
		double y1 = 0, y2 = 7.6, y3 = 5.38, y4 = 3.76;
		double v0 = 25;
		double theta = .5235;
		double t1 = 0, t2 = 1, t3 = 2, t4 = 2.2;
		assertEquals(PhysicsEngine.findYPos(v0, theta, t1), y1, DELTA);
		assertEquals(PhysicsEngine.findYPos(v0, theta, t2), y2, DELTA);
		assertEquals(PhysicsEngine.findYPos(v0, theta, t3), y3, DELTA);
		assertEquals(PhysicsEngine.findYPos(v0, theta, t4), y4, DELTA);
		
		double y5 = 0, y6 = 27.47, y7 = 45.13, y8 = 47.48;
		double v1 = 38.6;
		double phi = .9948;
		assertEquals(PhysicsEngine.findXPos(v1, phi, t1), y5, DELTA);
		assertEquals(PhysicsEngine.findXPos(v1, phi, t2), y6, DELTA);
		assertEquals(PhysicsEngine.findXPos(v1, phi, t3), y7, DELTA);
		assertEquals(PhysicsEngine.findXPos(v1, phi, t4), y8, DELTA);
	}

}
