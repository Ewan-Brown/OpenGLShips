package openglships.modules;


import openglships.graphics.Drawable;
import openglships.main.BasicShip;
import openglships.main.Data;

public class StaticThruster implements Drawable{

	public float thrusterX;
	public float thrusterY;
	public float throttleMultiplier = 0.001f;
	public float baseAngle = 0;
	public float rMod;
	public float theta;
	public float alphaAtMaxThrottle = 0;
	public float rX;
	public float rY;
	BasicShip parent;
	//XXX Clean this class up
	public StaticThruster(BasicShip ship, float x, float y, float angleRelative, float centerX, float centerY, float rSquared,Direction d){
		Direction = d;
		thrusterX = x;
		thrusterY = y;
		rX = x - centerX;
		rY = y - centerY;
		rMod = Data.getMod(rX, rY);
		float fMod = throttleMultiplier;
		this.baseAngle = angleRelative;
		float fX = (throttleMultiplier * (float)Math.cos(Math.toRadians(angleRelative)));
		float fY = (throttleMultiplier * (float)Math.sin(Math.toRadians(angleRelative)));
		float theta = getAngle(fX, rX, fY, rY, rMod, fMod);
		float theta2 = theta * Math.signum(rY);
		if(rMod == 0){
			theta2 = 0;
		}
		alphaAtMaxThrottle = rMod * fMod * theta2 / rSquared;
	}
	public Direction Direction;
	public static enum Direction{
		FORWARD,BACKWARD,LEFT,RIGHT
	}
	public float getAngle(float x1, float x2, float y1, float y2, float aMod, float bMod){
		float dotProduct = x1*x2 + y1*y2;
		return (float) Math.acos(dotProduct /(aMod*bMod));
	}
	@Override
	public float getX() {
		return 0;
	}
	@Override
	public float getY() {
		return 0;
	}
	@Override
	public float getAngle() {
		return 0;
	}
	@Override
	public float getScale() {
		return 0;
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
	public Drawable[] getSubDrawables() {
		return null;
	}
	
}
