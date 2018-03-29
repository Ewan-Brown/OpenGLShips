package openglships.modules;


import openglships.main.BasicShip;
import openglships.main.Data;
import openglships.main.Drawable;
import openglships.main.Shapes;

public class Thruster implements Drawable{

	public float thrusterX;
	public float thrusterY;
	public float centerX;
	public float centerY;
	public float angleRelative = 0;
	public float bMod;
	public float theta;
	public float lastThrottle;
	public float alpha = 0;
	public float scale;
	public float bX;
	public float bY;
	public float Fx;
	public float Fy;
	public float rSquared;
	BasicShip parent;
	//TODO Center never gets updated. IF ship shapes change then it will have to get updates
	public Thruster(BasicShip ship, float x, float y, float angleRelative, float centerX, float centerY, float rSquared, float throttle){
		parent = ship;
		this.thrusterX = x;
		this.thrusterY = y;
		this.centerX = centerX;
		this.centerY = centerY;
		this.angleRelative = angleRelative;
		this.lastThrottle = throttle;
		this.rSquared = rSquared;
		bX = x - centerX;
		bY = y - centerY;
		bMod = Data.getMod(bX, bY);
		Fx = (throttle * (float)Math.cos(Math.toRadians(angleRelative)));
		Fy = (throttle * (float)Math.sin(Math.toRadians(angleRelative)));
		float theta = calculateAngle(Fx, bX, Fy, bY, bMod, throttle);
		//XXX For some reason angle does not go -90 to +90, is always positive so this is here to fix that. This should all be cleaner
		theta *= Math.signum(bX);
		alpha = bMod * throttle * theta / rSquared;
		if(bMod == 0){
			alpha = 0;
		}
	}
	public static Thruster createThruster(BasicShip ship, float x, float y, float angleRelative, float centerX, float centerY, float rSquared, float throttle){
		return new Thruster(ship,x,y,angleRelative,centerX,centerY,rSquared,throttle){
			@Override
			public float[] getVertices() {
				return Shapes.rectangle;
			}
			public float getScale() {
				return 0.04f;
			}
	};
	}
	public float calculateAngle(float x1, float x2, float y1, float y2, float aMod, float bMod){
		float dotProduct = x1*x2 + y1*y2;
		return (float) Math.acos(dotProduct /(aMod*bMod));
	}
	public float getAlpha(float currentThrottle){
		if(currentThrottle != lastThrottle){
			updateAngle(currentThrottle);
		}
		return theta;
	}
	public float updateAngle(float newThrottle){
		float theta = calculateAngle(Fx, bX, Fy, bY, bMod, newThrottle);
		//XXX For some reason angle does not go -90 to +90, is always positive so this is here to fix that. This should all be cleaner
		theta *= Math.signum(bX);
		alpha = bMod * newThrottle * theta / rSquared;
		if(bMod == 0){
			alpha = 0;
		}
		return alpha;
	}
	@Override
	public float getX() {
		return thrusterX;
	}
	@Override
	public float getY() {
		return thrusterY;
	}
	@Override
	public float getAngle() {
		return angleRelative;
	}
	@Override
	public float getScale() {
		return scale;
	}
	@Override
	public float[] getVertices() {
		return null;
	}
	@Override
	public float[] getColors() {
		return null;
	}
	@Override
	public Drawable[] getSubDraws() {
		return null;
	}
	
}
