package openglships.main;

import java.util.ArrayList;
import java.util.Random;

import openglships.graphics.Drawable;
import openglships.modules.DynamicThruster;;

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
				return 0.2f;
			}
		};
		float s = p.rand.nextFloat()*0.9f + 0.1f;
		p.dynamicThrusters.add(new DynamicThruster(-1f,s,0,p,0.25f*0.25f));
		p.dynamicThrusters.add(new DynamicThruster(-1f, -s, 0, p, 0.25f * 0.25f));
		p.angle = 180;
		return p;
	}

	public Drawable[] getSubDrawables() {
		return dynamicThrusters.toArray(new DynamicThruster[dynamicThrusters.size()]);
	}

	public void update() {
		super.update();
		xSpeed -= xSpeed / 3;
		ySpeed -= ySpeed / 3;
		turnSpeed -= turnSpeed / 3;
		updateAI();
	}

	// XXX Move this to a subclass with AI
	public float targetX;
	public float targetY;
	public float theshold = 5f;
	public float speed = 1f;
	float targetAngle = 90;
	Random rand = new Random();
	public void updateAI() {
		float angleDiff = targetAngle%360 - angle%360;
		if(Math.abs(angleDiff) < theshold)targetAngle += 90;
		System.out.println(targetAngle%360 + " " + angleDiff);
		float throttle = angleDiff / 180f;
		if(Math.abs(throttle) > 1) throttle = 1 * Math.signum(throttle);
		applyDynamicThrustButAlsoTurn(1, throttle);
		
	}

	ArrayList<DynamicThruster> dynamicThrusters = new ArrayList<DynamicThruster>();
    //Negative is turn right positive is left
	public void applyDynamicThrustButAlsoTurn(float currentThrottle, float turn) {
		float sumX = 0;
		float sumY = 0;
		float sumAlpha = 0;
		float lSide = Math.min(1, -2*turn + 1);
		float rSide = Math.min(1, 2*turn + 1);
		for (DynamicThruster t : dynamicThrusters) {
			float T = t.thrustMultiplier * currentThrottle;
			float sumXC = (float) (T * Math.cos(Math.toRadians(angle + t.baseAngle + t.currentAngle)));
			float sumYC = (float) (T * Math.sin(Math.toRadians(angle + t.baseAngle + t.currentAngle)));
			float sumAlphaC = (float) (currentThrottle * Math.toDegrees(t.getTurningAcceleration()));
			double d = 1;
			if(t.getY() > 0) {
				d = lSide;
			}
			else{
				d = rSide;
			}
			sumX += sumXC * d;
			sumY += sumYC * d;
			sumAlpha += sumAlphaC * d;
		}
		turnSpeed += sumAlpha;
		xSpeed += sumX;
		ySpeed += sumY;
	}
	public void applyDynamicThrustOrTurn(float currentThrottle, float turn) {
		float sumX = 0;
		float sumY = 0;
		float sumAlpha = 0;
		float lSide = Math.min(1, -2*turn + 1);
		float rSide = Math.min(1, 2*turn + 1);
		for (DynamicThruster t : dynamicThrusters) {
			float T = t.thrustMultiplier * currentThrottle;
			float sumXC = (float) (T * Math.cos(Math.toRadians(angle + t.baseAngle + t.currentAngle)));
			float sumYC = (float) (T * Math.sin(Math.toRadians(angle + t.baseAngle + t.currentAngle)));
			float sumAlphaC = (float) (currentThrottle * Math.toDegrees(t.getTurningAcceleration()));
			double d = 1;
			if(t.getY() > 0) {
				d = lSide;
			}
			else{
				d = rSide;
			}
			sumX += sumXC * d;
			sumY += sumYC * d;
			sumAlpha += sumAlphaC * d;
		}
		turnSpeed += sumAlpha;
		xSpeed += sumX;
		ySpeed += sumY;
	}

}
