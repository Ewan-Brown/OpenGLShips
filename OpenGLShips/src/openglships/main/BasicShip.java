package openglships.main;

import java.util.ArrayList;

import openglships.modules.Thruster;

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
		
		p.thrusters.add(new Thruster(p, 0.0f, 0.1f, 45, p.xCenter, p.yCenter, 0.25f * 0.25f));
//		p.thrusters.add(new Thruster(p, -0.1f, 0f, 90, p.xCenter, p.yCenter, 0.25f * 0.25f));
		return p;
	}

	public void update() {
		super.update();
		applyFullThrust();
	}

	// Thruster t;
	ArrayList<Thruster> thrusters = new ArrayList<Thruster>();

	public void applyFullThrust() {
		float sumX = 0;
		float sumY = 0;
		float sumAlpha = 0;
		for (Thruster t : thrusters) {
			float T = t.throttle;
			sumX += (float) (T * Math.cos(Math.toRadians(angle + t.angleRelative)));
			sumY += (float) (T * Math.sin(Math.toRadians(angle + t.angleRelative)));
			sumAlpha += Math.toDegrees(t.alpha);
		}
		turnSpeed += sumAlpha;
		xSpeed += sumX;
		ySpeed += sumY;
	}

}
