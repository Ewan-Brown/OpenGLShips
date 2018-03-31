package openglships.main;

import java.util.ArrayList;

import openglships.modules.StaticThruster;
import openglships.modules.StaticThruster.Direction;;

public class BasicShip extends Entity {

	private BasicShip(float x, float y, float angle, int health) {
		super(x, y, angle, health);
	}

	public static BasicShip createShip(float x, float y, float angle, float scale) {
		BasicShip p = new BasicShip(x, y, angle, 100) {
			{
				xCenter = (float) Shapes.rectangleCenter.getX();
				yCenter = (float) Shapes.rectangleCenter.getY();
			}

			@Override
			public float[] getVertices() {
				return Shapes.rectangle;
			}
			public float getScale() {
				return 0.1f;
			}
		};

		p.thrusters.add(new StaticThruster(p, 0.0f, 0.1f, 0, p.xCenter, p.yCenter, 0.25f * 0.25f,Direction.FORWARD));
		p.thrusters.add(new StaticThruster(p, 0.0f, -0.1f, 0, p.xCenter, p.yCenter, 0.25f * 0.25f,Direction.FORWARD));
		return p;
	}
	//XXX CUrrently have a method for collecting each one with the sum loop. throuttle should be calcualted before then summed so no repeating code
	//Throttle positive for Right, negative for left
	public void doTurnWithThrottle(float currentThrottle){
	}
	public void update() {
		super.update();
		//		doTurnWithThrottle(0.5f);
		applyThrust(1f);
	}

	// Thruster t;
	ArrayList<StaticThruster> thrusters = new ArrayList<StaticThruster>();

	public void applyThrust(float currentThrottle) {
		float sumX = 0;
		float sumY = 0;
		float sumAlpha = 0;
		for (StaticThruster t : thrusters) {
			float T = t.throttleMultiplier * currentThrottle ;
			sumX += (float) (T * Math.cos(Math.toRadians(angle + t.angleRelative)));
			sumY += (float) (T * Math.sin(Math.toRadians(angle + t.angleRelative)));
			sumAlpha += Math.toDegrees(t.alphaAtMaxThrottle) * T;
		}
		turnSpeed += sumAlpha;
		xSpeed += sumX;
		ySpeed += sumY;
	}
	public void applyThrust(float currentThrottle,Direction d) {
		float sumX = 0;
		float sumY = 0;
		float sumAlpha = 0;
		for (StaticThruster t : thrusters) {
			if(t.Direction == d){
				float T = t.throttleMultiplier * currentThrottle ;
				sumX += (float) (T * Math.cos(Math.toRadians(angle + t.angleRelative)));
				sumY += (float) (T * Math.sin(Math.toRadians(angle + t.angleRelative)));
				sumAlpha += Math.toDegrees(t.alphaAtMaxThrottle) * T;
			}
		}
		turnSpeed += sumAlpha;
		xSpeed += sumX;
		ySpeed += sumY;
	}

}
