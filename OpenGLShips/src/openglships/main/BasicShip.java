package openglships.main;

import java.util.ArrayList;

import openglships.graphics.Drawable;
import openglships.modules.DynamicThruster;
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

		p.dynamicThrusters.add(new DynamicThruster(1f,0f,90,p,0.25f*0.25f));
		p.dynamicThrusters.add(new DynamicThruster(-1f,0f,90,p,0.25f*0.25f));
		return p;
	}
	public Drawable[] getSubDrawables(){
		return staticThrusters.toArray(new StaticThruster[staticThrusters.size()]);
	}
	public void update() {
		super.update();
		xSpeed -= xSpeed / 3;
		ySpeed -= ySpeed / 3;
		turnSpeed -= turnSpeed / 3;
		applyDynamicThrust(1f);
	}

	ArrayList<StaticThruster> staticThrusters = new ArrayList<StaticThruster>();
	ArrayList<DynamicThruster> dynamicThrusters = new ArrayList<DynamicThruster>();

	public void applyStaticThrust(float currentThrottle) {
		float sumX = 0;
		float sumY = 0;
		float sumAlpha = 0;
		for (StaticThruster t : staticThrusters) {
			float T = t.throttleMultiplier * currentThrottle ;
			sumX += (float) (T * Math.cos(Math.toRadians(angle + t.baseAngle)));
			sumY += (float) (T * Math.sin(Math.toRadians(angle + t.baseAngle)));
			sumAlpha += Math.toDegrees(t.alphaAtMaxThrottle);
		}
		turnSpeed += sumAlpha;
		xSpeed += sumX;
		ySpeed += sumY;
	}
	public void applyDynamicThrust(float currentThrottle) {
		float sumX = 0;
		float sumY = 0;
		float sumAlpha = 0;
		for (DynamicThruster t : dynamicThrusters) {
			float T = t.thrustMultiplier * currentThrottle ;
			sumX += (float) (T * Math.cos(Math.toRadians(angle + t.baseAngle + t.currentAngle)));
			sumY += (float) (T * Math.sin(Math.toRadians(angle + t.baseAngle + t.currentAngle)));
			sumAlpha += Math.toDegrees(t.getTurningAcceleration());
		}
		turnSpeed += sumAlpha;
		xSpeed += sumX;
		ySpeed += sumY;
	}

}
