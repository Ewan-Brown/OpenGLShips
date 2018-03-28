package openglships.modules;


import openglships.main.BasicShip;
import openglships.main.Data;
import openglships.main.Drawable;

public class Thruster implements Drawable{

	public float thrusterX;
	public float thrusterY;
	public float throttle = 0.001f;
	public float angleRelative = 0;
	public float bMod;
	public float theta;
	public float alpha = 0;
	BasicShip parent;
	//TODO Center never gets updated. IF ship shapes change then it will have to get updates
	public Thruster(BasicShip ship, float x, float y, float angleRelative, float centerX, float centerY, float rSquared){
		this.thrusterX = x;
		this.thrusterY = y;
		float bX = x - centerX;
		float bY = y - centerY;
		bMod = Data.getMod(bX, bY);
		float fMod = throttle;
		this.angleRelative = angleRelative;
		float Fx = (throttle * (float)Math.cos(Math.toRadians(angleRelative + 90)));
		float Fy = (throttle * (float)Math.sin(Math.toRadians(angleRelative + 90)));
		float theta = getAngle(Fx, bX, Fy, bY, bMod, fMod);
		//XXX For some reason angle does not go -90 to +90, is always positive so this is here to fix that. This should all be cleaner
		theta *= Math.signum(bX);
		alpha = bMod * fMod * theta / rSquared;
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
		// TODO Auto-generated method stub
		return null;
	}
	
}
