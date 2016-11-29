package gameStuff;

public class PhysicsEngine {
	
	public static final double GRAV_ACCEL = 9.81;

	public static double findXPos(double v0,  double theta, double t) {
		return v0*Math.cos(theta)*t;
	}
	
	public static double findYPos(double v0, double theta, double t) {
		return v0*Math.sin(theta)*t - .5*GRAV_ACCEL*Math.pow(t, 2);
	}
	
	public static double findXEnd(double v0, double theta) {
		return (Math.pow(v0, 2)*Math.sin(2*theta))/GRAV_ACCEL;
	}	
	
}
