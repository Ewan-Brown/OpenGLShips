package openglships.main;

import java.util.ArrayList;

import openglships.modules.Thruster;

public class BasicShip extends Entity {

	private BasicShip(float x, float y, float angle, int health) {
		super(x, y, angle, health);
	}

	public static BasicShip createShip(float x, float y, float angle) {
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
		
		p.thrusters.add(Thruster.createThruster(p, 0.1f, 0.0f, 0, p.xCenter, p.yCenter, 0.25f * 0.25f,0.0008f));
//		p.thrusters.add(Thruster.createThruster(p, 0.1f, 0.1f, 0, p.xCenter, p.yCenter, 0.25f * 0.25f,0.0008f));
		return p;
	}

	public void update() {
		super.update();
		applyFullThrust();
	}

	//TODO Maybe this should be an array for multithreading performance purposes
	ArrayList<Thruster> thrusters = new ArrayList<Thruster>();

	
	//TODO Get Thruster health here
	public void applyFullThrust() {
		float sumX = 0;
		float sumY = 0;
		float sumAlpha = 0;
		for (Thruster t : thrusters) {
			float T = t.lastThrottle;
			sumX += (float) (T * Math.cos(Math.toRadians(angle + t.angleRelative)));
			sumY += (float) (T * Math.sin(Math.toRadians(angle + t.angleRelative)));
			sumAlpha += Math.toDegrees(t.alpha);
		}
		turnSpeed += sumAlpha;
		xSpeed += sumX;
		ySpeed += sumY;
	}
	@Override
	public Drawable[] getSubDraws() {
		return thrusters.toArray(new Thruster[thrusters.size()]);
	}
}
