package gameStuff;

public class PhysicsEngine {
	
	public static final double GRAV_ACCEL = 9.81;
	public static final double PI = 3.14159265;

	public static double findXPos(double v0,  double theta, double t, double xStart) {
		return (v0*Math.cos(theta*PI/180)*t) + xStart;
	}
	
	public static double findYPos(double v0, double theta, double t, double yStart) {
		return (v0*Math.sin(theta*PI/180)*t - .5*GRAV_ACCEL*Math.pow(t, 2)) + yStart;
	}
	
	public static double findXEnd(double v0, double theta, double xStart) {
		return ((Math.pow(v0, 2)*Math.sin(2*theta*PI/180))/GRAV_ACCEL) + xStart;
	}	
	
}
